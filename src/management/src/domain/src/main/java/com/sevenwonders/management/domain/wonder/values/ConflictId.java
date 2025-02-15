package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class ConflictId extends Identity {

  public ConflictId(){
    super();
  }

  private ConflictId(String value){
    super(value);
  }


  public static ConflictId of (String value){
    return new ConflictId(value);
  }
}
