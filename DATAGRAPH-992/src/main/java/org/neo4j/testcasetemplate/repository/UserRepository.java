package org.neo4j.testcasetemplate.repository;

import org.neo4j.testcasetemplate.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {

    User findByEmail(String email);

    User findByEmailsContains(String email);

    List<User> findByLastNameLike(String lastName);
}
