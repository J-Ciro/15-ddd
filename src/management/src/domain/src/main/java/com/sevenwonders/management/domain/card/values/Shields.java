package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;


public class Shields implements IValueObject {

  private final Integer value;

  private Shields(Integer value){
    this.value = value;
    validate();
  }

  public static Shields of (Integer value){
    return new Shields(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Shields cant be null");
    }

    if (this.value > 10) {
      throw new IllegalArgumentException("The Shields cant be greater than 10");
    }
  }

  public Integer getValue() {
    return value;
  }
}
