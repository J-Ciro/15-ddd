package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class AssignedWonder extends DomainEvent {



  private final String wonderName;
  private final String mode;


  public AssignedWonder( String name, String mode) {
    super(EventsEnum.ASSIGNED_WONDER.name());
    this.wonderName = name;
    this.mode = mode;
  }



  public String getWonderName() {
    return wonderName;
  }

  public String getMode() {
    return mode;
  }
}
