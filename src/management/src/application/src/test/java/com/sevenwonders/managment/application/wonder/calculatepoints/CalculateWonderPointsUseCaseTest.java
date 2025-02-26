package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.management.domain.wonder.events.CalculatePoints;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateWonderPointsUseCaseTest {

  private final CalculateWonderPointsUseCase useCase;
  private final IEventsRepositoryPort repository;

  public CalculateWonderPointsUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new CalculateWonderPointsUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AssignedWonder("Pyramid", "NIGHT"),
        new CalculatePoints("wonder123", List.of(5))
      ));
    CalculateWonderPointsRequest request = new CalculateWonderPointsRequest("wonder123", "asd12", List.of(10));
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("wonder123", response.getWonderId());
        assertEquals(5, response.getConflict().getMarks().get(0));
        assertEquals(10, response.getConflict().getMarks().get(1));
        assertEquals("NIGHT", response.getMode());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
  }

}