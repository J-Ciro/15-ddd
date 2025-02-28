package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;


public class CalculatePoints  extends DomainEvent {

  private  Integer marks;

  public CalculatePoints() {
    super(EventsEnum.CALCULATED_POINTS.name());
  }

  public CalculatePoints( Integer  marks) {
    super(EventsEnum.CALCULATED_POINTS.name());
    this.marks = marks;
  }


  public Integer  getMarks() {
    return marks;
  }

  public void setMarks(Integer  marks) {
    this.marks = marks;
  }

}

