package org.neo4j.ogm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.test.domain.User;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;
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
        User anna = new User("noone@nowhere.com", "Anna", "Doe");
        User bob = new User("noone@nowhere.com", "Bob", "Doe");
        User charlie = new User("noone@nowhere.com", "Charlie", "Doe");

        session.save(anna);
        session.save(bob);
        session.save(charlie);

        anna.setFriends(newHashSet(charlie));
        session.save(anna);

        Collection<User> allUsers = session.loadAll(User.class,
                                                    newHashSet(anna.getId(), bob.getId(), charlie.getId()),
                                                    new SortOrder().add("firstName"),
                                                    1);

        assertThat(allUsers).extracting(u -> u.getFirstName())
                            .containsExactly("Anna", "Bob", "Charlie");
    }

    @Test
    public void sampleTestLoadByType() {
        User anna = new User("noone@nowhere.com", "Anna", "Doe");
        User bob = new User("noone@nowhere.com", "Bob", "Doe");
        User charlie = new User("noone@nowhere.com", "Charlie", "Doe");

        session.save(anna);
        session.save(bob);
        session.save(charlie);

        anna.setFriends(newHashSet(charlie));
        session.save(anna);

        Collection<User> allUsers = session.loadAll(User.class,
                                                    new SortOrder().add("firstName"),
                                                    1);

        assertThat(allUsers).extracting(u -> u.getFirstName())
                            .containsExactly("Anna", "Bob", "Charlie");
    }
}
