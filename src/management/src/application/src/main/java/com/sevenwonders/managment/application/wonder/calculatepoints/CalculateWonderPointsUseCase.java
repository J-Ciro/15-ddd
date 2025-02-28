package com.sevenwonders.managment.application.wonder.calculatepoints;

import com.sevenwonders.management.domain.wonder.Wonder;
import com.sevenwonders.managment.application.ICommandUseCase;
import reactor.core.publisher.Mono;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.application.shared.wonder.WonderMapper;
import com.sevenwonders.managment.application.shared.wonder.WonderResponse;

public class CalculateWonderPointsUseCase implements ICommandUseCase<CalculateWonderPointsRequest, Mono<WonderResponse>> {
  private final IEventsRepositoryPort repository;

  public CalculateWonderPointsUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<WonderResponse> execute(CalculateWonderPointsRequest request) {

    if (request.getMarks() == null) {
      return Mono.error(new IllegalArgumentException("Marks cannot be null"));
    }

    return repository.findEventsByAggregatedId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Wonder wonder = Wonder.from(request.getAggregateId(), events);
        wonder.calculatePoints(request.getMarks());
        wonder.getUncommittedEvents().forEach(repository::save);
        wonder.markEventsAsCommitted();
        return WonderMapper.mapToWonder(wonder);
      });

  }
}