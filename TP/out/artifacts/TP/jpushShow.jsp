<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JPush推送界面</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/global.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	var names = "name1";
	var values = "nameValue1";
	var strongName1 = "strong11";
	var strongName2 = "strong21";
	var buttonName = "button1";
	var number = 3;
	var count = 1;
	$(function() {
		$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {
			e.stopPropagation();
		});
	});
	window.onload = function() {
		document.getElementById('areaId').onkeyup = function () {
			if (this.value.length >220) {
				this.value = this.value.substr(0,220);
			}else{
				number=220-this.value.length;
				document.getElementById('numberId').innerHTML="<em>"+number+"</em>";
			}
		}
	};
	function addInfo() {
		var s = document.getElementById("addli");
		var a = document.createElement('strong');
		var a2 = document.createElement('strong');
		var input1 = document.createElement('input');
		var input2 = document.createElement('input');
		var b = document.createElement('input');
		a.setAttribute('id', strongName1);
		a.setAttribute('name', strongName1);
		a2.setAttribute('id', strongName2);
		a2.setAttribute('name', strongName2);
		input1.setAttribute('type', 'text');
		input1.setAttribute('id', names);
		input1.setAttribute('name', names);
		input1.setAttribute('type', 'text');
		input1.setAttribute('style', "width: 120px;height: 27px;");
		b.setAttribute('id', buttonName);
		b.setAttribute('name', buttonName);
		b.setAttribute('type', 'button');
		b.setAttribute('value', '删除');
		b
				.setAttribute('style',
						"margin-left:5px;width: 70px;border-radius:4px;background-color: #FAFAFA;");
		b.setAttribute('onclick', "deleteInfo(" + names + "," + values + ","
				+ strongName1 + "," + strongName2 + "," + buttonName + ")");
		input2.setAttribute('type', 'text');
		input2.setAttribute('style', "width: 120px;height: 27px;");
		input2.setAttribute('id', values);
		input2.setAttribute('name', values);
		a.innerHTML = "<br><strong style='margin-left: 100px;margin-right: 5px;margin-top: 10px;'>键:</strong>";
		a2.innerHTML = "<strong style='margin-left: 24px;margin-right: 5px;margin-top: 10px;'>值:</strong>";
		s.appendChild(a);
		s.appendChild(input1);
		s.appendChild(a2);
		s.appendChild(input2);
		s.appendChild(b);
		count = count + 1;
		names = names.substring(0, names.length - 1) + count;
		values = values.substring(0, values.length - 1) + count;
		strongName1 = strongName1.substring(0, strongName1.length - 1) + count;
		strongName2 = strongName2.substring(0, strongName2.length - 1) + count;
		buttonName = buttonName.substring(0, buttonName.length - 1) + count;	
		$("#addli :button").click(function(event){
			// 阻止事件冒泡到DOM树上
			event.stopPropagation(); // 只执行button的click，如果注释掉该行，将执行button、p和div的click
		});
	};

	function deleteInfo(names, values, strongName1, strongName2, buttonName) {
		var s = document.getElementById("addli");
		s.removeChild(names);
		s.removeChild(values);
		s.removeChild(buttonName);
		s.removeChild(strongName1);
		s.removeChild(strongName2);
	};
	
	function sendJPush(){
		var themes=document.getElementById("usersId").value;
		var content=document.getElementById("areaId").value;
		var timeToLive=document.getElementById("selectId").value;
		var jsonKey=new Array();
		jsonKey={};
		jsonKey.themes=themes;
		jsonKey.content=content;
		jsonKey.timeToLive=timeToLive;
		var jsonKeyResult = JSON.stringify(jsonKey);  
		var files = $("#addli").find("input[type='text']");
		var count=2;
		var jsonResult=new Array();
		var i=0;
		var j=0;
		var flag=0;
		 files.each(function(){
    	 	if($(this).val()==""){
				flag=1;
    	 	 }
    	});
    	if(flag==0){
    		 files.each(function(){
    	 	if(count%2==0){
    	 		jsonResult[i]={};
    	 		jsonResult[i].key=$(this).val(); 		
    	 		i++;
    	 	}else{
    	 		jsonResult[j].value=$(this).val();
    	 		j++;
    	 	}       	
        		count++;
    		});
    		var jsonText = JSON.stringify(jsonResult);  
    		  $.post("/TP/jPushAction", {jsonKeyResult:jsonKeyResult,jsonResult:jsonText},function(data,status){
      			if(status=="success"){
      				document.getElementById("dialogs").innerHTML="<h3>推送成功</h3>";
      				dialog();
      			}
           },"json");
    	}else{
    		document.getElementById("dialogs").innerHTML="<h3>健值不能为空，请重新填写！</h3>";
      		dialog();
    	}
	   };
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
				<button id="buttonId" type="button" data-dismiss="modal" style="color: #FFFFFF;background-color:#FB8F02; text-align:center;
            	  padding:10px;border: 1px solid #dedede;-moz-border-radius: 15px;-webkit-border-radius: 15px;  border-radius:15px;vertical-align:middle;">我知道了
            	</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



	<div class="columnconn">
		<span class="columntitle">推送信息</span>
	</div>
	<div
		style="float:left;margin-left:80px;margin-top:5px;width:70%;height:90%;">
		<div>
			<div>
				<strong style="font-size: 20px;">主题</strong> <input type="text"
					id="usersId" name="usersId"
					style="margin-left: 10px;margin-top: 2px;width: 730px;height: 30px" /></br>
			</div>
			<div
				style="width: 780px;height:210px;border:1px solid #BDC6CD;margin-top: 20px;background-color: #FFFFFF;">
				<div
					style="margin-left: 15px;padding-top:5px;font-weight:bold;font-size: 20px;">内容</div>
				<div
					style="margin-left: 15px;margin-top:2px;background-color: #F8F8F8;height: 170px;margin-right: 10px;">
					<div style="margin-left:10px;padding-top:10px">
						<textarea id="areaId" name="areaName" cols="108" rows="7"
							style="background-color: #FAFAFA; "></textarea>
					</div>
					<div style="margin-left: 15px;padding-top:8px;">
						您还剩下<em id="numberId" style="font-size: 20px;">220</em>个汉字可以输入
					</div>
				</div>
			</div>
		</div>

		<div style="margin-top:20px;" id="divId2">
			<nav class="navbar navbar-default" role="navigation">
			<div>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <strong style="font-size: 20px;">可选设置</strong>
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li
								style="width:800px;margin-top: 3px;margin-left: 10px;height: 30px;font-size:large;"
								data-stopPropagation="true"><strong>离线消息保留时长:</strong> <select id="selectId"
								name="selectId" style="margin-left: 10px;width: 150px;">
									<option value="21600">六个小时</option>
									<option value="43200">半天</option>
									<option value="86400">一天</option>
									<option value="172800">两天</option>
									<option value="864000">十天</option>
							</select></li>
							<li class="divider"></li>
							<li id="addli"
								style="width:800px;margin-top: 3px;margin-left: 10px;height:100%;font-size:large;"
								data-stopPropagation="true"><strong>附加字段:</strong> <strong
								style="margin-left: 15px;margin-right: 5px;">键:</strong><input
								name="name0" id="name0" type="text"
								style="width: 120px;height: 27px;" /> <strong
								style="margin-left: 20px;margin-right: 5px;">值:</strong><input
								name="nameValue0" id="nameValue0" type="text"
								style="width: 120px;height: 27px;" /> <input type="button"
								onclick="addInfo();" value="添加"
								style="width: 70px;border-radius:4px;background-color: #FAFAFA" />
							</li>
						</ul></li>
				</ul>
			</div>
			</nav>
		</div>
	</div>
	<div style="float:left;margin-top:300px;">
		<button onclick="sendJPush()"
			style="background-color:#50C0D8; width:100px; height:30px; margin-top:3px;margin-left:10px;border-radius:10px;">推送信息</button>
	</div>
</body>
</html>