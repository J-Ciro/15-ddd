package shared.repositories;

import com.sevenwonders.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {

  Flux<DomainEvent> findEventsByAggregatedId(String aggregateId);
  void save(DomainEvent domainEvent);

}
