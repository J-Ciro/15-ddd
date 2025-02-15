package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class ConstructionId extends Identity {


  public ConstructionId(){
    super();
  }

  private ConstructionId(String value){
    super(value);
  }


  public static ConstructionId of (String value){
    return new ConstructionId(value);
  }
}