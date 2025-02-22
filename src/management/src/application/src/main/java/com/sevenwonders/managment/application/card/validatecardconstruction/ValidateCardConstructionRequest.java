package com.sevenwonders.managment.application.card.validatecardconstruction;

import com.sevenwonders.managment.application.Request;

public class ValidateCardConstructionRequest extends Request {
  private final String cardId;
  private final String status;
  private final Boolean chained;
  private final Integer shields;
  private final String effect;

  public ValidateCardConstructionRequest(String cardId, String status, Boolean chained,
                                         Integer shields, String effect) {
    super(cardId);
    this.cardId = cardId;
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }

  public String getCardId() { return cardId; }
  public String getStatus() { return status; }
  public Boolean getChained() { return chained; }
  public Integer getShields() { return shields; }
  public String getEffect() { return effect; }
}