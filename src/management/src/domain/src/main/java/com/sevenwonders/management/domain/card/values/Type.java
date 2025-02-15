package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Type implements IValueObject {

  private final String value;

  private Type(String value){
    this.value = value;
    validate();
  }

  public static Type of (String value){
    return new Type(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Type cant be null");
    }

    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Type cant be empty");
    }

  }

  public String getValue() {
    return value;
  }
}
