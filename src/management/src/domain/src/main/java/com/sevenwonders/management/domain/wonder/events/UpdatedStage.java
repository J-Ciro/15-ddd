package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class UpdatedStage  extends DomainEvent {

  private final String id;
  private final String wonderName;
  private final String stage;

  public UpdatedStage(String id, String stage, String wonderName) {
    super(EventsEnum.CHECKED_STAGE.name());
    this.id = id;
    this.stage = stage;
    this.wonderName = wonderName;
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
