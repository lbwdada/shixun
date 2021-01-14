package sdyu.sx.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import sdyu.sx.entity.Orders;

public class OrdersDao {
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	private QueryRunner qr = new QueryRunner(dataSource);
	/*
	 * 创建订单
	 */
	public int createOrders(Orders o) {
		String sql = "insert into orders values(?,?,?,?,?,?,?)";
		try {
			return qr.update(sql,o.getOrders_number(),o.getUid(),o.getSum_price(),o.getState(),o.getCreate_time(),o.getCount_number(),o.getOrders_name());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * 查询用户订单
	 */
	public List<Orders> findOrdersByUid(int id) {
		String sql = "select * from orders where uid=? order by create_time desc";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 更新订单支付状态
	 */
	public int updateOrders(String order_number, int i) {
		String sql = "update orders set state=? where orders_number=?";
		try {
			return qr.update(sql,i,order_number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
