package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class DiscardedConstruction extends DomainEvent {

  private final String id;
  private final String status;
  private final Boolean chained;
  private final Integer shields;
  private final String effect;


  public DiscardedConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
    super(EventsEnum.DISCARDED_CONSTRUCTION.name());
    this.id = id;
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }

  public String getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public Boolean getChained() {
    return chained;
  }

  public Integer getShields() {
    return shields;
  }

  public String getEffect() {
    return effect;
  }
}
