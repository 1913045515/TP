<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>完整demo</title>
    <link rel="stylesheet" href="css/demo.css" type="text/css">
    <link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="themes/default/dialogbase.css" type="text/css">
    <link rel="stylesheet" href="themes/default/css/ueditor.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="themes/default/css/ueditor.min.css" type="text/css"> 
    <script type="text/javascript" charset="utf-8" src="js/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="js/zh-cn.js"></script>
    <script src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.core.js"></script>
   	<script src="js/bootstrap.min.js"></script>
   	
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
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
				<button type="button" data-dismiss="modal" style="color: #FFFFFF;background-color:#FB8F02; text-align:center;
            	  padding:10px;border: 1px solid #dedede;-moz-border-radius: 15px;-webkit-border-radius: 15px;  border-radius:15px;vertical-align:middle;">我知道了
            	</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div style="margin-left:20px;margin-top:5px;">
	<div>
        <h5>联系人:<input type="text" id="usersId" name="usersId" style="margin-left: 10px;margin-top: 2px;width: 800px;height: 25px"/></br>
        <h5>主题:<input type="text" id="themes" name="themes"  style="margin-left: 25px;margin-top:2px;width: 800px;height: 25px"/>
    </div>
	<div  style="float:left;">
		 内容：<script id="editor" type="text/plain" style="width:800px;height:300px;margin-left:50px;"></script>
	</div>
    <div style="float:right;margin-top:-400px;margin-right:20px;width:auto;clear:both;">
       <div class="zTreeDemoBackground left">
           <ul id="treeDemo" class="ztree" style="background-color: #FFFFFF;"></ul>
       </div>
       <button onclick="sendEmail()" style="background-color:#50C0D8; width:100px; height:28px; margin-top:3px;margin-left:10px;border-radius:15px;">发送邮件</button>      
    </div>
</div>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e);
    };
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e);
    };
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value);
    };
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    };
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml());
    };
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    };
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'));
    };
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor');
        alert(arr.join("\n"));
    };
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    };

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    };

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt);
    };

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    };
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    };
    function setFocus() {
        UE.getEditor('editor').focus();
    };
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    };
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    };
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    };

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    };

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    };
    
   	function dialog(){ 
     	$("#mymodal").modal("toggle");
   	};
     function sendEmail(){
     	 var usersId=document.getElementById("usersId").value;
     	 var themes=document.getElementById("themes").value;
     	 var flag=UE.getEditor('editor').hasContents();
     	 if(usersId==""){
     	 	 document.getElementById("dialogs").innerHTML="<h3>收件地址不能为空</h3>";
     	 	 dialog();
     	 }else if(themes==""){
     	 	document.getElementById("dialogs").innerHTML="<h3>邮件主题不能为空</h3>";
     	 	dialog();
     	 }else if(flag==false){
     	 	 document.getElementById("dialogs").innerHTML="<h3>邮件内容不能为空！</h3>";
     	 	 dialog();
     	 }else{    	 
     	 	var content=UE.getEditor('editor').getContent();
     	 	$.post("/TP/emailAction?type=eamil",{usersId:usersId,themes:themes,content:content},function(data,status){
    			if(data==true){
    				document.getElementById("dialogs").innerHTML="<h3>邮件发送成功！</h3>";
     	 			dialog();
    			}else{
    				document.getElementById("dialogs").innerHTML="<h3>邮件发送失败！</h3>";
     	 			dialog();
    			}
   			 },"json");	
     	 }
    }
</script>

<SCRIPT type="text/javascript">
	var result="";
    var setting = {
        view: {
            showIcon: showIconForTree
        },      
        data: {
        	key: {
				title:"t"
			},
            simpleData: {
                enable: true
            }
        },
      callback: {
		  onClick: onClick
	  }
    };
    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
	function onClick(event, treeId, treeNode, clickFlag) {
		if(!treeNode.isParent){
			if(clickFlag==1){
			 	result=treeNode.name+";";
				document.getElementById("usersId").value=result;
				result="";
			}else if(clickFlag==2){
				result=result+treeNode.name+";";
			 	document.getElementById("usersId").value=result;
			}		 
		}			
	}		
    $(document).ready(function(){
    	var nodesValue=new Array();
    	$.get("/TP/emailAction?type=tree",function(data,status){
    		$.each(data,function(n,value){
    			nodesValue[n]={};
    			nodesValue[n].name=value.name;
    			nodesValue[n].children=new Array();
    			for(var i=0;i<value.children.length;i++){
    				nodesValue[n].children[i]={};
    				nodesValue[n].children[i].name=value.children[i].name;
    			}    	
    		});
       		$.fn.zTree.init($("#treeDemo"), setting, data); 
   		 },"json");	
   		
    });
</SCRIPT>
</body>
</html>
