package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Effect implements IValueObject {

  private final String value;

  private Effect(String value){
    this.value = value;
    validate();
  }

  public static Effect of (String value){
    return new Effect(value);
  }

  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Effect value");
    Utils.validateNotBlank(this.value);
    Utils.validateNotSpecialCharacters(this.value);

  }

  public String getValue() {
    return value;
  }
}
