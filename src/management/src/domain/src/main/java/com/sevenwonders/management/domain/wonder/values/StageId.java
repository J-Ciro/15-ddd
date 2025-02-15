package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class StageId extends Identity {

  public StageId(){
    super();
  }

  private StageId(String value){
    super(value);
  }


  public static StageId of (String value){
    return new StageId(value);
  }
}
