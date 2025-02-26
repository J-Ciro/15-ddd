package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class CalculatePoints  extends DomainEvent {

  private  String id;
  private  List<Integer> marks;


  public CalculatePoints() {
    super(EventsEnum.CALCULATED_POINTS.name());
  }

  public CalculatePoints(String id, List<Integer>  marks) {
    super(EventsEnum.CALCULATED_POINTS.name());
    this.id = id;
    this.marks = marks;
  }

  public String getId() {
    return id;
  }

  public List<Integer>  getMarks() {
    return marks;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMarks(List<Integer>  marks) {
    this.marks = marks;
  }


}
