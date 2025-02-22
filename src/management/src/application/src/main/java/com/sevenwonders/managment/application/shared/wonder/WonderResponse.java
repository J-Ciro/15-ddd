package com.sevenwonders.managment.application.shared.wonder;

import java.util.List;

public class WonderResponse {
  private final String wonderId;
  private final String wonderName;
  private final String mode;
  private final Stage stage;
  private final Vault vault;
  private final Conflict conflict;
  private final List<Card> cards;

  public WonderResponse(String wonderId, String wonderName, String mode,
                        Stage stage, Vault vault, Conflict conflict,
                        List<Card> cards) {
    this.wonderId = wonderId;
    this.wonderName = wonderName;
    this.mode = mode;
    this.stage = stage;
    this.vault = vault;
    this.conflict = conflict;
    this.cards = cards;
  }

  // Getters
  public String getWonderId() { return wonderId; }
  public String getWonderName() { return wonderName; }
  public String getMode() { return mode; }
  public Stage getStage() { return stage; }
  public Vault getVault() { return vault; }
  public Conflict getConflict() { return conflict; }
  public List<Card> getCards() { return cards; }

  public static class Card {
    private final String id;
    private final String name;
    private final Integer era;
    private final String type;
    private final String color;

    public Card(String id, String name, Integer era,
                         String type, String color) {
      this.id = id;
      this.name = name;
      this.era = era;
      this.type = type;
      this.color = color;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getEra() { return era; }
    public String getType() { return type; }
    public String getColor() { return color; }
  }

  public static class Stage {
    private final String name;
    private final List<String> resources;
    private final String status;

    public Stage(String name, List<String> resources, String status) {
      this.name = name;
      this.resources = resources;
      this.status = status;
    }


    public String getName() { return name; }
    public List<String> getResources() { return resources; }
    public String getStatus() { return status; }
  }



  public static class Vault {
    private final Integer coins;
    private final List<String> resources;

    public Vault(Integer coins, List<String> resources) {
      this.coins = coins;
      this.resources = resources;
    }

    public Integer getCoins() { return coins; }
    public List<String> getResources() { return resources; }
  }

  public static class Conflict {
    private final List<Integer> marks;
    private final Integer shields;
    private final String location;

    public Conflict(List<Integer> marks, Integer shields, String location) {
      this.marks = marks;
      this.shields = shields;
      this.location = location;
    }


    public List<Integer> getMarks() { return marks; }
    public Integer getShields() { return shields; }
    public String getLocation() { return location; }
  }
}