<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-9
  Time: 下午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="dependences.jsp"%>
    <title>提示信息</title>
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
    <%@include file="header.jsp"%>
    <div class="col-xs-12 col-lg-4">

    </div>
    <div class="col-xs-12 col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title">
                    <%--<div class="text-center">${promptInfo.title}</div>--%>
                    <div class="text-center">${promptInfo.title}</div>
                </div>
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <li class="list-group-item text-center">
                        ${promptInfo.message}
                    </li>
                    <li class="list-group-item">
                        <div class="row text-center">
                            <div class="col-xs-12 col-lg-6">
                                <s:if test="%{#request.promptInfo.togo!=null }">
                                    <a href="${pageContext.request.contextPath}${promptInfo.togo}" class="btn btn-success">解决</a>
                                </s:if>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <button type="button" class="btn btn-info" id="back">返回</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-lg-4">

    </div>
</div>
</body>
</html>
