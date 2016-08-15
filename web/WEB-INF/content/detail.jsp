<%@ page import="java.util.List" %>
<%@ page import="com.fozoto.duobao.model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-12
  Time: 下午6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fozoto.css"/>--%>
    <title>商品详情</title>
    <script>
        $(document).ready(function () {
            $.myChange = {
                change: function (j) {
                    $("#first").attr("src", j);
                }
            }

            // bootstrap图片自适应
            $(".auto img").addClass("carousel-inner img-responsive img-rounded");
        })
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
    <div class="row">
        <div class="col-xs-12 col-lg-5">
            <div class="thumbnail">
                <s:if test="%{#request.piece.trait!=null }">
                    <img src="${piece.trait}" style="position: absolute; top: 0px; left: 16px;">
                </s:if>
                <%!
                    int j = -1;
                %>
                <s:iterator var="l" value="#request.pictures" status="L">
                    <s:if test="#L.first">
                        <img style="border:solid #E3E3E3 1px;" id="first" src="${l.image}">
                    </s:if>
                </s:iterator>
                <div class="caption">
                    <div class="row">
                        <s:iterator var="l" value="#request.pictures" status="L">
                            <s:if test="%{#l.type==1}">
                                <%
                                    j++;
                                %>
                                <div class="col-lg-2dot4 to_shape">
                                    <%
                                        if (j == 0) {
                                    %>
                                    <img style="border:solid #E3E3E3 1px;"
                                         onclick="javascript:$.myChange.change('${pictures[0].image}')"
                                         class="preview_<%=j%>" src="${l.image}">
                                    <%
                                        }
                                    %>
                                    <%
                                        if (j == 1) {
                                    %>
                                    <img style="border:solid #E3E3E3 1px;"
                                         onclick="javascript:$.myChange.change('${pictures[1].image}')"
                                         class="preview_<%=j%>" src="${l.image}">
                                    <%
                                        }
                                    %>
                                    <%
                                        if (j == 2) {
                                    %>
                                    <img style="border:solid #E3E3E3 1px;"
                                         onclick="javascript:$.myChange.change('${pictures[2].image}')"
                                         class="preview_<%=j%>" src="${l.image}">
                                    <%
                                        }
                                    %>
                                    <%
                                        if (j == 3) {
                                    %>
                                    <img style="border:solid #E3E3E3 1px;"
                                         onclick="javascript:$.myChange.change('${pictures[3].image}')"
                                         class="preview_<%=j%>" src="${l.image}">
                                    <%
                                        }
                                    %>
                                    <%
                                        if (j == 4) {
                                    %>
                                    <img style="border:solid #E3E3E3 1px;"
                                         onclick="javascript:$.myChange.change('${pictures[4].image}')"
                                         class="preview_<%=j%>" src="${l.image}">
                                    <%
                                        }
                                    %>
                                </div>
                            </s:if>
                            <s:if test="#L.last">
                                <%
                                    j = -1;
                                %>
                            </s:if>
                        </s:iterator>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-xs-12 col-lg-7">
            <h4><strong>${piece.intro}</strong></h4>
            <p>${piece.remind}</p>
            <hr style="border:1px solid #E3E3E3" width="100%" size="1"/>
            <p>
                <s:if test="%{#request.piece.trait!=null }">
                    <span style="font-size: 22px;">十元夺宝</span>
                </s:if>
                <s:else>
                    <span style="font-size: 22px;">一元夺宝</span>
                </s:else>
                &nbsp;&nbsp;&nbsp;&nbsp;
                期号:${piece.issueId}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: #5e5e5e">每满总需人次，即抽取1人获得该商品</span>
            </p>
            <div class="row">
                <div class="col-xs-12 col-lg-6">
                    <div class="progress">
                        <div class="progress-bar progress-bar-warning" role="progressbar"
                             aria-valuenow="${piece.done}" aria-valuemin="0" aria-valuemax="${piece.total}"
                             style="width: ${piece.degree}%;">
                            <span class="sr-only">${piece.degree}% 完成</span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-2">
                    <span>已完成${piece.degree}%</span>
                </div>
                <div class="col-xs-12 col-lg-4">

                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-lg-6">
                    <span>总需人次:${piece.total}</span>
                    <span class="pull-right text-danger">剩余人次:${piece.last}</span>
                </div>
                <div class="col-xs-12 col-lg-6">

                </div>
            </div>

            <p>
            <div class="row">
                <div class="col-xs-12 col-lg-1" style="padding: 0!important;">
                    参与:
                </div>
                <div class="col-xs-12 col-lg-3" style="padding: 0!important;">
                    <div data-trigger="spinner">
                                <span class="input-group">
                                    <a class="input-group-addon" href="javascript:;" data-spin="down">-</a>
                                    <input class="form-control" maxlength="7" type="text" value="1"
                                           data-rule="quantity"/>
                                    <a class="input-group-addon" href="javascript:;" data-spin="up">+</a>
                                </span>
                    </div>

                </div>
                <div class="col-xs-12 col-lg-1" style="padding: 0!important;">
                    人次
                </div>
            <div class="col-xs-12 col-lg-3" style="padding: 0!important; background-color: #e3e3e3">
                <span class="text-danger">加大参与人次，夺宝在望！</span>
            </div>
            </div>
            </p>

            <p>
                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login"
                   class="btn btn-lg btn-danger">立即购买</a>
                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-lg btn-success"><img src="${pageContext.request.contextPath}/images/ic_cart_black_white.png" style="width: 24px;">加入清单</a>
            </p>

            <div class="well">
                <a href="${pageContext.request.contextPath}/gamester-login">请登录</a>,查看您的夺宝号
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-lg-1">

        </div>
        <div class="col-xs-12 col-lg-10">
            <s:iterator var="l" value="#request.pictures" status="L">
                <s:if test="%{#l.type==2}">
                    <div class="auto"><img src="${l.image}"></div>
                </s:if>
            </s:iterator>
            <hr style="border:1px solid #E3E3E3" width="100%" size="1"/>
            <strong>${piece.explains}</strong>
        </div>
        <div class="col-xs-12 col-lg-1">

        </div>
    </div>
</div>
</body>
</html>
