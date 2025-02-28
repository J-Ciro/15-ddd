package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.shared.domain.generic.DomainEvent;

public class DiscardedCard extends DomainEvent {

  private  String id;
  private  String name;
  private  Integer era;
  private  String type;
  private  String color;
  private  Requirement requirements;
  private  Construction constructions;

  public DiscardedCard() {
    super(EventsEnum.DISCARDED_CARD.name());
  }


  public DiscardedCard(String id, String name, Integer era, String type, String color, Requirement requirements, Construction constructions) {
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

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
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
