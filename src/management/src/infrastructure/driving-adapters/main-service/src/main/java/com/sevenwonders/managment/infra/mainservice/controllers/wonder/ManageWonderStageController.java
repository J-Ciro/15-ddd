package com.sevenwonders.managment.infra.mainservice.controllers.wonder;

import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import com.sevenwonders.managment.application.wonder.managestage.ManageWonderStageRequest;
import com.sevenwonders.managment.application.wonder.managestage.ManageWonderStageUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/manage-stage")

public class ManageWonderStageController {

  private final ManageWonderStageUseCase useCase;


  public ManageWonderStageController(ManageWonderStageUseCase useCase){
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<WonderResponse> execute(@RequestBody ManageWonderStageRequest request){
   return useCase.execute(request);
  }


}
