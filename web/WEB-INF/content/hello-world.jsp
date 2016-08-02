<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-30
  Time: 下午8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp"%>
    <title>Title</title>
    <script>
        $(document).ready(function() {
            $("#send").click( function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/hello-world/test",
                    type: "POST",
                    dataType: "json",
                    async: true,
                    data: { "name":$("#name").val()},
                    success: function (result) {
                        alert(result);
                    },
                    error: function () {
                        alert("error");
                    }
                });
            });
        });
    </script>
</head>
<body>
<form onsubmit="return false">
    <input type="text" placeholder="请输入名字" id="name" />
    <button type="submit" id="send">发送ajax</button>
</form>
</body>
</html>
