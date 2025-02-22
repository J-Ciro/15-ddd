package shared.card;

import java.util.List;

public class CardResponse {
  private final String cardId;
  private final String name;
  private final Integer era;
  private final String type;
  private final String color;
  private final Construction construction;
  private final Requirement requirement;

  public CardResponse(String cardId, String name, Integer era, String type,
                      String color, Construction construction, Requirement requirement) {
    this.cardId = cardId;
    this.name = name;
    this.era = era;
    this.type = type;
    this.color = color;
    this.construction = construction;
    this.requirement = requirement;
  }

  // Getters
  public String getCardId() { return cardId; }
  public String getName() { return name; }
  public Integer getEra() { return era; }
  public String getType() { return type; }
  public String getColor() { return color; }
  public Construction getConstruction() { return construction; }
  public Requirement getRequirement() { return requirement; }

  public static class Construction {
    private final String status;
    private final Boolean chained;
    private final Integer shields;
    private final String effect;

    public Construction(String status, Boolean chained, Integer shields, String effect) {
      this.status = status;
      this.chained = chained;
      this.shields = shields;
      this.effect = effect;
    }

    public String getStatus() { return status; }
    public Boolean getChained() { return chained; }
    public Integer getShields() { return shields; }
    public String getEffect() { return effect; }
  }

  public static class Requirement {
    private final Integer amount;
    private final List<String> resources;
    private final Integer minimumPlayers;

    public Requirement(Integer amount, List<String> resources, Integer minimumPlayers) {
      this.amount = amount;
      this.resources = resources;
      this.minimumPlayers = minimumPlayers;
    }

    public Integer getAmount() { return amount; }
    public List<String> getResources() { return resources; }
    public Integer getMinimumPlayers() { return minimumPlayers; }
  }
}