package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class ValidatedStage  extends DomainEvent {

  private final String id;
  private final String wonderName;
  private final String stage;
  private final Integer coins;
  private final List<String> resources;

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

  public String getWonderName() {
    return wonderName;
  }

  public String getStage() {
    return stage;
  }

  public Integer getCoins() {
    return coins;
  }

  public List<String> getResources() {
    return resources;
  }
}
