package org.neo4j.ogm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.test.domain.Company;
import org.neo4j.ogm.test.domain.User;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

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
        User owner = new User("noone@nowhere.com", "Avon", "Barksdale");
        session.save(owner);
        session.clear();

        User employee = new User("none", "DeAngelo", "Barksdale");
        session.save(employee);
        session.clear();

        Company company = new Company(owner);
        company.setStaff(Arrays.asList(employee));
        session.save(company);
        session.clear();

        assertTrue(1 == company.getStaff().size());

        // observe that now both owner AND employee exist inside the "staff" field
        company = session.load(Company.class, company.getId(), 1);
        assertTrue(1 == company.getStaff().size());

    }
}
