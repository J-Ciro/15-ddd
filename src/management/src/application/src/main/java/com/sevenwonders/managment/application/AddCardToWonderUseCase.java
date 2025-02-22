package com.sevenwonders.managment.application;

import reactor.core.publisher.Mono;
import shared.wonder.WonderResponse;

public class AddCardToWonderUseCase implements ICommandUseCase<AddCardToWonderRequest, Mono<WonderResponse>> {


  @Override
  public Mono<WonderResponse> execute(AddCardToWonderRequest request) {
    return null;
  }
}
