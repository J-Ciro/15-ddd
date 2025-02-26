package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class ValidatedConstruction extends DomainEvent {

  private  String id;
  private  String status;
  private  Boolean chained;
  private  Integer shields;
  private  String effect;


  public ValidatedConstruction(){
    super(EventsEnum.VALIDATED_CONSTRUCTION.name());
  }


  public ValidatedConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
    super(EventsEnum.VALIDATED_CONSTRUCTION.name());
    this.id = id;
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Boolean getChained() {
    return chained;
  }

  public void setChained(Boolean chained) {
    this.chained = chained;
  }

  public Integer getShields() {
    return shields;
  }

  public void setShields(Integer shields) {
    this.shields = shields;
  }

  public String getEffect() {
    return effect;
  }

  public void setEffect(String effect) {
    this.effect = effect;
  }
}
