package com.sevenwonders.managment.application.shared.card;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;

public class CardMapper {

  public static CardResponse mapToCard(Card card) {
    return new CardResponse(
      card.getIdentity().getValue(),
      card.getCardName().getValue(),
      card.getEra().getValue(),
      card.getType().getValue(),
      card.getColor().getValue(),
      mapToConstruction(card.getConstruction()),
      mapToRequirement(card.getRequirement())
    );
  }

  private static CardResponse.Construction mapToConstruction(Construction construction) {

    return new CardResponse.Construction(
      construction.getStatus().getValue(),
      construction.getChained().getValue(),
      construction.getShields().getValue(),
      construction.getEffect().getValue()
    );
  }

  private static CardResponse.Requirement mapToRequirement(Requirement requirement) {
    return new CardResponse.Requirement(
      requirement.getAmount().getValue(),
      requirement.getResource().getValue(),
      requirement.getMinimumPlayers().getValue()
    );
  }
}