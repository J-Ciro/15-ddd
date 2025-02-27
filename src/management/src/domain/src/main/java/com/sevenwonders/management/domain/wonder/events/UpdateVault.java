package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class UpdateVault  extends DomainEvent {

  private  String id;
  private  String wonderName;
  private  Integer coins;
  private  List<String> resources;

  public UpdateVault() {
    super(EventsEnum.UPDATED_VAULT.name());
  }

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

  public void setId(String id) {
    this.id = id;
  }

  public String getWonderName() {
    return wonderName;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
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
