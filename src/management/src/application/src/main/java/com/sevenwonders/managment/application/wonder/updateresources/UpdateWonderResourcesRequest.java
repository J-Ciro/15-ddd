package com.sevenwonders.managment.application.wonder.updateresources;

import com.sevenwonders.managment.application.Request;

import java.util.List;

public class UpdateWonderResourcesRequest extends Request {

  private  String wonderId;
  private  String wonderName;
  private  Integer coins;
  private  List<String> resources;


  public UpdateWonderResourcesRequest(String aggregateId) {
    super(aggregateId);
  }

  protected UpdateWonderResourcesRequest(String aggregateId, String wonderId, String wonderName, Integer coins, List<String> resources) {
    super(aggregateId);
    this.wonderId = wonderId;
    this.wonderName = wonderName;
    this.coins = coins;
    this.resources = resources;
  }


  public String getWonderId() {
    return wonderId;
  }

  public String getWonderName() {
    return wonderName;
  }

  public Integer getCoins() {
    return coins;
  }

  public List<String> getResources() {
    return resources;
  }

  public void setWonderId(String wonderId) {
    this.wonderId = wonderId;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
  }

  public void setCoins(Integer coins) {
    this.coins = coins;
  }

  public void setResources(List<String> resources) {
    this.resources = resources;
  }
}
