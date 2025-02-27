package com.sevenwonders.managment.application;

import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddCardToWonderUseCaseTest {

  private AddCardToWonderUseCase useCase;
  private IEventsRepositoryPort repository;

  public AddCardToWonderUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new AddCardToWonderUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregatedId("wonder123"))
      .thenReturn(Flux.just(
        new AssignedWonder("Alexandria", "NIGHT")
      ));

//    Mockito.when(repository.findEventsByAggregatedId("card123"))
//      .thenReturn(Flux.just(
//        new SelectedCard(
//          "card123",
//          1,
//           "CIVIL",
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

    AddCardToWonderRequest request = new AddCardToWonderRequest(
      "wonder123",
      "card123",
      3,
      List.of("WOOD"),
      2
    );

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("wonder123", response.getWonderId());
        assertEquals("Alexandria", response.getWonderName());
        assertEquals("NIGHT", response.getMode());
        assertNotNull(response.getVault());
        assertEquals(3, response.getVault().getCoins());
        assertEquals(1, response.getVault().getResources().size());
        assertTrue(response.getVault().getResources().contains("IRON"));
        assertFalse(response.getVault().getResources().contains("WOOD"));
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregatedId("wonder123");
  }


  @Test
  void executeNoEnoughResources() {
    String wonderAggregateId = "W1";
    String cardAggregateId = "C1";

    Mockito.when(repository.findEventsByAggregatedId(wonderAggregateId))
      .thenReturn(Flux.just(
        new AssignedWonder("Alexandria", "NIGHT")
      ));

//    Mockito.when(repository.findEventsByAggregatedId(cardAggregateId))
//      .thenReturn(Flux.just(
//        new SelectedCard(
//          cardAggregateId,
//          1,
//          "CIVIL",
//          "BLUE",
//          new Requirement(
//            RequirementId.of("R1"),
//            Amount.of(5),     // necesito 5 moneda
//            Resources.of(List.of("STONE")),  // solo hay 1 de eso
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

    AddCardToWonderRequest request = new AddCardToWonderRequest(
      wonderAggregateId,
      cardAggregateId,
      3,
      List.of("WOOD"),
      2
    );

    StepVerifier
      .create(useCase.execute(request))
      .expectErrorMatches(throwable ->
        throwable instanceof IllegalStateException &&
          throwable.getMessage().contains("Insufficient"))
      .verify();

    Mockito.verify(repository).findEventsByAggregatedId(wonderAggregateId);

  }

  @Test
  void executeNotEnoughPlayers() {
    Mockito.when(repository.findEventsByAggregatedId("wonder123"))
      .thenReturn(Flux.just(
        new AssignedWonder("Alexandria", "NIGHT")
      ));

//    Mockito.when(repository.findEventsByAggregatedId("card123"))
//      .thenReturn(Flux.just(
//        new SelectedCard(
//          "card123",
//          1,
//          "CIVIL",
//          "BLUE",
//          new Requirement(
//            RequirementId.of("R1"),
//            Amount.of(3),
//            Resources.of(List.of("WOOD")),
//            MinimumPlayers.of(7)
//          ),
//          new Construction(
//            Status.of("INPROGRESS"),
//            Chained.of(false),
//            Shields.of(0),
//            Effect.of("NONE")
//          )
//        )
//      ));

    AddCardToWonderRequest request = new AddCardToWonderRequest(
      "wonder123",
      "card123",
      3,
      List.of("WOOD"),
      4
    );

    StepVerifier
      .create(useCase.execute(request))
      .expectErrorMatches(throwable ->
        throwable instanceof IllegalStateException &&
          throwable.getMessage().contains("Not enough players to add card. Required: 7, Available: 4"))
      .verify();
  }

}