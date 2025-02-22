package com.sevenwonders.managment.application.wonder.updateresources;

import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import com.sevenwonders.managment.application.wonder.managestage.ManageWonderStageUseCase;
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
  private final IEventsRepository repository;

  public UpdateWonderResourcesUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new UpdateWonderResourcesUseCase(repository);
  }


  @Test
  void executeSuccess() {

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


}