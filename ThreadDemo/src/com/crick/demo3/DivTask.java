package com.crick.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {

	int a;
	int b;
	public DivTask(int a,int b) {
		this.a=a;
		this.b=b;
	}
	@Override
	public void run() {
		double re=a/b;
		System.out.println(re);
	}

	public static void main(String[] args) {
		ThreadPoolExecutor pools=new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>()){
			private Runnable warp(final Runnable task,final Exception clientStack,String clientThreadname) {
				return new Runnable() {
					@Override
					public void run() {
						try {
							task.run();
						} catch (Exception e) {
							clientStack.printStackTrace();
							throw e;
						}
					}
				};
			}
			
			private Exception clientTrack() {
				return new Exception("Client stack trace");
			}
			@Override
			public void execute(Runnable command) {
				super.execute(warp(command, clientTrack(), Thread.currentThread().getName()));
			}
			
			@Override
			public Future<?> submit(Runnable task) {
				return super.submit(warp(task, clientTrack(), Thread.currentThread().getName()));
			}
		};
		ExecutorService es=Executors.newCachedThreadPool();
		for (int i = 0; i <5; i++) {
//			pools.submit(new DivTask(100, i));
			es.submit(new DivTask(100, i));
		}
	}
}
