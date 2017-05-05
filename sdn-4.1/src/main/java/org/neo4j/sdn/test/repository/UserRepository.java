package org.neo4j.sdn.test.repository;

import org.neo4j.sdn.test.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface UserRepository extends GraphRepository<User> {

    User findByEmail(String email);

    List<User> findByLastNameLike(String lastName);
}
