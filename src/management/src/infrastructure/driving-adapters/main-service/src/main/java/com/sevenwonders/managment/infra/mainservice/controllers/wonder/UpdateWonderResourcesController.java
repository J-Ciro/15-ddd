package com.sevenwonders.managment.infra.mainservice.controllers.wonder;

import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import com.sevenwonders.managment.application.wonder.updateresources.UpdateWonderResourcesRequest;
import com.sevenwonders.managment.application.wonder.updateresources.UpdateWonderResourcesUseCase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/update-resources")
public class UpdateWonderResourcesController {


  private final UpdateWonderResourcesUseCase useCase;

  public UpdateWonderResourcesController(UpdateWonderResourcesUseCase useCase){
    this.useCase = useCase;
  }

  @PutMapping
  public Mono<WonderResponse> execute(@RequestBody UpdateWonderResourcesRequest request){
   return useCase.execute(request);
  }

}
