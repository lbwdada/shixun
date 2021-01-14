package sdyu.sx.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import sdyu.sx.entity.Category;
import sdyu.sx.entity.Commodity;
import sdyu.sx.entity.PageBean;
import sdyu.sx.service.CategoryService;
import sdyu.sx.service.CommodityService;

@WebServlet("/commodity")
@MultipartConfig
public class CommodityServlet extends HttpServlet{
	private CommodityService service = new CommodityService();
	private CategoryService cservice = new CategoryService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if(method.equals("findAllByPage")) {
			
			
			findAllByPage(req,resp);
			
		}else if(method.equals("findBySelect")) {
			
			findBySelect(req,resp);
			
		}else if(method.equals("findSelectByPage")) {
			
			findSelectByPage(req,resp);
			
		}else if(method.equals("findAllCategory")) {
			findAllCategory(req,resp);
		}else if(method.equals("addCommodity")) {
			try {
				addCommodity(req,resp);
			} catch (IllegalAccessException | InvocationTargetException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("findCommodityByCid")) {
			findCommodityByCid(req,resp);
		}else if(method.equals("updateCommodity")) {
			try {
				updateCommodity(req,resp);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("findCommodity")) {
			findCommodity(req,resp);
		}
	}

	/*
	 * （首页）根据商品id展示商品信息
	 */
	private void findCommodity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取商品id
		String cid = req.getParameter("cid");
		//调用业务
		Commodity commodity = service.findCommodityByCid(cid);
		//将此对象放入request域对象中转发到detail.jsp页面
		req.setAttribute("commodity", commodity);
		//转发
		req.getRequestDispatcher("/detail.jsp").forward(req, resp);
	}

	/*
	 * 更新商品信息
	 */
	private void updateCommodity(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, InvocationTargetException, IOException, ServletException {
		//获取表单提交的数据
		Map<String, String[]> map = req.getParameterMap();
		//创建commodity实体类
		Commodity commodity = new Commodity();
		//将表单中的数据映射到实体类中
		BeanUtils.populate(commodity, map);
		
		//文件处理
		//获取上传的资源
				Part part = req.getPart("pic");
				
				//获取到文件名
				String fileName = part.getSubmittedFileName();
				
				
				
				if(fileName==null || fileName.trim().equals("")) {
					//没有上传新图片
					
					//获取原文件名
					fileName = req.getParameter("pic1");
					
					
					
				}else {
					//上传了新图片
					//重命名文件名
					fileName=UUID.randomUUID().toString()+fileName;
					
					//重组路径
					File f = new File("E:\\jsp_workspace\\shixun\\picture");
					
					//如果此文件夹不存在
					if(!f.exists()) {
						//创建文件夹
						f.mkdir();
					}
					//D:\\xm_upload/2d370544-b521-4ac7-8607-fdfa16361959小浣熊.jpg
					String path =f+"/"+fileName;
					
					//获取文件流
					InputStream fis = part.getInputStream();
					
					byte arr[] = new byte[1024*8];
					
					int len;//读取到的有效字节个数
					
					//创建文件字节输出流对象
					
					FileOutputStream fos = new FileOutputStream(path);
					
					while((len=fis.read(arr))!=-1) {
						
						fos.write(arr, 0, len);
						
					}
					//关闭资源
					fos.close();
					
					fis.close();
					
//					--------文件上传完毕
				}
				//设置文件名
				commodity.setPic(fileName);
				
				int row =service.updateCommodity(commodity);
				
				if(row>0) {
					//更新成功
					
					//重定向到分页+组合条件查询
					resp.sendRedirect(req.getContextPath()+"/commodity?method=findSelectByPage");
				}else {
					
					//更新失败
					throw new RuntimeException("更新商品失败");
				}
	}

	/*
	 * 根据商品id查询商品信息
	 */
	private void findCommodityByCid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//根据商品id查询商品信息
		String cid = req.getParameter("cid");
		Commodity c = service.findCommodityByCid(cid);
		//查询所有的分类对象
		List<Category> list = cservice.findAllCategory();
		//将所查询到的信息保存到request对象中并转发
		req.setAttribute("commodity", c);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/commodity_update.jsp").forward(req, resp);
		
	}

