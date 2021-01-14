package sdyu.sx.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import sdyu.sx.entity.Category;

/*
 * 持久层
 */
public class CategoryDao {
	
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private QueryRunner qr = new QueryRunner(dataSource);
	/*
	 * 分页查询分类数据
	 */
	public List<Category> findCategoryByLimit(int cpage, int size) {
		String sql = "select * from category limit  ?,?";
		//（当前页-1）*每页显示条数 
		int strat = (cpage-1)*size;
				
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class),strat,size);
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
				
				return null;
	}
	/*
	 * 查询总条数
	 */
	public int findCategoryByCount() {
		String sql = "select count(*) from category";
		Long l;
		try {
			l = (Long) qr.query(sql, new ScalarHandler());
			return l.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	/*
	 * 添加分类
	 */
	public int addCategory(Category category) {
		String sql = "insert into category values(?,?,?,?,?,?)";
		try {
			return qr.update(sql, null,category.getName(),category.getState(),category.getOrder_number(),category.getDescription(),category.getCreate_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * 批量删除分类
	 */
	public int deleteAllSelected(String ids) {
		String sql = "delete from category where gid in("+ids+")";
		try {
			return qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/*
	 * 根据分类id查询分类信息
	 */
	public Category findCategoryByGid(String gid) {
		String sql ="select * from category where gid=?";
		try {
			return qr.query(sql, new BeanHandler<Category>(Category.class),gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 根据gid更新分类
	 */
	public int updateCategory(Category c) {
		String sql = "update category set name=?,state=?,order_number=?,description=?,create_time=? where gid=?";
		try {
			return qr.update(sql, c.getName(),c.getState(),c.getOrder_number(),c.getDescription(),c.getCreate_time(),c.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/*
	 * 查询所有分类信息
	 */
	public List<Category> findAllCategory() {
		String sql = "select * from category";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 获取首页分类信息
	 */
	public List<Category> getIndexCategory() {
		//获取所有已激活分类信息  按照排序字段  降序  取前10条
		String sql = "select * from category where state=1 order by order_number desc limit 0,10";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
