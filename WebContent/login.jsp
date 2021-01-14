<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery-1.8.3.min.js"></script>
    <style>

    </style>
<script type="text/javascript">
//读秒的方法
var iTime = 59;
var Account;
function RemainTime(){
	document.getElementById('zphone').disabled = true;
	var iSecond,sSecond="",sTime="";
	if (iTime >= 0){
		iSecond = parseInt(iTime%60);
		iMinute = parseInt(iTime/60)
		if (iSecond >= 0){
			if(iMinute>0){
				sSecond = iMinute + "分" + iSecond + "秒";
			}else{
				sSecond = iSecond + "秒";
			}
		}
		sTime=sSecond;
		if(iTime==0){
			clearTimeout(Account);
			sTime='获取手机验证码';
			iTime = 59;
			document.getElementById('zphone').disabled = false;
		}else{
			Account = setTimeout("RemainTime()",1000);
			iTime=iTime-1;
		}
	}else{
		sTime='没有倒计时';
	}
	document.getElementById('zphone').value = sTime;
}
</script>
</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <a href="index.html"><img src="img/zzx_tou.png" alt=""></a>
    <div class="register_head_right">
        <p class="register_head_right_p1">康养小镇</p>
        <p class="register_head_right_p2">让每个人都享受生活乐趣</p>
    </div>

</div>

<div class="register">
    <div class="register_boby">
    <!-- 验证码登录 -->
        <div class="register_boby_min" id="phoneLogin" style="display: block">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">手机验证码登录 </span>
                </div>
            </div>
            <form id="f3" name="ff" action="user?method=login" method="post">
            
            <!-- fs区分的方法 -->
            <input name="fs" value="checkCode" type="hidden">
            
            <div class="register_boby_no2">
            	<span id="msg" style="color: red;font-size: 12px;margin-left: 20px;">${msg}</span>
                <input id="phone_number" name="phone_number" type="text" placeholder="手机号码" onblur="func1(this.value,'手机号不能为空!')" onfocus="func2()">
                
                <input name="code"  type="password" placeholder="手机校验码" style="width: 200px; margin-left: 15px;float: left;">
                <!-- 新增加 -->
                <input id="zphone" type="button" value=" 获取手机验证码 " style="width: 138px;float: left;height: 53px;margin-left: 5px;"  onClick="sendMsg()"> 
                <div style="clear: both;">
                
                <div class="register_boby_no2_div">
                    <span id="sub">登录</span>
                </div>
            </div>
            </div>
            
            </form>
            
            <div class="register_boby_no3">
                <a href="javascript:void(0);" style="color: #ff6700" onclick="changeLoginWay('username')">账号密码登录</a>
                <sapn class="register_boby_no3_span">
                    <a href="register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="avascript:void(0);">忘记密码?</a>
                </sapn>

            </div>
            <div class="register_boby_no4">
                <img src="img/register02.jpg" alt="">
            </div>

        </div>
        <!-- 验证码登录结束 -->
        <!-- 用户名登陆 -->
          <div class="register_boby_min" id="usernameLogin" style="display:none">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">用户名登录 </span>
                </div>
            </div>
            <form id="f4" name="ff1" action="user?method=loginByusername" method="post">
            <div class="register_boby_no2">
            	<span id="msg1" style="color: red;font-size: 12px;margin-left: 20px;"></span>
                <input id="username" name="username" type="text" placeholder="用户名" onblur="func1_username(this.value,'用户名不能为空!')" onfocus="func2()">
                
                <input name="password"  type="password" placeholder="请输入密码" onblur="func1_username(this.value,'密码不能为空!')">
                
                
                <div style="clear: both;">
                
                <div class="register_boby_no2_div">
                    <span id="sub1">登录</span>
                </div>
            </div>
            </div>
            
            </form>
            
            <div class="register_boby_no3">
                <a href="javascript:void(0);" style="color: #ff6700" onclick="changeLoginWay('phone')">手机号码登录</a>
                <sapn class="register_boby_no3_span">
                    <a href="register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="avascript:void(0);">忘记密码?</a>
                </sapn>

            </div>
            <div class="register_boby_no4">
                <img src="img/register02.jpg" alt="">
            </div>

        </div>
        <!-- 用户名登陆结束 -->
    </div>
</div>
<div class="register_foot">
    <img  src="img/register03.jpg" alt="">
</div>


</body>
<script type="text/javascript">
//标识手机号是否已注册   false 代表未注册或者不合法..   true就代表手机号已注册
    var flag =false;
	function func1(value,info){
	if(value.trim()==""){
				
				$("#msg").html(info);
				
			}else{
				//不为空
				
				if(info=='手机号不能为空!'){
					
					//对手机号进行合法性校验
					
					if(!(/^1[3456789]\d{9}$/.test(value))){ 
						
						$("#msg").html("手机号码有误，请重填");
				        return false; 
				    }else{
				    	
				    	//合法   查询手机号是否是已注册
				    	
				    	$.get("user?method=testPhone",{"phone":value},function(d){
				    		
				    		
				    		if(d==1){
				    			//已注册 
				    			$("#msg").html("<font color='green'>√</font>");
				    			flag=true;
				    			
				    		}else{
				    			//手机号未注册
				    			
				    			$("#msg").html("手机号未注册点击<a href='register.jsp'>注册</a>");
				    		}
				    		
				    		
				    		
				    	},"text")
				    	
				    	
				    }
					
				}
				
			}
			
	}
	
	function func2(){
		$("#msg").html("");
	}
	
	
	function sendMsg(){
		//发送验证码的前提需要 手机号合法并且已注册  
		//√ 说明是已注册的会员
		
		 var a =$("#msg").text();
		
		if(a=='√'){
			
			//1、发送验证码
			
			$.get("user?method=sendMsg",{"phone":$("#phone_number").val()},function(d){
				
				
				
				if(d==1){
					//短信发送成功
					
					$("#msg").html("<font color='green'>短信发送成功</font>");
				}else{
					//短信发送失败
					$("#msg").html("<font color='red'>短信发送失败</font>");
					
				}
				
				
			})
			
			//2、读秒
			RemainTime();
			
		}
	}
	
	//登录
	$("#sub").click(function(){
		
			var phone_number =ff.phone_number.value;
			
			var code =ff.code.value;
			//不仅要满足用户名或者验证码不为空  还要满足 手机号合法 并且是已注册
			
			if(phone_number.trim()=="" || code.trim()=="" || flag==false){
				
				$("#msg").html("请检查表单项");
				
			}else{
				
				$("#f3").submit();
				
			}
			
			
	})
	
	
	//切换登陆方式
	function changeLoginWay(way){
		if(way == "username"){
			$("#phoneLogin").css('display','none');
			$("#usernameLogin").css('display','block');
		}
		else if(way == "phone"){
			$("#usernameLogin").css('display','none');
			$("#phoneLogin").css('display','block');
		}
	}
	
	function func1_username(value,info){
		
		if(value.trim()==""){
			$("#msg1").html(info);
		}
	}
	
	$("#sub1").click(function(){
		var username = ff1.username.value;
		var password = ff1.password.value;
		if(username=="" || password==""){
			$("#msg1").html("请检查表单项!")
		}else{
			$("#f4").submit();
		}
	})
</script>
</html>