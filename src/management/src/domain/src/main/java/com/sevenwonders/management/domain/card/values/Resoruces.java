package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

import java.util.List;

public class Resoruces implements IValueObject {

  private final List<String> value;

  private Resoruces(List<String> value){
    this.value = value;
    validate();
  }

  public static Resoruces of (List<String> value){
    return new Resoruces(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Resources cant be null");
    }

    if (this.value.isEmpty()){
      throw new IllegalArgumentException("The Resources cant be empty");
    }

    if (this.value.size() > 4){
      throw new IllegalArgumentException("The Resources cant be greater than 4");
    }

  }

  public List<String> getValue() {
    return value;
  }
}
