var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
function querySale(obj, target) {
	_this = $(obj);
	var url = querySaleUrl;
	if (target == 0) {
		order = 0;
	}
	if (target == 1) {
		order = $.trim(_this.attr("data-type")) == 5 ? 6 : 5;
	}
	if (target == 2) {
		order = $.trim(_this.attr("data-type")) == 1 ? 2 : 1;
	}
	if (target == 3) {
		order = $.trim(_this.attr("data-type")) == 3 ? 4 : 3;
	}
	url = url + "/order/" + order;
	var keyword = $.trim($("input[name='keyWord']").val());
	if (keyword) {
		url = url + "/keyword/" + keyword;
	}
	var type = $.trim($("input[name='type']").val());
	if (type) {
		url = url + "/type/" + type;
	}
	// console.log(order)
	location.href = url;
}
$(".fullSlide").hover(function() {
	$(this).find(".prev,.next").stop(true, true).fadeTo("show", 0.5)
}, function() {
	$(this).find(".prev,.next").fadeOut()
});
$(".fullSlide").slide(
		{
			titCell : ".hd ul",
			mainCell : ".bd ul",
			effect : "fold",
			autoPlay : true,
			autoPage : true,
			trigger : "click",
			delayTime : 1000,
			interTime : 3000,
			startFun : function(i) {
				var curLi = jQuery(".fullSlide .bd li").eq(i);
				if (!!curLi.attr("_src")) {
					curLi.css("background-image", curLi.attr("_src"))
							.removeAttr("_src")
				}
			}
		});
$("img.lazyload").show().lazyload({
	threshold : 100,
	placeholder : "/public/images/icon/small_loading_default.png",
	effect : "fadeIn",
	effectspeed : 500,
	failure_limit : 10,
});

var schoolId="";
var classId="";
$(function() {
	$('.toTop').hide();
	$(window).scroll(function() {
		if ($(window).scrollTop() >= 100) {
			$('.toTop').fadeIn(1000);
		} else {
			$('.toTop').fadeOut(1000);
		}
	});
	$('.toTop').click(function() {
		$('body,html').animate({
			scrollTop : 0
		}, 1000, 'swing');
	});
	getUserInfo();
	if(getQueryString("schoolId")==null  || getQueryString("schoolId")==undefined  || getQueryString("schoolId")==""){
	}else{
		schoolId=utf8to16(decode64(getQueryString("schoolId")));
	}
	
	if(getQueryString("classId")==null  || getQueryString("classId")==undefined  || getQueryString("classId")==""){
	}else{
		classId=utf8to16(decode64(getQueryString("classId")));
	}
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	if(schoolId!="" && reg.test(schoolId)){
		$(".xuexiao").html(schoolId);
	}
	
	if(getQueryString("keyword")==null  || getQueryString("keyword")==undefined  || getQueryString("keyword")==""){
	}else{
		keyword=utf8to16(decode64(getQueryString("keyword")));
	}
	
	goodsShow(1);// 显示该学校的商品
})

