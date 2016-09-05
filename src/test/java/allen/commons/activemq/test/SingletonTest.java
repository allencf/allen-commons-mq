package allen.commons.activemq.test;

public class SingletonTest {
	
	private SingletonTest(){
		
	}
	
	
	private static SingletonTest instance;
	
	public static SingletonTest getIntance(){
		
		if(instance == null) 
			instance = new SingletonTest();
		
		System.out.println(instance.toString());
		
		return instance;
	}
	
	public static void main(String[] args) {
		SingletonTest s1 = SingletonTest.getIntance();
		SingletonTest s2 = SingletonTest.getIntance();
		
		System.out.println(s1 == s2);
	}

}
