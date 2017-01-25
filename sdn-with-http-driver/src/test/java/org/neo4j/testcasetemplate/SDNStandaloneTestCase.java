package org.neo4j.testcasetemplate;

import org.neo4j.testcasetemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by markangrish on 25/01/2017.
 */
public class SDNStandaloneTestCase {

	@Autowired
	private UserRepository userRepository;
}
