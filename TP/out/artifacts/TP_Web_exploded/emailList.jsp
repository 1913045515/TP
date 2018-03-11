<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/common/easyui/themes/default/easyui.css">  
<link rel="stylesheet" type="text/css" href="js/common/easyui/themes/icon.css">  
<script type="text/javascript" src="js/common/jquery.js"></script>
<script type="text/javascript" src="js/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript">
$(function(){
	$("#datagrid").datagrid({
		width:$(this).width()*0.99,  				//设置宽度为97%
		height:510,
		rownumbers:true,
		singleSelect:false,
		pagination:true,
		pageSize: 20, //每页显示的记录条数，默认为10    
		toolbar:"#toolbar",
		columns:[[  
			{field:'subject',title:'主题',width:200,align:'left'},
			{field:'from',title:'发件人',width:200,align:'center'},
			{field:'to',title:'收件人',width:200,align:'center'},
			{field:'content',title:'内容',width:365,align:'center'},
			{field:'senddate',title:'时间',width:135,align:'center'}
		]],  
		 onClickCell: function (rowIndex, field, value) {
        	//alert(rowIndex+"  "+field+"   "+value);
   		 },
   		 
   		onClickRow:function(rowIndex,rowData){
   			var result=JSON.stringify(rowData);
  			var turnForm = document.createElement("form");   
    		document.body.appendChild(turnForm);
   		    turnForm.method = "post";
 			turnForm.action = "/TP/emailAction?type=detail";
			 var newElement = document.createElement("input");
    		 newElement.setAttribute("name","result");
   			 newElement.setAttribute("type","hidden");
   			 newElement.setAttribute("value",result);
    		 turnForm.appendChild(newElement);
    		 turnForm.submit();
 		 },
 		 
 		 onLoadSuccess: function (data) {
               var panel = $(this).datagrid("getPanel");
               var tr = panel.find("div.datagrid-body tr");  
                tr.each(function () {
                    var td = $(this).children("td");
                  	 td.css({	
                        "border-width": "0",
                    });      	 
                });
         },
		url:'/TP/emailAction?type=getEmail'
	});
	      
	});  	
</script>
</head>

<body class="nested">
    <div class="maincontainer">
    	<div class="columnconn"><span class="columntitle">邮件信息列表</span></div>
		<div class="listcontainer">
            <div id="datagrid" style="width:auto;"></div>
         </div>
    </div>
</body>
</html>
