package com.sevenwonders.management.domain.wonder.entities;

import com.sevenwonders.management.domain.wonder.values.Coins;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.management.domain.wonder.values.VaultId;
import com.sevenwonders.shared.domain.generic.Entity;

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
}
