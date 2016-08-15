<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-10
  Time: 上午10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>一元夺宝</title>
    <script>
        $(function () {
            // 回到顶部
            $.goup({
                trigger: 100,
                bottomOffset: 150,
                locationOffset: 100,
                title: '顶部',
                titleAsText: true
            });
        });
    </script>
    <style type="text/css">
        .none-padding {
            padding: 0 !important;
        }

        .col-lg-2dot4 {
            position: relative;
            min-height: 1px;
            padding-right: 15px;
            padding-left: 15px;
        }

        @media (min-width: 1200px) {
            .col-lg-2dot4 {
                float: left;
            }

            .col-lg-2dot4 {
                width: 20%;
            }

            .col-lg-pull-2dot4 {
                right: 20%;
            }

            .col-lg-push-2dot4 {
                left: 20%;
            }

            .col-lg-offset-2dot4 {
                margin-left: 20%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <%@include file="util/header_home.jsp" %>
    <%--导航--%>
    <div class="row">
        <div class="col-xs-12 col-lg-2 none-padding">
            <div class="list-group-item" style="background-color: #DB3652">
                <strong style="color: #FFFFFF">商品分类</strong>
            </div>
        </div>
        <div class="col-xs-12 col-lg-10 text-center">
            <div class="row">
                <div class="col-xs-12 col-lg-8">
                    <div class="list-group-item" style="border: none;">
                        <div class="row hidden-xs">
                            <div class="col-xs-12 col-lg-2">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/"><strong>首页</strong></a>
                            </div>
                            <div class="col-xs-12 col-lg-2">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=优选商品"><strong>优选商品</strong></a>
                            </div>
                            <div class="col-xs-12 col-lg-2">
                                <a rel="nofollow" href="#"><strong>最新揭晓</strong></a>
                            </div>
                            <div class="col-xs-12 col-lg-2">
                                <a rel="nofollow" href="#"><strong>晒单分享</strong></a>
                            </div>
                            <div class="col-xs-12 col-lg-2">
                                <a rel="nofollow" href="#"><strong>发现</strong></a>
                            </div>
                            <div class="col-xs-12 col-lg-2">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-4">
                    <div style="width: 200px; height: 36px; background-color: #DB3652;">
                        <a rel="nofollow" href="#" style="text-decoration: none;">
                            <img src="${pageContext.request.contextPath}/images/ic_cart_black_white.png" height="33px">
                            <span class="text-center">
                                <span style="color: #FFFFFF">清&nbsp;&nbsp;单</span>
                                <span class="badge">0</span>
                            </span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--分类--%>
    <div class="row">
        <div class="col-xs-12 col-lg-2 none-padding">
            <ul class="list-group">
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/all?page=1&size=40"><strong style="color: #FFFFFF">所有商品</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=手机平板"><strong style="color: #FFFFFF">手机平板</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=电脑办公"><strong style="color: #FFFFFF">电脑办公</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=数码影音"><strong style="color: #FFFFFF">数码影音</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=女性时尚"><strong style="color: #FFFFFF">女性时尚</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=美食天地"><strong style="color: #FFFFFF">美食天地</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=潮流新品"><strong style="color: #FFFFFF">潮流新品</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=网易周边"><strong style="color: #FFFFFF">网易周边</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=其他商品"><strong style="color: #FFFFFF">其他商品</strong></a>
                </li>
            </ul>
        </div>

        <%--滚动的广告--%>
        <div class="col-xs-12 col-lg-8 none-padding ">
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
        <div class="col-xs-12 col-lg-2 none-padding" style="background-color: #DB3652">
            <ul class="list-group">
                <li class="list-group-item text-center" style="background-color: #DB3652">
                    <span style="color: #FFFFFF;">毛遂自荐</span>
                </li>
                <li class="list-group-item">
                    <p style="color: #DB3652;">站长:朱占全</p>
                    <div style="color: #DB3652;">电话:133-5291-6252</div>
                    <span style="color: #DB3652;">邮箱:</span>
                    <a rel="nofollow" href="mailto:qingyan@fozoto.com" style="color: #DB3652;">qingyan@fozoto.com</a>
                    <p style="color: #DB3652;"></p>
                    <p style="color: #DB3652;">&nbsp;&nbsp;&nbsp;&nbsp;本人目前住在宝安区,在深圳找JavaWeb和Android的工作,望各位HR能给小弟一个面试的机会,感激不尽!</p>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">

                </li>
                <li class="list-group-item" style="background-color: #DB3652">

                </li>
                <li class="list-group-item text-center">
                    <a rel="nofollow" class="text-success" href="${pageContext.request.contextPath}/goods-navi">后台管理入口</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="stick_hide hidden-xs" hidden="hidden">
        <%@include file="util/navi-stick.jsp"%>
    </div>

    <%--优选商品--%>
    <div>
        <label for="great">优选商品</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=优选商品">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="great">
        <s:iterator var="l" value="#request.greatPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--手机平板--%>
    <div>
        <label for="phone">手机平板</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=手机平板">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="phone">
        <s:iterator var="l" value="#request.phonePiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6 text-left">
                                <div>${l.done}</div>
                                <div>已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6 text-left">
                                <div>${l.last}</div>
                                <div>剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--电脑办公--%>
    <div>
        <label for="pc">电脑办公</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=电脑办公">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="pc">
        <s:iterator var="l" value="#request.pcPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--数码影音--%>
    <div>
        <label for="digital">数码影音</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=数码影音">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="digital">
        <s:iterator var="l" value="#request.digitalPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--女性时尚--%>
    <div>
        <label for="women">女性时尚</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=女性时尚">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="women">
        <s:iterator var="l" value="#request.womenPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--美食天地--%>
    <div>
        <label for="food">美食天地</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=美食天地">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="food">
        <s:iterator var="l" value="#request.foodPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--潮流新品--%>
    <div>
        <label for="new">潮流新品</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=潮流新品">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="new">
        <s:iterator var="l" value="#request.newPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--网易周边--%>
    <div>
        <label for="yi">网易周边</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=网易周边">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="yi">
        <s:iterator var="l" value="#request.yiPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <%--其他商品--%>
    <div>
        <label for="other">其他商品</label>
        <div class="text-right"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=其他商品">更多商品，点击查看>></a></div>
    </div>
    <div class="row" id="other">
        <s:iterator var="l" value="#request.otherPiece" status="L">
            <div class="col-lg-2dot4 none-padding">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}"><img src="${l.image}"/></a>
                    <div class="caption">
                        <p><a href="${pageContext.request.contextPath}/detail?goodsId=${l.goodsId}">${l.intro}</a></p>
                        <p>总需:${l.total}人次</p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar"
                                 aria-valuenow="${l.done}" aria-valuemin="0" aria-valuemax="${l.total}"
                                 style="width: ${l.degree}%;">
                                <span class="sr-only">${l.degree}% 完成</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-left">${l.done}</div>
                                <div class="text-left">已参与人次</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="text-right">${l.last}</div>
                                <div class="text-right">剩余人次</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"><img
                                        src="${pageContext.request.contextPath}/images/cart_normal.png" width="36px"
                                        height="36px"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
</body>
</html>
