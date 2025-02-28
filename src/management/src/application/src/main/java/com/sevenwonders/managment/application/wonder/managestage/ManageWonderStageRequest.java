package com.sevenwonders.managment.application.wonder.managestage;

import com.sevenwonders.managment.application.Request;

import java.util.List;

public class ManageWonderStageRequest extends Request {

  private  String wonderId;
  private  String wonderName;
  private  String stageName;
  private  Integer requiredCoins;
  private  List<String> requiredResources;


  public ManageWonderStageRequest(String aggregateId) {
    super(aggregateId);
  }

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


  public void setWonderId(String wonderId) {
    this.wonderId = wonderId;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
  }

  public void setStageName(String stageName) {
    this.stageName = stageName;
  }

  public void setRequiredCoins(Integer requiredCoins) {
    this.requiredCoins = requiredCoins;
  }

  public void setRequiredResources(List<String> requiredResources) {
    this.requiredResources = requiredResources;
  }
}
