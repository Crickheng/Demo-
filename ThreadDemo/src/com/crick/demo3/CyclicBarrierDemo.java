package com.crick.demo3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static class Soldier implements Runnable{
		private String soldier;
		private final CyclicBarrier cyclic;
		
		
		Soldier(String soldier, CyclicBarrier cyclic) {
			this.soldier = soldier;
			this.cyclic = cyclic;
		}


		@Override
		public void run() {
			try {
				cyclic.await();
				doWork();
				cyclic.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		void doWork() {
			try {
				Thread.sleep(new Random().nextInt(10)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(soldier+"�������");
		}
		
	}
	
	public static class BarrierRun implements Runnable{
		boolean flag;
		int N;
		
		public BarrierRun(boolean flag, int n) {
			super();
			this.flag = flag;
			N = n;
		}

		@Override
		public void run() {
			if(flag){
				System.out.println("˾���ʿ��"+N+"�����������");
			}else {
				System.out.println("˾���ʿ��"+N+"�����������");
				flag=true;
			}
		}
	}

	public static void main(String[] args) {
		final int N=10;
		Thread[] soldier=new Thread[N];
		boolean flag=false;
		CyclicBarrier cyclic=new CyclicBarrier(N, new BarrierRun(flag, N));
		System.out.println("���϶���");
		for (int i = 0; i < N; i++) {
			System.out.println("ʿ��"+i+"����");
			soldier[i]=new Thread(new Soldier("ʿ��"+i, cyclic));
			soldier[i].start();
//			if(i==5){
//				soldier[i].interrupt();
//			}
		}
	}
}
