package com.crick.demo2;

public class WaitAndNotify {
	final static Object obj =new Object();
	
	public static class T1 extends Thread{
		@Override
		public void run() {
			synchronized (obj) {
				System.err.println(System.currentTimeMillis()+":T1 start");
				try {
					System.err.println(System.currentTimeMillis()+":T1 wait");
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(System.currentTimeMillis()+":T1 end");
			}
		}
	}
	
	public static class T2 extends Thread{
		@Override
		public void run() {
			synchronized (obj) {
				System.err.println(System.currentTimeMillis()+":T2 start");
				obj.notify();
				System.err.println(System.currentTimeMillis()+":T2 end");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		T1 t1=new T1();
		T2 t2=new T2();
		t1.start();
		t2.start();
	}

}
