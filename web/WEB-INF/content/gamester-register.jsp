<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-8-9
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>用户注册</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
    <script>
        $(document).ready(function () {

            /**
             * 登录后返回请求登录页面
             */
            var urlStr = window.location.href.split("?from=");
            var fromStr = urlStr[1];

            /**
             * 判断用户是否已经登录了，用户已登录不允许再登录
             */
            /*这个Landed_Gamester是在com.fozoto.duobao.model.Gamester定义的,在登录时加入session的*/
            if(${landingGamester!=null && landingGamester.power!=0}) {
                //跳转到首页
                window.location.href="${pageContext.request.contextPath}/goods";
            }

            /**
             * 检查账号
             */
            $("#account").change(function() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/gamester/check",
                    type: "POST",
                    dataType: "json",
                    async: true,
                    data: {"gamester.account":$("#account").val()},
                    success:function(json) {
                        var s = $.parseJSON(json);
                        if (s.result=='error') {
                            $("#registerBtn").attr("disabled", true);
                            $("#accountError").html("账号已存在!").show();
                        } else if (s.result=='ok'){
                            $("#registerBtn").removeAttr("disabled");
                            $("#accountError").html("").hide();
                        }
                    }
                });
            });

            /**
             * 表单验证，使用了jQuery的validate插件
             * validate是通过name属性获取的，不是id属性
             */
            $("#registerForm").validate({
                rules: {
                    "gamester.account": {
                        required: true,
                        minlength:6,
                        maxlength:25
                    },
                    "gamester.password": {
                        required: true,
                        minlength: 6,
                        maxlength: 18
                    },
                    "gamester.repassword": {
                        required: true,
                        equalTo:"#password",
                        minlength: 6,
                        maxlength: 18
                    },
                    "gamester.nickname": {
                        required: true,
                        minlength: 2,
                        maxlength: 18
                    }
                },
                messages: {
                    "gamester.account": "长度6~25个字符",
                    "gamester.password": "请输入密码，6~18位",
                    "gamester.repassword": "两次输入密码不一样",
                    "gamester.nickname": "长度2~18个字符"
                },
                /**
                 * 通过valide的submitHandler发送ajax请求
                 */
                submitHandler: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/gamester/register",
                        type: "POST",
                        dataType: "json",
                        async: true,
                        data: $("#registerForm").serialize(),
                        success: function (json) {
                            var s = $.parseJSON(json);
                            if (s.result == 'ok') {
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/gamester/login",
                                    type: "POST",
                                    dataType: "json",
                                    async: true,
                                    data: {
                                        "gamester.account": $("#account").val(),
                                        "gamester.password": $("#password").val(),
                                        "gamester.remember": true
                                    },
                                    success: function (json) {
                                        //window.location.href=fromStr;
                                        //返回请求注册的页面
                                        var s = $.parseJSON(json);
                                        if (s.result == 'ok') {
                                            window.location.href = "javascript:history.go(-1)";
                                        }
                                    },
                                    error: function() {
                                        alert("error!");
                                    }
                                });
//                                window.location.href = "javascript:history.go(-1)";
                            }
                        },
                        error: function() {
                            alert("error!");
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
    <%@include file="util/header_gamester.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-sm-3">
            <%--页面布局，让注册框居中--%>
        </div>
        <div class="col-xs-12 col-sm-6">
            <h3 style="text-align: center; margin-bottom:20px;"><strong>成就梦想的开始!</strong></h3>
            <%-- 注册框 --%>
            <form class="form-horizontal" role="form" id="registerForm">
                <%-- 帐号 --%>
                <div class="form-group">
                    <label for="account" class="col-xs-12 col-sm-2 control-label">帐号</label>
                    <div class="col-xs-9 col-sm-8">
                        <input class="form-control" type="text" placeholder="请输入邮箱或手机号作为账号" id="account"
                               name="gamester.account">
                    </div>
                    <div class="col-xs-3 col-sm-2 pull-left">
                        <label class="text-danger control-label" id="accountInputTips" for="account">*必填</label>
                    </div>

                </div>
                <%-- 账号错误提示 --%>
                <div class="form-group">
                    <div class="col-xs-2">

                    </div>
                    <div class="col-xs-10">
                        <div class="text-danger" id="accountError"></div>
                    </div>
                </div>

                <%-- 密码 --%>
                <div class="form-group">
                    <label for="password" class="col-xs-12 col-sm-2 control-label">密码</label>
                    <div class="col-xs-9 col-sm-8">
                        <input class="form-control" type="password" placeholder="请输入密码，6~18位" id="password"
                               name="gamester.password">
                    </div>
                    <div class="col-xs-3 col-sm-2 pull-left">
                        <label class="text-danger control-label inputTips" id="passwordInputTips"
                               for="password">*必填</label>
                    </div>
                </div>

                <%-- 确认密码 --%>
                <div class="form-group">
                    <label for="confirmPassword" class="col-xs-12 col-sm-2 control-label">确认密码</label>
                    <div class="col-xs-9 col-sm-8">
                        <input class="form-control" type="password" placeholder="请确认密码" id="confirmPassword"
                               name="gamester.repassword">
                    </div>
                    <div class="col-xs-3 col-sm-2 pull-left">
                        <label class="text-danger control-label inputTips" id="confirmPasswordInputTips"
                               for="confirmPassword">*必填</label>
                    </div>
                </div>

                <%-- 昵称 --%>
                <div class="form-group">
                    <label for="nickname" class="col-xs-12 col-sm-2 control-label">昵称</label>
                    <div class="col-xs-9 col-sm-8">
                        <input class="form-control" type="text" placeholder="字母、数字或汉字，至少两位" id="nickname"
                               name="gamester.nickname">
                    </div>
                    <div class="col-xs-3 col-sm-2 pull-left">
                        <label class="text-danger control-label" id="nicknameInputTips" for="nickname">*必填</label>
                    </div>
                </div>

                <%-- 注册按钮 --%>
                <div class="form-group">
                    <div class="col-xs-2 col-sm-2"></div>
                    <div class="col-xs-8 col-sm-8">
                        <button class="form-control btn btn-danger" id="registerBtn" type="submit">
                            注册
                        </button>
                    </div>
                    <div class="col-xs-2 col-sm-2"></div>
                </div>
                <%-- 登录按钮 --%>
                <div class="form-group">
                    <div class="col-xs-2 col-sm-2"></div>
                    <div class="col-xs-8 col-sm-8">
                        已有账号？
                        <a class="form-control btn btn-info"
                           href="${pageContext.request.contextPath}/gamester-login">去登录</a>
                    </div>
                    <div class="col-xs-2 col-sm-2"></div>
                </div>
            </form>
        </div>
        <div class="col-xs-12 col-sm-3">

        </div>
    </div>
</div>
</body>
</html>
