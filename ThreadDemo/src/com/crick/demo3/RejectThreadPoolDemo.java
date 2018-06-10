package com.crick.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadPoolDemo {
	public static class MyTask implements Runnable{
		
		public String name;
		public MyTask(String name) {
			this.name=name;
		}
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getId()+name);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyTask task=new MyTask("haha");
		Runtime.getRuntime().availableProcessors();
		//自定义线程策略
//		ExecutorService es=new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new RejectedExecutionHandler() {
//			@Override
//			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//				System.out.println(r.toString()+"is discard");
//			}
//		});
		
		
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			es.submit(task);
//			Thread.sleep(10);
//		}
		
		//自定义线程创建
//		ExecutorService es=new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
//			
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread t=new Thread(r);
//				t.setDaemon(true);
//				System.out.println("create"+t);
//				return t;
//			}
//		});
//		
//		for (int i = 0; i < 5; i++) {
//			es.submit(task);
//		}
//		Thread.sleep(2000);
		//线程池扩展
		ExecutorService es=new ThreadPoolExecutor(5, 5, 0l, TimeUnit.SECONDS, new LinkedBlockingQueue<>()){
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行"+((MyTask)r).toString());
			}
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完成"+((MyTask)r).toString());
			}
			@Override
			protected void terminated() {
				System.out.println("线程池退出");
			}
		};
		for (int i = 0; i < 5; i++) {
			MyTask mytask=new MyTask("Task-"+i);
			es.execute(mytask);
//			es.submit(mytask);
			Thread.sleep(10);
		}
		es.shutdown();
	}
}
