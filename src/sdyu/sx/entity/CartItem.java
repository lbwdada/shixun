package sdyu.sx.entity;

public class CartItem {
private Commodity commodity;//商品对象
	
	private int count;//购买数量
	
	private double subPrice;//小计

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	//小计的计算
	public double getSubPrice() {
		
		return commodity.getPrice()*count;
	}

	
	
	
}
