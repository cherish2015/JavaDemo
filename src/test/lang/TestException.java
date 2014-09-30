package test.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TestException implements ITest{
	
	private static Logger log = LoggerFactory.getLogger(TestException.class);
	
	private static int count= 1;

	public static void main(String[] args) {
		new TestException().test();
	}

	@Test
	@Override
	public void test() {
		while(true){
			log.info("start");
			try {
				testException();
				log.info("break");
				break;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	private void testException() throws Exception{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
		log.info("count++;  {}",count);
		if (count < 500000) {
			throw new Exception("test exception");			
		}
	}

}
