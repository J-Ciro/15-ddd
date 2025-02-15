package com.sevenwonders.management.domain.wonder.entities;

import com.sevenwonders.management.domain.wonder.values.Coins;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.management.domain.wonder.values.VaultId;
import com.sevenwonders.shared.domain.generic.Entity;

import java.util.List;

public class Vault extends Entity<VaultId> {


  private Coins coins;
  private Resources resources;


  public Vault(VaultId identity, Coins coins, Resources resources) {
    super(identity);
    this.coins = coins;
    this.resources = resources;
  }

  public Vault(Coins coins, Resources resources) {
    super(new VaultId());
    this.coins = coins;
    this.resources = resources;
  }


  // region Methods



  public Integer calculateScore(Integer coins) {
    return coins / 3;
  }

  public String buyMaterial(String resource) {
    if (resources.getValue().contains(resource)) {
      return "Already bought";
    }
    resources.getValue().add(resource);
    updateCoins(-3);
    return "Bought";
  }


  public boolean sellMaterial(String resource) {
    if (!resources.getValue().contains(resource)) {
      return false;
    }
    resources.getValue().remove(resource);
    updateCoins(3);
    return true;
  }

  public void updateCoins(Integer amount) {
    if (amount == null) return;
   coins =  Coins.of(coins.getValue() + amount);
  }

  public void updateResources(List<String> currentResources, List<String> usedResources) {
    if (usedResources == null) return;
    usedResources.forEach(currentResources::remove);
  }


  //endregion

 // region Getters and Setters


  public Coins getCoins() {
    return coins;
  }

  public void setCoins(Coins coins) {
    this.coins = coins;
  }

  public Resources getResources() {
    return resources;
  }

  public void setResources(Resources resources) {
    this.resources = resources;
  }

  //endregion
}
