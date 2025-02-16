package com.sevenwonders.management.domain.wonder.values;
import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

public class Coins implements IValueObject {

  private final Integer value;

  private Coins(Integer value){
    this.value = value;
    validate();
  }

  public static Coins of (Integer value){
    return new Coins(value);
  }


  @Override
  public void validate() {

   Utils.validateNotNull(this.value, "Coins value");
   Utils.validateNotNegative(this.value);
   Utils.validateGreaterThan(this.value, 0, "Coins value");
  }

  public Integer getValue() {
    return value;
  }
}
