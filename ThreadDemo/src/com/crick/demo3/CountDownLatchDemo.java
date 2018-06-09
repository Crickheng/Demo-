package com.crick.demo3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo implements Runnable {
	static final CountDownLatch end=new CountDownLatch(10);
	static final CountDownLatchDemo demo=new CountDownLatchDemo();

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("check complete");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(demo).start();
		}
		end.await();
		System.out.println("fire");
	}

}
