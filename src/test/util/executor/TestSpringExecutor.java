package test.util.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import test.ITest;

public class TestSpringExecutor implements ITest{
	
	private static int seq = 0;

	public static void main(String[] args) {
		new TestSpringExecutor().test();
	}

	@Override
	public void test() {
		ThreadPoolTaskExecutor tpefb = new ThreadPoolTaskExecutor();
		tpefb.setCorePoolSize(10);
		tpefb.setMaxPoolSize(50);
		tpefb.setKeepAliveSeconds(20);
		tpefb.setWaitForTasksToCompleteOnShutdown(true);
		tpefb.initialize();
		for (int i = 0; i < 100; i++) {
			seq++;
			Runnable task = new TestThread(seq);
			tpefb.submit(task);
		}
		/*try {
			Thread.sleep(302);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		tpefb.shutdown();
	}
	
	private static class TestThread implements Runnable{
		
		private int seq = 0;
		
		public TestThread(int seq) {
			this.seq = seq;
		}

		@Override
		public void run() {
			System.err.println(seq);
			/*try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}

}
