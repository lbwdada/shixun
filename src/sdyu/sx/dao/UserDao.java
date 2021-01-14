package sdyu.sx.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import sdyu.sx.entity.User;

/*
 * 持久层
 *   负责对user表的CRUD
 */
public class UserDao {
	
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	
	private QueryRunner qr = new QueryRunner(dataSource);
			
	
	public User findUserByPhone(String phone) {
		
		
		String sql ="select  * from user where phone_number = ?"; 
		
		
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
public User findUser(String username, String passwd) {
		
		
		String sql ="select  * from user where username = ? && password=?"; 
		
		
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),username,passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	public int saveUser(User u) {
		
		String sql ="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		 /* `id`,
		  `name`,
		  `sex`,
		  `phone_number`,
		  `area`,
		  `manager`,
		  `username`,
		  `password`,
		  `photo`,
		  `create_time`
		*/
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date = format.format(new Date());
		
		
		Object arr[] = {null,u.getName(),u.getSex(),u.getPhone_number(),u.getArea(),u.getManager(),u.getUsername(),u.getPassword(),u.getPhoto(),date};
		
		
		try {
			return qr.update(sql,arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public User findUserByUsername(String username) {
		String sql = "select * from user where username=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
