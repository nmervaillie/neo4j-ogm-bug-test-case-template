package org.neo4j.ogm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.test.domain.Role;
import org.neo4j.ogm.test.domain.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class OgmTestCase {

    @Rule
    public Neo4jRule neoServer = new Neo4jRule();
    private Session session;

    @Before
    public void setUp() throws Exception {

        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");

        SessionFactory sessionFactory = new SessionFactory(configuration, "org.neo4j.ogm.test.domain");
        session = sessionFactory.openSession();
        session.purgeDatabase();
    }

    @Test
    public void sampleTest() {
        User user = new User("noone@nowhere.com", "No", "One");
        session.save(user);

        user.setRoles(Arrays.asList(Role.ARCHITECT, Role.DEV));
        session.save(user);
        session.clear();

        user = session.load(User.class, user.getId(), 0);
        assertThat(!user.getRoles().isEmpty());
        session.clear();

        // observe proper behavior
        Iterable<User> result1 = session.query(User.class, "MATCH (n:User) WHERE ID(n)=" + user.getId() + " RETURN n", Collections.EMPTY_MAP);
        assertThat(!result1.iterator().next().getRoles().isEmpty());
        session.clear();

        // classCastException here
        Result result2 = session.query("MATCH (n:User) WHERE ID(n)=" + user.getId() + " RETURN n", Collections.EMPTY_MAP);
        assertThat(null != result2);


    }
}
