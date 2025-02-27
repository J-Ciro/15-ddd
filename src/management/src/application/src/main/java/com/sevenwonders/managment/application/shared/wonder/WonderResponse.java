package com.sevenwonders.managment.application.shared.wonder;

import java.util.List;

public class WonderResponse {
  private final String wonderId;
  private final String wonderName;
  private final String mode;
  private final Stage stage;
  private final Vault vault;
  private final Conflict conflict;

  public WonderResponse(String wonderId, String wonderName, Stage stage ,
                        String mode
                        , Vault vault, Conflict conflict) {
    this.wonderId = wonderId;
    this.wonderName = wonderName;
    this.mode = mode;
    this.stage = stage;
    this.vault = vault;
    this.conflict = conflict;
  }

  // Getters
  public String getWonderId() { return wonderId; }
  public String getWonderName() { return wonderName; }
  public String getMode() { return mode; }
  public Stage getStage() { return stage; }
  public Vault getVault() { return vault; }
  public Conflict getConflict() { return conflict; }

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
    private final Integer marks;
    private final Integer shields;
    private final String location;

    public Conflict(Integer marks, Integer shields, String location) {
      this.marks = marks;
      this.shields = shields;
      this.location = location;
    }


    public Integer getMarks() { return marks; }
    public Integer getShields() { return shields; }
    public String getLocation() { return location; }
  }
}