package com.sevenwonders.managment.infra.mainservice.controllers.card;

import com.sevenwonders.managment.application.card.selectedcard.SelectedCardRequest;
import com.sevenwonders.managment.application.card.selectedcard.SelectedCardUseCase;
import com.sevenwonders.managment.application.shared.card.CardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-card")
public class SelectedCardController {

  private final SelectedCardUseCase useCase;

  public SelectedCardController(SelectedCardUseCase useCase){
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<CardResponse> execute(@RequestBody SelectedCardRequest request){
   return useCase.execute(request);
  }

}
