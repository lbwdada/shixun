package sdyu.sx.service;

import java.util.ArrayList;
import java.util.List;

import sdyu.sx.dao.CommodityDao;
import sdyu.sx.entity.Commodity;
import sdyu.sx.entity.PageBean;

/*
 * 商品的业务层
 */
public class CommodityService {
	
	private CommodityDao dao  = new CommodityDao();
	
	/*
	 * 商品的分页查询
	 */
	public PageBean<Commodity> findAllByPage(String currentPage, String pageSize) {
		//设置默认页码
		if(currentPage==null || currentPage.trim().equals("")) {
			
			currentPage="1";
		}
		
		if(pageSize==null || pageSize.trim().equals("")) {
			
			pageSize="3";
		}
		
		PageBean<Commodity> pb = new PageBean<Commodity>();
		
		int cpage = Integer.parseInt(currentPage);
		int size = Integer.parseInt(pageSize);
		
		
		pb.setCurrentPage(cpage);
		pb.setPageSize(size);
		 
		//通过用户想看的页码  每页显示条数查询用户想看的数据
		
		List<Commodity> list =dao.findCommodityByLimit(cpage,size);
		
		pb.setList(list);
		
		//查询商品的总记录
		
		int totalCount =dao.findCommodityByCount();
		
		pb.setTotalCount(totalCount);
		
		//计算总页数
		
		int totalPage =totalCount%size==0?totalCount/size:totalCount/size+1;
		
		pb.setTotalPage(totalPage);
		
		
		
		return pb;
	}
	/*
	 * 组合条件查询商品
	 */
	public List<Commodity> findBySelect(String name, String state, String strat_date, String end_date) {
		//初始化sql
	    StringBuilder sb = new StringBuilder("select * from commodity where 1=1   ");
		
	    //创建一个集合存储数据
		
	    ArrayList<Object> list = new ArrayList<Object>();
	    
	    if(name!=null && !name.trim().equals("")) {
	    	
	    	sb.append("   and name like ?   ");
	    	list.add("%"+name+"%");
	    }
	    
	    if(state!=null && !state.trim().equals("")) {
	    	
	    	sb.append("   and state =?   ");
	    	
	    	list.add(state);
	    }
	    
	    if((strat_date!=null && !strat_date.trim().equals(""))  && (end_date!=null && !end_date.trim().equals(""))) {
	    	
	    	sb.append("   and product_date between ? and ?  ");
	    	
	    	list.add(strat_date);
	    	
	    	list.add(end_date);
	    }
	    
	    
		
		
		return dao.findBySelect(sb.toString(),list.toArray());
	}
	/*
	 * 分页+组合条件查询
	 */
	public PageBean<Commodity> findSelectByPage(String name, String state, String strat_date, String end_date,
			String currentPage, String pageSize) {
		//定义初始化sql
		StringBuilder sb =  new StringBuilder("select * from commodity where 1=1   ");
		
		
		//定义集合存储数据
		ArrayList<Object> list = new ArrayList<Object>();
		
		if(name!=null && !name.trim().equals("")) {
			
			sb.append("   and name like  ?   ");
			
			list.add("%"+name+"%");
		}
		
		
		//-1
		if(state!=null && !state.equals("") && !state.equals("-1")) {
			
			sb.append("    and state=?   ");
			
			list.add(state);
		}
		
		if((strat_date!=null && !strat_date.equals("")) && (end_date!=null && !end_date.equals("")) ) {
			
			sb.append("   and product_date between ? and ?   ");
			list.add(strat_date);
			
			list.add(end_date);
		}
		
		if(currentPage==null || currentPage.trim().equals("")) {
			
			currentPage="1";
		}
		if(pageSize==null || pageSize.trim().equals("")) {
			
			pageSize="3";
		}
		
		int cpage = Integer.parseInt(currentPage);
		int size = Integer.parseInt(pageSize);
		
		PageBean<Commodity> pb = new PageBean<Commodity>();
		
		pb.setCurrentPage(cpage);
		
		pb.setPageSize(size);
		
		
		//查询符合条件数据的 总记录数
		
		List<Commodity> listCount = dao.findBySelect(sb.toString(), list.toArray());
				
		int totalCount = listCount.size();
		
		pb.setTotalCount(totalCount);
		
		//根据用户想看的页面 以及每页显示条数 查询 符合条件的数据
		
		List<Commodity> lis= dao.findBySelectByLimit(sb,list,cpage,size);
		
		pb.setList(lis);
		
		//总页数
		
		int totalPage =totalCount%size==0?totalCount/size:totalCount/size+1;
		
		pb.setTotalPage(totalPage);
		
		return pb;
	}

	/*
	 * 添加商品业务
	 */
	public int addCommodity(Commodity commodity) {
		return dao.addCommodity(commodity);
	}

	/*
	 * 根据id查找商品
	 */
	public Commodity findCommodityByCid(String cid) {
		return dao.findCommodityByCid(cid);
	}

	/*
	 * 更新商品信息
	 */
	public int updateCommodity(Commodity commodity) {
		return dao.updateCommodity(commodity);
	}

	/*
	 * （首页）根据商品状态码和条数查询商品信息业务
	 */
	public List<Commodity> getCommodityByState(String state, String count) {
		return dao.getCommodityByState(state,count);
	}

	/*
	 * (首页)根据商品分类和条数查询商品信息业务
	 */
	public List<Commodity> getCommodityByGid(String gid, String count) {
		return dao.getCommodityByGid(gid,count);
	}

}