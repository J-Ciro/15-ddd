package com.sevenwonders.managment.application.shared.ports;

import com.sevenwonders.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepositoryPort {

  Flux<DomainEvent> findAllAggregates();
  Flux<DomainEvent> findEventsByAggregatedId(String aggregateId);
  void save(DomainEvent domainEvent);

}
