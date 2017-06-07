package logic;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import java.sql.SQLException;

/**
 * Created by jnieland on 01/06/17.
 */
public class Neo4jBolt {

    private static String hostname = "localhost";
    private static String username = "neo4j";
    private static String password = "neo4jay";

//     private static String hostname = "sea";
//    private static String username = "neo4j";
//    private static String password = "Riku6aiCahtohth9uuchaek0mai7beix0oBoo2oh";

   public static Session getSession() throws SQLException {
        Driver driver = GraphDatabase.driver( "bolt://"+hostname+":7687", AuthTokens.basic( username, password ) );
        Session session = driver.session();
        return session;
    }

    public static Driver getDriver() {
        Driver driver = GraphDatabase.driver( "bolt://"+hostname+":7687", AuthTokens.basic( username, password ) );
        return driver;
    }
}
