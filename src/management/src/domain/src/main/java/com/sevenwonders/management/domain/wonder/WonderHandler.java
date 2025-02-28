package com.sevenwonders.management.domain.wonder;

import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.entities.Conflict;
import com.sevenwonders.management.domain.wonder.entities.Stage;
import com.sevenwonders.management.domain.wonder.entities.Vault;
import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.management.domain.wonder.events.CalculatePoints;
import com.sevenwonders.management.domain.wonder.events.CalculateResources;
import com.sevenwonders.management.domain.wonder.events.CardAddedToWonder;
import com.sevenwonders.management.domain.wonder.events.CheckedStage;
import com.sevenwonders.management.domain.wonder.events.UpdateVault;
import com.sevenwonders.management.domain.wonder.events.UpdatedStage;
import com.sevenwonders.management.domain.wonder.events.ValidatedStage;
import com.sevenwonders.management.domain.wonder.values.Coins;
import com.sevenwonders.management.domain.wonder.values.Location;
import com.sevenwonders.management.domain.wonder.values.Marks;
import com.sevenwonders.management.domain.wonder.values.Mode;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.DomainActionsContainer;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WonderHandler extends DomainActionsContainer {

  public WonderHandler(Wonder wonder) {
    add(assignedWonder(wonder));
    add(calculatePoints(wonder));
    add(calculateResources(wonder));
    add(checkedStage(wonder));
    add(updateStage(wonder));
    add(updateVault(wonder));
    add(validateStage(wonder));
    add(cardAddedToWonder(wonder));
  }


  public Consumer<? extends DomainEvent> assignedWonder(Wonder wonder) {
    return (AssignedWonder event) -> {

      if (!event.getMode().equals("DAY") && !event.getMode().equals("NIGHT")) {
        throw new IllegalArgumentException("Invalid game mode");
      }
      wonder.setWonderName(Name.of(event.getWonderName()));
      wonder.setMode(Mode.of(event.getMode()));
      wonder.setConflict(new Conflict(Marks.of(2), Shields.of(0), Location.of("")));
      wonder.setVault(new Vault(Coins.of(3), Resources.of(List.of("WOOD", "IRON"))));
      wonder.setStage(new Stage(Name.of("ERA 1"), Resources.of(List.of("CLAY", "CLAY")), Status.of("STARTED")));
    };
  }

  public Consumer<? extends DomainEvent> calculatePoints(Wonder wonder) {
    return (CalculatePoints event) -> {
      Conflict currentConflict = wonder.getConflict();
      Integer currentMarks = currentConflict.getMarks().getValue();
      currentMarks += event.getMarks();
      wonder.setConflict(new Conflict(
        Marks.of(currentMarks),
        currentConflict.getShields(),
        currentConflict.getLocation()
      ));
    };
  }

  public Consumer<? extends DomainEvent> calculateResources(Wonder wonder) {
    return (CalculateResources event) -> {
      Resources currentResources = wonder.getVault().getResources();
      Coins currentCoins = wonder.getVault().getCoins();
      wonder.setVault(
        new Vault(
          currentCoins,
          currentResources
        )

      );

    };
  }


  public Consumer<? extends DomainEvent> checkedStage(Wonder wonder){
    return (CheckedStage event) -> {

      Stage currentStage = wonder.getStage();
      List<String> currentResources = currentStage.getResources().getValue();
      Integer currentCoins = wonder.getVault().getCoins().getValue();
      wonder.validateStage(event.getId(), event.getWonderName(), event.getStage(), currentCoins , currentResources);
    };
  }

  public Consumer<? extends DomainEvent> updateStage(Wonder wonder) {
    return (UpdatedStage event) -> {
      Stage currentStage = wonder.getStage();


      if (!currentStage.getStatus().getValue().equals("VALIDATED")) {
        throw new IllegalStateException("Cannot update stage: current stage not validated");
      }

      String nextEra;
      if (currentStage.getName().getValue().equals("ERA 1")) {
        nextEra = "ERA 2";
      } else if (currentStage.getName().getValue().equals("ERA 2")) {
        nextEra = "ERA 3";
      } else if (currentStage.getName().getValue().equals("ERA 3")) {
        nextEra = "COMPLETE";
      } else {
        nextEra = currentStage.getName().getValue();
      }

      List<String> nextEraResources;
      if (nextEra.equals("ERA 2")) {
        nextEraResources = List.of("CLAY", "STONE", "STONE");
      } else if (nextEra.equals("ERA 3")) {
        nextEraResources = List.of("CLAY", "STONE", "GLASS", "PAPYRUS");
      } else {
        nextEraResources = new ArrayList<>();
      }

      Stage updatedStage = new Stage(
        Name.of(nextEra),
        Resources.of(nextEraResources),
        Status.of("STARTED")
      );

      wonder.setStage(updatedStage);
    };
  }



  public Consumer<? extends DomainEvent> updateVault(Wonder wonder){
    return (UpdateVault event) -> {
      Vault currentVault = wonder.getVault();
      Coins currentCoins = currentVault.getCoins();


      List<String> updatedResources = new ArrayList<>(event.getResources());

      Integer updatedCoinsValue = currentCoins.getValue() + event.getCoins();

      wonder.setVault(new Vault(
        Coins.of(updatedCoinsValue),
        Resources.of(updatedResources)
      ));
    };
  }


public Consumer<? extends DomainEvent> validateStage(Wonder wonder){
  return (ValidatedStage event) -> {
    Stage currentStage = wonder.getStage();
    Vault currentVault = wonder.getVault();

//    if (!currentStage.getName().getValue().equals("ERA 1")) {
//      throw new IllegalStateException("Stage mismatch: Current stage does not match validated stage: " + event.getStage() + " vs " + currentStage.getName().getValue());
//
//    }

    List<String> requiredResources = event.getResources();
    List<String> availableResources = currentVault.getResources().getValue();

    if (currentStage.getStatus().getValue().equals("STARTED")) {

    wonder.setStage(new Stage(
      currentStage.getName(),
      currentStage.getResources(),
      Status.of("VALIDATED")
    ));

    List<String> remainingResources = new ArrayList<>(availableResources);
    remainingResources.removeAll(requiredResources);

    wonder.setVault(new Vault(
      Coins.of(currentVault.getCoins().getValue() - event.getCoins()),
      Resources.of(remainingResources)
    ));
    }

  };

  }

  public Consumer<? extends DomainEvent> cardAddedToWonder(Wonder wonder) {
    return (CardAddedToWonder event) -> {
      List<String> currentCardList = wonder.getCardList();
      if (currentCardList == null) {
        currentCardList = new ArrayList<String>(List.of());
      }
      currentCardList.add(event.getCardId());
      wonder.setCardList(currentCardList);
    };
  }
}
