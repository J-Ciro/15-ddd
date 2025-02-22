package com.sevenwonders.managment.application.wonder.managestage;

import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.ICommandUseCase;

import reactor.core.publisher.Mono;
import shared.repositories.IEventsRepository;
import shared.wonder.WonderMapper;
import shared.wonder.WonderResponse;

public class ManageWonderStageUseCase  implements ICommandUseCase<ManageWonderStageRequest, Mono<WonderResponse>> {

  private final IEventsRepository repository;

  public ManageWonderStageUseCase(IEventsRepository repository) {
    this.repository = repository;
  }


  @Override
  public Mono<WonderResponse> execute(ManageWonderStageRequest request) {
    return repository
      .findEventsByAggregatedId(request.getWonderId())
      .collectList()
      .map(events -> Wonder.from(request.getWonderId(), events))
      .flatMap(wonder -> {
        wonder.checkStage(
          request.getWonderId(),
          request.getWonderName(),
          request.getStageName()
        );

        wonder.validateStage(
          request.getWonderId(),
          request.getWonderName(),
          request.getStageName(),
          request.getRequiredCoins(),
          request.getRequiredResources()
        );

        wonder.updateStage(
          request.getWonderId(),
          request.getStageName(),
          request.getWonderName()
        );

        wonder.getUncommittedEvents().forEach(repository::save);
        wonder.markEventsAsCommitted();
        return Mono.just(WonderMapper.mapToWonder(wonder));
      });
  }
}
