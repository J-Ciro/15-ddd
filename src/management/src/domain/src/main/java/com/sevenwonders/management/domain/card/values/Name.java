package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Name implements IValueObject {

  private final String value;

  private Name(String value){
    this.value = value;
    validate();
  }

  public static Name of (String value){
    return new Name(value);
  }

  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Name value");
    Utils.validateNotBlank(this.value);
    Utils.validateNotSpecialCharacters(this.value);


  }


  public String getValue() {
    return value;
  }
}
