package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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

    Utils.validateNotNull(this.value, "MinimumPlayers value");

    if (this.value < 3 || this.value > 7){
      throw new IllegalArgumentException("The MinimumPlayers cant be greater than 7 or less than 3");
    }
  }

  public Integer getValue() {
    return value;
  }
}
