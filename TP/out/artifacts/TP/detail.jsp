<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/masonry-docs.css" rel="stylesheet" type="text/css" />
<link href="css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
</head>
<body>
    <div class="maincontainer">
    	<div class="columnconn">
            <span class="columntitle">商品信息</span>
            <a href="list.jsp"><input type="button" name="submit" class="btn" value="返回上一个界面" style="margin-left: 900px;"/></a>
        </div>
        <div class="fmcontainer">
            <table class="inputtable" width="100%">
                <tr>
                    <td class="leftlabel">商品 ID：</td><td><input name="" type="text" value="${list[0].id}" disabled="true"/></td>
                    <td class="leftlabel">商品名称：</td><td><input name="" type="text" value="${list[0].name}" disabled="true"/></td>
                    <td class="leftlabel">商品原价：</td><td><input name="" type="text" value="${list[0].originalPrice}" disabled="true"/></td>               
                </tr>
                <tr>
                  <td class="leftlabel">商品售价：</td><td><input name="" type="text" value="${list[0].price}" disabled="true"/></td>
                    <td class="leftlabel">商品数量：</td><td><input name="" type="text" value="${list[0].number}" disabled="true"/></td>
                    <td class="leftlabel">新旧程度：</td><td><input name="" type="text" value="${list[0].degree}" disabled="true"/></td>               
                </tr>
                <tr>
                   <td class="leftlabel">发布时间：</td><td><input name="" type="text" value="${list[0].releaseTime}" disabled="true"/></td>
                   <td class="leftlabel">编辑时间：</td><td><input name="" type="text" value="${list[0].editTime}" disabled="true"/></td>
                   <td class="leftlabel">商品状态：</td><td><input name="" type="text" value="${list[0].shelfState}" disabled="true"/></td>                  
                </tr>
                 <tr>
                   <td class="leftlabel">发布用户：</td><td><input name="" type="text" value="${list[0].usersId}" disabled="true"/></td>
                   <td class="leftlabel">商品分类：</td><td><input name="" type="text" value="${list[0].type}"/ disabled="true"></td>
                   <td class="leftlabel">举报次数：</td><td><input name="" type="text" disabled="true"/></td>
                 </tr>
                <tr>
                    <td class="leftlabel">商品描述：</td>
                    <td colspan="7">
                    	<textarea name="" cols="100" rows="7" disabled="true">${list[0].describe}</textarea>
                    </td>
                </tr>
            </table>
            <div style="margin-left: 5px;margin-right: 5px;margin-top: 5px;">
                <div class="duo__cell example__demo">
                    <div class="grid grid--images" data-js-module="imagesloaded-callback">
                        <div class="grid-sizer"></div>
                        	<c:forEach items="${list[0].path}" var="pathValue">
                        		 <div class="grid-image-item">
                            	  	<img src="${pathValue}">
                          		 </div>
							</c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript"  src="js/masonry-docs.min.js"></script>
<script type="text/javascript" src="js/common/datepicker/WdatePicker.js"></script>
</html>
