package com.sevenwonders.management.domain.wonder.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

import java.util.List;

public class Marks implements IValueObject {

  private final List<Integer> value;

  private Marks(List<Integer> value){
    this.value = value;
    validate();
  }

  public static Marks of (List<Integer> value){
    return new Marks(value);
  }

  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Marks value");
    Utils.validateInRange(this.value.size(), 1, 6, "Marks value");

  }

  public List<Integer> getValue() {
    return value;
  }
}
