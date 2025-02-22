package com.sevenwonders.managment.application.wonder.updateresources;

import com.sevenwonders.managment.application.Request;

import java.util.List;

public class UpdateWonderResourcesRequest extends Request {

  private final String wonderId;
  private final String wonderName;
  private final Integer coins;
  private final List<String> resources;


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



}
