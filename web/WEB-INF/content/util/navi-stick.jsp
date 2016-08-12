<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-11
  Time: 下午7:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fozoto/fozoto.js"></script>
<div class="row stick" style="z-index:99999;">
    <div class="col-xs-12 col-lg-2 none-padding">
        <div class="dropdown">
            <a href="#" class="list-group-item dropdown-toggle" style="background-color: #DB3652" id="cateMenu"
               data-toggle="dropdown">
                <strong style="color: #FFFFFF">商品分类</strong><span class="caret"></span></a>
            <ul class="dropdown-menu list-group" aria-labelledby="cateMenu">
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow" href="${pageContext.request.contextPath}/all?page=1&size=40"><strong style="color: #000000">所有商品</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=手机平板"><strong
                            style="color: #000000">手机平板</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=电脑办公"><strong
                            style="color: #000000">电脑办公</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=数码影音"><strong
                            style="color: #000000">数码影音</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=女性时尚"><strong
                            style="color: #000000">女性时尚</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=美食天地"><strong
                            style="color: #000000">美食天地</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=潮流新品"><strong
                            style="color: #000000">潮流新品</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=网易周边"><strong
                            style="color: #000000">网易周边</strong></a>
                </li>
                <li class="list-group-item" style="background-color: #DB3652">
                    <a rel="nofollow"
                       href="${pageContext.request.contextPath}/cate?page=1&size=40&sort=其他商品"><strong
                            style="color: #000000">其他商品</strong></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="col-xs-12 col-lg-10 text-center">
        <div class="row">
            <div class="col-xs-12 col-lg-8">
                <div class="list-group-item" style="border: none;">
                    <div class="row">
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
                                <span style="color: #FFFFFF;">清&nbsp;&nbsp;单</span>
                                <span class="badge">0</span>
                            </span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
