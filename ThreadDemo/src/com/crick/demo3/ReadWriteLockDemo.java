package com.crick.demo3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	
	@SuppressWarnings("unused")
	private static Lock lock=new ReentrantLock();
	private static ReentrantReadWriteLock readwritelock=new ReentrantReadWriteLock();
	private static Lock readlock=readwritelock.readLock();
	private static Lock writelock=readwritelock.writeLock();
	private int value;
	
	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			 System.out.println("¶Á²Ù×÷:" + value);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			 System.out.println("Ð´²Ù×÷:" + value);
			value=index;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final ReadWriteLockDemo demo=new ReadWriteLockDemo();
		Runnable readRunable=new Runnable() {
			
			@Override
			public void run() {
				try {
					demo.handleRead(readlock);
//					demo.handleRead(lock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable writeRunable=new Runnable() {
			
			@Override
			public void run() {
				try {
					demo.handleWrite(writelock, new Random().nextInt());
//					demo.handleWrite(lock, new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for (int i = 0; i < 18; i++) {
			new Thread(readRunable).start();
		}
		for (int i = 18; i < 20; i++) {
			new Thread(writeRunable).start();
		}
	}
	

}
