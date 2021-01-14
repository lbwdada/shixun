package sdyu.sx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdyu.sx.entity.Orders;
import sdyu.sx.entity.User;
import sdyu.sx.service.OrdersService;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	OrdersService service = new OrdersService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if(method.equals("createOrders")) {
			createOrders(req,resp);
		}else if(method.equals("findOrdersByUid")) {
			findOrdersByUid(req,resp);
		}else if(method.equals("updateOrders")) {
			updateOrders(req,resp);
		}
	}

	/*
	 * 更新订单状态
	 */
	private void updateOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//付款后的订单编号
		String order_number = req.getParameter("order_number");
		
		//更新订单业务
		int row = service.updateOrders(order_number,1);
		if(row>0) {
			
			resp.sendRedirect(req.getContextPath()+"/orders?method=findOrdersByUid");
			
		}else {
			
			throw new RuntimeException("更新订单失败");
		}
		
	}

	/*
	 * 查询用户所属的订单
	 */
	private void findOrdersByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		
		//调用业务
		List<Orders> list = service.findOrdersByUid(u.getId());
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/orders_history.jsp").forward(req, resp);
	}

	/*
	 * 创建订单
	 */
	private void createOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//组装订单
		Orders orders = new Orders();
		String count_number = req.getParameter("count");
		
		//订单商品总数量
		orders.setCount_number(Integer.parseInt(count_number));
		
		//日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = format.format(new Date());
		//下订单日期
		orders.setCreate_time(create_date);
		
		User u = (User) req.getSession().getAttribute("user");
		
		//订单名称
		orders.setOrders_name(u.getName()+"的订单");
		
		//订单编号
		String order_number = UUID.randomUUID().toString().replace("-","");
		orders.setOrders_number(order_number);
		
		//订单支付状态
		orders.setState(0);//未支付
		
		//订单总金额
		String sum_price = req.getParameter("totalPrice");
		orders.setSum_price(Double.parseDouble(sum_price));
		
		//下单用户的id
		orders.setUid(u.getId());
		
		//调用保存业务
		int row = service.createOrders(orders);
		
		//调用结果
		if(row>0) {
			//添加成功 清空购物车 转到订单详情
			req.getSession().removeAttribute("cart");
			resp.sendRedirect(req.getContextPath()+"/orders?method=findOrdersByUid");
		}else {
			throw new RuntimeException("创建订单失败");
		}
	}

}
