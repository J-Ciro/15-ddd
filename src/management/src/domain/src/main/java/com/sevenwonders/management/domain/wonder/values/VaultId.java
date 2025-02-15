package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class VaultId extends Identity {

  public VaultId(){
    super();
  }

  private VaultId(String value){
    super(value);
  }


  public static VaultId of (String value){
    return new VaultId(value);
  }

}
