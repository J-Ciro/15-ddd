package com.sevenwonders.management.domain.card.entities;

import com.sevenwonders.management.domain.card.values.Amount;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.RequirementId;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.Entity;

import java.util.HashSet;
import java.util.List;

public class Requirement extends Entity<RequirementId> {

  private Amount amount;
  private Resources resource;
  private MinimumPlayers minimumPlayers;


  public Requirement(Amount amount, Resources resource, MinimumPlayers minimumPlayers) {
    super(new RequirementId());
    this.amount = amount;
    this.resource = resource;
    this.minimumPlayers = minimumPlayers;
  }

  public Requirement(RequirementId identity, Amount amount, Resources resource, MinimumPlayers minimumPlayers) {
    super(identity);
    this.amount = amount;
    this.resource = resource;
    this.minimumPlayers = minimumPlayers;
  }

  //region Methods

  public void validateAmount(Integer amount, Integer playerCoins) {
    if (amount > playerCoins) {
      throw new IllegalArgumentException("You dont have enough coins to use this card");
    }
  }

  public void validateMinimumPlayers(Integer minimumPlayers, Integer players) {
    if (players < minimumPlayers) {
      throw new IllegalArgumentException("You cant use this card with less than " + minimumPlayers + " players");
    }
  }

  public void validateResource(List<String> resources, Integer playerResources) {
    if (resources.size() > playerResources) {
      throw new IllegalArgumentException("You dont have enough resources to use this card");
    }
  }

  public Integer updatePrice(Integer price, String cardEffect,String cardColor ,Integer cardDirection) {
    if (!"Commercial Structures".equals(cardEffect)) {
      return price;
    }

    boolean isYellow = "Yellow".equals(cardColor);
    boolean isGray = "Gray".equals(cardColor);

    if ((isYellow || isGray) && price == 2) {
      return 1;
    }

    return price;
  }


  public void calculatePrice(List<String> cardResources, List<String> playerResources ) {
      if (!new HashSet<>(playerResources).containsAll(cardResources)) {
        throw new IllegalStateException("Player doesnt have all the resources required to use this card");
      }
    playerResources.removeAll(cardResources);

  }


  //endregion


  // region Getters and Setters

  public Amount getAmount() {
    return amount;
  }

  public void setAmount(Amount amount) {
    this.amount = amount;
  }

  public Resources getResource() {
    return resource;
  }

  public void setResource(Resources resource) {
    this.resource = resource;
  }

  public MinimumPlayers getMinimumPlayers() {
    return minimumPlayers;
  }

  public void setMinimumPlayers(MinimumPlayers minimumPlayers) {
    this.minimumPlayers = minimumPlayers;
  }


  //endregion
}
