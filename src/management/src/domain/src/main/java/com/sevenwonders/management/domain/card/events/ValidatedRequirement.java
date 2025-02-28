package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class ValidatedRequirement extends DomainEvent {

  private  String id;
  private  Integer price;
  private  List<String> resources;
  private  Integer minimumPlayers;

  public ValidatedRequirement() {
    super(EventsEnum.VALIDATED_REQUIREMENT.name());
  }


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

  public void setId(String id) {
    this.id = id;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public List<String> getResources() {
    return resources;
  }

  public void setResources(List<String> resources) {
    this.resources = resources;
  }

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }

  public void setMinimumPlayers(Integer minimumPlayers) {
    this.minimumPlayers = minimumPlayers;
  }
}
