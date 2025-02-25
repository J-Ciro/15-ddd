package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class CalculatePoints  extends DomainEvent {

  private  String id;
  private  Integer marks;


  public CalculatePoints() {
    super(EventsEnum.CALCULATED_POINTS.name());
  }

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

  public void setId(String id) {
    this.id = id;
  }

  public void setMarks(Integer marks) {
    this.marks = marks;
  }


}
