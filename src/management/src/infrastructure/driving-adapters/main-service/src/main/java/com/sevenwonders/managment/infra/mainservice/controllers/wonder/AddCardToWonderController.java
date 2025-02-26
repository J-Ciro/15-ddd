package com.sevenwonders.managment.infra.mainservice.controllers.wonder;

import com.sevenwonders.managment.application.AddCardToWonderRequest;
import com.sevenwonders.managment.application.AddCardToWonderUseCase;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/addcard-wonder")

public class AddCardToWonderController {

  private final AddCardToWonderUseCase useCase;

  public AddCardToWonderController(AddCardToWonderUseCase useCase){
    this.useCase = useCase;
  }


  @PostMapping
  public Mono<WonderResponse> execute(@RequestBody AddCardToWonderRequest request){
    return  useCase.execute(request);
  }


}
