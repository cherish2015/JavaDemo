package test.lang.sychronized;

import test.ITest;

public class TestSychronized implements ITest {
	
	public static void main(String[] args) {
		//new TestSychronized().test();
		TestThread t1 = new TestThread();
		t1.start();
		TestThread t2 = new TestThread();
		t2.start();
	}
	

	@Override
	public void test() {
		for (int i = 0; i < 10; i++) {
			TestSingleton ts = TestSingleton.getInstance();
			ts.test();
		}
	}
	
	private static class TestThread extends Thread{

		@Override
		public void run() {
			TestSingleton ts = TestSingleton.getInstance2();
			ts.test();
		}
		
	}
	
	private static class TestSingleton{
		private static volatile TestSingleton instance = null;
		
		private static final Object obj = new Object();
		
		private TestSingleton() {
			System.out.println("TestSychronized.TestSingleton.TestSingleton()");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public static TestSingleton getInstance(){
			if (instance == null) {
				Object obj = new Object();
				synchronized (obj) {
					System.err.println(obj);
					instance = new TestSingleton();
					System.out
							.println("TestSychronized.TestSingleton.getInstance()");
				}
			}else{}
			return instance;
		}
		
		public static TestSingleton getInstance2(){
			if (instance == null) {
				synchronized (obj) {
					System.err.println(obj);
					if (instance == null) {
						instance = new TestSingleton();						
					}
					System.out
							.println("TestSychronized.TestSingleton.getInstance()");
				}
			}else{}
			return instance;
		}
		
		public void test(){
			System.out.println("test()");
		}
	}

}
