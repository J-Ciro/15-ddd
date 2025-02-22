package com.sevenwonders.managment.application.wonder.updateresources;

import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;
import com.sevenwonders.managment.application.shared.wonder.WonderMapper;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;

public class UpdateWonderResourcesUseCase implements ICommandUseCase<UpdateWonderResourcesRequest, Mono<WonderResponse>> {


  private final IEventsRepository repository;

  public UpdateWonderResourcesUseCase(IEventsRepository repository) {
    this.repository = repository;
  }


  @Override
  public Mono<WonderResponse> execute(UpdateWonderResourcesRequest request) {
    return repository.findEventsByAggregatedId(request.getWonderId())
      .collectList()
      .map(events -> Wonder.from(request.getWonderId(), events))
      .map(wonder -> {
        wonder.calculateResources(
          request.getWonderId(),
          request.getWonderName(),
          request.getCoins(),
          request.getResources()
        );

        wonder.updateVault(
          request.getWonderId(),
          request.getWonderName(),
          request.getCoins(),
          request.getResources()
        );

        wonder.getUncommittedEvents().forEach(repository::save);
        wonder.markEventsAsCommitted();
        return WonderMapper.mapToWonder(wonder);
      });
  }

}
