package com.sevenwonders.management.domain.wonder.values;
import com.sevenwonders.shared.domain.generic.IValueObject;

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

    if (this.value == null){
      throw new IllegalArgumentException("The Coins cant be null");
    }
    if (this.value < 0 ){
      throw new IllegalArgumentException("The Coins cant be negative");
    }
  }

  public Integer getValue() {
    return value;
  }
}
