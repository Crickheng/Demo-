package com.crick.demo3;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	private static Object u=new Object();
	static ChangeObjectThread t1=new ChangeObjectThread("t1");
	static ChangeObjectThread t2=new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in "+getName());
				LockSupport.park();
				if(Thread.interrupted()){
					System.out.println(getName()+"���ж�");
				}
//				Thread.currentThread().suspend();
			}
			System.out.println(getName()+"ִ�н���");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
//		t1.interrupt();
		LockSupport.unpark(t2);
//		t1.resume();    
//		t2.resume();   //��t2suspend֮ǰ�����£�t2����һֱ����
		t1.join();
		t2.join();
	}

}
