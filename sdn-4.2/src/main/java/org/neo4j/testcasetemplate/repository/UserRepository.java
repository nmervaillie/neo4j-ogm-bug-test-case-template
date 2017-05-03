package org.neo4j.testcasetemplate.repository;

import org.neo4j.testcasetemplate.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByLastNameLike(String lastName);
}
