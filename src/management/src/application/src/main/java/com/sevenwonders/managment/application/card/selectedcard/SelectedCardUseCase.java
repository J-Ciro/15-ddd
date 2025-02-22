package com.sevenwonders.managment.application.card.selectedcard;
import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import shared.card.CardMapper;
import shared.card.CardResponse;
import shared.repositories.IEventsRepository;

public class SelectedCardUseCase implements ICommandUseCase<SelectedCardRequest, Mono<CardResponse>> {
  private final IEventsRepository repository;

  public SelectedCardUseCase(IEventsRepository repository){
    this.repository = repository;
  }

  @Override
  public Mono<CardResponse> execute(SelectedCardRequest request) {

    Card card = new Card(
      request.getId(),
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

  }
}
