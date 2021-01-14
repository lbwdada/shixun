package sdyu.sx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sdyu.sx.entity.Category;
import sdyu.sx.entity.Commodity;
import sdyu.sx.service.CategoryService;
import sdyu.sx.service.CommodityService;

@WebServlet("/index")
public class indexServlet extends HttpServlet{
	
	private CategoryService service = new CategoryService();
	
	
	private CommodityService cservice = new CommodityService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//修改response响应对象缓冲器编码类型
		resp.setContentType("text/html;charset=utf-8");
		String method = req.getParameter("method");
		if(method.equals("getIndexCategory")) {
			getIndexCategory(req,resp);
		}else if(method.equals("getCommodityByState")) {
			getCommodityByState(req,resp);
		}else if(method.equals("getCommodityByGid")) {
			getCommodityByGid(req,resp);
		}
	}

	/*
	 * 根据商品分类和所要信息条数查询商品
	 */
	private void getCommodityByGid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String gid = req.getParameter("gid");
		String count = req.getParameter("count");
		//根据商品所属分类和条数查询所属商品
		List<Commodity> list = cservice.getCommodityByGid(gid,count);
		//创建json转换对象
		ObjectMapper mapper = new ObjectMapper();
				
		//将集合转换为json字符串
		String asString = mapper.writeValueAsString(list);
				
		//将json相应回浏览器
				
		resp.getWriter().print(asString);
	}

	/*
	 * 根据商品的状态码和条数  查询商品
	 */
	private void getCommodityByState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String state = req.getParameter("state");
		String count = req.getParameter("count");
		//根据状态码查询商品 state是商品状态码 count是想要查询的数量
		List<Commodity> list = cservice.getCommodityByState(state,count);
		//创建json转换对象
		ObjectMapper mapper = new ObjectMapper();
		//将集合转化为json字符串
		String asString = mapper.writeValueAsString(list);
		//将数据响应回浏览器
		resp.getWriter().print(asString);
	}

	/*
	 * 获取分类
	 */
	private void getIndexCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//调用业务获取分类数据
		List<Category> list=service.getIndexCategory();
		//创建json转换对象
		ObjectMapper mapper = new ObjectMapper();
		//将集合转换为json字符串
		String asString = mapper.writeValueAsString(list);
		//将json响应回浏览器
		resp.getWriter().print(asString);
		
	}

}
