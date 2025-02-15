package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Mode implements IValueObject {

  private final String value;

  private Mode(String value){
    this.value = value;
    validate();
  }

  public static Mode of (String value){
    return new Mode(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Mode cant be null");
    }

    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Mode cant be empty");
    }

  }

  public String getValue() {
    return value;
  }
}
