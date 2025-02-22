package com.sevenwonders.managment.application.wonder.managestage;

import com.sevenwonders.managment.application.Request;

import java.util.List;

public class ManageWonderStageRequest extends Request {

  private final String wonderId;
  private final String wonderName;
  private final String stageName;
  private final Integer requiredCoins;
  private final List<String> requiredResources;

  public ManageWonderStageRequest(String wonderId, String wonderName, String stageName, Integer requiredCoins, List<String> requiredResources) {
    super(wonderId);
    this.wonderId = wonderId;
    this.wonderName = wonderName;
    this.stageName = stageName;
    this.requiredCoins = requiredCoins;
    this.requiredResources = requiredResources;
  }

  public String getWonderName() {
    return wonderName;
  }

  public String getWonderId() {
    return wonderId;
  }

  public String getStageName() {
    return stageName;
  }

  public Integer getRequiredCoins() {
    return requiredCoins;
  }

  public List<String> getRequiredResources() {
    return requiredResources;
  }
}
