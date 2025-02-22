package com.sevenwonders.managment.application.wonder.assingwonder;

import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import shared.repositories.IEventsRepository;
import shared.wonder.WonderResponse;
import static shared.wonder.WonderMapper.mapToWonder;


public class AssignWonderUseCase implements ICommandUseCase<AssignWonderRequest, Mono<WonderResponse>> {
  private final IEventsRepository repository;

  public AssignWonderUseCase(IEventsRepository repository){
    this.repository = repository;
  }

  @Override
  public Mono<WonderResponse> execute(AssignWonderRequest request) {
    Wonder wonder = new Wonder(request.getWonderName(), request.getMode());
    wonder.getUncommittedEvents().forEach(repository::save);
    wonder.markEventsAsCommitted();
    return Mono.just(mapToWonder(wonder));
  }

}
