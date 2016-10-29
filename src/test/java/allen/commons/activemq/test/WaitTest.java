package allen.commons.activemq.test;

public class WaitTest {
	
	
	
	public static void main(String[] args) {
		
		ThreadB threadb = new ThreadB();
		threadb.setName("threadb");
		threadb.start();
		
		
		try {
			//Thread.sleep(100);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		synchronized (threadb) {
			try {
				threadb.wait();
				//WaitTest.class.notify();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(threadb.total);
		
	}

}


class ThreadB extends Thread{
	
	int total = 0 ;
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			total += i;
		}
		System.out.println(Thread.currentThread().getName());
	}
	
}
