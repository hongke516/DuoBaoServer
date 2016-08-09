<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-9
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
    <title>登录</title>
    <script>
        $(document).ready(function () {
            //得到上一页面的地址
            var urlStr = window.location.href.split("?from=");
            var fromStr = urlStr[1];
            // alert(window.parent.location);
            /**
             * 判断用户是否已经登录了，用户已登录不允许再登录
             */
            /*这个Landed_Gamester是在com.fozoto.duobao.model.Gamester定义的,在登录时加入session的*/
            if(${landingGamester!=null && landingGamester.power!=0}) {
                //跳转到首页
                window.location.href="${pageContext.request.contextPath}/goods";
            }


            /**
             * 得到用户是否记住密码的checkbox的true或false值
             */
            $("#rememberPassword").change(function () {
                $("#rememberLogin").val($("#rememberPassword").prop('checked'));
            });

            /**
             * 表单验证，使用了jQuery的validate插件
             * validate是通过name属性获取的，不是id属性
             */
            $("#loginForm").validate({
                rules: {
                    "gamester.account": {
                        required: true
                    },
                    "gamester.password": {
                        required: true,
                        minlength: 6,
                        maxlength: 18
                    }
                },
                messages: {
                    "gamester.account": "请输入正确的账号",
                    "gamester.password": "请输入密码，6~18位"
                },
                submitHandler: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/gamester/login",
                        type: "POST",
                        dataType: "json",
                        async: true,
                        data: {
                            "gamester.account": $("#account").val(),
                            "gamester.password": $("#password").val(),
                            "gamester.remember": $("#rememberLogin").val()
                        },
                        success: function (json) {
                            var value = $.parseJSON(json);
                            if (value.result == 'ok') {
                                //window.location.href=fromStr;
                                if (window.parent.location == "/gamester-register") {
                                    window.location.href = "${pageContext.request.contextPath}/goods";
                                } else {
                                    window.location.href = "javascript:history.go(-1)";
                                }
                            } else {
                                $("#noUserExist").html("<strong>账号或密码错误!</strong>");
                            }
                        },
                        error: function () {
                            alert("登录失败!");
                        }
                    });
                },
                invalidHandler: function () {  //不通过回调
                    return false;
                }
            });
        })
    </script>
</head>
<body>
<div class="container">
    <%@include file="/WEB-INF/content/util/header_gamester.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-sm-4">
            <%--页面布局，是登录框居中--%>
        </div>
        <div class="col-xs-12 col-sm-4">
            <h3 style="text-align: center; margin-bottom:20px;"><strong>快加入实现梦想</strong></h3>
            <div class="text-center" id="noUserExist" style="color: red; margin: 15px;"></div>
            <%--<form action="${pageContext.request.contextPath}/user/login" method="post" role="form" id="loginForm">--%>
            <form role="form" id="loginForm">
                <%-- 账号 --%>
                <div class="form-group">
                    <input class="form-control" name="gamester.account" id="account" type="text" placeholder="请输入账号">
                </div>
                <%-- 密码--%>
                <div class="form-group">
                    <input class="form-control" name="gamester.password" id="password" type="password"
                           placeholder="请输入密码">
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-6">
                            <span class="cookieHide"><input class="cookieHide" type="checkbox" id="rememberPassword">记住密码</span>
                            <input name="gamester.remember" type="hidden" id="rememberLogin" value="false">
                        </div>
                        <div class="col-xs-6">
                            <a href="#">忘记密码？</a>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button class="form-control btn btn-danger" id="btnLogin" type="submit">登录</button>
                </div>
                <div class="form-group cookieHide">
                    没有账号？
                    <a class="form-control btn btn-info" id="btnRegister"
                       href="${pageContext.request.contextPath}/gamester-register">去注册</a>
                </div>
            </form>
        </div>
        <div class="col-xs-12 col-sm-4">
            <%--页面布局，使登录框居中--%>
        </div>
    </div>
</div>
</body>
</html>
