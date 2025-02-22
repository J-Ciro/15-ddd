package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.managment.application.Request;

public class CalculateWonderPointsRequest extends Request {

  private final String wonderId;
  private final Integer marks;


  public CalculateWonderPointsRequest(String wonderId, Integer marks) {
    super(wonderId);
    this.wonderId = wonderId;
    this.marks = marks;
  }

  public String getWonderId() {
    return wonderId;
  }

  public Integer getMarks() {
    return marks;
  }


}
