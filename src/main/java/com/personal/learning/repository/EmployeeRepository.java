package com.personal.learning.repository;

import com.personal.learning.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

    Mono<Employee> findByEmpId(String id);
    Flux<Employee> findByName(String id);

}
