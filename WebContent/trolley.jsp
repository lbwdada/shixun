<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/img_pro/";
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="${pageContext.request.contextPath }/js/jquery.1.11.1.min.js"></script>
    
<script type="text/javascript">
	
	
</script>
</head>
<body>
    <div class="order_head">
        <div class="head_background">
            <div class="head_box">
                <a href="index.html" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
                <h1 class="head_h1">我的购物车</h1>
                <div class="head_right">
                    <span class="head_right_in"> ${user.name }/${user.username } </span>
                    <span class="head_right_in">|</span>
                    <a href="" class="head_right_in">我的订单</a>
                </div>

            </div>
        </div>
    </div>
    <div class="trolley_background">
        <div class="trolley_background_in">
            <div class="tro_tab_h">
                <div class="col tro_tab_check">
                    <h1 class="tro_tab_check_p">√</h1>
                    <span class="tro_tab_check_sp">全选</span>

                </div>
                <div class="col tro_tab_img">

                </div>
                <p class="col tro_tab_name">商品名称</p>
                <div class="col tro_tab_price">单价</div>
                <div class="col tro_tab_num">数量</div>
                <div class="col tro_tab_total">小计</div>
                <div class="col tro_tab_action">操作</div>

            </div>
            
            
            <c:if test="${cart.map!=null }">
            
            	<c:forEach items="${cart.map }" var="item">
            	  <div class="tro_tab_h1">
		                <div class="col tro_tab_check">
		                    <h1 class="tro_tab_check_p" style="background-color: #fff">
								<input type="checkbox" name="ids">
							</h1>
		                    <span class="tro_tab_check_sp"></span>
		
		                </div>
		                <div class="col tro_tab_img">
		                    <img src="http://localhost:8080/pictrue/${item.commodity.pic }" alt="">

	            		    </div>
	                <div class="col tro_tab_name">
		                <!--<h2 tro_tab_name_h2>小米电视4A 32英寸 黑色 32英寸</h2>-->
		               <li class="tro_tab_name_li1" style="font-size: 16px;">${item.commodity.name }&nbsp;${item.commodity.color }</li>
	                </div>
	                <div class="col tro_tab_price">
	                    <span  id="price">${item.commodity.price }</span><span>元</span>
	                </div>
	                <div class="col tro_tab_num">
	  
	                    <input type="text" value="${item.count }"  name="num" id="num">
	                  
	                </div>
	                <div class="col tro_tab_total "><span class="tro_tab_total_value" id="prices" >${item.subPrice }</span>元
	
	                </div>
	                <div class="col tro_tab_action" style="cursor: pointer;width: 40px;height: 40px;" onclick="delGoods(${item.commodity.cid})">删除</div>
	            </div>
            	
            	
            	</c:forEach>
            
            
            
            </c:if>
            
          <script type="text/javascript">
     			//移除购物项
	          	function delGoods(cid){
	          		
	          		
	          		$.get("cart?method=removeCart",{"cid":cid},function(d){
	          			
	          			
	          			if(d==1){
	          				//移除成功
	          				//重新加载文档
	          				location.reload();
	          			}
	          			
	          			
	          			
	          		})
	          		
	          		
	          	}
          	
	          	//清空购物项
	          	function clearCart(){
	          		
	          		$.get("cart?method=clearCart",{},function(d){
	          			
	          			
	          			if(d==1){
	          				//清空购物项成功
	          				
	          				//重新加载文档
	          				location.reload();
	          			}
	          			
	          		})
	          		
	          	}
	          	
	          	
	          	function pay(){
	          		//获取总金额 
	          		
	          		var totalPrice ="${cart.totalPrice}"
	          		
	          		//获取购物项中数量的累加和
	          		var inputs =$("input[name='num']");
	          		
	          		if($("span.nimailema").val()==0){
	          			alert("购物车为空")
	          		}
	          		else{
	          		var count=0;
	          		
	          		for(var i =0;i<inputs.length;i++){
	          			
	          			count+=parseInt(inputs[i].value);
	          		}
	          		
	          		//发送请求封装orders
	          		location.href="orders?method=createOrders&totalPrice="+totalPrice+"&count="+count;
	          		
	          		
	          	}
	          	}
	          	
          
          </script>
	           
            
        </div>
        
        <div class="tro_close_bot ">
            <!--<p class="tro_bot_ppp">+</p>-->
            <p class="tro_close_p "> <a href="${pageContext.request.contextPath }/index.jsp">继续购物 </a>  | 共<span class="nimailema">${fn:length(cart.map)}</span>种商品</p>
           
            <p class="tro_close_p_c">合计:<span id="close">${cart.totalPrice }</span>元</p>
            
            <p class="tro_close_p_r" style="cursor: pointer;" onclick="clearCart()">清空购物车</p>
            <p class="tro_close_p_r" style="cursor: pointer;" onclick="pay()">去结算</p>
        </div>

    </div>


   <jsp:include page="footer.jsp"></jsp:include>

   
</body>
</html>