package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class UpdatedStage  extends DomainEvent {

  private final String id;
  private final String wonderName;
  private final String stage;

  public UpdatedStage(String id, String wonderName, String stage) {
    super(EventsEnum.CHECKED_STAGE.name());
    this.id = id;
    this.wonderName = wonderName;
    this.stage = stage;
  }

  public String getId() {
    return id;
  }

  public String getWonderName() {
    return wonderName;
  }

  public String getStage() {
    return stage;
  }


}
