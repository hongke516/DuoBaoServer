<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-31
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>

    <title>管理</title>
    <script>

        // 检查是否有上一页
        function checkPrePage() {
            if (${goodsPage.currentPage>1}) {
                return true;
            } else {
                return false;
            }
        }
        // 检查是否有下一页
        function checkNextPage() {
            if (${goodsPage.currentPage<goodsPage.allPage}) {
                return true;
            } else {
                return false;
            }
        }

        $(document).ready(function () {

            // 翻页
            $("#turnTo").click(function () {
                if ($("#turnToValue").val() >${goodsPage.allPage}) {
                    $("#turnToValue").val("");
                    return false;
                }
                $("#turnTo").attr("href", "${pageContext.request.contextPath}/goods/page?page=" + $("#turnToValue").val()+"&size=10");
            });

            // 回到顶部
            $.goup({
                trigger: 100,
                bottomOffset: 150,
                locationOffset: 100,
                title: '顶部',
                titleAsText: true
            });

            /* $.ajax({
             url: "
            ${pageContext.request.contextPath}/goods/page",
             type: "POST",
             dataType: "json",
             async: true,
             data: {"page": 1, "size":10},
             success: function (result) {
             var goodsPage = $.parseJSON(result);
             // 总记录数
             var allRows = goodsPage.allRows;
             var currentPage = goodsPage.goodsPage;
             var pageSize = goodsPage.pageSize;
             // 总共分几页
             var allPage = goodsPage.allPage;
             $.each(goodsPage.queryResultList, function(index, element){
             alert(element.remind);
             });
             //alert("result="+result.allPage);
             //                    var goodsPage = $.parseJSON(result);
             //                    alert(goodsPage);

             },
             error: function () {
             alert("error");
             }
             });*/

        });
    </script>
</head>
<body>
<%
    int id = 0;
%>
<div class="container">
    <%@include file="util/header.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-sm-1">
            <%--布局居中--%>
        </div>
        <%--待写--%>
        <div class="col-xs-12 col-sm-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="text-center">商品管理</div>
                    </div>
                </div>
                <div class="panel-body">
                    <s:iterator var="l" value="#request.goodsPage.queryResultList">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="text-info">${l.intro}</span>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                        <img src="${l.image}" height="180px" width="210px"/>
                                        <s:if test="%{#l.trait!=''}">
                                            <img src="${l.trait}" width="40px" height="40px"/>
                                        </s:if>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                                        <h3 class="text-success">
                                            <a href="${pageContext.request.contextPath}/goods/detail?goodsId=${l.id}">${l.intro}</a>
                                        </h3>
                                        <ul class="list-group">
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                        价格:${l.price}
                                                    </div>
                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                        总需人次:${l.total}
                                                    </div>
                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                        每份人次:${l.per}
                                                    </div>
                                                </div>

                                            </li>
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                                        是否参与夺宝:
                                                        <s:if test="%{#l.available=='new'}">
                                                            <span class="text-success">新品</span>
                                                        </s:if>
                                                        <s:elseif test="%{#l.available=='old'}">
                                                            <span class="text-danger">下架</span>
                                                        </s:elseif>
                                                    </div>
                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">

                                                    </div>

                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">

                                                    </div>
                                                </div>
                                            </li>
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                        首次夺宝:${l.time}
                                                    </div>
                                                    <div class="col-xs-12 col-sm-12 col-md-12  col-lg-6">
                                                        重返夺宝:${l.retime}
                                                    </div>
                                                </div>

                                            </li>
                                        </ul>

                                    </div>
                                </div>
                                <div class="text-center">
                                    <span class="text-warning">${l.remind}</span>
                                </div>
                            </li>
                        </ul>
                    </s:iterator>
                </div>
            </div>

            <%--上一页、下一页、跳页--%>
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <ul class="pagination">
                        <li><a href="${pageContext.request.contextPath}/goods/page?page=1&size=10"
                               style="cursor: pointer;text-decoration: none;">首页</a></li>
                        <li><a onclick="return checkPrePage();"
                               href="${pageContext.request.contextPath}/goods/page?page=${goodsPage.currentPage-1}&size=10"
                               style="cursor: pointer;text-decoration: none;">上一页</a></li>
                        <li><a onclick="return checkNextPage();"
                               href="${pageContext.request.contextPath}/goods/page?page=${goodsPage.currentPage+1}&size=10"
                               style="cursor: pointer;text-decoration: none;">下一页</a></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/goods/page?page=${goodsPage.allPage}&size=10"
                               style="cursor: pointer;text-decoration: none;">末页</a></li>
                    </ul>
                </div>

                <%--跳页--%>
                <div class="col-xs-12 col-md-8">
                    <div class="pagination">
                        <span style="font-size: 10px; padding-left: 16px;">当前第<span
                                style="font-size: 23px;">${goodsPage.currentPage}</span>页<span style="font-size: 10px;">/总共有<span
                                style="font-size: 23px;">${goodsPage.allPage}</span>页</span></span>
                        <input class="pagination-control" id="turnToValue"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"
                               style="width:50px; margin-left: 10px;"
                                placeholder="页码"/>
                        <a id="turnTo" class="btn btn-success" style="padding: 3px; margin-left: 7px;"
                           href="${pageContext.request.contextPath}/goods/page"
                           style="cursor: pointer;text-decoration: none;">跳转</a>
                    </div>

                </div>
            </div>
            <div class="col-xs-12 col-sm-1">
                <%--布局居中--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
