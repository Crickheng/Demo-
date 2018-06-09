package com.crick.demo3;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLook implements Runnable {

	public static ReentrantLock lock =new ReentrantLock();
	public static int i=0;
	@Override
	public void run() {
		for (int j = 0; j < 1000000; j++) {
			lock.lock();
			try {
				i++;
			} finally{
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLook lo=new ReenterLook();
		Thread t1=new Thread(lo);
		Thread t2=new Thread(lo);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
