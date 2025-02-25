package com.sevenwonders.managment.infra.mainservice.controllers.card;

import com.sevenwonders.managment.application.card.validatecardconstruction.ValidateCardConstructionRequest;
import com.sevenwonders.managment.application.card.validatecardconstruction.ValidateCardConstructionUseCase;
import com.sevenwonders.managment.application.shared.card.CardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/validate-construction")
public class ValidateCardConstructionController {


  private final ValidateCardConstructionUseCase useCase;

  public ValidateCardConstructionController(ValidateCardConstructionUseCase useCase){
    this.useCase = useCase;
  }


@PostMapping

  public Mono<CardResponse> execute(ValidateCardConstructionRequest request) {
   return useCase.execute(request);
}

}
