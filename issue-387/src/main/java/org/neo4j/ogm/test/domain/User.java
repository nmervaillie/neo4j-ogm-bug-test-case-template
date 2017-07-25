package org.neo4j.ogm.test.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User {

    @GraphId
    private Long id;

    private String firstName;

    private String lastName;

    // init to empty List
    private List<Long> myLongs = new ArrayList<Long>();

    public User() {
        // required by
    }

    public User(List<Long> myLongs, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myLongs = myLongs;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Long> getMyLongs() {
        return myLongs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
