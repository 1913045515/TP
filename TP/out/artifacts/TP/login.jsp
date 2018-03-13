<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>校园二手交易信息网站设计与实现</title>
<link href="css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src='js/jquery-1.9.1.js'></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="modal" id="mymodal" tabindex="-1">
    <div class="modal-dialog">
    	<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">亲，您好</h4>
			</div>
			<div class="modal-body" id="dialogs">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="but ton" data-dismiss="modal" style="color: #FFFFFF;background-color:#FB8F02; text-align:center;
            	  padding:10px;border: 1px solid #dedede;-moz-border-radius: 15px;-webkit-border-radius: 15px;  border-radius:15px;vertical-align:middle;">我知道了
            	</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



	<div class="logincontainer">
   	  <div class="logintitle">校园二手交易信息网站设计与实现</div>
   	  <div class="loginbg">
       	<div class="loginfmbg">
        	<div class="sysname">管理系统</div>
            <div class="loginfm">   
           	  <p><label class="lbright">用户名：</label>
              	 <span class="spinput"><input type="text" name="usersName" id="username"/></span>
              </p>
              <p><label class="lbright">密  码：</label>
                 <span class="spinput"><input type="password" name="password" id="password"/></span></p>
              <p><label class="lbright">验证码：</label>
               <span>
                 	<input type="text" name="validcode" style="width:70px; vertical-align:middle;" id="validcode"/>
                 	<img id="codePic" src="/TP/codePic" width="60" height="21" style="vertical-align:middle;cursor:pointer;"/>                 
                 </span>               
                 <a class="blurry" id="newPic" onclick="getPic();">看不清楚，换一张</a>
              </p>              
            </div>
            <div class="submitcon">
            	<input type="button" value="登 录" style="height:45px;width:130px;margin-top:15px;color: #FFFFFF;background-color:#FB8F02;font-size: 20px;
            	  padding:5px;border: 3px solid #dedede;-moz-border-radius: 15px;-webkit-border-radius: 15px;  border-radius:15px;vertical-align:middle;text-align:center;" onclick="login();"/>         
            </div>
        </div>
   	  </div>
      <div class="copyright">Copyright 2015-2016 版权所有 </div>
	</div>
</body>
  <script type="text/javascript">
         function getPic(){ 
             $("#codePic").attr("src","/TP/codePic?flag="+Math.random()); 
         };
        function dialog(){ 
     		$("#mymodal").modal("toggle");
   		};
     function login(){  
      var userName=document.getElementById("username").value;  
      var pwd=document.getElementById("password").value;  
      var validcode=document.getElementById("validcode").value;  
      var matchResult=true;  
      if(userName==""){  
            document.getElementById("dialogs").innerHTML="<h3>用户账号不能为空！</h3>";
      		dialog();
            matchResult=false;  
      }else if(pwd==""){  
            document.getElementById("dialogs").innerHTML="<h3>用户密码不能为空！</h3>";
      		dialog();
            matchResult=false;  
      }else if(validcode==""){  
            document.getElementById("dialogs").innerHTML="<h3>验证码不能为空！</h3>";
      		dialog();
            matchResult=false;  
      }else if(userName.length<6||userName.length>20){  
            document.getElementById("dialogs").innerHTML="<h3>用户名长度应在6到20个字符之间！</h3>";
            dialog();
            matchResult=false;  
      }else if(pwd.length<6||pwd.length>20){  
            document.getElementById("dialogs").innerHTML="<h3>密码或重复密码长度应在6到20个字符之间！</h3>";
            dialog();
            matchResult=false;  
      }  
      if(matchResult==true){
      	  $.post("/TP/usersAction?method=login", {usersName:userName,password:pwd, validcode:validcode},function(data,status){
      	 	console.log('data',data)
      	 	  var error=data.error;
      	 	 var result=data.result;  
      	 	 getPic();
  	     if(error=="error"){
  	         errors="true";
  	     	 document.getElementById("dialogs").innerHTML="<h3>验证码错误，请重新输入！</h3>";
      		 dialog();
  	     }
  	     if(result=="0"){
  	     	 document.getElementById("dialogs").innerHTML="<h3>您输入的用户名不存在！</h3>";
  	     	  document.getElementById("username").value="";
      		 dialog();
  	     }else if(result=="1"){
  	     	 document.getElementById("dialogs").innerHTML="<h3>您输入的密码错误，请重新输入！</h3>";
  	     	 document.getElementById("password").value="";
      		 dialog();
  	     }else if(result=="2"){
  	     	document.getElementById("dialogs").innerHTML="<h3>您的管理员权限不够！</h3>";
      		 dialog();
  	     }else if(result=="3"){
  	     	location.href="/TP/main.jsp";
  	     }	   
        },"json");
      }  
     };
</script>
</html>
