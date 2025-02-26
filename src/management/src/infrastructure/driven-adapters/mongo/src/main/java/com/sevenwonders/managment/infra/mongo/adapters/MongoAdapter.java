package com.sevenwonders.managment.infra.mongo.adapters;

import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import com.sevenwonders.managment.infra.mongo.entities.Event;
import com.sevenwonders.managment.infra.mongo.repositories.IEventsRepository;
import com.sevenwonders.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventsRepositoryPort {


  private final IEventsRepository eventsRepository;

  public MongoAdapter(IEventsRepository eventsRepository){
    this.eventsRepository = eventsRepository;
  }


  @Override
  public Flux<DomainEvent> findAllAggregates() {
    return eventsRepository.findAll().map(Event::getDomainEvent);
  }

  @Override
  public Flux<DomainEvent> findEventsByAggregatedId(String aggregateId) {
    return findAllAggregates().filter(event -> event.getAggregateRootId().equals(aggregateId));
  }

  @Override
  public void save(DomainEvent domainEvent) {
    eventsRepository.save(new Event(domainEvent)).subscribe();

  }


}
