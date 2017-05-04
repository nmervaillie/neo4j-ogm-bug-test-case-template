## Spring Data Neo4j application template

This is a simple template for creating issue reproduction projects per
the [README in the root of this repository](https://github.com/nmervaillie/neo4j-ogm-bug-test-case-template#readme).
Please read that document completely before starting.

As described at the link above, do not edit this project directly! Rather
use the `./create-repro-project.sh` script to create a fresh copy to
a new directory having the same name as the jira issue you're trying
to reproduce and edit from there.

Note that this project contains a `logback-test.xml` file in
`src/test/resources` that you may wish to configure to emit more
detailed logging.