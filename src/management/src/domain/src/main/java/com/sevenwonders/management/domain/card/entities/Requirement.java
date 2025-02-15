package com.sevenwonders.management.domain.card.entities;

import com.sevenwonders.management.domain.card.values.Amount;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.RequirementId;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.Entity;

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
}
