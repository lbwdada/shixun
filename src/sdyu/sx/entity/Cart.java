package sdyu.sx.entity;

/*
 * 购物车对象
 */

import java.util.Collection;
import java.util.HashMap;

public class Cart {
	
	
	//若干个购物项     key =商品的id    value = 购物项 {10- }
	private  HashMap<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	
	
	private double totalPrice;//总金额
	
	/*
	 * 清空购物车
	 */
	public void clearCart() {
		//购物项清空
		map.clear();
		
		
		totalPrice=0.0;
	}
	
	/*
	 * 移除购物项
	 */
	public void remove(String cid) {
		
		//被移除的对象
		CartItem item = map.remove(Integer.parseInt(cid));
		
		//总金额 = 总金额 -被移除购物项的小计
		
		
		totalPrice-=item.getSubPrice();
		
	}
	
	//添加购物项到购物项集合中
	public void addCart(CartItem item) {
		
		//判断新加购物项在购物项集合中是否存在   
		CartItem cartItem = map.get(item.getCommodity().getCid());
		
		
		if(cartItem==null) {
			
			//新添加购物项在购物项中不存在
			
			//直接添加购物项到购物项集合
			map.put(item.getCommodity().getCid(), item);
			
		}else {
			
			//新添加的购物项在购物车中存在
			// 设置原购物项的购买数量=  原购物项的数量+新购物项的数量
			cartItem.setCount(cartItem.getCount()+item.getCount());
		}
		
		
		//总金额 = 购物项小计累加和
		totalPrice+=item.getSubPrice();
	}



	public Collection<CartItem> getMap() {
		return map.values();
	}



	public double getTotalPrice() {
		return totalPrice;
	}




	
	
	
}
