package org.neo4j.testcasetemplate.repository;

import org.neo4j.testcasetemplate.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface UserRepository extends GraphRepository<User> {

    User findByEmail(String email);

    List<User> findByLastNameLike(String lastName);
}
