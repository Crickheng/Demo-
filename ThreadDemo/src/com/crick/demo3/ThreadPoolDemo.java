package com.crick.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	public static class MyTask implements Runnable{
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyTask task=new MyTask();
		ExecutorService es=Executors.newFixedThreadPool(5);
		for (int i = 0; i <10; i++) {
			es.submit(task);
//			es.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
//			es.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
		}
		es.shutdown();
	}
}
