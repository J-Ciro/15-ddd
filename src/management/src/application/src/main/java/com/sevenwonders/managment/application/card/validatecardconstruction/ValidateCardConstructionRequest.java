package com.sevenwonders.managment.application.card.validatecardconstruction;

import com.sevenwonders.managment.application.Request;

public class ValidateCardConstructionRequest extends Request {
//  private final String cardId;
  private  String aggregateId;
  private  String status;
  private  Boolean chained;
  private  Integer shields;
  private  String effect;

  public ValidateCardConstructionRequest(String aggregateId) {
    super(aggregateId);
  }


  public ValidateCardConstructionRequest(String aggregateId, String status, Boolean chained,
                                         Integer shields, String effect) {
    super(aggregateId);
    this.aggregateId = aggregateId;
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }




  public void setAggregateId(String aggregateId) {
    this.aggregateId = aggregateId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Boolean getChained() {
    return chained;
  }

  public void setChained(Boolean chained) {
    this.chained = chained;
  }

  public Integer getShields() {
    return shields;
  }

  public void setShields(Integer shields) {
    this.shields = shields;
  }

  public String getEffect() {
    return effect;
  }

  public void setEffect(String effect) {
    this.effect = effect;
  }
}