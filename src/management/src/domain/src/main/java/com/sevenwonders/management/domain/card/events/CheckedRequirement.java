package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class CheckedRequirement extends DomainEvent {

  private final String id;
  private final String price;
  private final List<String> resources;
  private final Integer minimumPlayers;


  public CheckedRequirement(String id, String price, List<String> resources, Integer minimumPlayers) {
    super(EventsEnum.CHECKED_CONSTRUCTION.name());
    this.id = id;
    this.price = price;
    this.resources = resources;
    this.minimumPlayers = minimumPlayers;
  }


  public String getPrice() {
    return price;
  }

  public List<String> getResources() {
    return resources;
  }

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }
}
