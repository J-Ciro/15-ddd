package com.sevenwonders.management.domain.wonder.entities;

import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.management.domain.wonder.values.StageId;
import com.sevenwonders.shared.domain.generic.Entity;

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
}
