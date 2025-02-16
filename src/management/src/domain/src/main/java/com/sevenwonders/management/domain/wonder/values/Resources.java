package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

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

    Utils.validateNotNull(this.value, "Resources value");

  }

  public List<String> getValue() {
    return value;
  }
}
