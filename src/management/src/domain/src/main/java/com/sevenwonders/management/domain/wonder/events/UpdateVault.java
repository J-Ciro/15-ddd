package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class UpdateVault  extends DomainEvent {

  private final String id;
  private final String wonderName;
  private final Integer coins;
  private final List<String> resources;

  public UpdateVault( String id, String wonderName, Integer coins, List<String> resources) {
    super(EventsEnum.UPDATED_VAULT.name());
    this.id = id;
    this.wonderName = wonderName;
    this.coins = coins;
    this.resources = resources;
  }

  public String getId() {
    return id;
  }

  public String getWonderName() {
    return wonderName;
  }

  public Integer getCoins() {
    return coins;
  }

  public List<String> getResources() {
    return resources;
  }
}
