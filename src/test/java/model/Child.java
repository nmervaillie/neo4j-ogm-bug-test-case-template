package model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;

/**
 * Created by jnieland on 30/05/17.
 */

@NodeEntity
public class Child extends Entity {

    String name;

    public Child() {

    }

}
