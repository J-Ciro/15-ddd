package com.sevenwonders.managment.infra.mongo.repositories;

import com.sevenwonders.managment.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
