package org.neo4j.testcasetemplate.repository;

import java.util.List;

import org.neo4j.testcasetemplate.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	List<User> findByLastNameLike(String lastName);
}
