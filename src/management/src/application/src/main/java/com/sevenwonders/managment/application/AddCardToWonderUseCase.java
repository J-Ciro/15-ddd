package com.sevenwonders.managment.application;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.application.shared.wonder.WonderMapper;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;

import java.util.ArrayList;
import java.util.List;

public class AddCardToWonderUseCase implements ICommandUseCase<AddCardToWonderRequest, Mono<WonderResponse>> {
  private final IEventsRepositoryPort repository;

  public AddCardToWonderUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<WonderResponse> execute(AddCardToWonderRequest request) {
    return repository.findEventsByAggregatedId(request.getAggregateId())
      .collectList()
      .map(wonderEvents -> Wonder.from(request.getAggregateId(), wonderEvents))
      .flatMap(wonder ->
        repository.findEventsByAggregatedId(request.getCardId())
          .collectList()
          .map(cardEvents -> Card.from(request.getCardId(), cardEvents))
          .map(card -> {
            card.checkRequirement(
              card.getIdentity().getValue(),
              request.getPrice(),
              request.getResources(),
              request.getMinimumPlayers()
            );

            card.checkConstruction(
              card.getIdentity().getValue(),
              "INPROGRESS",
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

            return WonderMapper.mapToWonder(wonder);
          })
      );
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