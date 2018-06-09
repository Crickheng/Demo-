package com.crick.demo2;

public class StopThreadUnsafe2 {
	 public static User u=new User();
	 public static class User{
		private int id;
		private String name;
		
		public User() {
			id=0;
			name="0";
		}
		 
		public void setId(int id) {
			this.id = id;
		}
		public int getId() {
			return id;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return "User:"+getId()+","+getName();
		}
	 }
	 
	 public static class change extends Thread{
		 volatile boolean stopme=false;
		 public void stopMe(){
			 stopme=true;
		 }
		 
		 @Override
		public void run() {
			 while (true) {
				 if(stopme){
					 System.out.println("exit");
					 break;
				 }
					synchronized (u) {
						int v=(int) (System.currentTimeMillis()/1000);
						u.setId(v);
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						u.setName(String.valueOf(v));
					}
					Thread.yield();
					
				}
		}
	 }

	public static void main(String[] args) throws InterruptedException {
		Runnable read=()->{
			while (true) {
				synchronized (u) {
					if(u.getId()!=Integer.parseInt(u.getName())){
						System.out.println(u.toString());
					}
				}
				
			}
		};
		Thread r=new Thread(read);
		r.start();
		while (true) {
			change c=new change();
			c.start();
			Thread.sleep(150);
			c.stopMe();
			
		}
	}
}