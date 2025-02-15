package com.sevenwonders.management.domain.wonder.entities;

import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.management.domain.wonder.values.StageId;
import com.sevenwonders.shared.domain.generic.Entity;

import java.util.HashSet;
import java.util.List;

public class Stage extends Entity<StageId> {

  private Name name;
  private Resources resources;
  private Status status;


  public Stage(StageId identity, Name name, Resources resources, Status status) {
    super(identity);
    this.name = name;
    this.resources = resources;
    this.status = status;
  }

  public Stage(Name name, Resources resources, Status status) {
    super(new StageId());
    this.name = name;
    this.resources = resources;
    this.status = status;
  }

 // region Methods
  public void calculateMaterials(List<String> resources, List<String> playerResources) {
    if (!new HashSet<>(playerResources).containsAll(resources)) {
      throw new IllegalStateException("Player doesnt have all the resources required to use this card");
    }

  }

  public String updateStage(Integer stage, List<String> resources, List<String> playerResources) {
    calculateMaterials(resources, playerResources);
    return switch (stage) {
      case 1 -> "ERA 1";
      case 2 -> "ERA 2";
      case 3 -> "ERA 3";
      default -> throw new IllegalArgumentException("Invalid stage: " + stage);
    };
  }

 // endregion


// region Getters and Setters
  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Resources getResources() {
    return resources;
  }

  public void setResources(Resources resources) {
    this.resources = resources;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  //endregion
}
