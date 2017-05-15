package org.neo4j.ogm.test.domain;

/**
 * @author jarhanco
 */
public enum Role {
    MANAGER("manager"),
    ARCHITECT("architect"),
    DEV("dev"),
    INTERN("intern");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
