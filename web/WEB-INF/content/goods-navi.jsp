<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-9
  Time: 上午11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>商品管理</title>
</head>
<body>
<div class="container">
    <%@include file="util/header.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-sm-1">
            <%--布局居中--%>
        </div>
        <%--待写--%>
        <div class="col-xs-12 col-sm-10">

            <%--分类管理--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="text-center">分类管理</div>
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27优选商品%27">优选商品</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27手机平板%27">手机平板</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27电脑办公%27">电脑办公</a>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27数码影音%27">数码影音</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27女性时尚%27">女性时尚</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27美食天地%27">美食天地</a>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27潮流新品%27">潮流新品</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27网易周边%27">网易周边</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow"
                                       href="${pageContext.request.contextPath}/goods/cate?page=1&size=10&sort=%27其他商品%27">其他商品</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="text-center">是否参与夺宝</div>
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow" href="${pageContext.request.contextPath}/goods/page">所有商品</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow" href="#">新品</a>
                                </div>
                                <div class="col-xs-12 col-lg-4 text-center">
                                    <a rel="nofollow" href="#">下架</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="text-center">广告管理</div>
                    </div>
                </div>
                <div class="panel-body text-center">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/ad/list">广告管理</a>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-1">
            <%--布局居中--%>
        </div>
    </div>
</div>
</body>
</html>
