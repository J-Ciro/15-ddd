package com.sevenwonders.managment.application.card.selectedcard;
import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.management.domain.card.values.Amount;
import com.sevenwonders.management.domain.card.values.Chained;
import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.RequirementId;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.values.Resources;
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

    if (request.getRequirement().resources() == null) {
      return Mono.error(new IllegalArgumentException("Card requirement resources cannot be null"));
    }

      Card card = new Card(
        request.getName(),
        request.getEra(),
        request.getType(),
        request.getColor(),
        new Construction(
          Status.of(request.getConstruction().status()),
          Chained.of(request.getConstruction().chained()),
          Shields.of(request.getConstruction().shields()),
          Effect.of(request.getConstruction().effect())
        ),
        new Requirement(
          RequirementId.of(request.getRequirement().toString()),
          Amount.of(request.getRequirement().amount()),
          Resources.of(request.getRequirement().resources()),
          MinimumPlayers.of(request.getRequirement().minimumPlayers())
        )
      );

      return Mono.just(card)
        .map(card1 -> {
          card1.getUncommittedEvents().forEach(repository::save);
          card1.markEventsAsCommitted();
          return CardMapper.mapToCard(card1);
        });
  }
}
