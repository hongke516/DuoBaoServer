<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-11
  Time: 下午11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp"%>
    <title>个人中心</title>
    <script>

        $(document).ready(function() {
            if(location.hash) {
                $('a[href=' + location.hash + ']').tab('show');
            }
            $(document.body).on("click", "a[data-toggle]", function(event) {
                location.hash = this.getAttribute("href");
            });
        });
        $(window).on('popstate', function() {
            var anchor = location.hash || $("a[data-toggle=tab]").first().attr("href");
            $('a[href=' + anchor + ']').tab('show');
        });
    </script><script>

    $(document).ready(function() {
        if(location.hash) {
            $('a[href=' + location.hash + ']').tab('show');
        }
        $(document.body).on("click", "a[data-toggle]", function(event) {
            location.hash = this.getAttribute("href");
        });
    });
    $(window).on('popstate', function() {
        var anchor = location.hash || $("a[data-toggle=tab]").first().attr("href");
        $('a[href=' + anchor + ']').tab('show');
    });
</script>
</head>
<body>
<div class="container">
    <%@include file="util/header_home.jsp"%>
    <div class='container-fluid'>
        <div class='row'>
            <!--
              选项卡：通过BS的类来控制选项卡的样式
            -->
            <div class="col-xs-12 col-lg-2">
                <ul class='nav nav-pills nav-stacked text-center'>
                    <li><div class="text-danger"><strong>我的夺宝</strong></div></li>
                    <hr style="border:1px solid #888888" width="100%" size="1" />
                    <li class='active'><a href='#one' data-toggle='tab'>夺宝记录</a></li>
                    <li><a href='#lucky' data-toggle='tab'>幸运记录</a></li>
                    <hr style="border:1px solid #E3E3E3" width="100%" size="1" />
                    <li><a href='#buy' data-toggle='tab'>购买记录</a></li>
                    <hr style="border:1px solid #E3E3E3" width="100%" size="1" />
                    <li><a href='#packet' data-toggle='tab'>我的红包</a></li>
                    <li><a href='#stone' data-toggle='tab'>我的宝石</a></li>
                    <li><a href='#want' data-toggle='tab'>我的心愿单</a></li>
                    <li><a href='#show' data-toggle='tab'>我的晒单</a></li>
                    <hr style="border:1px solid #E3E3E3" width="100%" size="1" />
                    <li><a href='#detail' data-toggle='tab'>个人资料</a></li>
                    <li><a href='#basic' data-toggle='tab'>基本设置</a></li>
                    <li><a href='#name' data-toggle='tab'>实名认证</a></li>
                    <li><a href='#goods' data-toggle='tab'>收货地址</a></li>
                    <li><a href='#charge' data-toggle='tab'>充值记录</a></li>
                </ul>
            </div>
            <div class="col-xs-12 col-lg-10">
                <!--
              选项卡的内容定义
            -->
                <div class='tab-content'>
                    <%--夺宝记录--%>
                    <div class='tab-pane active' id='one'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#one">夺宝记录</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--幸运记录--%>
                    <div class='tab-pane' id='lucky'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#lucky">幸运记录</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--购买记录--%>
                    <div class='tab-pane' id='buy'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#buy">购买记录</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--我的红包--%>
                    <div class='tab-pane' id='packet'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#packet">我的红包</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--我的宝石--%>
                    <div class='tab-pane' id='stone'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#stone">我的宝石</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--我的心愿单--%>
                    <div class='tab-pane' id='want'><ol class="breadcrumb">
                        当前位置:
                        <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                        <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#want">我的心愿单</a></li>
                    </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--我的晒单--%>
                    <div class='tab-pane' id='show'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#show">我的晒单</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--个人资料--%>
                    <div class='tab-pane' id='detail'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#detail">个人资料</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--基本设置--%>
                    <div class='tab-pane' id='basic'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#basic">基本设置</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--实名认证--%>
                    <div class='tab-pane' id='name'>
                        <ol class="breadcrumb">
                        当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#name">实名认证</a></li>
                    </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--收货地址--%>
                    <div class='tab-pane' id='goods'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#goods">收货地址</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--充值记录--%>
                    <div class='tab-pane' id='charge'>
                        <ol class="breadcrumb">
                            当前位置:
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my">我的夺宝</a></li>
                            <li><a rel="nofollow" href="${pageContext.request.contextPath}/my#charge">充值记录</a></li>
                        </ol>
                        <div style="border: 1px solid #e3e3e3">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                    <div class="col-xs-12 col-lg-4">
                                        <img src="${pageContext.request.contextPath}/images/ic_sad.png">
                                        <p>
                                            您还没有购买记录
                                        </p>
                                        <div>
                                            <a href="${pageContext.request.contextPath}/" class="btn btn-danger">马上去购买</a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-lg-4">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
