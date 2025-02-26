package com.sevenwonders.managment.application.wonder.assingwonder;

import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;
import static com.sevenwonders.managment.application.shared.wonder.WonderMapper.mapToWonder;


public class AssignWonderUseCase implements ICommandUseCase<AssignWonderRequest, Mono<WonderResponse>> {
  private final IEventsRepositoryPort repository;

  public AssignWonderUseCase(IEventsRepositoryPort repository){
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
