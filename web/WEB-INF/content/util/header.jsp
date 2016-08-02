<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-31
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <nav class="navbar navbar-default" role="navigation">
        <!-- fozoto的logo -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-header-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <div style="margin-left: 20px">
                <a href="${pageContext.request.contextPath}/" target="_self" title="fozoto">
                    <figure>
                        <img alt="fozoto" src="${pageContext.request.contextPath}/images/logo.png" />
                    </figure>
                </a>
            </div>

        </div>
        <!--导航-->
        <div class="collapse navbar-collapse navbar-header-collapse">
            <ul id="header_menu" class="nav navbar-nav">
                <li><a target="_self" href="${pageContext.request.contextPath}/goods/page">管理商品</a></li>
                <li><a target="_self" href="${pageContext.request.contextPath}/goods-add">添加商品</a></li>
                <li><a target="_self" href="${pageContext.request.contextPath}/goods-select">查询商品</a></li>
            </ul>
        </div>
    </nav>
</header>
