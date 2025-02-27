package com.sevenwonders.management.domain.card.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class UpdatedRequirement extends DomainEvent {

  private  String id;
  private  Integer price;
  private  List<String> resources;
  private  Integer minimumPlayers;

  public UpdatedRequirement() {
    super(EventsEnum.UPDATED_REQUIREMENT.name());
  }


  public UpdatedRequirement(String id, Integer price, List<String> resources, Integer minimumPlayers) {
   super(EventsEnum.UPDATED_REQUIREMENT.name());
    this.id = id;
    this.price = price;
    this.resources = resources;
    this.minimumPlayers = minimumPlayers;
  }



}
