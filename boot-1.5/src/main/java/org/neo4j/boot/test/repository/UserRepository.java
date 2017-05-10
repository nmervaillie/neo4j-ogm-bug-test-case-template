package org.neo4j.boot.test.repository;

import org.neo4j.boot.test.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {

}
