package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class ValidatedStage  extends DomainEvent {

  private  String id;
  private  String wonderName;
  private  String stage;
  private  Integer coins;
  private  List<String> resources;


  public ValidatedStage() {
    super(EventsEnum.VALIDATED_STAGE.name());
  }

  public ValidatedStage(String id, String wonderName, String stage, Integer coins, List<String> resources) {
    super(EventsEnum.VALIDATED_STAGE.name());
    this.id = id;
    this.wonderName = wonderName;
    this.stage = stage;
    this.coins = coins;
    this.resources = resources;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWonderName() {
    return wonderName;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public Integer getCoins() {
    return coins;
  }

  public void setCoins(Integer coins) {
    this.coins = coins;
  }

  public List<String> getResources() {
    return resources;
  }

  public void setResources(List<String> resources) {
    this.resources = resources;
  }
}
