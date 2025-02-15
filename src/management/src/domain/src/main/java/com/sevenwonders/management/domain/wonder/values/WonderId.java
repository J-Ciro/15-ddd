package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class WonderId extends Identity {


  public WonderId(){
    super();
  }

  private WonderId(String value){
    super(value);
  }


  public static WonderId of (String value){
    return new WonderId(value);
  }


}
