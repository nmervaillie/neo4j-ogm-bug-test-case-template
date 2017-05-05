package org.neo4j.ogm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.test.domain.User;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class OgmTestCase {

    @Rule
    public Neo4jRule neoServer = new Neo4jRule();
    private Session session;

    @Before
    public void setUp() throws Exception {

        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI(neoServer.boltURI().toString());
//                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
//                .setURI(neoServer.httpURI().toString());

        SessionFactory sessionFactory = new SessionFactory(configuration, User.class.getPackage().getName());
        session = sessionFactory.openSession();
        session.purgeDatabase();
    }

    @Test
    public void sampleTest() {
        User user = new User("noone@nowhere.com", "No", "One");
        session.save(user);

        Collection<User> allUsers = session.loadAll(User.class);
        assertThat(allUsers).hasSize(1);

        assertThat(allUsers.iterator().next().getEmail()).isEqualTo("noone@nowhere.com");
    }
}
