package sdyu.sx.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import sdyu.sx.entity.User;
import sdyu.sx.service.UserService;
import sdyu.sx.utils.sendsms;

@WebServlet("/user")
@MultipartConfig
public class userServlet extends HttpServlet {
	private UserService service = new UserService();
	
	/*
	 * 用户模块  
	 *       用户登录
	 * 	注册
	 * 	校验手机号码
	 * 	发短信
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String method = req.getParameter("method");
		
		if(method.equals("register")) {
			
			try {
				register(req,resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("login")) {
			
			login(req,resp);
		}else if(method.equals("testPhone")) {
			
			testPhone(req,resp);
		}else if(method.equals("sendMsg")) {
			sendMsg(req,resp);
		}else if(method.equals("testUsername")){
			testUsername(req, resp);
		}else if(method.equals("loginByusername")) {
			loginByusername(req,resp);
		}
	}
	
	/* 用户名登陆 */
	private void loginByusername(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String passwd = req.getParameter("password");
		User u = service.testUser(username,passwd);
		if(u!=null) {
			//登陆成功
			req.getSession().setAttribute("user", u);
			
			//重定向到首页面
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			
		}else {
			//用户名或密码错误
			req.setAttribute("msg", "用户名或密码错误!");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			
		}
	}

	/*
	 * 发短信
	 */
	private void sendMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//1、获取要发送短信的手机号码
		String phone = req.getParameter("phone");
		
		//2 发短信
		String str =sendsms.sendMsg(phone,req.getSession());
		
		
		if(str.equals("2")) {
			//短信发送成功
			resp.getWriter().print(1);
			
		}else {
			//短信发送失败
			resp.getWriter().print(0);
		}
		
		
	}

	private void testPhone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		1、接受参数
		String phone = req.getParameter("phone");
		
//	 	2、调用业务
		
		User u = service.testPhone(phone);
		
//      3、响应结果
		
		if(u!=null) {
			//手机号已注册
			resp.getWriter().print(1);
			
		}else {
			//手机号未注册
			resp.getWriter().print(0);
			
			
		}
		
	}
	
	private void testUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		1、接受参数
		String username = req.getParameter("username");
		
//	 	2、调用业务
		
		User u = service.testUsername(username);
		
//      3、响应结果
		
		if(u!=null) {
			//手机号已注册
			resp.getWriter().print(1);
			
		}else {
			//手机号未注册
			resp.getWriter().print(0);
			
			
		}
		
	}
	/* 登陆 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
				//获取用户输入的手机号
				String phone_number = req.getParameter("phone_number");
				//获取用户输入的验证码
				String input_code = req.getParameter("code");
				//获取session中存放的验证码
				Integer code = (Integer) req.getSession().getAttribute("code");
				if(input_code.equals(code+"")) {
					//登录成功
					User u =service.testPhone(phone_number);
					
					req.getSession().setAttribute("user", u);
					
					//重定向到首页面
					resp.sendRedirect(req.getContextPath()+"/index.jsp");
					
				}else {
					//登录失败
					
					req.setAttribute("msg", "验证码错误");
					
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
		
	}

	/*
	 * 注册
	 * 
	 */	
	private void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//接受表单中所有数据
				Map<String, String[]> map = req.getParameterMap();
				User user = new User();
				//使用BeanUtils将map中的数据映射到user中
				BeanUtils.populate(user, map);
				
				//处理文件上传
				//获取到上传的资源
				Part part = req.getPart("photo");
				
				//获取文件名
				String fileName = part.getSubmittedFileName();
				//重命名文件名 5807829f-3007-41af-bd2a-b968d40456ce李小璐.jpg
					
				fileName =UUID.randomUUID().toString()+fileName;
				//组装写入路径《这个路径指向服务器根目录下的项目文件夹中的picture而不是当前的eclipse项目文件夹如果移植需要对picture进行转移
				//String path7 = req.getSession().getServletContext().getRealPath("/");
		        //System.out.println("path7: "+path7);
				//拼装写入路径
				File f = new File("E:\\jsp_workspace\\shixun\\picture");
				//判断如果此文件夹不存在
				if(!f.exists()) {
					//创建一个文件夹
					f.mkdir();
				}
				
				String path =f+"/"+fileName;
					
				//获取图片的字节流对象
				InputStream fis = part.getInputStream();
				//创建一个数组 
				byte arr[] = new byte[1024*8];
				int len;//读取到的有效字节个数
					
				//创建文件字节输出流对象
					
				FileOutputStream fos = new FileOutputStream(path);
					
				while((len =fis.read(arr))!=-1) {
						
					fos.write(arr, 0, len);
						
				}
					
				//关闭资源
				fos.close();
				fis.close();
					
				//----------图片就上传到E:\jsp_workspace\shixun\picture下了
					
				//设置头像名称
				user.setPhoto(fileName);
			
				
				
				
				//调用业务保持用户信息
				int row =service.addUser(user);
				
				
				if(row>0) {
					//保持成功
					//重定向到登录页面
					resp.sendRedirect(req.getContextPath()+"/login.jsp");
					
					
				}else {
					throw new RuntimeException("注册用户失败");
				}
				
	}

}
