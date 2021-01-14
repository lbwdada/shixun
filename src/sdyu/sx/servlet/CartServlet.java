package sdyu.sx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sdyu.sx.entity.Cart;
import sdyu.sx.entity.CartItem;
import sdyu.sx.entity.Commodity;
import sdyu.sx.entity.User;
import sdyu.sx.service.CommodityService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	CommodityService service = new CommodityService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if(method.equals("addCart")) {
			addCart(req,resp);
		}else if(method.equals("removeCart")) {
			
			removeCart(req,resp);
			
		}else if(method.equals("clearCart")) {
			
			clearCart(req,resp);
		}
		
	}

	/*
	 * 清空购物项-
	 */
	private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//获取购物车
		Cart cart = getCart(req);
		
		//清空购物车
		cart.clearCart();
		//清空成功
		resp.getWriter().print(1);
		
	}
	/*
	 * 移除购物项
	 */
	private void removeCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
		//获取移除购物项的id
		String cid = req.getParameter("cid");
		//获取购物车
		Cart cart = getCart(req);
		
		//移除购物项
		cart.remove(cid);
		//移除成功
		resp.getWriter().print(1);
		
	}	
	/*
	 * 添加购物车
	 */
	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//判断用户是否登陆
		User u = (User) req.getSession().getAttribute("user");
		if(u==null) {
			//未登录
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		}
		//获取传递的参数
		String cid = req.getParameter("cid");
		String count = req.getParameter("count");
		//调用业务
		Commodity commodity = service.findCommodityByCid(cid);
		
		//组装购物项对象
		CartItem cartItem = new CartItem();
		cartItem.setCommodity(commodity);
		cartItem.setCount(Integer.parseInt(count));
		
		//获取购物车de方法
		Cart cart = getCart(req);
		cart.addCart(cartItem);
		
		//跳转到展示界面
		resp.sendRedirect(req.getContextPath()+"/trolley.jsp");
		
	}
	/*
	 * 获取购物车的方法
	 */
	private Cart getCart(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		//从session中获取购物车
		Cart cart  = (Cart) session.getAttribute("cart");
		
		
		if(cart==null) {
			//没有从session中获取
			//创建一个购物车
			cart = new Cart();
			//将购物车放入到session中
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}
	
}
