<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-11
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.spinner.js"></script>
    <title>${sort}</title>
    <script>

        // 检查是否有上一页
        function checkPrePage() {
            if (${page>1}) {
                return true;
            } else {
                return false;
            }
        }
        // 检查是否有下一页
        function checkNextPage() {
            if (${page<num}) {
                return true;
            } else {
                return false;
            }
        }

        $(document).ready(function () {

            // 翻页
            $("#turnToAll").click(function () {
                if ($("#turnToValueAll").val() >${num}) {
                    $("#turnToValueAll").val("");
                    return false;
                }
                $("#turnToAll").attr("href", "${pageContext.request.contextPath}/all?page=" + $("#turnToValueAll").val() + "&size=40");
            });

            $("#turnToCate").click(function () {
                <s:else>
                if ($("#turnToValueCate").val() >${num}) {
                    $("#turnToValueCate").val("");
                    return false;
                }
                $("#turnToCate").attr("href", "${pageContext.request.contextPath}/cate?page=" + $("#turnToValueCate").val() + "&size=40&sort=${sort}");
                </s:else>
            });

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
</head>
<body>
<div class="container">
    <%@include file="util/header_home.jsp" %>
    <div class="hidden-xs">
        <%@include file="util/navi-stick.jsp" %>
    </div>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td class="text-center"><a
                    href="${pageContext.request.contextPath}/all?page=1&size=40"><strong>所有商品</strong></a></td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=手机平板"><strong>手机平板</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=电脑办公"><strong>电脑办公</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=数码影音"><strong>数码影音</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=女性时尚"><strong>女性时尚</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=美食天地"><strong>美食天地</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=潮流新品"><strong>潮流新品</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=网易周边"><strong>网易周边</strong></a>
            </td>
            <td class="text-center"><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=其他商品"><strong>其他商品</strong></a>
            </td>
        </tr>
        </tbody>
    </table>
    <s:if test="%{#request.sort==null}">
        <label for="sort">
            所有商品
        </label>
    </s:if>
    <s:else>
        <label for="sort">
                ${sort}
        </label>
    </s:else>
    <div id="sort">
        <s:iterator var="l" value="#request.sortPiece" status="L">
            <s:if test="%{#L.index%4==0}">
                <div class="row">
            </s:if>
            <div class="col-xs-12 col-lg-3">
                <div class="thumbnail">
                    <img src="${l.image}"/>
                    <div class="caption">
                        <p>${l.intro}</p>
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
                        <div class="well">
                            <p>
                            <div class="row">
                                <div class="col-xs-12 col-lg-4"  style="padding: 0!important;">
                                    我要参与:
                                </div>
                                <div class="col-xs-12 col-lg-8"  style="padding: 0!important;">
                                    <div data-trigger="spinner">
                                <span class="input-group">
                                    <a class="input-group-addon" href="javascript:;" data-spin="down">-</a>
                                    <input class="form-control" maxlength="7" type="text" value="1"
                                           data-rule="quantity"/>
                                    <a class="input-group-addon" href="javascript:;" data-spin="up">+</a>
                                </span>
                                    </div>
                                </div>
                            </div>
                            </p>


                            <p>
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-danger">立即购买</a>
                                <a rel="nofollow" href="${pageContext.request.contextPath}/gamester-login" class="btn btn-info">加入清单</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <s:if test="%{#L.index%4==3 || #L.last}">
                </div>
            </s:if>
        </s:iterator>
    </div>

    <%--上一页、下一页、跳页--%>
    <s:if test="%{#request.sort==null}">
        <div class="row">
            <div class="col-xs-12 col-md-4">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/all?page=1&size=40"
                           style="cursor: pointer;text-decoration: none;">首页</a></li>
                    <li><a onclick="return checkPrePage();"
                           href="${pageContext.request.contextPath}/all?page=${page-1}&size=40"
                           style="cursor: pointer;text-decoration: none;">上一页</a></li>
                    <li><a onclick="return checkNextPage();"
                           href="${pageContext.request.contextPath}/all?page=${page+1}&size=40"
                           style="cursor: pointer;text-decoration: none;">下一页</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/all?page=${num}&size=40"
                           style="cursor: pointer;text-decoration: none;">末页</a></li>
                </ul>
            </div>

                <%--跳页--%>
            <div class="col-xs-12 col-md-8">
                <div class="pagination">
                        <span style="font-size: 10px; padding-left: 16px;">当前第<span
                                style="font-size: 23px;">${page}</span>页<span style="font-size: 10px;">/总共有<span
                                style="font-size: 23px;">${num}</span>页</span></span>
                    <input class="pagination-control" id="turnToValueAll"
                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                           style="width:50px; margin-left: 10px;"
                           placeholder="页码"/>
                    <a id="turnToAll" class="btn btn-success" style="padding: 3px; margin-left: 7px;"
                       href="${pageContext.request.contextPath}/all"
                       style="cursor: pointer;text-decoration: none;">跳转</a>
                </div>

            </div>
        </div>
    </s:if>
    <s:else>
        <div class="row">
            <div class="col-xs-12 col-md-4">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=${sort}"
                           style="cursor: pointer;text-decoration: none;">首页</a></li>
                    <li><a onclick="return checkPrePage();"
                           href="${pageContext.request.contextPath}/cate?page=${page-1}&size=40&sort=${sort}"
                           style="cursor: pointer;text-decoration: none;">上一页</a></li>
                    <li><a onclick="return checkNextPage();"
                           href="${pageContext.request.contextPath}/cate?page=${page+1}&size=40&sort=${sort}"
                           style="cursor: pointer;text-decoration: none;">下一页</a></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/cate?page=${num}&size=40&sort=${sort}"
                           style="cursor: pointer;text-decoration: none;">末页</a></li>
                </ul>
            </div>

                <%--跳页--%>
            <div class="col-xs-12 col-md-8">
                <div class="pagination">
                        <span style="font-size: 10px; padding-left: 16px;">当前第<span
                                style="font-size: 23px;">${page}</span>页<span style="font-size: 10px;">/总共有<span
                                style="font-size: 23px;">${num}</span>页</span></span>
                    <input class="pagination-control" id="turnToValueCate"
                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                           style="width:50px; margin-left: 10px;"
                           placeholder="页码"/>
                    <a id="turnToCate" class="btn btn-success" style="padding: 3px; margin-left: 7px;"
                       href="${pageContext.request.contextPath}/cate"
                       style="cursor: pointer;text-decoration: none;">跳转</a>
                </div>

            </div>
        </div>
    </s:else>

</div>
</body>
</html>