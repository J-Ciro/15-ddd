package com.sevenwonders.management.domain.card;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.management.domain.card.events.CheckedConstruction;
import com.sevenwonders.management.domain.card.events.CheckedRequirement;
import com.sevenwonders.management.domain.card.events.DiscardedCard;
import com.sevenwonders.management.domain.card.events.DiscardedConstruction;
import com.sevenwonders.management.domain.card.events.SelectedCard;
import com.sevenwonders.management.domain.card.events.UpdatedRequirement;
import com.sevenwonders.management.domain.card.events.ValidatedConstruction;
import com.sevenwonders.management.domain.card.events.ValidatedRequirement;
import com.sevenwonders.management.domain.card.values.Amount;
import com.sevenwonders.management.domain.card.values.Chained;
import com.sevenwonders.management.domain.card.values.Color;
import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.Era;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.card.values.Type;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.DomainActionsContainer;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.function.Consumer;

public class CardHandler extends DomainActionsContainer {

  public CardHandler(Card card){
    add(checkedConstruction(card));
    add(selectedCard(card));
    add(checkedRequirement(card));
    add(discardConstruction(card));
    add(discardedCard(card));
//    add(updatedRequirement(card));
    add(validateConstruction(card));
    add(validateRequirement(card));

  }


  public Consumer<? extends DomainEvent> checkedConstruction(Card card) {
    return (CheckedConstruction event) -> {
      Construction construction = card.getConstruction();

      String validatedStatus = construction.checkStatus(event.getStatus());
      construction.setStatus(Status.of(validatedStatus));

      Integer shields = construction.calculateShields();
      construction.setShields(Shields.of(shields));

      Boolean chainedStatus = Boolean.valueOf(construction.checkChained(event.getChained()));
      construction.setChained(Chained.of(chainedStatus));

      String checkedEffect = construction.checkEffect(event.getEffect());
      construction.setEffect(Effect.of(checkedEffect));

    };
  }

  public Consumer<? extends DomainEvent> checkedRequirement(Card card) {

    return (CheckedRequirement event) -> {
      Requirement currentRequirement = card.getRequirement();

      if (event.getPrice() < currentRequirement.getAmount().getValue()) {
        throw new IllegalStateException("Insufficient coins to add card. Required: " +
          currentRequirement.getAmount().getValue() + ", Available: " + event.getPrice());
      }

      List<String> requiredResources = currentRequirement.getResource().getValue();
      List<String> availableResources = event.getResources();
      if (!availableResources.containsAll(requiredResources)) {
        throw new IllegalStateException("Insufficient resources to add card. Required: " +
          requiredResources + ", Available: " + availableResources);
      }

      if (event.getMinimumPlayers() < currentRequirement.getMinimumPlayers().getValue()) {
        throw new IllegalStateException("Not enough players to add card. Required: " +
          currentRequirement.getMinimumPlayers().getValue() + ", Available: " + event.getMinimumPlayers());
      }

      Requirement updatedRequirement = new Requirement(
        Amount.of(event.getPrice()),
        Resources.of(event.getResources()),
        MinimumPlayers.of(event.getMinimumPlayers())
      );

      card.setRequirement(updatedRequirement);
    };
  }

  public Consumer<? extends DomainEvent> discardedCard(Card card) {
    return (DiscardedCard event) -> {
      card.setCardName(Name.of(event.getName()));
      card.setEra(Era.of(event.getEra()));
      card.setType(Type.of(event.getType()));
      card.setColor(Color.of(event.getColor()));

      if (event.getRequirements() != null) {
        Requirement discardedRequirement = new Requirement(
          Amount.of(event.getRequirements().getAmount().getValue()),
          Resources.of(event.getRequirements().getResource().getValue()),
          MinimumPlayers.of(0) // Reset minimum players when discarded
        );
        card.setRequirement(discardedRequirement);
      }else{
        card.setRequirement(null);
      }

      if (event.getConstructions() != null) {
        Construction discardedConstruction = new Construction(
          Status.of("DISCARDED"),
          event.getConstructions().getChained(),
          Shields.of(0),
          Effect.of("DISCARDED")
        );
        card.setConstruction(discardedConstruction);
      }else{
        card.setConstruction(null);
      }
    };
  }

  public Consumer<? extends DomainEvent> discardConstruction(Card card) {
    return (DiscardedConstruction event) -> {
      Construction currentConstruction = card.getConstruction();

      String updatedStatus = currentConstruction.updateStatus("discarded");

      Construction discardedConstruction = new Construction(
        Status.of(updatedStatus),
        Chained.of(event.getChained()),
        Shields.of(event.getShields()),
        Effect.of(currentConstruction.getEffect().getValue())
      );

      card.setConstruction(discardedConstruction);
    };
  }

  public Consumer<? extends DomainEvent> selectedCard(Card card) {
    return (SelectedCard event) -> {
      card.setCardName(Name.of(event.getName()));
      card.setEra(Era.of(event.getEra()));
      card.setType(Type.of(event.getType()));
      card.setColor(Color.of(event.getColor()));

      Construction construction = new Construction(
        Status.of(event.getConstructions().getStatus().getValue()),
        Chained.of(event.getConstructions().getChained().getValue()),
        Shields.of(event.getConstructions().getShields().getValue()),
        Effect.of(event.getConstructions().getEffect().getValue())
      );

      if (card.getRequirement() == null) {
        Requirement requirement = new Requirement(
          Amount.of(event.getRequirements().getAmount().getValue()),
          Resources.of(event.getRequirements().getResource().getValue()),
          MinimumPlayers.of(event.getRequirements().getMinimumPlayers().getValue())
        );
        card.setRequirement(requirement);
      }

      card.setConstruction(construction);
    };
  }

//  public Consumer<? extends DomainEvent> updatedRequirement(Card card) {
//    return (UpdatedRequirement event) -> {
//
//      Requirement updatedRequirement = new Requirement(
//        Amount.of(event.get),
//        Resources.of(event.getResources()),
//        MinimumPlayers.of(event.getMinimumPlayers())
//      );
//
//      card.setRequirement(updatedRequirement);
//    };
//  }

  public Consumer<? extends DomainEvent> validateConstruction(Card card) {
    return (ValidatedConstruction event) -> {
      Construction currentConstruction = card.getConstruction();

      String validatedStatus = currentConstruction.checkStatus(event.getStatus());
      String validatedEffect = currentConstruction.checkEffect(event.getEffect());

      Construction validatedConstruction = new Construction(
        Status.of(validatedStatus),
        Chained.of(event.getChained()),
        Shields.of(event.getShields()),
        Effect.of(validatedEffect)
      );

      card.setConstruction(validatedConstruction);
    };
  }

  public Consumer<? extends DomainEvent> validateRequirement(Card card) {
    return (ValidatedRequirement event) -> {

      Requirement validatedRequirement = new Requirement(
        Amount.of(event.getPrice()),
        Resources.of(event.getResources()),
        MinimumPlayers.of(event.getMinimumPlayers())
      );

      card.setRequirement(validatedRequirement);
    };
  }

}
