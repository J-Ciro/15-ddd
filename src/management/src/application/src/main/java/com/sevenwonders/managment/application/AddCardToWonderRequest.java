package com.sevenwonders.managment.application;

import java.util.List;

public class AddCardToWonderRequest extends Request {
  private final String wonderId;
  private final String cardId;
  private final Integer price;
  private final List<String> resources;
  private final Integer minimumPlayers;

  public AddCardToWonderRequest(
    String wonderId,
    String cardId,
    Integer price,
    List<String> resources,
    Integer minimumPlayers
  ) {
    super(wonderId);
    this.wonderId = wonderId;
    this.cardId = cardId;
    this.price = price;
    this.resources = resources;
    this.minimumPlayers = minimumPlayers;
  }


  public String getWonderId() {
    return wonderId;
  }

  public String getCardId() {
    return cardId;
  }

  public Integer getPrice() {
    return price;
  }

  public List<String> getResources() {
    return resources;
  }

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }
}