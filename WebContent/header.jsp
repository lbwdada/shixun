<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="box">
      <div class="inner whiteGL">
          <div class="left">
              <a class="mix" href="">计科八仙女学习专用</a>
          </div>
          <c:if test="${user==null }">
              <div class="right">
	              <a class="mix" href="login.jsp">登录</a>
	              <a href="register.jsp">注册</a>
	              <a class="max"  href="">消息通知</a>
	              <a href="javascript:void(alert('请登录'))"><img src="img/cart.jpg" alt=""></a>
         		</div>
          </c:if>
          
          <c:if test="${user!=null }">
              <div class="right">
	              <a class="mix" href="register.html">欢迎您:${user.name}</a>
	              <a class="max"  href="">消息通知</a>
	              <a href="trolley.jsp"><img src="img/cart.jpg" alt=""></a>
         		</div>
          </c:if>
      </div>
  </div>
  <div class="logo">
      <div class="logo_left">
          <div>
              <a href="javascript:void(0);" title="小米官网"><img class="xiaomi" src="img/zzx_tou.png"></a>
          </div>
      </div>
      <ul class="logo_center orangeGL">
          <a href="">首页</a>
          <a href="">机构</a>
          <a href="">新闻动态</a>
          <a href="">服务</a>
          <a href="">品牌</a>
          <a href="">视频</a>
          <a href="">合作</a>
      </ul>
      <formv class="logo_right">
         <div class="logo_input"><input type="text">
             <div class="logo_input_div">
                 <a class="logo_input_a" href="">保健品 新品</a>
                 <a class="logo_input_a" href="">大米</a>
             </div>


         </div>
          <a class="logo_right_a"><img src="img/find.jpg"></a>
          <!--<a href="">红米5新品</a>-->
          <!--<a href="">小米Noto 3</a>-->
      </formv>
  </div>
</body>
</html>