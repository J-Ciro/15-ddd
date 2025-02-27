package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class UpdatedStage  extends DomainEvent {

  private  String id;
  private  String wonderName;
  private  String stage;

  public UpdatedStage() {
    super(EventsEnum.UPDATED_STAGE.name());
  }

  public UpdatedStage(String id, String stage, String wonderName) {
    super(EventsEnum.CHECKED_STAGE.name());
    this.id = id;
    this.stage = stage;
    this.wonderName = wonderName;
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
