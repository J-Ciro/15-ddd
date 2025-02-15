package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Marks implements IValueObject {

  private final Integer value;

  private Marks(Integer value){
    this.value = value;
    validate();
  }

  public static Marks of (Integer value){
    return new Marks(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Marks cant be null");
    }

    if (this.value > 18 || this.value < -6) {
      throw new IllegalArgumentException("The Marks must be between -6 and 18.");
    }

  }

  public Integer getValue() {
    return value;
  }
}
