package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Location implements IValueObject {

  private final String value;

  private Location(String value){
    this.value = value;
    validate();
  }

  public static Location of (String value){
    return new Location(value);
  }

  @Override
  public void validate() {

   Utils.validateNotNull(this.value, "Location value");
   Utils.validateNotBlank(this.value);
   Utils.validateNotSpecialCharacters(this.value);
   Utils.validateInRange(this.value.length(), 4, 5, "Location value");

  }

  public String getValue() {
    return value;
  }
}
