package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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
    Utils.validateNotNull(this.value, "Chained value");
  }


  public Boolean getValue() {
    return value;
  }

}
