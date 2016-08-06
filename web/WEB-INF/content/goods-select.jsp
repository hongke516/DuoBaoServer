<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-31
  Time: 下午8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>查询</title>
    <script>
        $(document).ready(function () {
            $("#result").hide();
            $("#select").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/goods/select",
                    type: "POST",
                    dataType: "json",
                    async: true,
                    data: {"goodsId": $("#goodsId").val()},
                    success: function (result) {
                        $("#result").show();
                        // 商品基本信息
                        var goods = $.parseJSON(result);
                        $("#image").attr("src", goods.image);
                        $("#trait").attr("src", goods.trait);
                        if (goods.available == '新品') {
                            $("#available").text("新品");
                        } else {
                            $("#available").text("下架");
                        }
                        $("#intro").text(goods.intro);
                        $("#remind").text(goods.remind);
                        $("#per").text(goods.per);
                        $("#price").text(goods.price);
                        $("#total").text(goods.total);
                        $("#time").text(goods.time);
                        $("#retime").text(goods.retime);
                        $("#detail").attr("href", "${pageContext.request.contextPath}/goods/detail?goodsId=" + goods.id);
                        $("#detail").text(goods.intro);

                        // 修改框
                        $("#modifyId").val(goods.id);
                        $("#modifyPer").val(goods.per);
                        $("#modifyPer").attr("placeholder", "原值:" + goods.per);
                        $("#modifyPrice").val(goods.price);
                        $("#modifyPrice").attr("placeholder", "原值:" + goods.price);
                        $("#modifyTotal").val(goods.total);
                        $("#modifyTotal").attr("placeholder", "原值:" + goods.total);
                        $("#modifyCate").val(goods.cate);
                        $("#modifyAvailable").val(goods.available);

                        // 更新
                        $("#update").attr("href", "${pageContext.request.contextPath}/goods/update?goodsId=" + goods.id);

                        // 删除
                        $("#deletea").attr("href", "${pageContext.request.contextPath}/goods/delete?goodsId=" + goods.id);

                        // 提示信息去除
                        $("#info").hide();
                    },
                    error: function () {
                        $("#info").show();
                        $("#info").text("商品不存在!");
                    }
                })
            });


            /*删除商品*/
            $("[data-toggle='popover']").popover({
                html: true,
                placement: "top",
                container: "body",
                title: "<div class='text-center'>确认删除?</div>",
                content: "<button class='btn btn-danger' onclick='javascript:$.myFunction.delete();'>删除</button>" +
                "&nbsp;&nbsp;" +
                "<button class='btn btn-default cancelPopover' onclick='javascript:$.myFunction.cancel();'>取消</button><br>"
            });

            //被删除商品调用的函数
            $.myFunction = {
                delete: function () {
                    $("#delete").click();
                },

                cancel: function () {
                    $('.deletePopover').popover('hide');
                }
            };

        })
    </script>
