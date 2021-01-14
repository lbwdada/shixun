<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>购物车</title>
    <link href="css/index.css"  rel="stylesheet"  rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
</script>
   <script src="js/jquery.1.11.1.min.js"></script>
</head>
<body>
    <div class="order_head">
        <div class="head_background">
            <div class="head_box">
                <a href="index.html" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
                <h1 class="head_h1">历史订单</h1>
                <div class="head_right">
                    <span class="head_right_in">${user.phone_number }</span>
                    <span class="head_right_in">|</span>
                    <a href="${pageContext.request.contextPath }/index.jsp" class="head_right_in">继续购物</a>
                </div>

            </div>
        </div>
    </div>
    <div class="trolley_background" >
    	 <form action="${pageContext.request.contextPath }/pay?method=alipay" method="post">
    	<center>
    
    		<table border="1" cellspacing="0" style="width: 100%;background-color: #FFF" >
	           	<tr style="height: 80px;">
	           		<th width="220px">订单号</th>
	           		<th>订单名称</th>
	           		<th>商品总数量</th>
	           		<th>总价</th>
	           		<th>订单状态</th>
	           		<th width="220px">创建时间</th>
	           		<th>操作</th>
	           	</tr>
	         <c:if test="${list!=null }">
				 	<c:forEach items="${list }" var="order">
	         	
	         			<tr align="center">
	         				<td>${order.orders_number }</td>
	         				<td>${order.orders_name }</td>
	         				<td>${order.count_number }</td>
	         				<td>${order.sum_price }</td>
	         				<td>
	         					<c:if test="${order.state==0 }">未付款/待支付</c:if>
	         					<c:if test="${order.state==1 }">已付款/待发货 </c:if>
	         					<c:if test="${order.state==2 }">已发货/待收货 </c:if>
	         					<c:if test="${order.state==3 }">已收货/待评价</c:if>
	         				</td>
	         				<td>${order.create_time }</td>
	         				<td>
	         					<c:if test="${order.state==0 }"><a href="${pageContext.request.contextPath }/wappay/pay.jsp?WIDout_trade_no=${order.orders_number }&WIDsubject=${order.orders_name }&WIDtotal_amount=${order.sum_price}">去支付</a></c:if>
	         					<c:if test="${order.state!=0 }"><a href="#">查看订单</a></c:if>
	         				</td>
	         			</tr>
	         	
	         		</c:forEach>
				
				
				</c:if>	         	
           </table>
        
           	</center>
             </form>
    </div>
   <jsp:include page="footer.jsp"></jsp:include>

   
</body>
</html>