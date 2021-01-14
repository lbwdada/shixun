package sdyu.sx.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import sdyu.sx.entity.Category;
import sdyu.sx.entity.PageBean;
import sdyu.sx.service.CategoryService;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	
	CategoryService service = new CategoryService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		if(method.equals("findAllByPage")) {
			findAllByPage(req,resp);
		}else if(method.equals("addCategory")) {
			try {
				addCategory(req,resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(method.equals("deleteAllSelected")) {
			deleteAllSelected(req,resp);
		}else if(method.equals("findCategoryByGid")){
			findCategoryByGid(req,resp);
		}else if(method.equals("updateCategory")) {
			try {
				updateCategory(req,resp);
			} catch (IllegalAccessException | InvocationTargetException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 更新分类
	 */
	private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, InvocationTargetException, IOException {
		//接受表单信息
		Map<String, String[]> map = req.getParameterMap();
		//创建category对象
		Category category = new Category();
		//将表单信息映射到category类对象中
		BeanUtils.populate(category, map);
		
		//调用分类更新业务
		int row = service.updateCategory(category);
		if(row>0) {
			//更新成功
			resp.sendRedirect(req.getContextPath()+"/category?method=findAllByPage");
		}else {
			//更新失败
			throw new RuntimeException("更新失败");
		}
		
	}

	/*
	 * 根据分类id查询信息并回显
	 */
	private void findCategoryByGid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取id
		String gid = req.getParameter("gid");
		//调用业务查询所属分类
		Category c = service.findCategoryByGid(gid);
		req.setAttribute("category", c);
		
		//转发到修改页面
		
		req.getRequestDispatcher("/admin/category_update.jsp").forward(req, resp);
	}

	/*
	 * 批量删除
	 */
	private void deleteAllSelected(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取传入的ids
		String ids = req.getParameter("ids");
		//调用批量删除业务
		int row = service.deleteAllSelected(ids);
		//System.out.println(ids.length());
		//row>0但不等于ids 中包含的gid的个数情况（未完全删除）;
		if(row >0 ) {
			//删除成功从新请求分页查询
			resp.sendRedirect(req.getContextPath()+"/category?method=findAllByPage");
		}else {
			//删除失败
			throw new RuntimeException("批量删除分类失败");
		}
	}
	/*
	 * 添加分类
	 */
	private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//接受所有的表单信息以表单提交，那么request.getParameterMap()中将包含你表单里面所有input标签的数据，以其name为key，以其value为值
		Map<String,String[]> map = req.getParameterMap();
		//创建category对象
		Category category = new Category();
		//将获取到的map映射到Category实体对象中
		BeanUtils .populate(category, map);
		//调用添加业务
		int row = service.addCategory(category);
		//根据返回的行数响应数据
		if(row>0) {
			//添加成功
			resp.sendRedirect(req.getContextPath()+"/category?method=findAllByPage");
		}else {
			//添加失败
			throw new RuntimeException("添加分类失败");
		}
		
	}
	/*
	 * 分页查询分类
	 */
	private void findAllByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前页码
		String currentPage = req.getParameter("currentPage");
		//获取每页显示的条数
		String pageSize = req.getParameter("pageSize");
		//调用业务
		PageBean<Category> pb = service.findAllByPage(currentPage,pageSize);
		//响应数据
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/admin/category_list.jsp").forward(req, resp);
	}
	
}
