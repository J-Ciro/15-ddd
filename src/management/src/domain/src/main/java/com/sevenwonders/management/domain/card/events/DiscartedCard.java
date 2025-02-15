package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class DiscartedCard extends DomainEvent {

  private final String id;
  private final String name;
  private final Integer era;
  private final String type;
  private final String color;
  private final Requirement requirements;
  private final Construction constructions;


  public DiscartedCard(String id, String name, Integer era, String type, String color, Requirement requirements, Construction constructions) {
    super(EventsEnum.DISCARDED_CARD.name());
    this.id = id;
    this.name = name;
    this.era = era;
    this.type = type;
    this.color = color;
    this.requirements = requirements;
    this.constructions = constructions;
  }

  public String getId() {
    return id;
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