function classFunc(classId){
	this.classId=classId;
	goodsShow(1);// 显示该学校的商品
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

//搜索商品
function toSearch() {
	keyword = $("#serachWord").val();
	classId="";
	goodsShow(1);
}
function classShow() {
	$
			.getJSON(
					"classificationAction?type=index_all",
					function(result) {
						$("#schoolId").html("");
						var school = '<li class="school" id="schoolId"><div class="college"><span class="icon iconfontitems">&#xe60b;</span> <ahref="/resource/index.html"><span class="xuexiao">你还没有选择学校~</span></a></div><div class="change"><a href="/resource/index.html" class="switch">切换</a></div></li>';
						var school_li = "";
						$
								.each(
										result,
										function(i, field) {
											var li = "<a href=\"/sale/querysale/type/"
													+ field.id + "\">";
											li += "<li class=\"daibu item0\"><span class=\"icon iconfontitems\">"
													+ field.path + "</span>";
											li += "<span class=\"text\">"
													+ field.name
													+ "</span></li></a>";
											school_li += li;
										});
						$("#schoolId").html(school + school_li);
					});
}

var keyword = "";
function goodsShow(page) {
	var pageSize = 20;
	var pageLength = 8;// 分页显示数
	var str = "";
	var url = "";
	var schoolID=encode64(utf16to8(schoolId));
	if (keyword == "") {
		url = "commodityAction?type=all&schoolId="+schoolId+"&classId="+classId;
	} else {
		url = "commodityAction?type=index_selete&schoolId="+schoolId+"&classId="+classId;
	}
	$
			.getJSON(
					url,
					{
						typeName : keyword,
						pageSize : pageSize,
						pageNumber : page
					},
					function(result) {
						$("#ulId").html("");
						pageFunc(page, result.total, pageSize, pageLength);// (当前页数，总数，每页显示数，分页显示数)
						$
								.each(
										result.rows,
										function(i, field) {
											var strLi = "<li class=\"items clearfix\"><div class=\"col-md-3 col-sm-6\"><div class=\"col-md-3 col-sm-6\">";
											strLi += "<div class=\"col-md-3 col-sm-6\"><div class=\"class-item\"><div class=\"class-bg-layer\"></div><div class=\"class-item-bg\">";
											strLi += "<a href="
													+ "/TP/resource/indexGoodsDetail.html?goodsID="+field.id+"&schoolID="+schoolID+">"
													+ "<img class=\"img-responsive lazyload\"alt=\""
													+ field.name + "\"";
											strLi += "src=\"/TP/"
													+ field.indexPath
													+ "\"></a><div class=\"pricehot clearfix\"><span class=\"price\">￥&nbsp;<span>";
											strLi += field.price
													+ "</span></span><span class=\"hot\">原价&nbsp;<span>"
													+ field.originalPrice
													+ "</span></span></div>";
											strLi += "<a target=\"_blank\" href=\""
													+ "#"
													+ "\"class=\"title\">"
													+ field.name
													+ "</a><div class=\"some  clearfix\">";
											strLi += "<span class=\"school\">新旧："
													+ field.degree
													+ "</span> <span class=\"renzheng\">分类："
													+ field.type
													+ "</span></div></div></div></div></li>";
											str += strLi;
										});
						$("#ulId").html(str);
					});
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

function pageFunc(page, total, pageNumber, pageLength) {
	$(".pager").html("");
	var totalPage = Math.ceil(total / pageNumber);
	var pageStr = "<div>";
	var start = pageLength * parseInt((page - 1) / pageLength) + 1;
	var end = pageLength * parseInt((page - 1) / pageLength) + pageLength;
	if (end > totalPage) {
		end = totalPage;
	}

	var prevStr = "";
	if (totalPage > pageLength && page > pageLength) {
		prevStr += "<a class=\"first\" href=\"javascript:void(0);\" onclick=\"goodsShow("
				+ 1 + ")\">第一页</a>";
	}
	if (page > 1) {
		prevStr += "<a class=\"prev\" href=\"javascript:void(0);\" onclick=\"goodsShow("
				+ (page - 1) +")\">上一页</a>"
	}
	pageStr += prevStr;
	for (var i = start; i <= end; i++) {
		if (i == page) {
			pageStr += "<span class=\"current\">" + page + "</span>";
		} else {
			pageStr += "<a class=\"num\" href=\"javascript:void(0);\" onclick=\"goodsShow("
					+ i + ")\">" + i + "</a>";
		}
	}
	var nextStr = "";
	if (page < totalPage) {
		nextStr += "<a class=\"next\" href=\"javascript:void(0);\" onclick=\"goodsShow("
				+ (page + 1) + ")\">下一页</a>";
	}
	var lastEnd = pageLength * parseInt((totalPage - 1) / pageLength) + 1;
	if (totalPage > pageLength && page < lastEnd) {
		nextStr += "<a class=\"end\" href=\"javascript:void(0);\" onclick=\"goodsShow("
				+ lastEnd +")\">最后页</a>";
	}
	pageStr += nextStr;
	pageStr += "<span class=\"rows\">第<b>" + page + "</b>页/共<b>" + totalPage
			+ "</b>页&nbsp;&nbsp;共<b>" + total + "</b>条记录</span></div>";
	$(".pager").html(pageStr);
}
// 将Base64编码字符串转换成Ansi编码的字符串
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

$("#pushGoodsID").on("click", function(){
	  if(userID==""){
		  event.preventDefault();  
		  alert("登录完才可以发布商品");
	  }
});
