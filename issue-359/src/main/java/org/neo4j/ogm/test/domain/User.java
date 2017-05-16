package org.neo4j.ogm.test.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class User {

    @GraphId
    private Long id;

    private String firstName;

    private String lastName;

    private List<Role> roles;

    private String email;

    public User() {
        // required by
    }

    public User(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
