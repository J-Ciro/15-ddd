package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;

import java.util.List;

public class Resources implements IValueObject {

  private final List<String> value;

  private Resources(List<String> value){
    this.value = value;
    validate();
  }

  public static Resources of (List<String> value){
    return new Resources(value);
  }

  @Override
  public void validate() {

    if (this.value == null){
      throw new IllegalArgumentException("The Mode cant be null");
    }

  }

  public List<String> getValue() {
    return value;
  }
}
