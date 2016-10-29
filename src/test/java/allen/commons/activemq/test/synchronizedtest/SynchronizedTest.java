package allen.commons.activemq.test.synchronizedtest;

public class SynchronizedTest extends Thread{
	
	private int val;
	
	public SynchronizedTest(int val) {
		this.val = val;
	}

	
	private void printVal(int v){
		//while(true)
		synchronized (SynchronizedTest.class) {
			for (int i = 0; i < 2000; i++) {
				System.out.println(val);
			}
		}
	}


	@Override
	public void run() {
		printVal(val);
	}


	public void testMethod(){
		
	}
	
	
	class Num {
		private int i = 0;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
		
	}

}

