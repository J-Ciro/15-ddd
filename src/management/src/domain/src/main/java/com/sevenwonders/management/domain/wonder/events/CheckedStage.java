package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;



public class CheckedStage extends DomainEvent {
  private  String id;
  private  String wonderName;
  private  String stage;


  public CheckedStage() {
    super(EventsEnum.CHECKED_STAGE.name());
  }

  public CheckedStage(String id, String wonderName, String stage) {
    super(EventsEnum.CHECKED_STAGE.name());
    this.id = id;
    this.wonderName = wonderName;
    this.stage = stage;

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

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }
}
