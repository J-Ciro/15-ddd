package com.sevenwonders.managment.infra.mainservice.controllers.wonder;


import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import com.sevenwonders.managment.application.wonder.assingwonder.AssignWonderRequest;
import com.sevenwonders.managment.application.wonder.assingwonder.AssignWonderUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assign-wonder")

public class AssignedWonderController {

private final AssignWonderUseCase useCase;

  public AssignedWonderController(AssignWonderUseCase useCase){
    this.useCase = useCase;
  }


  @PostMapping
  public Mono<WonderResponse> execute(@RequestBody AssignWonderRequest request){
    return useCase.execute(request);
  }


}
