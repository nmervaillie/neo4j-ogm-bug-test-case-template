package org.neo4j.ogm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
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
        User user1 = new User(Arrays.asList(12l, 123l, 1234l), "Tim", "Duncan");
        session.save(user1);

        user1 = session.load(User.class, user1.getId());
        assertThat(user1.getMyLongs()).containsSequence(12l, 123l, 1234l);

        User user2 = new User("Shawn", "Kemp");
        session.save(user2, 1);

        // get fresh data
        session.clear();

        String queryStr = "MATCH(n:User) WHERE ID(n) = " + user2.getId() + " RETURN n";
        Result r  = session.query(queryStr, Collections.EMPTY_MAP);
        assertThat(null != r);
    }
}
