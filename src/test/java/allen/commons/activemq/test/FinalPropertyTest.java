package allen.commons.activemq.test;


/**
 * @author allen
 * @description 针对静态属性的测试类 
 * @date 2016年9月6日 下午10:35:38
 *
 */
public class FinalPropertyTest {

	//实例成员变量
	final int a = 9;
	
	final String str;
	
	final int c;
	
	//类成员变量
	final static double d ;
	
	
	//初始化块(为实例final 变量复制)
	{
		str = "hello";
		
	}
	
	// 静态初始化块 (为类型final变量复制)
	static{
		d = 5.6;
	}

	//构造器(为实例final 变量赋值)
	public FinalPropertyTest() {
		c = 5;
	}
	
	
	public void test(final int a){
		//编译错误 不能对局部final变量重新赋值
		//a = 5;
	}
	
	
	
}
