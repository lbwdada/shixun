<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

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
    <li><a href="#">分类管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo"></div>
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        
        <li class="click"><span><img src="<%=basePath %>admin/images/t02.png" /></span>修改</li>
        <li><span><img src="<%=basePath %>admin/images/t04.png" /></span>统计</li>
        <li style="cursor: pointer;" onclick="add_category()"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加类别</li>
        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="all" type="checkbox" value="" /></th>
        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
        <th>类别名称</th>
        <th>启用状态</th>
        <th>排序序号</th>
        <th>创建时间</th>
        <th>描述</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        	<c:if test="${pb.list!=null }">
        		
        		<c:forEach items="${pb.list }" var="c" varStatus="p">
        		
        			<tr>
        				<td><input name="checkbox" type="checkbox" value="${c.gid }" /></td>
        				<td>${p.count }</td>
        				<td>${c.name }</td>
        				<td>${c.state }</td>
        				<td>${c.order_number }</td>
        				<td>${c.create_time }</td>
        				<td>${c.description }</td>
        				<td><a href="#">修改</a>&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
        			</tr>
        		
        		
        		
        		</c:forEach>
        	
        	</c:if>
        
        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${pb.totalCount} </i>条记录，当前显示第&nbsp;<i class="blue">${pb.currentPage} &nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="${pageContext.request.contextPath }/category?method=findAllByPage&currentPage=1&pageSize=3">首页</a></li>
			 <c:if test="${pb.currentPage!=1 }">
	         
	        			 <li class="paginItem"><a href="${pageContext.request.contextPath }/category?method=findAllByPage&currentPage=${pb.currentPage-1 }&pageSize=3">上一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage==1 }">
	         
	         	         <li class="paginItem"><a href="#">上一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage==pb.totalPage }">
	         
	         	         <li class="paginItem"><a href="#">下一页</a></li>
	         
	         </c:if>
	         <c:if test="${pb.currentPage!=pb.totalPage }">
	         
	         	     	 <li class="paginItem"><a href="${pageContext.request.contextPath }/category?method=findAllByPage&currentPage=${pb.currentPage+1 }&pageSize=3">下一页</a></li>
	         
	         </c:if>
	         
	         <li class="paginItem"><a href="${pageContext.request.contextPath }/category?method=findAllByPage&currentPage=${pb.totalPage }&pageSize=3">尾页</a></li>
	         
	         
	        
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" onclick="sure()" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$("input[name='all']").click(function(){
		$("input[name='checkbox']").prop("checked",this.checked)
	})
	//添加分类
	function add_category(){
		location.href="admin/category_add.jsp";
	}
	//批量删除
	function batchDelete(){
		//获取当前页面中所有选中的复选框
		var inputs = $(":checked");
		//遍历所有的复选框获取每一个复选框的value
		console.log(inputs);
		var ids = "";
		for(var i=0;i<inputs.length;i++){
			//当复选框中的id不为空的时候
			if(inputs[i].value!=""){
				//判断当前是不是最后一个索引
				if(i==inputs.length-1){
					//累加最后一个索引所在的id
					ids+=inputs[i].value;
				}else{
					//累加id
					ids+=inputs[i].value+",";
				}
			}
		}
		//将ids发送到后台
		location.href="/shixun_demo/category?method=deleteAllSelected&ids="+ids;
	}
	/* 
	修改分类信息
	 */
	function sure(){
		//获取被选中的复选框
		var inputs = $(":checked");
		console.log(inputs);
		if(inputs.length!=0){
			//获取被选中复选框的最后一个
			var ch = inputs[inputs.length-1];
			//将获取的gid发送到后台
			location.href="/shixun_demo/category?method=findCategoryByGid&gid="+ch.value;
		}else{
			alert("你倒是选个啥啊??")
		}
		
	}
	</script>
</body>
</html>
