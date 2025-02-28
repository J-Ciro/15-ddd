package com.sevenwonders.managment.application;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.application.shared.wonder.WonderMapper;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;

public class AddCardToWonderUseCase implements ICommandUseCase<AddCardToWonderRequest, Mono<WonderResponse>> {
  private final IEventsRepositoryPort repository;

  public AddCardToWonderUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<WonderResponse> execute(AddCardToWonderRequest request) {
    System.out.println("_________________________________________________");
    System.out.println("Agregate ID " + request.getAggregateId());
    System.out.println("CardId " + request.getCardId());
    System.out.println("_________________________________________________");
    return Mono.zip(
        repository.findEventsByAggregatedId(request.getAggregateId()).collectList(),
        repository.findEventsByAggregatedId(request.getCardId()).collectList()
      ).flatMap(tuple -> {
        Wonder wonder = Wonder.from(request.getAggregateId(), tuple.getT1());
        Card card = Card.from(request.getCardId(), tuple.getT2());


        if (card.getRequirement() == null) {
          wonder.addCard(card.getIdentity().getValue(), request.getAggregateId(), wonder.getWonderName().getValue());
          wonder.getUncommittedEvents().forEach(repository::save);
          card.getUncommittedEvents().forEach(repository::save);
          wonder.markEventsAsCommitted();
          card.markEventsAsCommitted();
          return Mono.just(WonderMapper.mapToWonder(wonder));
        } else {
          wonder.updateVault(
            card.getIdentity().getValue(),
            wonder.getWonderName().getValue(),
            wonder.getVault().getCoins().getValue() - card.getRequirement().getAmount().getValue(),
            wonder.getVault().getResources().getValue().stream()
              .filter(resource -> !card.getRequirement().getResource().getValue().contains(resource))
              .toList()
          );

          wonder.addCard(card.getIdentity().getValue(), request.getAggregateId(), wonder.getWonderName().getValue());
          wonder.getUncommittedEvents().forEach(repository::save);
          card.getUncommittedEvents().forEach(repository::save);
          wonder.markEventsAsCommitted();
          card.markEventsAsCommitted();
          return Mono.just(WonderMapper.mapToWonder(wonder));
        }

      }).doOnError(Throwable::printStackTrace)
      .onErrorResume(e -> Mono.error(new RuntimeException("Failed to add card to wonder: " + e.getMessage(), e)));
  }
}