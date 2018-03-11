$("#verifycode").click(function () {
        getPic();
})
    
function getPic(){ 
        $("#verifycode").attr("src","/TP/codePic?flag="+Math.random()); 
}

$(function(){
	init();
});

function init(){
    $.getJSON("campusAction?type=selete",function(result){
 		$("#school").html("");
         var str="<option value=\"\">请选择学校</option>"; 
		 $.each(result.rows, function(i, field){
			 str+="<option value=\""+field.id+"\">"+field.university+"</option>";
		 });
		 $("#school").html(str);
		 });
 }
    
var matchResult=[0,0,0,0,0,0];
$("#name").on('blur', function(){  
	var name=$("#name").val();
	if(name.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("用户名不能为空！");
		matchResult[0]=0;
	}else{
		$("#flag").html("");
		matchResult[0]=1;
	}
});

$("#pass").on('blur', function(){  
	var name=$("#pass").val();
	if(name.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("密码不能为空！");
		matchResult[1]=0;
	}else{
		$("#flag").html("");
		matchResult[1]=1;
	}
});

$("#re-pass").on('blur', function(){  
	var repass=$("#re-pass").val();
	var pass=$("#pass").val();
	if(repass.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("确认密码不能为空！");
		matchResult[2]=0;
	}else if(pass!=repass){
		$("#flag").html("两次密码不一致！");
		matchResult[2]=0;
	}else{
		$("#flag").html("");
		matchResult[2]=1;
	}
});

$("#email").on('blur', function(){  
	var name=$("#email").val();
	if(name.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("邮箱地址不能为空！");
		matchResult[3]=0;
	}else if(!checkMail(name)){
		$("#flag").html("邮箱格式不正确！");
		matchResult[3]=0;
	}else{
		$("#flag").html("");
		matchResult[3]=1;
	}
});

$("#code").on('blur', function(){  
	var name=$("#code").val();
	if(name.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("验证码不能为空！");
		matchResult[4]=0;
	}else{
		$("#flag").html("");
		matchResult[4]=1;
	}
});

//验证邮箱格式是否正确的
function checkMail(mail) {  
    var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;  
    if (filter.test(mail)){  
       return true;  
    }else {  
       return false;  
    }  
}  

$(".reg_btn").click(function () {
	register();
})

$("#school").change(function(){
    var name=$("#school").val();
    if(name.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("请选择学校！");
		matchResult[5]=0;
	}else{
		$("#flag").html("");
		matchResult[5]=1;
	}
});

function check(matchResult){
	var total=0;
	for(var i=0;i<matchResult.length;i++){
		total+=matchResult[i];
	}
	if(total==6){
		return true;
	}else{
		return false;
	}
}
function register(){  
	$("#flag").html("");
	var pass=$("#pass").val();
	var name=$("#name").val();
	var email=$("#email").val();
	var code=$("#code").val();
	var school=$("#school").val();
	if(school.replace(/(^\s*)|(\s*$)/g, "") == ""){
		$("#flag").html("请选择学校！");
	}else{
		$("#flag").html("");
	}
	var flagShow="";
      if(check(matchResult)){
      	 $.post("/TP/usersAction?method=register", {usersName:name,password:pass,validcode:code,email:email,school:school},function(data,status){
      	 	 var error=data.error;
      	 	 var result=data.result;  
  	     if(error=="error"){
  	         flagShow="验证码错误，请重新输入！";
      	 	 getPic();
  	         $("#flag").html(flagShow);
  	     }else{
  	    	 if(result=="0"){
  	    		flagShow="邮箱已经注册！";
  	    		$("#flag").html(flagShow);
  	    		 getPic();
  	  	     }	else{
   	  	     	location.href="indexInfo.html";
  	  	     } 
  	     }
        },"json");
      }else{
    	  flagShow="信息填写有误！";
    	  $("#flag").html(flagShow);
      }
  }

    