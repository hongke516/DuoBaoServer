<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-12
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>清单</title>
</head>
<body>
<div class="container">
    <%@include file="util/header_home.jsp" %>
    <ul class="list-group" style="border: 1px solid #E3E3E3;">
        <li class="list-group-item well">
            <div class="row">
                <div class="col-xs-12 col-lg-1">
                    <input type="checkbox"/>
                </div>
                <div class="col-xs-12 col-lg-5">
                    商品名称
                </div>
                <div class="col-xs-12 col-lg-1">
                    价值(元)
                </div>
                <div class="col-xs-12 col-lg-1">
                    夺宝单价(元/人次)
                </div>
                <div class="col-xs-12 col-lg-1">
                    参与人次
                </div>
                <div class="col-xs-12 col-lg-1">
                    参与期数
                </div>
                <div class="col-xs-12 col-lg-1">
                    小计(元)
                </div>
                <div class="col-xs-12 col-lg-1">
                    操作
                </div>
            </div>
        </li>
        <li class="list-group-item">
            <div class="row">
                <div class="col-xs-12 col-lg-1">
                    <input type="checkbox"/>
                </div>
                <div class="col-xs-12 col-lg-5">
                    商品名称
                </div>
                <div class="col-xs-12 col-lg-1">
                    价值(元)
                </div>
                <div class="col-xs-12 col-lg-1">
                    夺宝单价(元/人次)
                </div>
                <div class="col-xs-12 col-lg-1">
                    参与人次
                </div>
                <div class="col-xs-12 col-lg-1">
                    参与期数
                </div>
                <div class="col-xs-12 col-lg-1">
                    小计(元)
                </div>
                <div class="col-xs-12 col-lg-1">
                    操作
                </div>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
