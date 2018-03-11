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
		    {field:'ck',checkbox:true,width:100,align:'center'},  
			{field:'id',title:'举报ID',width:100,align:'center'},  
			{field:'cId',title:'被举报商品ID',width:100,align:'center'},  
			{field:'uId',title:'举报者ID',width:100,align:'center'},  
			{field:'time',title:'举报时间',width:200,align:'center'},  
			{field:'content',title:'举报内容',width:580,align:'center'}
		]],  
		url:'/TP/reportAction?type=all&pageNumber=1&pageSize=20'
	});  
		$("#datagrid").datagrid("getPager").pagination({  
                pageSize: 20, //每页显示的记录条数，默认为10    
                pageList: [15, 20, 25, 30], //可以设置每页记录条数的列表  
                onSelectPage: function(pageNumber, pageSize) {  
              		SearchTrainee(pageNumber, pageSize);//每次更换页面时触发更改   
              	 }  
       	 });         
	});  	
	
	 function SearchTrainee(pageNumber, pageSize) {  
           //异步获取数据到javascript对象，入参为查询条件和页码信息  
           $.post("/TP/reportAction?type=all", {  
               pageNumber:pageNumber,  
               pageSize:pageSize
           }, function(data) {  
              $("#datagrid").datagrid("loadData", JSON.parse(data));
               $("#datagrid").pagination({
              //更新pagination的导航列表各参数            
                pageSize: pageSize,//行数  
                pageNumber: pageNumber//页数  
             });
  		  });  
   }  
   
   function search(value,name){
   	  $.post("/TP/reportAction?type=selete", 
		  {typeName:name, typeValue:value},function(data,status){
              $("#datagrid").datagrid("loadData", JSON.parse(data));             
  		});  
   } 
   
     function deleteInfo(){
    	var checkedItems = $('#datagrid').datagrid('getChecked');
		var check = [];
		$.each(checkedItems, function(index, item){  
			check.push(item.id);
		}); 
		 var dg =$("#datagrid");  
         var opts =dg.datagrid("options");   
         var _pageNumber =opts.pageNumber;  
         var _pageSize =opts.pageSize;  
		$.post("/TP/reportAction?type=delete", 
		  {check:check.toString()},function(data,status){
		  	  SearchTrainee(_pageNumber,_pageSize);
		 }); 
    }
</script>
</head>

<body class="nested">
    <div class="maincontainer">
    	<div class="columnconn"><span class="columntitle">商品信息列表</span></div>
		<div class="listcontainer">
            <div id="toolbar">  
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteInfo();">删除</a> 
                <div class="searchcon">
                    <input id="ss" class="easyui-searchbox" data-options="searcher:search, prompt:'请输入要搜索的商品信息',menu:'#mm'" 
					style="width:240px;margin-top:-2px;" />
					<div id="mm" style="width:120px"">
   						 <div data-options="name:'id',iconCls:'icon-ok'">举报ID</div>
   						 <div data-options="name:'cId',iconCls:'icon-ok'">举报商品ID</div>
   						 <div data-options="name:'uId',iconCls:'icon-ok'">举报者ID</div>
					</div>
                </div>
		 	</div>
            <div id="datagrid" style="width:auto;"></div>
         </div>
    </div>
</body>
</html>
