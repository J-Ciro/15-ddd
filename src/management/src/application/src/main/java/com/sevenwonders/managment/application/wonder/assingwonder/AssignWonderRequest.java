package com.sevenwonders.managment.application.wonder.assingwonder;

import com.sevenwonders.managment.application.Request;

public class AssignWonderRequest extends Request {

  private final String wonderName;
  private final String mode;

  protected AssignWonderRequest(String aggregateId, String wonderName, String mode) {
    super(null);
    this.wonderName = wonderName;
    this.mode = mode;
  }

  public String getWonderName() {
    return wonderName;
  }

  public String getMode() {
    return mode;
  }


}
