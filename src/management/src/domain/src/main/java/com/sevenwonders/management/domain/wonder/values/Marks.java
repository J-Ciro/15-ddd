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

    if (this.value.size() > 1 || this.value.size() < 6) {
      throw new IllegalArgumentException("The Marks length must be 6 and not less than 1.");
    }

  }

  public List<Integer> getValue() {
    return value;
  }
}
