$(function(){
	initClassification();
	initCamous();
	getUserInfo();
});

function submitGoods(){
//	var name=$("#name").val();
//	var originalPrice=$("#originalPrice").val();
//	var price=$("#price").val();
//	var number=$("#number").val();
//	var degree=$("#degree").val();
//	var describes=$("#describes").val();
//	var shelfState=$("#shelfState").val();
//	var school=$("#school").val();
//	var item=$("#item").val();
	var urlPic=this.urlPic;
	var urlInfo=this.urlInfo;
	var goods = new Object(); 
	goods.name = $("#name").val();
	goods.originalPrice = $("#originalPrice").val();
	goods.price = $("#price").val();
	goods.describes =$("#describes").val();
	goods.degree = $("#degree").val();
	goods.number = $("#number").val();
	goods.shelfState = $("#shelfState").val();
	goods.school = $("#school").val();
	goods.item = $("#item").val();
	goods.urlInfo =this.urlInfo.substr(0, this.urlInfo.length-1);
	goods.urlPic = this.urlPic;
	postGoods(goods);
//	alert("name:"+name+" originalPrice:"+originalPrice+
//			"  price:"+price+"  number:"+number+"  degree:"+degree+
//			"  describes:"+describes
//			+"  shelfState:"+shelfState+"  school:"+school+"  item:"+item);
//	
//	if(name=="" || originalPrice=="" || price=="" || number=="" || degree=="" || 
//			describes=="" || school=="" || item==""){
//		alert("请正确填写信息，不可为空！");
//	}else{
//		if(response==""){
//			alert("请上传封面信息");
//		}else if(urlInfo==""){
//			alert("请上传商品图片信息");
//		}else{
//			
//		}
//	}
}

function postGoods(goods){
	if(goods.name=="" || goods.originalPrice=="" || goods.price==""
		|| goods.number=="" || goods.degree=="" || 
		goods.describes=="" || goods.school=="" || goods.item==""){
			alert("请正确填写信息，不可为空！");
	}else{
		if(goods.urlPic==""){
			alert("请上传封面信息");
		}else if(goods.urlInfo==""){
			alert("请上传商品图片信息");
		}else{
			$.post("commodityAction?type=postGoods", {goods:JSON.stringify(goods)},
					   function(data){
				this.urlPic="";
				this.urlInfo="";
				if(data=="true"){
					window.location.href="/TP/resource/indexGoods.html";
				}else{
					alert("上传失败，请重新上传");
				}
				
			});
		}
	}
	
}
function initCamous(){
    $.getJSON("campusAction?type=selete",function(result){
 		$("#school").html("");
         var str="<option value=\"\">请选择学校</option>"; 
		 $.each(result.rows, function(i, field){
			 str+="<option value=\""+field.id+"\">"+field.university+"</option>";
		 });
		 $("#school").html(str);
		 });
 }

function initClassification(){
    $.getJSON("classificationAction?type=index_all",function(result){
 		$("#item").html("");
 		var str="<option value=\"\">请选择商品分类</option>";
 		$.each(result, function(i, field){
 			str+="<option value=\""+field.id+"\">"+field.name+"</option>";
 		});
 		$("#item").html(str);
	});
}

//搜索商品
function toSearch() {
	keyword = $("#serachWord").val();
	keyword=encode64(utf16to8(keyword));
	schoolID=encode64(utf16to8(schoolID));
	window.location.href="/TP/resource/indexGoods.html?schoolId="+schoolID+"&keyword="+keyword;
}

var userID="";
function getUserInfo(){
	 $.getJSON("usersAction?method=userInfo", function(result){
		 if(result.id!=undefined && result.id!=null && result.id!=""){
			 userID=result.id;
			 $("#userID").html("<a>欢迎"+result.name+"登录系统</a><a class=\"line\">|</a><a"+
					" href=\"#\" onclick=\"getOutUser()\">退出</a>");
		 }
		
	 });
}

function getOutUser(){
	userID="";
	$.get("usersAction?method=getIndexOut", function(result){});
	$("#userID").html("<a href=\"/TP/resource/login.html\">登陆</a><a class=\"line\">|</a> <a"+
					" href=\"/TP/resource/register.html\">注册</a>");
}
