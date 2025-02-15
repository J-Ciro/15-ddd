package com.sevenwonders.management.domain.wonder;

import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.wonder.events.CalculatePoints;
import com.sevenwonders.management.domain.wonder.events.CalculateResources;
import com.sevenwonders.management.domain.wonder.events.CheckedStage;
import com.sevenwonders.management.domain.wonder.events.UpdateVault;
import com.sevenwonders.management.domain.wonder.events.UpdatedStage;
import com.sevenwonders.management.domain.wonder.events.ValidatedStage;
import com.sevenwonders.management.domain.wonder.values.CardId;
import com.sevenwonders.management.domain.wonder.values.Mode;
import com.sevenwonders.management.domain.wonder.values.WonderId;
import com.sevenwonders.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Wonder extends AggregateRoot<WonderId> {

private Name name;
private Mode mode;
private CardId cardId;

// region Constructors
public Wonder(){
  super(new WonderId());
}

private Wonder(WonderId identity){
  super(identity);
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

  public CardId getCardId() {
    return cardId;
  }

  public void setCardId(CardId cardId) {
    this.cardId = cardId;
  }

  //endregion

//region Domain Actions

public void updateStage(String id ,String wonderName, String stage){
  apply(new UpdatedStage(id, wonderName, stage));
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


}
