package allen.commons.activemq.test.synchronizedtest;

public class AccountTest {
	
	private static Account account = new Account("allen", 1000);
	
	private static Integer THREAD_NUM = 100;
	
	private static Thread[] threads = new Thread[THREAD_NUM];
	
	
	public static void singleThreadTest(){
		account.add(1000);
		account.subtract(1000);
		System.out.println(account.getBalance());
	}
	
	public static void multiThreadTest(){
		for(int i=0;i<THREAD_NUM;i++){
			 threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					account.add(1000);
					account.subtract(1000);
					//System.out.println(Thread.currentThread().getName());
				}
			});
			threads[i].start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(account.getBalance());
	}
	
	public static void main(String[] args) {
		//singleThreadTest();
		//multiThreadTest();
		
		SynchronizedTest test1 = new SynchronizedTest(1);
		test1.start();
		SynchronizedTest test2 = new SynchronizedTest(3);
		test2.start();
	}

}
