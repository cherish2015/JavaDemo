package test.lang;

import org.junit.Test;

import test.ITest;

public class TestString implements ITest{

	@Test
	@Override
	public void test() {
		String test = "2014-08-22 14:58:05";
		System.out.println(test.compareToIgnoreCase("2014-08-22 14:58:34"));
	}
	
	

}
