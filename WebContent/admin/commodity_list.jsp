<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>admin/js/My97DatePicker/WdatePicker.js"></script><script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});



</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">商品管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
   <div class="tools" align="center">
   <ul class="toolbar">
   <li style="cursor: pointer;" onclick="add_commodity()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加商品</li>
    	<li>
    	<form action="${pageContext.request.contextPath }/commodity?method=findSelectByPage" method="post" style="float:left;">
    	商品名称:<input name="name" value="${param.name }" type="text" class="dfinput" style="width: 150px;height:25px "/>
		商品状态:<select name="state"  class="dfinput" style="width:150px;height: 25px">
	    	 		<option value="-1" <c:if test="${param.state==-1 }">selected</c:if> >-请选择-</option>
	    	 		
	    	 		<option value="0"  <c:if test="${param.state==0 }">selected</c:if> >正常</option>
	    	 		<option value="1" <c:if test="${param.state==1 }">selected</c:if> >热门</option>
	    	 		<option value="2" <c:if test="${param.state==2 }">selected</c:if> >新品</option>
	    	 		<option value="3" <c:if test="${param.state==3 }">selected</c:if>>为你推荐</option>
	    	 		<option value="4" <c:if test="${param.state==4 }">selected</c:if>>小米明星单品</option>
	    	 </select>
	 	 开始日期:<input class="Wdate" value="${param.strat_date }" style="width: 200px;height: 25px;line-height: 32px;" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="strat_date" type="text" class="dfinput" />	 
	 	 结束日期:<input class="Wdate"  value="${param.end_date }" style="width: 200px;height: 25px;line-height: 32px;" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="end_date" type="text" class="dfinput"  />	 
	 	 <input  type="submit" value="查询"  style="width: 100px;height:30px;background:rgb(0, 87, 86);"/>
	 	</form> 
	 	</li>
	 	  </ul>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>序号<i class="sort"><img src="admin/images/px.gif" /></i></th>
        <th>商品类别</th>
        <th>商品名称</th>
        <th>商品颜色</th>
        <th>商品价格</th>
        <th width="10%">简介</th>
        <th width="20%">详情</th>
       	<th>商品展示图</th>
       	<th>是否热推</th>
       	<th>型号</th>
       	<th>生产日期</th>
       	<th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${pb.list!=null }">
        		<c:forEach items="${pb.list }" var="c" varStatus="p">
        			<tr>
        				<td><input name="" type="checkbox" value="" /></td>
        				<td>${p.count }</td>
        				<td>${c.gid }</td>
        				<td>${fn:substring(c.name,0,10) }</td>
        				<td>${c.color }</td>
        				<td>${c.price }</td>
        				<td>${fn:substring(c.description, 0, 6)}</td>
        				<td>${fn:substring(c.full_description, 0, 12)}</td>
        				<td>
        			<img src="http://localhost:8080/picture/${c.pic }" width="100px" height="100px"></img>
        				</td>
        				<td>
	        					<c:if test="${c.state==0 }">正常</c:if>
	        					<c:if test="${c.state==1 }">热门</c:if>
	        					<c:if test="${c.state==2 }">新品</c:if>
	        					<c:if test="${c.state==3 }">为你推荐</c:if>
	        					<c:if test="${c.state==4 }">明星单品</c:if>
        				</td>
        				<td>${c.version }</td>
        				<td>${c.product_date }</td>
        				<td><a href="commodity?method=findCommodityByCid&cid=${c.cid }">修改</a>&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
        			</tr>
        		</c:forEach>
        	</c:if>
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${pb.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${pb.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
	         <li class="paginItem"><a href="${pageContext.request.contextPath }/commodity?method=findSelectByPage&currentPage=1&pageSize=3&name=${param.name }&state=${param.state}&strat_date=${param.strat_date}&end_date=${param.end_date}">首页</a></li>
	         
	         <c:if test="${pb.currentPage==1 }">
	         
	         	         <li class="paginItem"><a href="#">上一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage!=1 }">
	         
	         	       	  <li class="paginItem"><a href="${pageContext.request.contextPath }/commodity?method=findSelectByPage&currentPage=${pb.currentPage-1 }&pageSize=3&name=${param.name }&state=${param.state}&strat_date=${param.strat_date}&end_date=${param.end_date}">上一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage==pb.totalPage }">
	         
	         	         <li class="paginItem"><a href="#">下一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage!=pb.totalPage }">
	         
	         	    <li class="paginItem"><a href="${pageContext.request.contextPath }/commodity?method=findSelectByPage&currentPage=${pb.currentPage+1 }&pageSize=3&name=${param.name }&state=${param.state}&strat_date=${param.strat_date}&end_date=${param.end_date}">下一页</a></li>

	         
	         </c:if>
	         
	         <li class="paginItem"><a href="${pageContext.request.contextPath }/commodity?method=findSelectByPage&currentPage=${pb.totalPage }&pageSize=3&name=${param.name }&state=${param.state}&strat_date=${param.strat_date}&end_date=${param.end_date}">尾页</a></li>
	         
	        
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	//添加商品
	function add_commodity(){
		//调用servlet中查询商品分类方法
		location.href="commodity?method=findAllCategory";
	}
	</script>
</body>
</html>
