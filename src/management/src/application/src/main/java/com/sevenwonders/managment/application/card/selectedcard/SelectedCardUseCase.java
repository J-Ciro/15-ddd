package com.sevenwonders.managment.application.card.selectedcard;
import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.card.CardMapper;
import com.sevenwonders.managment.application.shared.card.CardResponse;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;

public class SelectedCardUseCase implements ICommandUseCase<SelectedCardRequest, Mono<CardResponse>> {
  private final IEventsRepositoryPort repository;

  public SelectedCardUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<CardResponse> execute(SelectedCardRequest request) {

    if (request.getRequirement() == null) {
      return Mono.error(new IllegalArgumentException("Card requirement cannot be null"));
    }

    if (request.getRequirement().getResource() == null) {
      return Mono.error(new IllegalArgumentException("Card requirement resources cannot be null"));
    }

    try {
      Card card = new Card(
        request.getName(),
        request.getEra(),
        request.getType(),
        request.getColor(),
        request.getConstruction(),
        request.getRequirement()
      );

      return Mono.just(card)
        .map(card1 -> {
          card1.getUncommittedEvents().forEach(repository::save);
          card1.markEventsAsCommitted();
          return CardMapper.mapToCard(card1);
        });
    } catch (Exception e) {
      return Mono.error(new RuntimeException("Error creating card: " + e.getMessage(), e));
    }
  }
}
