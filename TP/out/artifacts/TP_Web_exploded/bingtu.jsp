<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ECharts</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="js/common/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/common/easyui/themes/icon.css">
<script type="text/javascript" src="js/common/jquery.js"></script>
<script type="text/javascript"
	src="js/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript" src="jquery-1.3.2.js"></script>
<script src="js/jquery.paginate.js" type="text/javascript"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main"
	style="margin-top:2%;margin-left:30%;width:400px;height:470px"></div>
<!-- ECharts单文件引入 -->

<script src="js/echarts-all.js"></script>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts图表
	$.get("/TP/statisticsAction", function(data, status) {
		var keyNum = new Array();
		var seriesData = [];
		var i = 0;
		$.each(data, function(key, value) {
			seriesData[i] = {};
			keyNum[i] = key;
			seriesData[i].name = key;
			seriesData[i].value = value;
			i++;

		});
		echart(keyNum, seriesData);
	}, "json");

	function echart(keyNum, seriesData) {
		var myChart = echarts.init(document.getElementById('main'));
		option = {
			title : {
				text : '各类商品销量分布',
				subtext : '',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : keyNum
			// data:['60以下','60-70','70-80','80-90','90-100']
			},
			toolbox : {
				show : false,
				feature : {
					mark : {
						show : false
					}
				}
			},
			calculable : true,
			series : [ {
				name : '成绩分类',
				type : 'pie',
				radius : '80%',
				center : [ '50%', '55%' ],
				itemStyle : {
					normal : {
						label : {
							position : 'inner',
							formatter : function(params) {
								return (params.percent - 0).toFixed(0) + '%'
							}
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						label : {
							show : true,
							formatter : "{b}\n{d}%"
						}
					}
				},
				data : seriesData
			} ]
		};
		// 为echarts对象加载数据
		myChart.setOption(option);
		// 填入数据
		myChart.on('click', function(param) {
			var name = param.name;
			alert("我的名字是：" + name);
		});
	}
</script>

<script type="text/javascript">
	var year=0;
	var month=0;
	$(function() {
		$("#demos").paginate({
			count : 12,
			start : 1,
			display : 12,
			border : true,
			border_color : '#BEF8B8',
			text_color : '#68BA64',
			background_color : '#E3F2E1',
			border_hover_color : '#68BA64',
			text_hover_color : 'black',
			background_hover_color : '#CAE6C6',
			images : false,
			mouse : 'press',
			onChange : function(page) {
				if (page == -1) {
					year = year - 1;
				} else if (page == 13) {
					year = year + 1;
				} else {
					month = page.substring(0, page.length - 1);
				}
				$.post("/TP/statisticsAction", {
					year : year,
					month : month
				}, function(data, status) {
					var keyNum = new Array();
					var seriesData = [];
					var i = 0;
					$.each(data, function(key, value) {
						seriesData[i] = {};
						keyNum[i] = key;
						seriesData[i].name = key;
						seriesData[i].value = value;
						i++;
					});
					if(keyNum==""){
						keyNum[0]="还没有卖出";
					}
					if(seriesData==""){
						seriesData[0] = {};
						seriesData[0].name = "还没有卖出";
						seriesData[0].value = 0;
					}			
					echart(keyNum,seriesData);
				}, "json");
			}
		});
	});
</script>

<style type="text/css">
body {
	font-family: verdana;
	padding: 0px;
	margin: 0px;
	letter-spacing: 2px;
}

.demo {
	width: 900px;
	padding: 10px;
	margin: 10px auto;
}

.button {
	border-radius: 5%;
	width: 110px;
	height: 25px;
	font-style: oblique;
	background-color: #F5F5F5;
}
</style>
</head>
<body>
	<div class="content" style="margin-top:5px;">
		<div id="paginationdemo" class="demo">
			<div id="demos"></div>
		</div>
	</div>
</body>
</html>