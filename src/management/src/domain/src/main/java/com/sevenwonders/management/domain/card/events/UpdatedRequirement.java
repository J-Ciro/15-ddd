package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class UpdatedRequirement extends DomainEvent {

  private final String id;
  private final String price;
  private final List<String> resources;
  private final Integer minimumPlayers;


  public UpdatedRequirement(String id, String price, List<String> resources, Integer minimumPlayers) {
   super(EventsEnum.UPDATED_REQUIREMENT.name());
    this.id = id;
    this.price = price;
    this.resources = resources;
    this.minimumPlayers = minimumPlayers;
  }


  public String getId() {
    return id;
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
