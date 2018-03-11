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
			{field:'id',title:'ID',width:100,align:'center'},  
			{field:'name',title:'用户名称',width:120,align:'center'},  
			{field:'password',title:'用户登陆密码',width:130,align:'center'},  
			{field:'phone',title:'联系号码',width:150,align:'center'},  
			{field:'acount',title:'用户账户',width:150,align:'center'},  
			{field:'address',title:'用户地址',width:170,align:'center'}, 
			{field:'school',title:'用户所在学校',width:150,align:'center'},  
			{field:'btId',title:'点击修改',width:100,align:'center',
				 formatter:function(value,rec){  
                	var btn = '<a href="usersAction?method=detail&id='+value+'">信息修改</a>';  
                	return btn;  
           		 }  
			}
		]],  
		url:'/TP/usersAction?method=all&pageNumber=1&pageSize=20'
	});  
		$("#datagrid").datagrid("getPager").pagination({  
                pageSize: 20, //每页显示的记录条数，默认为10    
                pageList: [15, 20, 25, 30], //可以设置每页记录条数的列表  
                onSelectPage: function(pageNumber, pageSize) {  
                	//alert("pageNumber="+pageNumber+"    pageSize="+pageSize);
              		SearchTrainee(pageNumber, pageSize);//每次更换页面时触发更改   
              	 }  
       	 });         
	});  	
	
	 function SearchTrainee(pageNumber, pageSize) {  
           //异步获取数据到javascript对象，入参为查询条件和页码信息  
           $.post("/TP/usersAction?method=all", {  
               pageNumber:pageNumber,  
               pageSize:pageSize  
           }, function(data) {  
              //alert(""+eval(data)[data.length-1].count);
              $("#datagrid").datagrid("loadData", JSON.parse(data));  
              pager.pagination({  
              //更新pagination的导航列表各参数            
                pageSize: pageSize,//行数  
                pageNumber: pageNumber//页数  
             });  
  		  });  
   }  
   
   function search(value,name){
   	  $.post("/TP/usersAction?method=select", 
		  {typeName:name, typeValue:value},function(data,status){
              $("#datagrid").datagrid("loadData", JSON.parse(data));             
  		});  
   } 
</script>
</head>

<body class="nested">
    <div class="maincontainer">
    	<div class="columnconn"><span class="columntitle">商品信息列表</span></div>
		<div class="listcontainer">
            <div id="toolbar">  
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"">用户信息</a> 
                <div class="searchcon">
                    <input id="ss" class="easyui-searchbox" data-options="searcher:search, prompt:'请输入要搜索的商品信息',menu:'#mm'" 
					style="width:240px;margin-top:-2px;" />
					<div id="mm" style="width:120px"">
   						 <div data-options="name:'id',iconCls:'icon-ok'">用户ID</div>
    					 <div data-options="name:'name',iconCls:'icon-ok'">用户账号</div>
					</div>
                </div>
		 	</div>
            <div id="datagrid" style="width:auto;"></div>
         </div>
    </div>
</body>
</html>
