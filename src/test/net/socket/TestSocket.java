package test.net.socket;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TestSocket implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(TestSocket.class);

	@Test
	@Override
	public void test() {
		testSocketObject();
	}
	
	public void testSocketObject(){
		log.info("testSocketObject");
	}

}
