package sdyu.sx.service;

import java.util.List;

import sdyu.sx.dao.OrdersDao;
import sdyu.sx.entity.Orders;

public class OrdersService {
	OrdersDao dao = new OrdersDao();
	/*
	 * 保存订单业务
	 */
	public int createOrders(Orders orders) {
		return dao.createOrders(orders);
		
	}

	/*
	 * 查询用户订单业务
	 */
	public List<Orders> findOrdersByUid(int id) {
	return dao.findOrdersByUid(id);	
	}

	/*
	 * 更新订单业务
	 */
	public int updateOrders(String order_number, int i) {
		return dao.updateOrders(order_number,i);
	}

}
