package org.mindtree.practice.trainingprog;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes=App.class)
//@SpringBootTest(classes = ApplicationTest.class)
//@WebAppConfiguration
public abstract class ApplicationTest {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
