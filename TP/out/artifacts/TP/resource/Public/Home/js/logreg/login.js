 function login(){  
	 $("#flagShow").html("");
      var userName=document.getElementById("username").value;  
      var pwd=document.getElementById("password").value;  
      var validcode=document.getElementById("validcode").value;  
      var matchResult=false;  
      var flagShow="";
      if(userName==""){  
    	  flagShow="用户账号不能为空！";
      }else if(pwd==""){  
    	  flagShow="用户密码不能为空！"; 
      }else if(validcode==""){  
    	  flagShow="验证码不能为空！";
      }else{
    	  matchResult=true;
      }
      
      if(matchResult==true){
      	  $.post("/TP/usersAction?method=loginUser", {usersName:userName,password:pwd, validcode:validcode},function(data,status){
              var error=data.error;
  	     if(error=="error"){
  	         flagShow="验证码错误，请重新输入！";
      	 	 getPic();
  	         $("#flagShow").html(flagShow);
  	     }else{
             var result=data.result;
             if(result=="3"){
  	  	     	location.href="indexGoods.html";
  	  	     }	else{
  	  	    	flagShow="登陆失败，请重新登录！";
  	      	 	 getPic();
  	  	    	$("#flagShow").html(flagShow);
  	  	     } 
  	     }
        },"json");
      }else{
    	  $("#flagShow").html(flagShow);
      }
  }
     
     $("#verifycode").click(function () {
         getPic();
     })
     
     function getPic(){ 
         $("#verifycode").attr("src","/TP/codePic?flag="+Math.random()); 
     }
     
     $(".reg_btn").click(function () {
         login();
     })