package com.sevenwonders.management.domain.wonder;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.wonder.entities.Conflict;
import com.sevenwonders.management.domain.wonder.entities.Stage;
import com.sevenwonders.management.domain.wonder.entities.Vault;
import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.management.domain.wonder.events.CalculatePoints;
import com.sevenwonders.management.domain.wonder.events.CalculateResources;
import com.sevenwonders.management.domain.wonder.events.CheckedStage;
import com.sevenwonders.management.domain.wonder.events.UpdateVault;
import com.sevenwonders.management.domain.wonder.events.UpdatedStage;
import com.sevenwonders.management.domain.wonder.events.ValidatedStage;
import com.sevenwonders.management.domain.wonder.values.Mode;
import com.sevenwonders.management.domain.wonder.values.WonderId;
import com.sevenwonders.shared.domain.generic.AggregateRoot;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class Wonder extends AggregateRoot<WonderId> {

private Name name;
private Mode mode;
private List<Card> cards ;
private Conflict conflict;
private Vault vault;
private Stage stage;

// region Constructors
public Wonder(String name, String mode){
  super(new WonderId());
  subscribe(new WonderHandler(this));
  apply(new AssignedWonder(name, mode));

}

private Wonder(WonderId identity){
  super(identity);
  subscribe(new WonderHandler(this));
}

//endregion

//region Getters and Setters

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }


  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public Conflict getConflict() {
    return conflict;
  }

  public void setConflict(Conflict conflict) {
    this.conflict = conflict;
  }

  public Vault getVault() {
    return vault;
  }

  public void setVault(Vault vault) {
    this.vault = vault;
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  //endregion

//region Domain Actions
public void assignedWonder( String name, String mode){
  apply(new AssignedWonder( name, mode));
}

public void updateStage(String id , String stage,String wonderName){
  apply(new UpdatedStage(id, stage, wonderName));
}

public void checkStage(String id, String stage, String wonderName){
  apply(new CheckedStage(id, stage, wonderName));
}

public void validateStage(String id, String wonderName, String stage, Integer coins, List<String> resources){
  apply(new ValidatedStage(id, wonderName, stage, coins, resources));
}

public void updateVault(String id, String wonderName, Integer coins, List<String> resources){
  apply(new UpdateVault(id, wonderName, coins, resources));
}

public void calculatePoints(String id, Integer marks){
  apply(new CalculatePoints(id, marks));
}

public void calculateResources(String id, String wonderName, Integer coins, List<String> resources){
  apply(new CalculateResources(id, wonderName, coins, resources));
}

//endregion

  public static Wonder from(final String identity, final List<DomainEvent> events){

  Wonder wonder = new Wonder(WonderId.of(identity));
  events.forEach(wonder::apply);

  return wonder;

  }


}
