package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.shared.domain.generic.DomainEvent;

public class SelectedCard extends DomainEvent {

  private final String name;
  private final Integer era;
  private final String type;
  private final String color;
  private final Requirement requirements;
  private final Construction constructions;


  public SelectedCard( String name, Integer era, String type, String color, Requirement requirements, Construction constructions) {
    super(EventsEnum.SELECTED_CARD.name());
    this.name = name;
    this.era = era;
    this.type = type;
    this.color = color;
    this.requirements = requirements;
    this.constructions = constructions;
  }


  @Override
  public String getName() {
    return name;
  }

  public Integer getEra() {
    return era;
  }

  public String getType() {
    return type;
  }

  public String getColor() {
    return color;
  }

  public Requirement getRequirements() {
    return requirements;
  }

  public Construction getConstructions() {
    return constructions;
  }
}
