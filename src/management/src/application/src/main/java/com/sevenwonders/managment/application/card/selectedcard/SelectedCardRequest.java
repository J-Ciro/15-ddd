package com.sevenwonders.managment.application.card.selectedcard;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.managment.application.Request;

public class SelectedCardRequest extends Request {

  private final String name;
  private final Integer era;
  private final String type;
  private final String color;
  private final Construction construction;
  private final Requirement requirement;

  public SelectedCardRequest( String name, Integer era, String type, String color, Construction construction, Requirement requirement) {
    super(null);
    this.name = name;
    this.era = era;
    this.type = type;
    this.color = color;
    this.construction = construction;
    this.requirement = requirement;
  }


  public String getName() {
    return name;
  }

  public Integer getEra() {
    return era;
  }

  public String getType() {
    return type;
  }

  public String getColor() {
    return color;
  }

  public Construction getConstruction() {
    return construction;
  }

  public Requirement getRequirement() {
    return requirement;
  }
}
