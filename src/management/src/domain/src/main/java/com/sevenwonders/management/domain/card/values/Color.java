package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

public class Color implements IValueObject {

  private final String value;

  private Color(String value){
    this.value = value;
    validate();
  }

  public static Color of (String value){
    return new Color(value);
  }


  @Override
  public void validate() {
    if (this.value == null){
      throw new IllegalArgumentException("The Color cant be null");
    }
    if (this.value.isBlank()){
      throw new IllegalArgumentException("The Color cant be empty");
    }

    if (this.value.length() > 10){
      throw new IllegalArgumentException("The Color cant be longer than 10 characters");
    }

  }

  public String getValue() {
    return value;
  }


}
