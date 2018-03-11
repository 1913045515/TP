/**
 * Created by zhoubing on 2015/3/11.
 */
var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
$(document).ready(function() {
	init();
	$(window).keydown(function(event) {
		if (event.keyCode == 13) {
			searSchool();
		}
	});
}).on("focus", ".search-ipt input", function() {
	$(this).addClass("search-active");
	$(".search-left").addClass("search-active");
}).on("blur", ".search-ipt input", function() {
	$(this).removeClass("search-active");
	$(".search-left").removeClass("search-active");
}).on("click", ".search-btn", function() {
	searSchool();
});

function init() {
	$
			.getJSON(
					"campusAction?type=index_query",
					function(result) {
						$(".wrap-school-list").html("");
						var str = "";
						$
								.each(
										result,
										function(i, field) {
											str += "<div class=\"school-item\"> <div class=\"item-title\"><div class=\"item-title-txt\">"
													+ field.province
													+ "</div></div><p class=\"school-list\">";
											for (var i = 0; i < field.school.length; i++) {
												var schoolsId = encode64(utf16to8(field.school[i]));
												str += "<a href=\"/TP/resource/indexGoods.html?schoolId="
														+ schoolsId
														+ "\">"
														+ field.school[i]
														+ "</a>";
											}
											str += "</p></div>";
										});
						$(".wrap-school-list").html(str);
					});
}

function searSchool() {
	var result = $(".search-ipt input").val();
	if (result == "") {
		return false;
	} else {
		$
				.ajax({
					url : "campusAction?type=index_queryName&keyword=" + result,
					success : function(data) {
						var data = $.parseJSON(data);
						var schools = data;
						var items = "";
						if (schools.length == 0) {
							items = '<a href="javascript:;">未搜索到任何结果！</a>';
						} else {
							for (var i = 0; i < schools.length; i++) {
								var schoolsId = encode64(utf16to8(schools[i].university));
								items += '<a href="/TP/resource/indexGoods.html?schoolId='
										+ schoolsId
										+ '>'
										+ schools[i].university + '</a>';
							}
						}

						if ($(".sear-result").size() == 0) {
							var item = '<div class="sear-result"><div class="school-item mt0"><div class="item-title"><div class="item-title-txt">搜索结果</div></div><p class="school-list sear-school-list">';
							item += items;
							item += '</p></div></div>';
							$(".wrap-school-list").before($(item));
							$(".sear-result").slideToggle(200);
						} else {
							$(".sear-school-list").html("").append($(items));
						}

					}
				})
	}
}

// 将Ansi编码的字符串进行Base64编码
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