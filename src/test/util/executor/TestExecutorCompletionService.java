package test.util.executor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import test.ITest;

public class TestExecutorCompletionService implements ITest {

	@Test
	@Override
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		ArrayList<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
		
		for (int i = 0; i < 10; i++) {
			double m = Math.random()*10;
			int n = (int)(Math.random()*1000);
			tasks.add(new Exp(m, n));
		}
		ExecutorCompletionService service = new ExecutorCompletionService(executor); 
		for (Callable<Double> task : tasks) {
			service.submit(task);
		}
		Lock lock = new ReentrantLock();
		for (int i = 0; i < tasks.size(); i++) {
			try {
				lock.lock();
				Double d = (Double) service.take().get();
				System.err.println("Result:"+d);
				lock.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
	
	public static class Exp implements Callable<Double>{
		
		private double m;
		private int n;
		
		public Exp(double m,int n) {
			this.m = m;
			this.n = n;
		}

		@Override
		public Double call() throws Exception {
			double result = 1;
			for (int i = 0; i < n; i++) {
				result *= m;
				Thread.sleep(10);
			}
			return result;
		}
		
	}

}



