package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class CalculatePoints  extends DomainEvent {

  private final String id;
  private final Integer marks;


  public CalculatePoints(String id, Integer marks) {
    super(EventsEnum.CALCULATED_POINTS.name());
    this.id = id;
    this.marks = marks;
  }

  public String getId() {
    return id;
  }

  public Integer getMarks() {
    return marks;
  }


}
