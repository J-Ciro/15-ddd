package com.sevenwonders.managment.application.wonder.assingwonder;


import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class AssignWonderUseCaseTest {

  private final AssignWonderUseCase useCase;
  private final IEventsRepository repository;

  public AssignWonderUseCaseTest() {

    repository = Mockito.mock(IEventsRepository.class);
    useCase = new AssignWonderUseCase(repository);
  }



  @Test
  void executeSuccess() {
    AssignWonderRequest request = new AssignWonderRequest("AggregateId", "WonderName", "Mode");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(request.getWonderName(), response.getWonderName());
        assertEquals(request.getMode(), response.getMode());
      })
      .verifyComplete();
  }

}