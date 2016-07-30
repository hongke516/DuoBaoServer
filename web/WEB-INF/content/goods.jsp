<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-30
  Time: 下午3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="dependences.jsp" %>
    <title>添加商品</title>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-xs-12 col-sm-1">
            <%--布局居中--%>
        </div>
        <%--待写--%>
        <div class="col-xs-12 col-sm-10">
            <%--bootstrap的标签页（Tab）插件--%>
            <ul id="myBlogTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#add" data-toggle="tab">
                        待写
                    </a>
                </li>
                <li>
                    <a href="#category" data-toggle="tab">待写</a>
                </li>
                <li>
                    <a href="#comment" data-toggle="tab">待写</a>
                </li>
                <li>
                    <a href="#setting" data-toggle="tab">待写</a>
                </li>
            </ul>
            <div id="myBlogTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="add">
                    <form class="form-horizontal" role="form" id="addBlogLogForm"  onsubmit="return false">
                        <%--待写--%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="label label-default" style="font-size: 24px;">添加商品</div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-12 col-md-2">
                                    <select class="form-control" id="logType">

                                    </select>
                                </div>
                                <div class="col-xs-12 col-md-10">
                                    <input class="form-control" name="blogLog.logTitle" id="logTitle" type="text" placeholder="请输入博文标题">
                                </div>
                            </div>
                        </div>

                        <%--待写--%>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <span class="label label-default" style="font-size: 16px;">待写</span>
                                </div>
                            </div>
                            <div class="panel-body">
                                <textarea id="logSummary" rows="5" placeholder="待写" style="width: 100%"></textarea>
                            </div>
                        </div>

                        <%--待写--%>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default" style="font-size: 16px;">待写</span>
                                <span class="text-info">待写</span>
                            </li>
                            <li class="list-group-item">
                                <input class="form-control"  id="logGroup" type="text" placeholder="分组名在50字符以内">
                            </li>
                            <li class="list-group-item" id="logGroups">

                            </li>
                        </ul>

                        <%--待写--%>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default" style="font-size: 16px;">待写</span>
                                <span class="text-info">待写</span>
                            </li>
                            <li class="list-group-item">
                                <input class="form-control"  id="logTagInput" type="text" placeholder="待写">
                            </li>
                        </ul>

                        <%--待写--%>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default" style="font-size: 16px;">待写</span>
                                <span class="text-info">待写</span>
                            </li>
                            <li class="list-group-item" id="categoryLi">

                            </li>
                        </ul>

                        <%--待写--%>
                        <div class="form-group text-center" style="margin: 0 15px;">
                            <div class="row">
                                <div class="col-xs-12 col-md-5">

                                </div>
                                <div class="col-xs-4 col-md-2">
                                    <input class="form-control btn btn-success" id="issueNow" type="submit" value="待写">
                                </div>

                                <div class="col-xs-12 col-md-5">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="tab-pane fade" id="category">
                    <p>正在开发中...</p>
                </div>
                <div class="tab-pane fade" id="comment">
                    <p>正在开发中...</p>
                </div>
                <div class="tab-pane fade" id="setting">
                    <p>正在开发中...</p>
                </div>
                <%--tab-content--%>
            </div>
            <div class="col-xs-12 col-sm-1">
                <%--布局居中--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
