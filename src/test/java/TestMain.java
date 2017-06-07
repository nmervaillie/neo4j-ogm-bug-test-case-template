import logic.Neo4jBolt;
import logic.Neo4jSessionFactory;
import model.Child;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.ogm.model.Result;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by jnieland on 07/06/17.
 */
public class TestMain {

    private Driver boltDriver;
    private org.neo4j.ogm.session.Session ogmSession;
    private Session boltSession;

    //add a Child node to your database and set its ID here.
    private int someIdInYourDatabase = 34617;

    @Test
    public void boltStraightQuery() throws Exception {
        StatementResult test = boltSession.run("MATCH (a:Child) WHERE id(a) = {id} RETURN a", parameters("id", someIdInYourDatabase));
        int count = 0;
        while(test.hasNext()) {
            System.out.println("test.next() = " + test.next());
            count++;
        }
        assertEquals("The node with ID "+someIdInYourDatabase+" should be returned by the bolt driver.", 1, count);
    }

    @Test
    public void ogmQuery() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put( "id", someIdInYourDatabase);
        Result query1 = ogmSession.query("MATCH (a:Child) WHERE id(a) = $id RETURN a", params);
        final int[] count = {0};
        query1.forEach(s->{
            Child neo4jWord = (Child) s.get("a");
            System.out.println("s = " + s);
            count[0]++;
        });
        assertEquals("The node with ID "+someIdInYourDatabase+" should be returned by the ogm driver (query).", 1, count[0]);
    }


    @Test
    public void ogmLoad() throws Exception {
        Child load = ogmSession.load(Child.class, someIdInYourDatabase, 1);
        System.out.println("load = " + load);
        assertNotNull("OGM should be able to load the Child by its id.", load);
    }


    @Before
    public void setUp() throws Exception {
        this.boltDriver = Neo4jBolt.getDriver();
        this.boltSession = boltDriver.session();
        this.ogmSession = Neo4jSessionFactory.getInstance().getNeo4jSession();
    }

    @After
    public void tearDown() throws Exception {
        this.boltSession.close();
    }
}
