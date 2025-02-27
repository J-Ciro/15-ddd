package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.managment.application.Request;

public class CalculateWonderPointsRequest extends Request {
  private String wonderId;
  private Integer marks;


  public CalculateWonderPointsRequest(String aggregateId, String wonderId, Integer marks) {
    super(aggregateId);
    this.wonderId = wonderId;
    this.marks = marks;
  }

  public String getWonderId() {
    return wonderId;
  }

  public void setWonderId(String wonderId) {
    this.wonderId = wonderId;
  }

  public Integer getMarks() {
    return marks;
  }

  public void setMarks(Integer marks) {
    this.marks = marks;
  }
}