package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Status implements IValueObject {
  private final String value;

  private Status(String value){
    this.value = value;
    validate();
  }

  public static Status of (String value){
    return new Status(value);
  }

  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Status value");
    Utils.validateNotBlank(this.value);
    Utils.validateNotSpecialCharacters(this.value);

  }

  public String getValue() {
    return value;
  }
}
