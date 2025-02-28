//package com.sevenwonders.managment.application.card.validatecardconstruction;
//
//import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import reactor.test.StepVerifier;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ValidateCardConstructionUseCaseTest {
//
//  private final ValidateCardConstructionUseCase useCase;
//  private final IEventsRepositoryPort repository;
//
//  public ValidateCardConstructionUseCaseTest() {
//    repository = Mockito.mock(IEventsRepositoryPort.class);
//    useCase = new ValidateCardConstructionUseCase(repository);
//  }
//
//  @Test
//  void executeCardConstruction() {
//
////    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
////      .thenReturn(Flux.just(
////        new SelectedCard(
////          "Bazaar",
////          1,
////          "CIVIL",
////          "BLUE",
////          new Requirement(
////            RequirementId.of("R1"),
////            Amount.of(3),
////            Resources.of(List.of("WOOD")),
////            MinimumPlayers.of(2)
////          ),
////          new Construction(
////            Status.of("INPROGRESS"),
////            Chained.of(false),
////            Shields.of(0),
////            Effect.of("NONE")
////          )
////        )
////      ));
//
//
//    StepVerifier
//      .create(useCase.execute(request))
//      .assertNext(response -> {
//        assertNotNull(response);
//        assertEquals("Bazaar", response.getCardName());
//        assertEquals(1, response.getEra());
//        assertEquals("CIVIL", response.getType());
//        assertEquals("BLUE", response.getColor());
//
//        assertNotNull(response.getConstruction());
//        assertEquals("COMPLETED", response.getConstruction().getStatus());
//        assertTrue(response.getConstruction().getChained());
//        assertEquals(2, response.getConstruction().getShields());
//        assertEquals("VICTORY_POINTS", response.getConstruction().getEffect());
//      })
//      .verifyComplete();
//
//    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
//  }
//
//}