var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

var schoolID="";
$(function() {
	var goodsID=getQueryString("goodsID");
	ajaxGoodsFunc(goodsID);
	getUserInfo();
	if(getQueryString("schoolID")==null  || getQueryString("schoolID")==undefined  || getQueryString("schoolID")==""){
	}else{
		schoolID=utf8to16(decode64(getQueryString("schoolId")));
	}

	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	if(schoolID!="" && reg.test(schoolID)){
		$(".xuexiao").html(schoolID);
	}
});


function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function ajaxGoodsFunc(goodsID){
	$.getJSON("/TP/commodityAction?type=goodsdetail&goodsID="+goodsID,
	function(result) {
		$.each(result,function(i, field) {
			console.info(field);
			$("#goodsName").html("</span>"+field.name+"</span>");
			$("#goodsPrice").html("</span><span class=\"howmuch\">售价"+field.price+"元</span>"
                       + "&nbsp;&nbsp;&nbsp;<span class=\"daofou\">原价"+field.originalPrice+"元</span>");
			var pathList="";
			var showGoods="";
			showGoods+="<img id=\"imgID\" alt=\""+field.name+"\" src=\"/TP/"+field.path[0]+"\">";
			showGoods+="<div class=\"magnifyingBegin\"></div>";
			showGoods+="<div class=\"magnifyingShow\">"
              +"<img alt=\""+field.name+"\" src=\"/TP/"+field.path[0]+"\"></div>";
			for(var i=0;i<field.path.length;i++){
				//onclick=\"imgFunc("+field.path[i]+")\"
				pathList+="<li class=\"active\"><img src=\"/TP/"+
				field.path[i]+"\"data-bigimg=\"/TP/"+field.path[i]+"\"></li>";
			}
			$("#imgListName").html(pathList);
			$("#showGoodsName").html(showGoods);
			$.getJSON("/TP/usersAction?method=userdetail&userID="+field.usersId,
					function(result) {
				$.each(result,function(i, field) {
					$("#userName").html("<a href=\"#\"><span class=\"name\">"
					           +"<span class=\"iconfont\">&#xe610;</span> &nbsp;&nbsp;&nbsp;"
					         +"<span class=\"\">"+field.name+"</span><span class=\"seehim iconfontitems\"></a>");		
					$("#replyUserID").html("回复 "+field.name)
					$("#qqName").html(" <span class=\"qq\"><span class=\"iconfont\">&#xe616;</span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"text\">"+field.acount+"</span>");
					$("#phoneName").html("<span class=\"tel\"><span class=\"iconfont\">&#xe63a;</span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"text\">"+field.phone+"</span>");
					$("#schoolName").html("<span class=\"\"><span class=\"iconfont\">&#xe63a;</span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"text\">"+field.school+"</span>");
					$("#addressName").html("<span class=\"\"><span class=\"iconfont\">&#xe63a;</span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"text\">"+field.address+"</span>");
				});
			});	
			$("#timeName").html("<span class=\"\"><span class=\"iconfont\">&#xe63a;</span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"text\">"+FormatDate(field.editTime)+"</span>");
			$("#user_cmt").html("<h3>"+field.describe+"</h3></br><h4>（记得说是在BBW上面看到的哦）</h4>");
		});
	});
}

function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

function  saleFavor(favorID){
	alert("功能待开发");
}

function  saleReport(reportID){
	alert("功能待开发");
}

function imgFunc(pathID){
	alert("pathID"+pathID);
}

//搜索商品
function toSearch() {
	keyword = $("#serachWord").val();
	keyword=encode64(utf16to8(keyword));
	schoolID=encode64(utf16to8(schoolID));
	window.location.href="/TP/resource/indexGoods.html?schoolId="+schoolID+"&keyword="+keyword;
}

$(document).on("click",'#pushGoodsID',function(){
	 if(userID==""){
		 event.preventDefault();  
		 alert("登录完才可以发布商品");
	 }
});

$(document).on("click",'.active',function(){
	var pathStr=$("img", this).attr("src");
	$(".magnifyingShow img").attr("src",pathStr);
	$("#imgID").attr("src",pathStr);
});

function classFunc(classId){
	var schoolID="";
	if("你还没有选择学校"!=$(".xuexiao").text()){
		schoolID=encode64(utf16to8($(".xuexiao").text()));
	}
	classId=encode64(utf16to8(classId));
	window.location.href="/TP/resource/indexGoods.html?schoolId="+schoolID+"&classId="+classId;
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

//将Base64编码字符串转换成Ansi编码的字符串
function decode64(input) {
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;
	if (input.length % 4 != 0) {
		return "";
	}
	var base64test = /[^A-Za-z0-9/+///=]/g;
	if (base64test.exec(input)) {
		return "";
	}
	do {
		enc1 = keyStr.indexOf(input.charAt(i++));
		enc2 = keyStr.indexOf(input.charAt(i++));
		enc3 = keyStr.indexOf(input.charAt(i++));
		enc4 = keyStr.indexOf(input.charAt(i++));
		chr1 = (enc1 << 2) | (enc2 >> 4);
		chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
		chr3 = ((enc3 & 3) << 6) | enc4;
		output = output + String.fromCharCode(chr1);
		if (enc3 != 64) {
			output += String.fromCharCode(chr2);
		}
		if (enc4 != 64) {
			output += String.fromCharCode(chr3);
		}
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);
	return output;
}

function utf8to16(str) {
	var out, i, len, c;
	var char2, char3;
	out = "";
	len = str.length;
	i = 0;
	while (i < len) {
		c = str.charCodeAt(i++);
		switch (c >> 4) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			// 0xxxxxxx
			out += str.charAt(i - 1);
			break;
		case 12:
		case 13:
			// 110x xxxx 10xx xxxx
			char2 = str.charCodeAt(i++);
			out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
			break;
		case 14:
			// 1110 xxxx 10xx xxxx 10xx xxxx
			char2 = str.charCodeAt(i++);
			char3 = str.charCodeAt(i++);
			out += String.fromCharCode(((c & 0x0F) << 12)
					| ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
			break;
		}
	}
	return out;
}


//将Ansi编码的字符串进行Base64编码
function encode64(input) {
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;
	do {
		chr1 = input.charCodeAt(i++);
		chr2 = input.charCodeAt(i++);
		chr3 = input.charCodeAt(i++);
		enc1 = chr1 >> 2;
		enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
		enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
		enc4 = chr3 & 63;
		if (isNaN(chr2)) {
			enc3 = enc4 = 64;
		} else if (isNaN(chr3)) {
			enc4 = 64;
		}
		output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
				+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);
	return output;
}

function utf16to8(str) {
	var out, i, len, c;
	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
		c = str.charCodeAt(i);
		if ((c >= 0x0001) && (c <= 0x007F)) {
			out += str.charAt(i);
		} else if (c > 0x07FF) {
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		} else {
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		}
	}
	return out;
}

