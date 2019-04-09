package org.anystub.it;

import org.anystub.AnystubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnystubApplication.class)
public class AnystubApplicationTests {

	@Autowired
	private String externalSystemUrl;
	@Test
	public void contextLoads() {
		assertNotNull(externalSystemUrl);
	}

}
