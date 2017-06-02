package org.neo4j.ogm.test.domain;

/**
 * @author Frantisek Hartman
 */

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Child extends Entity {
    String name;

    public Child() {
    }
}
