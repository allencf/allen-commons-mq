package allen.commons.activemq.test.synchronizedtest;


/**
 * 从函数的字面意思上我们很很好理解就是JVM在关闭的时候会逐渐关闭钩子里面添加的函数，见详细例子：
 *
 */
public class ShutDownHookTest {
	
	  static class ThreadOne extends Thread {  
	        public void run() {  
	            int i = 0;  
	            while (i < 3) {  
	                try {  
	                    Thread.sleep(2000);  
	                } catch (InterruptedException ex) {  
	                }  
	                System.out.println("=====Thread 1=====");  
	                i++;  
	            }  
	        }  
	    }  
	  
	    static class ThreadTwo extends Thread {  
	        public void run() {  
	            int i = 0;  
	            while (i < 3) {  
	                try {  
	                    Thread.sleep(1000);  
	                } catch (InterruptedException ex) {  
	                }  
	                System.out.println("======Thread 2=====");  
	                i++;  
	            }  
	        }  
	    }  
	  
	    static class ThreadThree extends Thread {  
	        public void run() {  
	            System.out.println("#####Thread 3#####");  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        Runtime.getRuntime().addShutdownHook(new ThreadThree());  
	        ThreadOne t1 = new ThreadOne();  
	        t1.start();  
	        ThreadTwo t2 = new ThreadTwo();  
	        t2.start();  
	    } 

}
