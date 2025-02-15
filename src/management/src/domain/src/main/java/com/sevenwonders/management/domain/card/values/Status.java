package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

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

    if (this.value == null){
      throw new IllegalArgumentException("The Status cant be null");
    }

    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Status cant be empty");
    }

  }

  public String getValue() {
    return value;
  }
}
