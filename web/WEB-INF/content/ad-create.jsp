<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-10
  Time: 上午10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp"%>
    <title>添加广告</title>
    <script>
        $(document).ready(function () {
            $("#back").click(function () {
                window.location.href = "javascript:history.go(-1)";
            });
        })
    </script>
</head>
<body>
<div class="container">
    <%@include file="util/header.jsp"%>
    <div class="col-xs-12 col-lg-2">

    </div>
    <div class="col-xs-12 col-lg-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title">
                    <div class="text-center">添加广告</div>
                </div>
            </div>
            <div class="panel-body">

                <ul class="list-group">
                    <li class="list-group-item text-center">
                        <s:form action="/ad/create" method="POST" class="form-horizontal" role="form" id="addAd">
                            <label for="image">广告图片地址</label>
                            <div class="row">
                                <div class="col-xs-12 col-lg-9">
                                    <input id="image" name="ad.image" class="form-control"/>
                                </div>
                                <div class="col-xs-12 col-lg-3">
                                    <button type="submit" class=" btn btn-success">添加广告</button>
                                </div>
                            </div>
                        </s:form>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-xs-12 col-lg-4"></div>
                            <div class="col-xs-12 col-lg-4 text-center">
                                <button type="button" class="btn btn-info" id="back">返回</button>
                                <a type="button" href="${pageContext.request.contextPath}/ad/list" class="btn btn-success" >查看效果</a>
                            </div>
                            <div class="col-xs-12 col-lg-4"></div>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>
    <div class="col-xs-12 col-lg-2">

    </div>
</div>
</body>
</html>
