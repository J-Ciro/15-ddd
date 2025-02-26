package com.sevenwonders.managment.infra.mainservice.controllers.wonder;

import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import com.sevenwonders.managment.application.wonder.calculatepoints.CalculateWonderPointsRequest;
import com.sevenwonders.managment.application.wonder.calculatepoints.CalculateWonderPointsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/calculate-points")
public class CalculateWonderPointsController {

  private final CalculateWonderPointsUseCase useCase;

  public CalculateWonderPointsController(CalculateWonderPointsUseCase useCase){
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<WonderResponse> execute(@RequestBody CalculateWonderPointsRequest request){
   return useCase.execute(request);
  }


}
