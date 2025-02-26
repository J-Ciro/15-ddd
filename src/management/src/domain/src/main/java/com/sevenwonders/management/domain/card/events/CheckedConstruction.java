package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class CheckedConstruction extends DomainEvent {

  private  String id;
  private  String status;
  private  Boolean chained;
  private  Integer shields;
  private  String effect;


  public CheckedConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
    super(EventsEnum.CHECKED_CONSTRUCTION.name());
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