package com.sevenwonders.managment.application.card.validatecardconstruction;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.card.CardMapper;
import com.sevenwonders.managment.application.shared.card.CardResponse;
import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;

public class ValidateCardConstructionUseCase implements ICommandUseCase<ValidateCardConstructionRequest, Mono<CardResponse>> {

  private final IEventsRepository repository;

  public ValidateCardConstructionUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<CardResponse> execute(ValidateCardConstructionRequest request) {
    return repository.findEventsByAggregatedId(request.getCardId())
      .collectList()
      .map(events -> Card.from(request.getCardId(), events))
      .map(card -> {
        card.validateConstruction(
          request.getCardId(),
          request.getStatus(),
          request.getChained(),
          request.getShields(),
          request.getEffect()
        );
        card.getUncommittedEvents().forEach(repository::save);
        card.markEventsAsCommitted();
        return CardMapper.mapToCard(card);
      });
  }

}
