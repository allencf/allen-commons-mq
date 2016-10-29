package allen.commons.activemq.test;

public class SynchronizedTest1 extends Thread{
	
	private static Num n = new Num();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (n) {
			n.setValue(n.getValue()+1);
			System.out.println(n.getClass());
			System.out.println(n.getValue());
			
		}
		
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			SynchronizedTest1 test = new SynchronizedTest1();
			test.start();
		}
		
	}
	
	
	

}

class Num {
	int value = 0 ;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
