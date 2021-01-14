package sdyu.sx.service;

import sdyu.sx.dao.UserDao;
import sdyu.sx.entity.User;

/*
 * 用户模块的业务层
 */
public class UserService {
	
	
	private UserDao dao = new UserDao();
	
	/*
	 * 校验手机号是否合法业务
	 */
	public User testPhone(String phone) {
		
		
		return dao.findUserByPhone(phone);
	}
	/*
	 * 校验用户名是否合法业务
	 */
	public User testUsername(String username) {
		
		
		return dao.findUserByUsername(username);
	}
	
	/*
	 * 保存用户业务
	 */
	public int addUser(User user) {
		
		
		return dao.saveUser(user);
	}

	/*
	 * 用户名密码查询
	 */
	public User testUser(String username, String passwd) {
		return dao.findUser(username,passwd);
	}

}
