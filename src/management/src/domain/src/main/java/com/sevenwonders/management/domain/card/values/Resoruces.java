package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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

   Utils.validateNotNull(this.value, "Resources value");
   Utils.validateNotEmpty(this.value);
   Utils.validateGreaterThan(this.value.size(), 4, "Resources value");


  }

  public List<String> getValue() {
    return value;
  }
}
