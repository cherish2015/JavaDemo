package test.util.log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class Log4jTest implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(Log4jTest.class);
	
	private static Logger fileLog = LoggerFactory.getLogger("fileLogger");
	
	private static Logger mailLog = LoggerFactory.getLogger("mailLogger");

	@Test
	@Override
	public void test() {
		
		log.info("this is a default logger test.");
		
		fileLog.info("this is a file logger test.");
		
		Exception e = new Exception("THIS IS JUST a TEST.");
		mailLog.error("this is a default logger test:{}",e.getMessage(),e);
	}

}
