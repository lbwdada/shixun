package sdyu.sx.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sdyu.sx.entity.Commodity;

/*
 * 持久层
 */
public class CommodityDao {
	
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private QueryRunner qr = new QueryRunner(dataSource);
	
	
	/*
	 * 分页查询商品信息
	 */
	
	public List<Commodity> findCommodityByLimit(int cpage, int size) {
		//起始页计算
		int start =(cpage-1)*size;
		
		String sql="select * from commodity limit ?,?";
		
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),start,size);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	 * 查询商品总记录数
	 */
	public int findCommodityByCount() {
		
		String sql ="select  count(*) from  commodity";
		try {
			Long l=	(Long) qr.query(sql, new ScalarHandler());
			
			
			return l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	/*
	 * 根据条件查询数据
	 */
	public List<Commodity> findBySelect(String sql, Object[] array) {
		
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),array);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 组合条件加分页查询
	 */
	public List<Commodity> findBySelectByLimit(StringBuilder sb, ArrayList<Object> list, int cpage, int size) {
		
		
		//计算起始页
		
		int strat =(cpage-1)*size;
		
		sb.append("   limit ?,?  ");
		
		list.add(strat);
		
		list.add(size);
		
		String sql = sb.toString();
		
		//查询参数
		Object[] array = list.toArray();
		
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),array);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	 * 添加商品
	 */
	public int addCommodity(Commodity c) {
		String sql ="insert into commodity values(?,?,?,?,?,?,?,?,?,?,?,?)";
		/* `cid`,
		  `gid`,
		  `name`,
		  `color`,
		  `size`,
		  `price`,
		  `description`,
		  `full_description`,
		  `pic`,
		  `state`,
		  `version`,
		  `product_date` 
		  */
		try {
			return qr.update(sql, null,c.getGid(),c.getName(),c.getColor(),c.getSize(),c.getPrice(),c.getDescription(),c.getFull_description(),c.getPic(),c.getState(),c.getVersion(),c.getProduct_date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public Commodity findCommodityByCid(String cid) {
		String sql = "select * from commodity where cid=?";
		try {
			return qr.query(sql, new BeanHandler<Commodity>(Commodity.class),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 更新商品信息
	 */
	public int updateCommodity(Commodity c) {
		
		String sql ="update commodity set gid=?,name=?,color=?,size=?,price=?,description=?,full_description=?,pic=?,state=?,version=?,product_date=? where cid =?";
		
		
		try {
			return qr.update(sql,c.getGid(),c.getName(),c.getColor(),c.getSize(),c.getPrice(),c.getDescription(),c.getFull_description(),c.getPic(),c.getState(),c.getVersion(),c.getProduct_date(),c.getCid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	
	}

	/*
	 * （首页）根据商品状态码和条数查询商品信息
	 */
	public List<Commodity> getCommodityByState(String state, String count) {
		String sql = "select * from commodity where state=? limit 0,?";
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),state,Integer.parseInt(count));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (首页)根据商品分类和条数查询商品信息业务
	 */
	public List<Commodity> getCommodityByGid(String gid, String count) {
		String sql = "select * from commodity where gid=? limit 0,?";
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),gid,Integer.parseInt(count));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
