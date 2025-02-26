package com.sevenwonders.managment.application.wonder.updateresources;

import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateWonderResourcesUseCaseTest {

  private final UpdateWonderResourcesUseCase useCase;
  private final IEventsRepositoryPort repository;

  public UpdateWonderResourcesUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new UpdateWonderResourcesUseCase(repository);
  }


  @Test
  void executeUpdateWonderResources() {

    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AssignedWonder("Alexandria", "NIGHT")
      ));

    UpdateWonderResourcesRequest request = new UpdateWonderResourcesRequest("123abc", "123", "Alexandria", 3, List.of("WOOD", "STONE"));
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("123", response.getWonderId());
        assertEquals("Alexandria", response.getWonderName());
        WonderResponse.Vault vault = response.getVault();
        assertNotNull(vault);
        assertEquals(6, vault.getCoins());
        assertTrue(vault.getResources().containsAll(List.of("WOOD", "STONE")));
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
  }


  @Test
  void executeWithEmptyResources() {
    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AssignedWonder("Alexandria", "NIGHT")
      ));

    UpdateWonderResourcesRequest request = new UpdateWonderResourcesRequest(
      "aggregated1",
      "W12",
      "Alexandria",
      0,
      List.of()
    );

    StepVerifier
      .create(useCase.execute(request))
      .expectErrorMatches(throwable ->
        throwable instanceof IllegalArgumentException &&
          throwable.getMessage().contains("can't be empty"))
      .verify();


    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
  }

}