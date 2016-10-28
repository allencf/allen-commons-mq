package allen.commons.activemq.test.synchronizedtest;

public class Account {
	
	private String userName;
	
	private Integer amount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Account(String userName, Integer amount) {
		this.userName = userName;
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * 模拟加金额
	 * @param money
	 */
	public synchronized void add(Integer money){
		amount += money;
		try {
			//模拟操作需要耗费的时间
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 模拟减金额
	 * @param money
	 */
	public synchronized void subtract(Integer money){
		amount -= money;
		try {
			//模拟操作需要耗费的时间
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取账户余额
	 * @return
	 */
	public Integer getBalance(){
		return amount;
	}
	
	
	

}
