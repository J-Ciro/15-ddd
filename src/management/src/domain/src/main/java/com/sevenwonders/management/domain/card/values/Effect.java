package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Effect implements IValueObject {

  private final String value;

  private Effect(String value){
    this.value = value;
    validate();
  }

  public static Effect of (String value){
    return new Effect(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Effect cant be null");
    }
    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Effect cant be empty");
    }

  }

  public String getValue() {
    return value;
  }
}