	//添加商品
	private void addCommodity(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, InvocationTargetException, IOException, ServletException {
		//获取到所有表单信息
		Map<String, String[]> map = req.getParameterMap();
		//创建一个实体类
		Commodity commodity = new Commodity();
		//将map中的数据映射到commodity实体类中
		BeanUtils.populate(commodity, map);
		
		//处理文件的上传
		//获取上传的文件
		Part part = req.getPart("pic");
		//获取文件名
		String fileName = part.getSubmittedFileName();
		//给文件名前面加入随机序列号
		fileName = UUID.randomUUID().toString()+fileName;
		//组装写入路径《这个路径指向服务器根目录下的项目文件夹中的picture而不是当前的eclipse项目文件夹如果移植需要对picture进行转移
		//String path7 = req.getSession().getServletContext().getRealPath("/");
		File f = new File("E:\\jsp_workspace\\shixun\\picture");
		//如果文件夹不存在
		if(!f.exists()) {
			//创建文件夹
			f.mkdir();
		}
		String path =f+"/"+fileName;
		
		//获取文件流
		InputStream fis = part.getInputStream();
		
		byte arr[] = new byte[1024*8];
		
		int len;//读取到的有效字节个数
		
		//创建文件字节输出流对象
		
		FileOutputStream fos = new FileOutputStream(path);
		
		while((len=fis.read(arr))!=-1) {
			
			fos.write(arr,0,len);
			
		}
		
		//关闭资源
		fos.close();
		
		fis.close();
		//---------------------- 文件上传结束
		//设置文件名
		commodity.setPic(fileName);
		
		
		//调用保存业务
		
		int row =service.addCommodity(commodity);
		
		if(row>0) {
			
			//重定向到分页加组合条件方法
			
			resp.sendRedirect(req.getContextPath()+"/commodity?method=findSelectByPage");
			
			
		}else {
			
			throw new RuntimeException("保存商品信息失败");
			
		}
		
		
	}

	//查询所有商品分类信息并回显
	private void findAllCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取所有分类信息
		List<Category> list = cservice.findAllCategory();
		//将分类信息放入request域转发commodity_add.jsp
		req.setAttribute("list",list );
		req.getRequestDispatcher("/admin/commodity_add.jsp").forward(req, resp);
	}

	/*
	 * 组合条件查询加分页
	 */
	private void findSelectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1、获取查询参数
		String name = req.getParameter("name");
		String state = req.getParameter("state");
		String strat_date = req.getParameter("strat_date");
		String end_date = req.getParameter("end_date");
//  	2、获取想看的页面 每页显示条数
		//接受想看的页码
		String currentPage = req.getParameter("currentPage");
		//想看的条数
		String pageSize = req.getParameter("pageSize");
		
		PageBean<Commodity> pb=	service.findSelectByPage(name,state,strat_date,end_date,currentPage,pageSize);
		
		req.setAttribute("pb", pb);
		
		//转发到商品展示页面
		req.getRequestDispatcher("/admin/commodity_list.jsp").forward(req, resp);
		
		
	}
	private void findBySelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1、获取查询参数
		String name = req.getParameter("name");
		String state = req.getParameter("state");
		String strat_date = req.getParameter("strat_date");
		String end_date = req.getParameter("end_date");
		
//      2、调用查询业务
		List<Commodity> list =service.findBySelect(name,state,strat_date,end_date);
		
		
//	    3、响应数据
		PageBean<Commodity> pb = new PageBean<Commodity>();
		
		pb.setList(list);
		req.setAttribute("pb", pb);
		//转发到商品展示页面
		req.getRequestDispatcher("/admin/commodity_list.jsp").forward(req, resp);
		
	
	}
	/*
	 * 商品分页查询
	 */
	private void findAllByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//接受想看的页码
		String currentPage = req.getParameter("currentPage");
		//想看的条数
		String pageSize = req.getParameter("pageSize");
		
		
		PageBean<Commodity> pb =service.findAllByPage(currentPage,pageSize);
		
		req.setAttribute("pb", pb);
		
		//转发到商品展示页面
		req.getRequestDispatcher("/admin/commodity_list.jsp").forward(req, resp);
		
	}

}
