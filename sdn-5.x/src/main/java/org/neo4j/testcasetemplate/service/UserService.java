package org.neo4j.testcasetemplate.service;

import org.neo4j.testcasetemplate.domain.User;
import org.neo4j.testcasetemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
