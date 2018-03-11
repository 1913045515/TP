<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/masonry-docs.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function updateInfo(){
		var name=document.getElementById("name").value;
		var describle=document.getElementById("describle").value;
		var path=document.getElementById("path").value;
		  $.post("/TP/classificationAction?type=save", {  
                name:name,
                describle:describle,
                path:path
           }, function(data) {        		
             	var flag=data[0];
             	if(flag==1){
             		  document.getElementById("dialogs").innerHTML="<h3>添加成功！</h3>";
      				  dialog();
             	}else if(flag==0){
             		  document.getElementById("dialogs").innerHTML="<h3>添加失败！</h3>";
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
            <span class="columntitle">类型信息</span>
            <a href="typeList.jsp"><input type="button" name="submit" class="btn" value="返回上一个界面" style="margin-left: 900px;"/></a>
        </div>
        <div class="fmcontainer">
            <table class="inputtable" width="100%">
                <tr>
                    <td class="leftlabel">类型名称：</td><td><input id="name" type="text"/></td> 
                    <td class="leftlabel">类型描述：</td><td><input id="describle" type="text"/></td>                           
                </tr>
                <tr>               	     
                    <td class="leftlabel">类型路径：</td><td><input id="path" type="text"}"/></td>       
                    <td class="leftlabel">保存信息：</td><td><input type="button" name="submit" class="btn" value="确定添加" onclick="updateInfo();"/></td>                         
                </tr>  
           </table>     
            </div>
        </div>
    </div>
</body>
<script type="text/javascript"  src="js/masonry-docs.min.js"></script>
<script type="text/javascript" src="js/common/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
