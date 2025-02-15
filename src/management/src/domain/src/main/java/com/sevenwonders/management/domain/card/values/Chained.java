package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Chained implements IValueObject {

  private final Boolean value;

  private Chained(Boolean value){
    this.value = value;
    validate();
  }

  public static Chained of (Boolean value){
    return new Chained(value);
  }


  @Override
  public void validate() {
    if (this.value == null){
      throw new IllegalArgumentException("The Chained cant be null");
    }

  }


  public Boolean getValue() {
    return value;
  }

}
