package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.managment.application.Request;

import java.util.List;

public class CalculateWonderPointsRequest extends Request {

  private final String wonderId;
  private final List<Integer> marks;


  public CalculateWonderPointsRequest(String aggregateId, String wonderId, List<Integer>  marks) {
    super(aggregateId);
    this.wonderId = wonderId;
    this.marks = marks;
  }

  public String getWonderId() {
    return wonderId;
  }

  public  List<Integer> getMarks() {
    return marks;
  }


}
