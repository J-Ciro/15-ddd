package com.sevenwonders.management.domain.card.values;

import com.sevenwonders.shared.domain.generic.Identity;

public class RequirementId extends Identity {


  public RequirementId() {
    super();
  }

  private RequirementId(String value) {
    super(value);
  }

  public static RequirementId of(String value) {
    return new RequirementId(value);
  }

}
