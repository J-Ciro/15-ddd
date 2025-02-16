package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Type implements IValueObject {

  private final String value;

  private Type(String value){
    this.value = value;
    validate();
  }

  public static Type of (String value){
    return new Type(value);
  }

  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Type value");
    Utils.validateNotBlank(this.value);

  }

  public String getValue() {
    return value;
  }
}
