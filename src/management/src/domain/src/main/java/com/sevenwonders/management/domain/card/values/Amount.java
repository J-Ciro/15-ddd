package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Amount implements IValueObject {

  private final Integer value;

  private Amount(Integer value){
    this.value = value;
    validate();
  }

  public static Amount of (Integer value){
    return new Amount(value);
  }


  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Amount cant be null");
    }
    if (this.value < 0){
      throw new IllegalArgumentException("The Amount cant be negative");
    }

  }


  public Integer getValue() {
    return value;
  }
}
