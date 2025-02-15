package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Era implements IValueObject {

  private final Integer value;

  private Era(Integer value){
    this.value = value;
    validate();
  }

  public static Era of (Integer value){
    return new Era(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Effect cant be null");
    }

    if (this.value < 1 || this.value > 3){
      throw new IllegalArgumentException("The Era cant be greater than 3 or less than 1");
    }
  }

  public Integer getValue() {
    return value;
  }
}
