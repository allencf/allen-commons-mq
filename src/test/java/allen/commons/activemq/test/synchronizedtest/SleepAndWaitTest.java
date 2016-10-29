package allen.commons.activemq.test.synchronizedtest;

public class SleepAndWaitTest {
	
	private static class Thread1 implements Runnable{

		@Override
		public void run() {
			synchronized (SleepAndWaitTest.class) {
				System.out.println("enter thread1");
				System.out.println("thread1 is waiting");
				try {
					System.out.println("");
					SleepAndWaitTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread1 is going");
				System.out.println("thread1 is over");
			}
			
		}
	}
	
	
	private static class Thread2 implements Runnable{

		@Override
		public void run() {
			synchronized (SleepAndWaitTest.class) {
				System.out.println("enter thread2");
				System.out.println("thread2 is sleeping");
				
				SleepAndWaitTest.class.notify();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread2 is going");
				System.out.println("thread2 is over");
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new Thread(new Thread1()).start();
		new Thread(new Thread2()).start();
		
	}

}
