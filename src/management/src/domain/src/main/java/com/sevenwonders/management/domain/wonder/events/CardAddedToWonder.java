// Nuevo archivo CardAddedToWonder.java en el paquete com.sevenwonders.management.domain.wonder.events
package com.sevenwonders.management.domain.wonder.events;

import com.sevenwonders.shared.domain.generic.DomainEvent;

public class CardAddedToWonder extends DomainEvent {
  private  String wonderId;
  private  String wonderName;
  private  String cardId;

  public CardAddedToWonder() {
    super(EventsEnum.CARD_ADDED_TO_WONDER.name());
  }

  public CardAddedToWonder(String wonderId, String wonderName, String cardId) {
    super(EventsEnum.CARD_ADDED_TO_WONDER.name());
    this.wonderId = wonderId;
    this.wonderName = wonderName;
    this.cardId = cardId;
  }

  public String getWonderId() {
    return wonderId;
  }

  public String getWonderName() {
    return wonderName;
  }

  public String getCardId() {
    return cardId;
  }

  public void setWonderId(String wonderId) {
    this.wonderId = wonderId;
  }

  public void setWonderName(String wonderName) {
    this.wonderName = wonderName;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }
}