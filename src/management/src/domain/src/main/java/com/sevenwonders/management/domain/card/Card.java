package com.sevenwonders.management.domain.card;

import com.sevenwonders.management.domain.card.events.CheckedConstruction;
import com.sevenwonders.management.domain.card.events.CheckedRequirement;
import com.sevenwonders.management.domain.card.events.DiscardedConstruction;
import com.sevenwonders.management.domain.card.events.UpdatedRequirement;
import com.sevenwonders.management.domain.card.events.ValidatedConstruction;
import com.sevenwonders.management.domain.card.events.ValidatedRequirement;
import com.sevenwonders.management.domain.card.values.CardId;
import com.sevenwonders.management.domain.card.values.Color;
import com.sevenwonders.management.domain.card.values.Era;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Type;
import com.sevenwonders.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Card extends AggregateRoot<CardId> {


  private Name name;
  private Era era;
  private Type type;
  private Color color;

  //region Constructors
  public Card() {
    super(new CardId());
  }

  private Card(CardId identity) {
    super(identity);
  }

  //endregion

  //region Getters and Setters

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Era getEra() {
    return era;
  }

  public void setEra(Era era) {
    this.era = era;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  //endregion

  //region Domain Actions

public void checkRequirement(String id, String price, List<String> resources, Integer minimumPlayers) {
  apply(new CheckedRequirement(id, price, resources, minimumPlayers));

}

public void updateRequirement(String id, String price, List<String> resources, Integer minimumPlayers) {
    apply(new UpdatedRequirement(id, price, resources, minimumPlayers));
}

public void validateRequirement(String id, String price, List<String> resources, Integer minimumPlayers) {
  apply(new ValidatedRequirement(id, price, resources, minimumPlayers));
}

public void discardConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new DiscardedConstruction(id, status, chained, shields, effect));
}

public void validateConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new ValidatedConstruction(id, status, chained, shields, effect));
}

public void checkConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new CheckedConstruction(id, status, chained, shields, effect));
}

  //endregion



}
