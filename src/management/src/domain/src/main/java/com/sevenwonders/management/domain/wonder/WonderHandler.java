package com.sevenwonders.management.domain.wonder;


import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
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
import com.sevenwonders.management.domain.wonder.values.Coins;
import com.sevenwonders.management.domain.wonder.values.Location;
import com.sevenwonders.management.domain.wonder.values.Marks;
import com.sevenwonders.management.domain.wonder.values.Mode;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.DomainActionsContainer;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
  }


  public Consumer<? extends DomainEvent> assignedWonder(Wonder wonder) {
    return (AssignedWonder event) -> {
      wonder.setName(Name.of(event.getWonderName()));
      wonder.setMode(Mode.of(event.getMode()));
      wonder.setCards(new ArrayList<>());
      wonder.setConflict(new Conflict(Marks.of(new ArrayList<>()), Shields.of(0), Location.of("")));
      wonder.setVault(new Vault(Coins.of(3), Resources.of(List.of("WOOD", "IRON"))));
      wonder.setStage(new Stage(Name.of("ERA 1"), Resources.of(List.of("CLAY", "CLAY")), Status.of("STARTED")));
    };
  }

  public Consumer<? extends DomainEvent> calculatePoints(Wonder wonder) {
    return (CalculatePoints event) -> {

      Conflict currentConflict = wonder.getConflict();
      List<Integer> currentMarks = currentConflict.getMarks().getValue();
      currentMarks.add(event.getMarks());
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
      List<String> calculatedResources = new ArrayList<>(event.getResources());
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

      Stage updatedStage = new Stage(
          Name.of(event.getStage()),
          currentStage.getResources(),
          Status.of("FINISH")
        );
        wonder.setStage(updatedStage);

    };
  }



  public Consumer<? extends DomainEvent> updateVault(Wonder wonder){
    return (UpdateVault event) -> {
      Vault currentVault = wonder.getVault();
      Coins currentCoins = currentVault.getCoins();
      Resources currentResources = currentVault.getResources();

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

    if (!currentStage.getName().getValue().equals(event.getStage())) {
      throw new IllegalStateException("Stage mismatch: Current stage does not match validated stage");
    }

    List<String> requiredResources = event.getResources();
    List<String> availableResources = currentVault.getResources().getValue();

    if (currentStage.getStatus().getValue().equals("BUILD")) {

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
}
