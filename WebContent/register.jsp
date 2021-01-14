<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
</head>
<body>
<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="index.html"><img src="img/zzx_tou.png" alt=""></a>
        </div>
        <div class="sign_background_no2" id="d1">注册帐号</div>
        <div class="sign_background_no3">
               
            <div class="sign_background_no5">
             	
             	<form id="ff" name="f1" action="user?method=register" method="post" enctype="multipart/form-data">
             	
             		<table style="width: 500px;" border="0" cellspacing="0">
             			<tr>
             				<td width="25%" class="_left">姓名：</td>
             				<td>
             				<input type="text" name="name" onblur="func1(this.value,'姓名','name')" onfocus="func2('name')">
             				<span id='name'></span>
             				</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">性别：</td>
             				<td>
             					男<input type="radio" value="1" name="sex">
             				 	女<input type="radio" value="0" name="sex">
							</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">电话号码：</td>
             				<td>
             				<input type="text" name="phone_number" onblur="func1(this.value,'电话号码','phonenum')" onfocus="func2('phonenum')">
             				<span id='phonenum'></span>
             				</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">所在地区：</td>
             				<td>
             				<input type="text" name="area" onblur="func1(this.value,'地区','area')" onfocus="func2('area')">
             				<span id='area'></span>
             				</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">账号：</td>
             				<td>
             				<input type="text" name="username" onblur="func1(this.value,'账号','count')"onfocus="func2('count')">
             				<span id='count'></span>
             				</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">密码：</td>
             				<td>
             				<input type="text" name="password" onblur="func1(this.value,'密码','passwd')"onfocus="func2('passwd')">
             				<span id='passwd'></span>
             				</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">上传头像：</td>
             				<td><input type="file" name="photo"></td>
             			</tr>
             		</table>
             		<div class="sign_background_no6" id="btn" onclick="sub()" >立即注册</div>
             	</form>
             	 
            </div>
        </div>
        <div class="sign_background_no7">注册帐号即表示您同意并愿意遵守养老院 <span>用户协议</span>和<span>隐私政策</span> </div>
    </div>
    <div class="sign_background_no8"><img src="img/sign01.jpg" alt=""></div>

</div>
<script type="text/javascript">
	var flag = false;
	var flag1 = false;
	function func1(value,name,span){
		if(value.trim() == ""){
			$("#"+span).html(name+"不能为空!");
		}else{
			if(span=="phonenum"){
				if(!(/^1[3456789]\d{9}$/.test(value))){
					$("#"+span).html(name+"有误!");
					return false;
				}else{
					//检查是否被注册
				$.get("user?method=testPhone",{"phone":value},function(d){
				    		
				    		if(d==0){
				    			//手机号可用
				    			$("#"+span).html("<font color='green'>√</font>");
				    			flag=true;
				    		}else{
				    			//手机号已被注册
				    			$("#"+span).html("手机号码已存在");
				    		}
				    	},"text")
			}
		}
			else if(span=="count") {
				
					$.get("user?method=testUsername",{"username":value},function(d){
			    		
			    		if(d==0){
			    			//用户名可用
			    			$("#"+span).html("<font color='green'>√</font>");
			    			flag1=true;
			    		}else{
			    			//用户名已被注册
			    			$("#"+span).html("用户名已存在");
			    		}
			    	},"text")
				
			}
		
	}
	}
	
	function func2(span){
		$("#"+span).html("");
	}
	
	function sub(){
		var name = f1.name.value;
		var phonenum = f1.phone_number.value;
		var area = f1.area.value;
		var username = f1.username.value;
		var password = f1.password.value;
		if(name.trim()=="" || phonenum.trim()=="" || flag==false || flag1==false || area.trim()=="" || username.trim()=="" || password.trim()==""){
			
			$("#d1").html("<font color='red'>请检查表单项</font>");
		}else{
			//必填项都输入了 表单提交
			$("#ff").submit();
		}
	}

</script>
</body>
</html>