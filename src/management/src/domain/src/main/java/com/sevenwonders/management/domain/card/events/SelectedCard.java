package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.shared.domain.generic.DomainEvent;

public class SelectedCard extends DomainEvent {

  private  String cardName;
  private  Integer era;
  private  String type;
  private  String color;
  private  Requirement requirements;
  private  Construction constructions;


  public SelectedCard() {
    super(EventsEnum.SELECTED_CARD.name());
  }

  public SelectedCard(String cardName, Integer era, String type, String color, Construction constructions, Requirement requirements) {
    super(EventsEnum.SELECTED_CARD.name());
    this.cardName = cardName;
    this.era = era;
    this.type = type;
    this.color = color;
    this.constructions = constructions;
    this.requirements = requirements;
  }



  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public Integer getEra() {
    return era;
  }

  public void setEra(Integer era) {
    this.era = era;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Requirement getRequirements() {
    return requirements;
  }

  public void setRequirements(Requirement requirements) {
    this.requirements = requirements;
  }

  public Construction getConstructions() {
    return constructions;
  }

  public void setConstructions(Construction constructions) {
    this.constructions = constructions;
  }
}
