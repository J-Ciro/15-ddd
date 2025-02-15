package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class MinimumPlayers implements IValueObject {

  private final Integer value;

  private MinimumPlayers(Integer value){
    this.value = value;
    validate();
  }

  public static MinimumPlayers of (Integer value){
    return new MinimumPlayers(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The MinimumPlayers cant be null");
    }

    if (this.value < 3 || this.value > 7){
      throw new IllegalArgumentException("The MinimumPlayers cant be greater than 7 or less than 3");
    }
  }

  public Integer getValue() {
    return value;
  }
}