</head>
<body>
<div class="container">
    <%@include file="util/header.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">
                <div class="text-center">商品查询</div>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-12 col-md-2 col-lg-4"></div>
                <div class="col-xs-12 col-md-8 col-lg-4">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <label for="goodsId">商品id</label>
                            <div class="row">
                                <div class="col-xs-12 col-lg-8">
                                    <input id="goodsId" class="form-control"
                                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                           name="goodsId" placeholder="请输入商品的id"/>
                                </div>
                                <div class="col-xs-12 col-lg-4">
                                    <button class="btn btn-info" type="button" id="select">查询</button>
                                </div>
                            </div>
                            <div class="text-danger" id="info"></div>
                        </li>
                    </ul>
                </div>
                <div class="col-xs-12 col-md-2 col-lg-4">

                </div>
            </div>

            <ul id="result" class="list-group">
                <li class="list-group-item">
                    <span id="intro" class="text-info">${goods.intro}</span>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                            <img id="image" src="${goods.image}" height="180px" width="210px"/>
                            <%--<s:if test="%{#goods.trait!=''}">--%>
                            <%--<img src="${goods.trait}" width="40px" height="40px"/>--%>
                            <%--</s:if>--%>
                            <img id="trait" src="${goods.trait}" width="40px" height="40px"/>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                            <h3 class="text-success">
                                <a id="detail"
                                   href="${pageContext.request.contextPath}/goods/detail?goodsId=${goods.id}">${goods.intro}</a>
                            </h3>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                            价格:<span id="price">${goods.price}</span>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                            总需人次:<span id="total">${goods.total}</span>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                            每份人次:<span id="per">${goods.per}</span>
                                        </div>
                                    </div>

                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                            是否参与夺宝:<span class="text-info" id="available"></span>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                                            <%--修改,删除--%>
                                            <!-- 模态框（Modal） -->
                                            <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
                                                 aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close"
                                                                    data-dismiss="modal" aria-hidden="true">
                                                                &times;
                                                            </button>
                                                            <h4 class="modal-title text-center" id="myModalLabel">
                                                                修改商品
                                                            </h4>
                                                        </div>
                                                        <form action="/goods/modify" class="form-group">
                                                            <div class="modal-body">

                                                                <ul class="list-group">
                                                                    <li class="list-group-item" hidden="hidden">
                                                                        <input id="modifyId" value="${goods.id}" name="goods.id" hidden="hidden"/>
                                                                    </li>
                                                                    <li class="list-group-item">

                                                                        <label for="modifyPrice">价格:</label>
                                                                        <input name="goods.price" id="modifyPrice"
                                                                               class="form-control"
                                                                               value="${goods.price}"
                                                                               placeholder="原值:${goods.price}"/>
                                                                    </li>
                                                                    <li class="list-group-item">
                                                                        <label for="modifyTotal">总需人次:</label>
                                                                        <input name="goods.total" id="modifyTotal"
                                                                               class="form-control"
                                                                               value="${goods.total}"
                                                                               placeholder="原值:${goods.total}"/>
                                                                    </li>
                                                                    <li class="list-group-item">
                                                                        <label for="modifyPer">每份人次:</label>
                                                                        <input name="goods.per" id="modifyPer"
                                                                               class="form-control" value="${goods.per}"
                                                                               placeholder="原值:${goods.per}"/>
                                                                    </li>
                                                                    <li class="list-group-item">
                                                                        <div class="row">
                                                                            <div class="col-xs-12 col-lg-6">
                                                                                <label for="modifyAvailable">是否参与夺宝:</label>
                                                                                <select class="form-control" name="goods.available"
                                                                                        id="modifyAvailable">
                                                                                    <option class="form-select-button" value="新品">新品</option>
                                                                                    <option class="form-select-button" value="下架">下架</option>
                                                                                </select>
                                                                            </div>
                                                                            <div class="col-xs-12 col-lg-6">
                                                                                <label for="modifyCate">商品分类:</label>
                                                                                <select class="form-control" name="goods.cate"
                                                                                        id="modifyCate">
                                                                                    <option class="form-select-button" value="优选商品">优选商品</option>
                                                                                    <option class="form-select-button" value="手机平板">手机平板</option>
                                                                                    <option class="form-select-button" value="电脑办公">电脑办公</option>
                                                                                    <option class="form-select-button" value="数码影音">数码影音</option>
                                                                                    <option class="form-select-button" value="女性时尚">女性时尚</option>
                                                                                    <option class="form-select-button" value="美食天地">美食天地</option>
                                                                                    <option class="form-select-button" value="潮流新品">潮流新品</option>
                                                                                    <option class="form-select-button" value="网易周边">网易周边</option>
                                                                                    <option class="form-select-button" value="其他商品">其他商品</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>

                                                            </div>
                                                            <div class="modal-footer">
                                                                <div class="text-center">
                                                                    <button type="button" class="btn btn-default"
                                                                            data-dismiss="modal">关闭
                                                                    </button>
                                                                    <button type="submit" class="btn btn-primary">
                                                                        提交修改
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal -->
                                            <div>
                                                <!-- 按钮触发模态框 -->
                                                <a class="btn btn-primary" data-toggle="modal"
                                                   data-target="#modifyModal">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary" id="update">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="deletePopover btn btn-danger" style="cursor: pointer;"
                                                   rel="nofollow" data-toggle="popover">删除</a>
                                                <a id="deletea"
                                                   class="text-danger" hidden="hidden"><span id="delete">删除</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                            首次夺宝:<span id="time"></span>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-6">
                                            重返夺宝:<span id="retime"></span>
                                        </div>
                                    </div>

                                </li>
                            </ul>

                        </div>
                    </div>
                    <div class="text-center">
                        <span id="remind" class="text-warning"></span>
                    </div>
                </li>
            </ul>
        </div>
    </div>

</div>

</body>
</html>
