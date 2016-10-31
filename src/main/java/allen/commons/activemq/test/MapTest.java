package allen.commons.activemq.test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	
	static void testMethod1(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("a", "a");
		map.put("a", "a1");
		
		System.out.println(map.get("a"));
		
		System.out.println(map.keySet());
	}
	
	public static void main(String[] args) {
		testMethod1();
	}

}
