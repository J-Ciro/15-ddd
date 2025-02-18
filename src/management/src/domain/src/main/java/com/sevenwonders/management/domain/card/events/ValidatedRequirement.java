package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class ValidatedRequirement extends DomainEvent {

  private final String id;
  private final Integer price;
  private final List<String> resources;
  private final Integer minimumPlayers;


  public ValidatedRequirement(String id, Integer price, List<String> resources, Integer minimumPlayers) {
    super(EventsEnum.VALIDATED_REQUIREMENT.name());
    this.id = id;
    this.price = price;
    this.resources = resources;
    this.minimumPlayers = minimumPlayers;
  }


  public String getId() {
    return id;
  }

  public Integer getPrice() {
    return price;
  }

  public List<String> getResources() {
    return resources;
  }

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }
}
