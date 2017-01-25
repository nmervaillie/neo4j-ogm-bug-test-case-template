package org.neo4j.testcasetemplate.service;

import java.util.Collections;

import org.neo4j.ogm.session.Session;
import org.neo4j.testcasetemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private final Session session;

	@Autowired
	public UserService(Session session) {
		this.session = session;
	}

	@Transactional
	public Iterable<User> findAllUsersUsingSession() {
		return session.query(User.class, "MATCH (n:User) RETURN n", Collections.<String, String>emptyMap());
	}
}
