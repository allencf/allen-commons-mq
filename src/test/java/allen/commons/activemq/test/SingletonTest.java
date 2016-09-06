package allen.commons.activemq.test;


/**
 * @author allen
 * @description 单列类测试 
 * @date 2016年9月5日 下午11:37:27
 *
 */
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
