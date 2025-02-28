package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class AssignedWonder extends DomainEvent {



  private  String wonderName;
  private  String mode;

  public AssignedWonder() {
    super(EventsEnum.ASSIGNED_WONDER.name());
  }

  public AssignedWonder( String name, String mode) {
    super(EventsEnum.ASSIGNED_WONDER.name());
    this.wonderName = name;
    this.mode = mode;
  }


  public String getWonderName() {
    return wonderName;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
}
