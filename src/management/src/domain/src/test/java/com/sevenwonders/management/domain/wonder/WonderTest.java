package com.sevenwonders.management.domain.wonder;

import com.sevenwonders.management.domain.card.Card;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.entities.Stage;
import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.management.domain.wonder.events.CalculatePoints;
import com.sevenwonders.management.domain.wonder.events.CalculateResources;
import com.sevenwonders.management.domain.wonder.events.CheckedStage;
import com.sevenwonders.management.domain.wonder.events.UpdateVault;
import com.sevenwonders.management.domain.wonder.events.UpdatedStage;
import com.sevenwonders.management.domain.wonder.events.ValidatedStage;
import com.sevenwonders.management.domain.wonder.values.Coins;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class WonderTest {


  private Wonder wonder;
  private Requirement requirements;
  private Resources resources;
  private Stage stage;
  private List<Card> cards;



  @BeforeEach
  void setUp() {
    stage = new Stage(Name.of("ERA 1"), Resources.of(List.of("WOOD")), Status.of("STARTED"));
    resources = Resources.of(List.of("WOOD"));
    wonder = new Wonder("Colossus of Rhodes", "DAY");
    cards = new ArrayList<>();
    Card card = new Card( "ALTAR", 1, "CIVILIAN", "BLUE", null, null);
    cards.add(card);
  }


  @Test
  void assignWonderSuccess(){
    wonder.assignedWonder("Colossus of Rhodes", "DAY");
    wonder.setCards(cards);
    assertEquals(1, cards.size());
    assertEquals("ALTAR", cards.get(0).getCardName().getValue());
    assertEquals("Colossus of Rhodes", wonder.getWonderName().getValue());
    assertEquals("DAY", wonder.getMode().getValue());
    assertEquals(2, wonder.getUncommittedEvents().size());
    assertInstanceOf(AssignedWonder.class, wonder.getUncommittedEvents().get(0));
  }

  @Test
  void calculatePoints(){
    wonder.calculatePoints("wonder123", 3);
    assertTrue(wonder.getConflict().getMarks().getValue().contains(3));
    assertEquals(2, wonder.getUncommittedEvents().size());
    assertInstanceOf(CalculatePoints.class, wonder.getUncommittedEvents().get(1));
  }

  @Test
  void calculateResources(){
    wonder.calculateResources("wonder123", "Colossus of Rhodes", 3, List.of("WOOD"));
    assertEquals(3, wonder.getVault().getCoins().getValue());
    assertTrue(wonder.getVault().getResources().getValue().contains("WOOD"));
    assertEquals(2, wonder.getUncommittedEvents().size());
    assertInstanceOf(CalculateResources.class, wonder.getUncommittedEvents().get(1));
  }


  @Test
  void checkStage() {
    wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    wonder.updateVault("wonder123", "Colossus of Rhodes", 3, List.of("WOOD", "IRON"));
    wonder.checkStage("wonder123", "Colossus of Rhodes", "ERA 1"); // Stage is checked
    assertEquals("ERA 1", wonder.getStage().getName().getValue());
    assertEquals(5, wonder.getUncommittedEvents().size());
    assertInstanceOf(CheckedStage.class, wonder.getUncommittedEvents().get(3));
  }

  @Test
  void updateStageSuccess() {
    wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    wonder.updateVault("wonder123", "Colossus of Rhodes", 3, List.of("WOOD", "WOOD"));
    wonder.updateStage("wonder123", "ERA 2", "Colossus of Rhodes");
    assertEquals("ERA 2", wonder.getStage().getName().getValue());
    assertEquals(4, wonder.getUncommittedEvents().size());
    assertInstanceOf(UpdatedStage.class, wonder.getUncommittedEvents().get(3));
  }


  @Test
  void updateVault(){
    wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    wonder.updateVault("wonder123", "Colossus of Rhodes", 3, List.of("WOOD"));
    assertEquals(6, wonder.getVault().getCoins().getValue()); // 3 initial + 3 updated
    assertTrue(wonder.getVault().getResources().getValue().contains("WOOD"));
    assertEquals(3, wonder.getUncommittedEvents().size());
    assertInstanceOf(UpdateVault.class, wonder.getUncommittedEvents().get(2));
  }

  @Test
  void validateStage(){
    wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    wonder.updateVault("wonder123", "Colossus of Rhodes", 3, List.of("WOOD", "IRON"));
    wonder.validateStage("wonder123", "Colossus of Rhodes", "ERA 1", 2, List.of("WOOD"));
    assertEquals("STARTED", wonder.getStage().getStatus().getValue());
    assertEquals(6, wonder.getVault().getCoins().getValue());
    assertTrue(wonder.getVault().getResources().getValue().contains("IRON"));
    assertEquals(4, wonder.getUncommittedEvents().size());
    assertInstanceOf(ValidatedStage.class, wonder.getUncommittedEvents().get(3));
  }

  @Test
  void StageInvalid(){
   wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    assertThrows(IllegalStateException.class, () -> {

      wonder.validateStage("wonder123", "Colossus of Rhodes", "ERA 2", 2, List.of("WOOD"));

    }, "Insufficient resources to validate stage");
  }

  @Test
  void validateStageInvalidCoins(){
    wonder.assignedWonder( "Colossus of Rhodes", "DAY");
    wonder.getVault().setCoins(Coins.of(1));
    assertThrows(IllegalStateException.class, () -> {

      wonder.validateStage("wonder123", "Colossus of Rhodes", "ERA 2", 2, List.of("WOOD"));

    }, "Insufficient resources to validate stage");
  }

  @Test
  void wonderFromMethod() {
    String wonderId = "wonder123";
    List<DomainEvent> events = List.of(
      new AssignedWonder("Colossus of Rhodes", "DAY"),
      new CheckedStage(wonderId, "ERA 1", "ERA 1")
    );

    Wonder wonder = Wonder.from(wonderId, events);

    assertNotNull(wonder);
    assertEquals(wonderId, wonder.getIdentity().getValue());
    assertEquals("Colossus of Rhodes", wonder.getWonderName().getValue());
    assertEquals("DAY", wonder.getMode().getValue());
    assertEquals("ERA 1", wonder.getStage().getName().getValue());
  }


}