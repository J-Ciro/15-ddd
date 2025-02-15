package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

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

    if (this.value == null){
      throw new IllegalArgumentException("The Name cant be null");
    }

    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Name cant be empty");
    }
  }


  public String getValue() {
    return value;
  }
}
