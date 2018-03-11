<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>校园二手交易信息网站设计与实现</title>
<link href="css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common/jquery.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
</head>
<body>
<div id="FrameTop" class="frame-top">
    	<div class="topdata">
            <div id="SysIcon"></div>
            <h1 class="systitle">校园二手交易信息网站设计与实现</h1>
          <div class="siteinfo">
           	<p>欢迎您，${users.nickName}</p>
                <div class="toprightmenu">
                    <a href="usersAction?method=getOut&userId=${users.id}">退出系统</a>
                </div>
          </div>
      </div>
    </div>
    
    <!-- 左侧菜单-->
    <div class="frame-left">
 		<ul class="leftmenu">
        	<li>
            	<a class="dropmenu">商品管理</a>
                 <ul class="submenu">
                	<li><a target="mainFrame" href="list.jsp">商品查看</a></li>
                    <li><a target="mainFrame" href="list.jsp">商品下架</a></li>
                </ul>
            </li>
            <li>
            	<a class="dropmenu">用户管理</a>
                 <ul class="submenu">
                	<li><a target="mainFrame" href="usersList.jsp">用户信息查询</a></li>
                    <li><a target="mainFrame" href="usersList.jsp">用户信息修改</a></li>
                </ul>
            </li>
            <li>
            	<a class="dropmenu">分类管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="typeList.jsp">分类查询</a></li>
                    <li><a target="mainFrame" href="typeAdd.jsp">分类添加</a></li>
                    <li><a target="mainFrame" href="typeList.jsp">分类修改</a></li>
                </ul>
            </li>
            <li>
            	<a class="dropmenu">用户举报管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="reportList.jsp">举报查询</a></li>
                </ul>
            </li>
             <li>
            	<a class="dropmenu">订单管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="orderList.jsp">订单查询</a></li>
                    <li><a target="mainFrame" href="orderList.jsp">订单修改</a></li>
                </ul>
            </li>
             <li>
            	<a class="dropmenu">校区管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="campusList.jsp">校区查询</a></li>
                    <li><a target="mainFrame" href="campusAdd.jsp">校区添加</a></li>
                    <li><a target="mainFrame" href="campusList.jsp">校区修改</a></li>
                </ul>
            </li>
            <li>
            	<a class="dropmenu">平台交易金额管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="platformList.jsp">用户金额查询</a></li>
                    <li><a target="mainFrame" href="platformList.jsp">用户金额修改</a></li>
                </ul>
            </li>
               <li>
            	<a class="dropmenu">JPush信息推送</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="jpushShow.jsp">手机端信息推送</a></li>
                </ul>
            </li>
             <li>
            	<a class="dropmenu">邮件信息管理</a>
                <ul class="submenu">
                	<li><a target="mainFrame" href="emailSend.jsp">邮件发送</a></li>
                    <li><a target="mainFrame" href="emailList.jsp">邮件接收</a></li>
                </ul>
            </li>
              <li>
            	<a class="dropmenu">信息统计</a>
                <ul class="submenu">
                    <li><a target="mainFrame" href="bingtu.jsp">各类商品销量统计</a></li>
                </ul>
            </li>
        </ul>
</div>
    <!-- 内容区域-->
    <div class="frame-main">
    	<iframe id="MainFrame" scrolling="auto" frameborder="no" name="mainFrame" src="list.jsp">
        </iframe>
	</div>
</body>
</html>
