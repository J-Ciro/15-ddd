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
import com.sevenwonders.management.domain.card.values.ConstructionId;
import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.RequirementId;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {

  private Card card;
  private Card card2;
  private Construction construction;
  private Requirement requirement;
  private Status status;
  private Chained chained;
  private Shields shields;
  private Effect effect;
  private Amount amount;
  private Resources resources;
  private MinimumPlayers minimumPlayers;
  private static final String CARD_ID = "123abc";

  @BeforeEach
  void setUp() {

    Construction construction = new Construction(
      Status.of("ACTIVE"),
      Chained.of(false),
      Shields.of(2),
      Effect.of("VICTORYPOINTS")
    );


    Requirement requirement = new Requirement(
      Amount.of(3),
      Resources.of(Collections.singletonList("WOOD")),
      MinimumPlayers.of(3)
    );

    Construction construction2 = new Construction(
      Status.of("VALID"),
      Chained.of(true),
      Shields.of(3),
      Effect.of("VICTORYPOINTS")
    );


    Requirement requirement2 = new Requirement(
      Amount.of(3),
      Resources.of(List.of("WOOD")),
      MinimumPlayers.of(3)
    );

    card = new Card("Altar",  1, "CIVILIAN", "BLUE", construction, requirement);
    card2 = new Card("Altar", 1, "CIVILIAN", "BLUE", construction2, requirement2);



  }


  @Test
  void checkConstructionSuccess() {
    card2.checkConstruction("123abc", "VALID", false, 3, "VICTORYPOINTS");

    Construction check = card2.getConstruction();
    assertEquals("VALID", check.getStatus().getValue());
    assertFalse(check.getChained().getValue());
    assertEquals(3, check.getShields().getValue());
    assertEquals("VICTORYPOINTS", check.getEffect().getValue());

    assertEquals(2, card2.getUncommittedEvents().size());
    assertInstanceOf(CheckedConstruction.class, card2.getUncommittedEvents().get(1));
  }

  @Test
  void checkRequirementSuccess() {
    card.checkRequirement("123abc", 4, List.of("WOOD"), 3);

    Requirement requirement = card.getRequirement();
    assertEquals(4, requirement.getAmount().getValue());
    assertEquals(List.of("WOOD"), requirement.getResource().getValue());
    assertEquals(3, requirement.getMinimumPlayers().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(CheckedRequirement.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void discardCardSuccess() {


    Requirement requirement = new Requirement(
      Amount.of(3),
      Resources.of(Collections.singletonList("WOOD")),
      MinimumPlayers.of(4)
    );

    Construction construction = new Construction(
      Status.of("ACTIVE"),
      Chained.of(false),
      Shields.of(2),
      Effect.of("VICTORYPOINTS")
    );

    card.discardedCard("123abc", "Temple", 2, "MILITARY", "RED", requirement, construction);
    assertEquals("Temple", card.getName().getValue());
    assertEquals(2, card.getEra().getValue());
    assertEquals("MILITARY", card.getType().getValue());
    assertEquals("RED", card.getColor().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(DiscardedCard.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void discardConstructionSuccess() {
    card.discardConstruction("123abc", "discarded", true, 0, "NONE");

    Construction construction = card.getConstruction();
    assertEquals("discarded", construction.getStatus().getValue());
    assertTrue(construction.getChained().getValue());
    assertEquals(0, construction.getShields().getValue());
    assertEquals("VICTORYPOINTS", construction.getEffect().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(DiscardedConstruction.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void createdCardSuccess() {
    assertEquals("Altar", card.getName().getValue());
    assertEquals(1, card.getEra().getValue());
    assertEquals("CIVILIAN", card.getType().getValue());
    assertEquals("BLUE", card.getColor().getValue());

    Construction construction = card.getConstruction();
    assertNotNull(construction);
    assertEquals("ACTIVE", construction.getStatus().getValue());
    assertFalse(construction.getChained().getValue());
    assertEquals(2, construction.getShields().getValue());
    assertEquals("VICTORYPOINTS", construction.getEffect().getValue());

    Requirement requirement = card.getRequirement();
    assertNotNull(requirement);
    assertEquals(3, requirement.getAmount().getValue());
    assertEquals(Collections.singletonList("WOOD"), requirement.getResource().getValue());
    assertEquals(3, requirement.getMinimumPlayers().getValue());

    assertEquals(1, card.getUncommittedEvents().size());
    assertInstanceOf(SelectedCard.class, card.getUncommittedEvents().get(0));
  }

  @Test
  void selectedCard() {
    Requirement requirement = new Requirement(
      Amount.of(3),
      Resources.of(List.of("WOOD", "CLAY")),
      MinimumPlayers.of(3)
    );

    Construction construction = new Construction(
      Status.of("ACTIVE"),
      Chained.of(false),
      Shields.of(2),
      Effect.of("VICTORYPOINTS")
    );

    card.selectedCard( "Temple", 2, "MILITARY", "RED", requirement, construction);
    assertEquals("Temple", card.getName().getValue());
    assertEquals(2, card.getEra().getValue());
    assertEquals("MILITARY", card.getType().getValue());
    assertEquals("RED", card.getColor().getValue());
  }

  @Test
  void updateRequirement() {
    card.updateRequirement("123abc", 5, List.of("CLAY"), 4);

    Requirement requirement = card.getRequirement();
    assertEquals(5, requirement.getAmount().getValue());
    assertEquals(List.of("CLAY"), requirement.getResource().getValue());
    assertEquals(4, requirement.getMinimumPlayers().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(UpdatedRequirement.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void validateConstruction() {
    card.validateConstruction("123abc", "VALID", true, 3, "BONUS");

    Construction construction = card.getConstruction();
    assertEquals("VALID", construction.getStatus().getValue());
    assertTrue(construction.getChained().getValue());
    assertEquals(3, construction.getShields().getValue());
    assertEquals("BONUS", construction.getEffect().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(ValidatedConstruction.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void validateRequirement() {
    card.validateRequirement("123abc", 6, List.of("GLASS"), 5);

    Requirement requirement = card.getRequirement();
    assertEquals(6, requirement.getAmount().getValue());
    assertEquals(List.of("GLASS"), requirement.getResource().getValue());
    assertEquals(5, requirement.getMinimumPlayers().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(ValidatedRequirement.class, card.getUncommittedEvents().get(1));
  }


  @Test
  void checkRequirementFailed() {
    card.checkRequirement("123abc", 4, List.of("WOOD"), 3);

    Requirement requirement = card.getRequirement();
    assertEquals(4, requirement.getAmount().getValue());
    assertEquals(List.of("WOOD"), requirement.getResource().getValue());
    assertEquals(3, requirement.getMinimumPlayers().getValue());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(CheckedRequirement.class, card.getUncommittedEvents().get(1));
  }


  @Test
  void discardConstructionFailed() {
    card.discardedCard("123ABC", "Temple", 1, "MILITARY", "RED", null, null);

    assertEquals("Temple", card.getName().getValue());
    assertEquals(1, card.getEra().getValue());
    assertEquals("MILITARY", card.getType().getValue());
    assertEquals("RED", card.getColor().getValue());
    assertNull(card.getRequirement());
    assertNull(card.getConstruction());

    assertEquals(2, card.getUncommittedEvents().size());
    assertInstanceOf(DiscardedCard.class, card.getUncommittedEvents().get(1));
  }

  @Test
  void testFromMethod() {

    List<DomainEvent> events = List.of(
      new SelectedCard("Altar", 1, "CIVILIAN", "BLUE",
        new Requirement(
          Amount.of(3),
          Resources.of(List.of("WOOD")),
          MinimumPlayers.of(2)),

        new Construction(
        ConstructionId.of("12"),
        Status.of("ET"),
        Chained.of(false),
        Shields.of(3),
        Effect.of("VICTORYPOINTS"))),

      new CheckedRequirement("123abc", 3, List.of("WOOD"), 2)
    );

    Card card = Card.from("123abc", events);
    assertNotNull(card);
    assertEquals("Altar", card.getName().getValue());
    assertEquals(1, card.getEra().getValue());
    assertEquals("CIVILIAN", card.getType().getValue());
    assertEquals("BLUE", card.getColor().getValue());
    assertEquals(2, card.getUncommittedEvents().size());
  }


}