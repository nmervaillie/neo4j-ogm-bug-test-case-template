package model;

/**
 * Created by jnieland on 17/05/17.
 */
public abstract class Entity {

   //each neo4jold model will receive a version in order to differentiate between different groups of nodes/edges
   private int version;

   /**
    * This id is necessary for Neo4J.
    */
   private Long id;

   public Long getId() {
      return id;
   }

   public enum EntityType {
      ANY
   }

   public int getVersion() {
      return version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

}
