package com.sevenwonders.managment.application.card.validatecardconstruction;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.management.domain.card.events.SelectedCard;
import com.sevenwonders.management.domain.card.values.Amount;
import com.sevenwonders.management.domain.card.values.Chained;
import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.MinimumPlayers;
import com.sevenwonders.management.domain.card.values.RequirementId;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.management.domain.wonder.values.Resources;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCardConstructionUseCaseTest {

  private final ValidateCardConstructionUseCase useCase;
  private final IEventsRepositoryPort repository;

  public ValidateCardConstructionUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new ValidateCardConstructionUseCase(repository);
  }

  @Test
  void executeCardConstruction() {

//    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
//      .thenReturn(Flux.just(
//        new SelectedCard(
//          "Bazaar",
//          1,
//          "CIVIL",
//          "BLUE",
//          new Requirement(
//            RequirementId.of("R1"),
//            Amount.of(3),
//            Resources.of(List.of("WOOD")),
//            MinimumPlayers.of(2)
//          ),
//          new Construction(
//            Status.of("INPROGRESS"),
//            Chained.of(false),
//            Shields.of(0),
//            Effect.of("NONE")
//          )
//        )
//      ));

    ValidateCardConstructionRequest request = new ValidateCardConstructionRequest(
      "card123",
      "COMPLETED",
      true,
      2,
      "VICTORY_POINTS"
    );

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("Bazaar", response.getName());
        assertEquals(1, response.getEra());
        assertEquals("CIVIL", response.getType());
        assertEquals("BLUE", response.getColor());

        assertNotNull(response.getConstruction());
        assertEquals("COMPLETED", response.getConstruction().getStatus());
        assertTrue(response.getConstruction().getChained());
        assertEquals(2, response.getConstruction().getShields());
        assertEquals("VICTORY_POINTS", response.getConstruction().getEffect());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
  }

}