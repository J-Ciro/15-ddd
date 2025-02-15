package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class CardId  extends Identity {


  public CardId(){
    super();
  }

  private CardId(String value){
    super(value);
  }


  public static CardId of (String value){
    return new CardId(value);
  }

}
