package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Mode implements IValueObject {

  private final String value;

  private Mode(String value){
    this.value = value;
    validate();
  }

  public static Mode of (String value){
    return new Mode(value);
  }

  @Override
  public void validate() {
    Utils.validateNotNull(this.value, "Mode value");
    Utils.validateNotBlank(this.value);
    Utils.validateNotSpecialCharacters(this.value);
  }

  public String getValue() {
    return value;
  }
}
