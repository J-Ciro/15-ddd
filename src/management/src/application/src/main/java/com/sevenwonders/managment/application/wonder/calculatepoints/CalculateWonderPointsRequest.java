package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.managment.application.Request;

public class CalculateWonderPointsRequest extends Request {
  private  Integer marks;

  public CalculateWonderPointsRequest(String aggregateId) {
    super(aggregateId);
  }

  public CalculateWonderPointsRequest(String aggregateId, Integer marks) {
    super(aggregateId);
    this.marks = marks;
  }

  public Integer getMarks() {
    return marks;
  }

  public void setMarks(Integer marks) {
    this.marks = marks;
  }
}