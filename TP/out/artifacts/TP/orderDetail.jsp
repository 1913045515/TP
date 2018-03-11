<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/masonry-docs.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes-data/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="themes-data/icon.css"/>
<script type="text/javascript">
	function updateInfo(){
		var oId=document.getElementById("oId").value;
		var name=document.getElementById("name").value;
		var price=document.getElementById("price").value;
		var state=document.getElementById("state").value;
		var remarks=document.getElementById("remarks").value;
		var time=document.getElementById("time").value;
		var number=document.getElementById("number").value;
		  $.post("/TP/orderAction?type=update", {  
                oId:oId,
                name:name,
                price:price,
                state:state,
                remarks:remarks,
                time:time,   
                number:number
           }, function(data) {     
             	var flag=data[0];
             	if(flag==1){
             		  document.getElementById("dialogs").innerHTML="<h3>修改成功！</h3>";
      				  dialog();
             	}else if(flag==0){
             		  document.getElementById("dialogs").innerHTML="<h3>修改失败！</h3>";
      		          dialog();
             	}
  		  });  
	}
	
	 function dialog(){ 
     		$("#mymodal").modal("toggle");
   		};
</script>
</head>
<body>

	<div class="modal" id="mymodal" tabindex="-1">
    <div class="modal-dialog">
    	<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">亲，您好</h4>
			</div>
			<div class="modal-body" id="dialogs">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" style="color: #FFFFFF;background-color:#50C0D8; text-align:center;
            	  padding:10px;border: 1px solid #dedede;-moz-border-radius: 15px;-webkit-border-radius: 15px;  border-radius:15px;vertical-align:middle;">我知道了
            	</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


    <div class="maincontainer">
    	<div class="columnconn">
            <span class="columntitle">学校信息</span>
            <a href="orderList.jsp"><input type="button" name="submit" class="btn" value="返回上一个界面" style="margin-left: 900px;"/></a>
        </div>
        <div class="fmcontainer">
            <table class="inputtable" width="100%">
                <tr>
                    <td class="leftlabel">订单号：</td><td><input id="oId" type="text" value="${list[0].oId}" disabled="true"/></td>
                    <td class="leftlabel">用户ID：</td><td><input id="uId" type="text" value="${list[0].uId}" disabled="true"/></td>  
                    <td class="leftlabel">商品ID：</td><td><input id="cId" type="text" value="${list[0].cId}" disabled="true"/></td>                            
                </tr>
                <tr>
                	<td class="leftlabel">商品名称：</td><td><input id="name" type="text" value="${list[0].name}"/></td>         
                    <td class="leftlabel">商品价格：</td><td><input id="price" type="text" value="${list[0].price}"/></td>  
                    <td class="leftlabel">商品数量：</td><td><input id="number" type="text" value="${list[0].number}"/></td>          
                </tr>  
                 <tr>
                 	<td class="leftlabel">订单状态：</td><td><input id="state" type="text" value="${list[0].state}"/></td>  
                 	<td class="leftlabel">下订单时间：</td><td><input class="easyui-datebox" id="time" type="text" value="${list[0].time}"/></td>  
                 	<td class="leftlabel">备注信息：</td><td><input id="remarks" type="text" value="${list[0].remarks}"/></td>                   	
                 </tr>
                 <tr>
                 	<td class="leftlabel">保存信息：</td><td><input type="button" name="submit" class="btn" value="确定修改" onclick="updateInfo();"/></td>        
                 </tr>
            </table>     
            </div>
        </div>
    </div>
</body>
<script type="text/javascript"  src="js/masonry-docs.min.js"></script>
<script type="text/javascript" src="js/common/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common/jquery.js"></script>
<script type="text/javascript" src="js-data/jquery.min.js"></script>
<script type="text/javascript" src="js-data/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js-data/easyui-lang-zh_CN.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
