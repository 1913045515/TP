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
			{field:'ck',checkbox:true,width:10,align:'center'},  
			{field:'id',title:'ID',width:50,align:'center'},  
			{field:'name',title:'商品名称',width:120,align:'center'},  
			{field:'originalPrice',title:'商品原价',width:70,align:'center'},  
			{field:'price',title:'商品售价',width:70,align:'center'},  
			{field:'number',title:'商品数量',width:66,align:'center'},  
			{field:'degree',title:'新旧程度',width:80,align:'center'}, 
			{field:'describe',title:'商品描述',width:160,align:'center'},  
			{field:'releaseTime',title:'发布时间',width:150,align:'center'} ,
			{field:'type',title:'类型',width:60,align:'center'},
			{field:'shelfState',title:'是否下架',width:80,align:'center'},  
			{field:'usersId',title:'发布者ID',width:60,align:'center'},
			{field:'btId',title:'点击详情',width:80,align:'center',
				 formatter:function(value,rec){  
				 	//alert("value="+value+"  rec="+rec);
                	var btn = '<a href="commodityAction?type=detail&showId='+value+'">详细信息</a>';  
                	return btn;  
           		 }  
			}
		]],  
		url:'/TP/commodityAction?type=backgroundAll&pageNumber=1&pageSize=20'
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
           $.post("/TP/commodityAction?type=backgroundAll", {
               pageNumber:pageNumber,  
               pageSize:pageSize  
           }, function(data) {  
              //alert(""+eval(data)[data.length-1].count);
              $("#datagrid").datagrid("loadData", JSON.parse(data));
               $("#datagrid").pagination({
              //更新pagination的导航列表各参数            
                pageSize: pageSize,//行数  
                pageNumber: pageNumber//页数  
             });  
  		});  
   }  
   
   function search(value,name){
   	  $.post("/TP/commodityAction?type=selete", 
		  {typeName:name, typeValue:value},function(data,status){
              $("#datagrid").datagrid("loadData", JSON.parse(data));             
  		});  
   }
    function addInfo(){
        var checkedItems = $('#datagrid').datagrid('getChecked');
        var check = [];
        $.each(checkedItems, function(index, item){
            check.push(item.id);
        });
        var dg =$("#datagrid");
        var opts =dg.datagrid("options");
        var _pageNumber =opts.pageNumber;
        var _pageSize =opts.pageSize;
        $.post("/TP/commodityAction?type=push",
                {check:check.toString()},function(data,status){
                    alert("上架成功");
                    SearchTrainee(_pageNumber,_pageSize);
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
		$.post("/TP/commodityAction?type=delete", 
		  {check:check.toString()},function(data,status){
		  	    alert("下架成功");
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
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteInfo();">商品下架</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addInfo();">商品上架</a>
                <div class="searchcon">
                    <input id="ss" class="easyui-searchbox" data-options="searcher:search, prompt:'请输入要搜索的商品信息',menu:'#mm'" 
					style="width:440px;margin-top:-2px;" />
					<div id="mm" style="width:220px"">
   						 <div data-options="name:'id',iconCls:'icon-ok'">商品ID</div>
    					 <div data-options="name:'name',iconCls:'icon-ok'">商品名称</div>
    					 <div data-options="name:'lucene',iconCls:'icon-ok'">搜索的信息</div>
					</div>
                </div>
		 	</div>
            <div id="datagrid" style="width:auto;"></div>
         </div>
    </div>
</body>
</html>
