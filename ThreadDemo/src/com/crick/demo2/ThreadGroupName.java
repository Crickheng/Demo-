package com.crick.demo2;

public class ThreadGroupName implements Runnable {
	
	public static void main(String[] args) {
		ThreadGroup tg=new ThreadGroup("printgroup");
		Thread t1=new Thread(tg, new ThreadGroupName(), "T1");
		Thread t2=new Thread(tg, new ThreadGroupName(), "T2");
		t1.start();
		t2.start();
		System.out.println(tg.activeCount());
		tg.list();
	}

	@Override
	public void run() {
		String groupAndName=Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
		while (true) {
			System.out.println("i am "+groupAndName);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
