package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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

   Utils.validateNotNull(this.value, "Era value");
   Utils.validateInRange(this.value, 1, 3, "Era value");

  }

  public Integer getValue() {
    return value;
  }
}
