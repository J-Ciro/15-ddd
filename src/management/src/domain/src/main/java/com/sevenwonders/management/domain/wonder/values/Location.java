package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

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

    if (this.value == null){
      throw new IllegalArgumentException("The Location cant be null");
    }
    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Location cant be empty");
    }
    if (this.value.length() > 5 || this.value.length() < 4){
      throw new IllegalArgumentException("The Location cant be greater than 10 characters");
    }

  }

  public String getValue() {
    return value;
  }
}
