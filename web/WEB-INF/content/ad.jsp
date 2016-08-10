<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-10
  Time: 上午10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>广告图片</title>
</head>
<body>
<div class="container">
    <%@include file="util/header.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-lg-1">
            <%--布局居中--%>
        </div>
        <%--Bootstrap之Carousel不能自动播放的解决办法 http://www.weste.net/2014/7-15/97829.html--%>
        <div class="col-xs-12 col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="text-center">广告滚动效果</div>
                    </div>
                </div>
                <div class="panel-body">
                    <div id="carousel-ad" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <s:iterator var="l" value="#request.adPageBean.queryResultList" status="L">
                                <s:if test="#L.first">
                                    <li data-target="#carousel-ad" data-slide-to="0" class="active"></li>
                                </s:if>
                                <s:else>
                                    <li data-target="#carousel-ad" data-slide-to="${L.index}"></li>
                                </s:else>
                            </s:iterator>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <s:iterator var="l" value="#request.adPageBean.queryResultList" status="L">
                                <s:if test="#L.first">
                                    <div class="item active"><img class="img-responsive" src="${l.image}"></div>
                                </s:if>
                                <s:else>
                                    <div class="item"><img class="img-responsive" src="${l.image}"></div>
                                </s:else>
                            </s:iterator>
                        </div>
                    </div>
                </div>
            </div>

            <ul class="list-group">
                <li class="list-group-item text-center">
                    <label for="image">广告图片地址</label>
                </li>

                <li class="list-group-item text-center">
                    <s:form action="/ad/create" method="POST" class="form-horizontal" role="form" id="addAd">

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
            </ul>

            <ul class="list-group">
                <li class="list-group-item text-center">
                    <strong>删除广告</strong>
                </li>
                <s:iterator var="l" value="#request.adPageBean.queryResultList" status="L">
                    <li class="list-group-item text-center">
                        <div class="row">
                            <div class="col-xs-12 col-lg-9">
                                <img src="${l.image}" width="140px" height="140px"/>
                            </div>

                            <div class="col-xs-12 col-lg-3">
                                <a class="btn btn-danger"
                                   href="${pageContext.request.contextPath}/ad/delete?adId=${l.id}">删除</a>
                            </div>
                        </div>
                    </li>
                </s:iterator>

            </ul>
        </div>

        <div class="col-xs-12 col-lg-1">
            <%--布局居中--%>
        </div>
    </div>
</div>
</body>
</html>
