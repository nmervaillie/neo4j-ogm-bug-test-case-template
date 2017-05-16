package org.neo4j.ogm.test.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * @author jarhanco
 */
@NodeEntity
public class Company {

    @GraphId
    private Long id;

    private User owner;

    private List<User> staff;


    public Company() {
    }

    public Company(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void setStaff(List<User> staff) {
        this.staff = staff;
    }
}
