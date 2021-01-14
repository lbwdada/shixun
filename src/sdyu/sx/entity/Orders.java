package sdyu.sx.entity;

public class Orders {
	private String orders_number;
	private int uid;
	private double sum_price;
	private int state;
	private String create_time;
	private int count_number;
	private String orders_name;
	
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getSum_price() {
		return sum_price;
	}
	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getCount_number() {
		return count_number;
	}
	public void setCount_number(int count_number) {
		this.count_number = count_number;
	}
	public String getOrders_name() {
		return orders_name;
	}
	public void setOrders_name(String orders_name) {
		this.orders_name = orders_name;
	}
	
	
	public Orders(String orders_number, int uid, double sum_price, int state, String create_time, int count_number,
			String orders_name) {
		super();
		this.orders_number = orders_number;
		this.uid = uid;
		this.sum_price = sum_price;
		this.state = state;
		this.create_time = create_time;
		this.count_number = count_number;
		this.orders_name = orders_name;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

}
