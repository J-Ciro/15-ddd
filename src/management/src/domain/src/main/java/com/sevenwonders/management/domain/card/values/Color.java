package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.IValueObject;
import com.sevenwonders.shared.domain.generic.utils.Utils;

import java.util.Set;

public class Color implements IValueObject {

  private final String value;

  private Color(String value){
    this.value = value;
    validate();
  }

  public static Color of (String value){
    return new Color(value);
  }


  private static final Set<String> VALID_COLORS = Set.of(
    "BROWN", "GRAY", "BLUE", "GREEN", "YELLOW", "RED", "PURPLE"
  );



  @Override
  public void validate() {

    Utils.validateNotNull(this.value, "Color value");
    Utils.validateNotBlank(this.value);

    if (this.value.length() > 10){
      throw new IllegalArgumentException("The Color cant be longer than 10 characters");
    }

    if (!VALID_COLORS.contains(this.value)) {
      throw new IllegalArgumentException("Invalid Color: " + this.value);
    }

  }

  public String getValue() {
    return value;
  }


}
