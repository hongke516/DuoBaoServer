<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-31
  Time: 下午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready(function () {
        /**
         * 登录
         */
        // 未登录
        if(${landingGamester==null}) {
            var cookie = $.cookie("Gamester_cookie");
            // alert(cookie);
            if(cookie!=null) {
                //ajax 利用cookie登录
                $.ajax({
                    url: "${pageContext.request.contextPath}/gamester/cookie",
                    type: "POST",
                    async: true,
                    success: function (json) {
                        var value = $.parseJSON(json);
                        // alert(value.result);
                        if(value.result='error') {
                            $("#isLanded").show();
                            $("#isNotLand").hide();
                        } else {
                            $("#isLanded").hide();
                            $("#isNotLand").show();
                            $("#userNickname").value(value.result);
                        }
                    },
                    error: function () {
                        alert("cookie登录失败,请联系青烟站长!");
                    }
                });
            } else {
                $("#isLanded").hide();
                $("#isNotLand").show();
            }
        } else {
            $("#isLanded").show();
            $("#isNotLand").hide();
        }

        //用户点击注销,删除cookie记录的用户数据
        $("#deleteCookie").click(function () {
            /**
             * 删除cookie
             * 因userService指定了cookie.setPath("/");
             * 所以指定路径删除cookie $.removeCookie("user.cookie", { path: "/"});
             */
            $.removeCookie("Gamester_cookie", {path: "/"});
            $.ajax({
                url: "${pageContext.request.contextPath}/gamester/logout",
                type: "POST",
                async: true,
                success: function (json) {
                    var s = $.parseJSON(json);
                    if (s.result=='ok') {
                        $("#isLanded").hide();
                        $("#isNotLand").show();
                    } else {
                        $("#isLanded").show();
                        $("#isNotLand").hide();
                    }
                },
                error: function () {
                    alert("系统出现错误,请联系青烟站长！");
                }
            });
        });
    })
</script>

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
                <li><a target="_self" href="${pageContext.request.contextPath}/goods-navi">管理商品</a></li>
                <li><a target="_self" href="${pageContext.request.contextPath}/goods-add">添加商品</a></li>
                <li><a target="_self" href="${pageContext.request.contextPath}/goods-select">查询商品</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right" id="isNotLand">
                <li><a href="${pageContext.request.contextPath}/gamester-login" style="color: red;" id="headerLogin"><span
                        class="glyphicon glyphicon-user">登录</span></a></li>
                <li><a href="${pageContext.request.contextPath}/gamester-register" id="headerRegister"><span
                        class="glyphicon glyphicon-registration-mark">注册</span></a></li>
            </ul>

            <%--登录成功后--%>
            <ul class="nav navbar-nav pull-right" id="isLanded" style="margin-right: 30px;">
                <li class="dropdown">
                    <a class="dropdown-toggle" id="userMenu" data-toggle="dropdown"
                       style="color: #4cae4c; cursor: pointer;"><strong><span
                            id="userNickname">${landingGamester.nickname}</span><span class="caret"></span></strong></a>
                    <ul class="dropdown-menu" aria-labelledby="userMenu">
                        <li><a rel="nofollow" href="#">我的信息</a></li>
                        <li class="divider"></li>
                        <li><a rel="nofollow" id="deleteCookie" style="cursor: pointer;">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
