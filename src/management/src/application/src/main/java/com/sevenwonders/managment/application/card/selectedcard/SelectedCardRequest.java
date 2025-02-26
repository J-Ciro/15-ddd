package com.sevenwonders.managment.application.card.selectedcard;

import com.sevenwonders.management.domain.card.events.EventsEnum;
import com.sevenwonders.managment.application.Request;

import java.util.List;

public class SelectedCardRequest extends Request {

  private final String name;
  private final String cardName;
  private final Integer era;
  private final String type;
  private final String color;
  private final Construction construction;
  private final Requirement requirement;

  public SelectedCardRequest(String name, String cardName, Integer era, String type, String color, Construction construction, Requirement requirement) {
    super(null);
    this.name = name;
    this.cardName = cardName;
    this.era = era;
    this.type = type;
    this.color = color;
    this.construction = construction;
    this.requirement = requirement;

  }



  public record Construction(String status, Boolean chained, Integer shields, String effect) {
  }

  public record Requirement(Integer amount, List<String> resources, Integer minimumPlayers) {
  }


  public String getName(){
    return name;
  }

  public String setName(){
    return  name;
  }

  public String getCardName() {
    return cardName;
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
