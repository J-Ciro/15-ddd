package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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

    Utils.validateNotNull(this.value, "Amount value");
    Utils.validateNotNegative(this.value);

  }


  public Integer getValue() {
    return value;
  }
}
