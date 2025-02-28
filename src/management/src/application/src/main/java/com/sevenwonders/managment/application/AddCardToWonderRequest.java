package com.sevenwonders.managment.application;

public class AddCardToWonderRequest extends Request {
  private final String cardId;

  public AddCardToWonderRequest(String wonderAggregateId, String cardId) {
    super(wonderAggregateId);
    this.cardId = cardId;
  }

  public String getCardId() {
    return cardId;
  }
}