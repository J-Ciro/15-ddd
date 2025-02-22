package com.sevenwonders.managment.application;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;
import com.sevenwonders.managment.application.shared.wonder.WonderMapper;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;

import java.util.ArrayList;
import java.util.List;

public class AddCardToWonderUseCase implements ICommandUseCase<AddCardToWonderRequest, Mono<WonderResponse>> {
  private final IEventsRepository repository;

  public AddCardToWonderUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<WonderResponse> execute(AddCardToWonderRequest request) {
    return Mono.zip(
      getWonder(request.getWonderId()),
      getCard(request.getCardId())
    ).flatMap(tuple -> {
      Wonder wonder = tuple.getT1();
      Card card = tuple.getT2();

      card.checkRequirement(
        card.getIdentity().getValue(),
        request.getPrice(),
        request.getResources(),
        request.getMinimumPlayers()
      );

      card.checkConstruction(
        card.getIdentity().getValue(),
        "IN_PROGRESS",
        false,
        0,
        "NONE"
      );

      wonder.updateVault(
        card.getIdentity().getValue(),
        wonder.getName().getValue(),
        calculateRemainingCoins(wonder, card),
        calculateRemainingResources(wonder, card)
      );
      wonder.getUncommittedEvents().forEach(repository::save);
      card.getUncommittedEvents().forEach(repository::save);
      return Mono.just(WonderMapper.mapToWonder(wonder));
    });
  }

  private Mono<Wonder> getWonder(String wonderId) {
    return repository.findEventsByAggregatedId(wonderId)
      .collectList()
      .map(events -> Wonder.from(wonderId, events));
  }

  private Mono<Card> getCard(String cardId) {
    return repository.findEventsByAggregatedId(cardId)
      .collectList()
      .map(events -> Card.from(cardId, events));
  }

  private Integer calculateRemainingCoins(Wonder wonder, Card card) {
    Integer currentCoins = wonder.getVault().getCoins().getValue();
    Integer cardPrice = card.getRequirement().getAmount().getValue();
    return currentCoins - cardPrice;
  }

  private List<String> calculateRemainingResources(Wonder wonder, Card card) {
    List<String> currentResources = new ArrayList<>(wonder.getVault().getResources().getValue());
    List<String> requiredResources = card.getRequirement().getResource().getValue();

    for (String resource : requiredResources) {
      currentResources.remove(resource);
    }

    return currentResources;
  }
}