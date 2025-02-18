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

import java.util.function.Consumer;

public class CardHandler extends DomainActionsContainer {

  public CardHandler(Card card){
    add(checkedConstruction(card));
    add(selectedCard(card));
    add(checkedRequirement(card));
    add(discardConstruction(card));
    add(discardedCard(card));
    add(updatedRequirement(card));
    add(validateConstruction(card));
    add(validateRequirement(card));

  }


  public Consumer<? extends DomainEvent> checkedConstruction(Card card) {
    return (CheckedConstruction event) -> {
      Construction construction = card.getConstruction();
      if (construction == null) {
        throw new IllegalStateException("Construction not initialized");
      }

      String validatedStatus = construction.checkStatus(event.getStatus());
      construction.setStatus(Status.of(validatedStatus));

      Integer shields = construction.calculateShields();
      construction.setShields(Shields.of(shields));

      String chainedStatus = construction.checkChained(event.getChained());
      construction.setChained(Chained.of(event.getChained()));

      String checkedEffect = construction.checkEffect(event.getEffect());
      construction.setEffect(Effect.of(checkedEffect));
    };
  }

  public Consumer<? extends DomainEvent> checkedRequirement(Card card) {
    return (CheckedRequirement event) -> {
      Requirement currentRequirement = card.getRequirement();

      if (currentRequirement == null) {
        throw new IllegalStateException("Requirement not initialized");
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
      card.setName(Name.of(event.getName()));
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
      }

      if (event.getConstructions() != null) {
        Construction discardedConstruction = new Construction(
          Status.of("DISCARDED"),
          event.getConstructions().getChained(),
          Shields.of(0),
          Effect.of("DISCARDED")
        );
        card.setConstruction(discardedConstruction);
      }
    };
  }


  public Consumer<? extends DomainEvent> discardConstruction(Card card) {
    return (DiscardedConstruction event) -> {
      Construction currentConstruction = card.getConstruction();

      if (currentConstruction == null) {
        throw new IllegalStateException("Construction not initialized");
      }

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

      card.setName(Name.of(event.getName()));
      card.setEra(Era.of(event.getEra()));
      card.setType(Type.of(event.getType()));
      card.setColor(Color.of(event.getColor()));

      Construction construction = new Construction(
        Status.of("SELECTED"),
        Chained.of(false),
        Shields.of(0),
        Effect.of("")
      );

      if (event.getConstructions() != null) {
        construction = new Construction(
          Status.of(event.getConstructions().getStatus().getValue()),
          Chained.of(event.getConstructions().getChained().getValue()),
          Shields.of(event.getConstructions().getShields().getValue()),
          Effect.of(event.getConstructions().getEffect().getValue())
        );
      }

      if (event.getRequirements() != null) {
        card.setRequirement(event.getRequirements());
      }

      card.setConstruction(construction);
    };
  }


  public Consumer<? extends DomainEvent> updatedRequirement(Card card) {
    return (UpdatedRequirement event) -> {
      Requirement currentRequirement = card.getRequirement();

      if (currentRequirement == null) {
        throw new IllegalStateException("Requirement not initialized");
      }

      Requirement updatedRequirement = new Requirement(
        Amount.of(event.getPrice()),
        Resources.of(event.getResources()),
        MinimumPlayers.of(event.getMinimumPlayers())
      );

      card.setRequirement(updatedRequirement);
    };
  }


  public Consumer<? extends DomainEvent> validateConstruction(Card card) {
    return (ValidatedConstruction event) -> {
      Construction currentConstruction = card.getConstruction();

      if (currentConstruction == null) {
        throw new IllegalStateException("Construction not initialized");
      }

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
      Requirement currentRequirement = card.getRequirement();

      if (currentRequirement == null) {
        throw new IllegalStateException("Requirement not initialized");
      }

      Requirement validatedRequirement = new Requirement(
        Amount.of(event.getPrice()),
        Resources.of(event.getResources()),
        MinimumPlayers.of(event.getMinimumPlayers())
      );

      card.setRequirement(validatedRequirement);
    };
  }

}
