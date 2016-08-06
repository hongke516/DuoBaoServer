<%--
  Created by IntelliJ IDEA.
  User: qingyan
  Date: 16-7-30
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="util/dependences.jsp" %>
    <title>商品详情</title>
    <script>
        $(document).ready(function(){
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

            // 回到顶部
            $.goup({
                trigger: 100,
                bottomOffset: 150,
                locationOffset: 100,
                title: '顶部',
                titleAsText: true
            });

            // bootstrap图片自适应
            $(".auto img").addClass("carousel-inner img-responsive img-rounded");
        })
    </script>

</head>
<body>
<div class="container">

    <%@include file="util/header.jsp" %>
    <div class="row">
        <div class="col-xs-12 col-sm-1">
            <%--布局居中--%>
        </div>

        <%--商品详情--%>
        <div class="col-xs-12 col-sm-10">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <%--<div class="text-center">${promptInfo.title}</div>--%>
                        <div class="text-center">商品详情</div>
                    </div>
                </div>
                <%--修改,删除--%>
                <div>
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
                                                <input name="goods.id" value="${goods.id}" hidden="hidden"/>
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
                                                            <s:if test="%{#request.goods.available=='新品'}">
                                                                <option class="form-select-button" selected="selected" value="新品">新品</option>
                                                                <option class="form-select-button" value="下架">下架</option>
                                                            </s:if>
                                                            <s:elseif test="%{#request.goods.available=='下架'}">
                                                                <option class="form-select-button" value="新品">新品</option>
                                                                <option class="form-select-button" selected="selected" value="下架">下架</option>
                                                            </s:elseif>
                                                        </select>
                                                    </div>
                                                    <div class="col-xs-12 col-lg-6">
                                                        <label for="modifyCate">是否参与夺宝:</label>
                                                        <select class="form-control" name="goods.cate"
                                                                id="modifyCate">
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='优选商品'}">selected</s:if> value="优选商品">优选商品</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='手机平板'}">selected</s:if> value="手机平板">手机平板</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='电脑办公'}">selected</s:if> value="电脑办公">电脑办公</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='数码影音'}">selected</s:if> value="数码影音">数码影音</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='女性时尚'}">selected</s:if> value="女性时尚">女性时尚</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='美食天地'}">selected</s:if> value="美食天地">美食天地</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='潮流新品'}">selected</s:if> value="潮流新品">潮流新品</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='网易周边'}">selected</s:if> value="网易周边">网易周边</option>
                                                            <option class="form-select-button" <s:if test="%{#request.goods.cate=='其他商品'}">selected</s:if> value="其他商品">其他商品</option>
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

                    <div class="text-center list-group-item">
                        <!-- 按钮触发模态框 -->
                        <a class="btn btn-primary" data-toggle="modal"
                           data-target="#modifyModal">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/goods/update?goodsId=${goods.id}">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="deletePopover btn btn-danger" style="cursor: pointer;"
                           rel="nofollow" data-toggle="popover">删除</a>
                        <a href="${pageContext.request.contextPath}/goods/delete?goodsId=${goods.id}"
                           class="text-danger" hidden="hidden"><span
                                id="delete">删除</span></a>
                    </div>

                </div>

                <div class="panel-body">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <%--<div class="text-center">${promptInfo.title}</div>--%>
                                <div class="text-center">商品简介</div>
                            </div>
                        </div>
                        <div class="panel-body">

                            <ul class="list-group">
                                <li class="list-group-item">
                                    <span class="text-info">${goods.intro}</span>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                            <img src="${goods.image}" height="180px" width="210px"/>
                                            <s:if test="%{#request.goods.trait!=''}">
                                                <img src="${goods.trait}" width="40px" height="40px"/>
                                            </s:if>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                                            <h3 class="text-success">
                                                ${goods.intro}
                                            </h3>
                                            <ul class="list-group">
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                            价格:${goods.price}
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                            总需人次:${goods.total}
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                            每份人次:${goods.per}
                                                        </div>
                                                    </div>

                                                </li>
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                                            是否参与夺宝:
                                                            <s:if test="%{#request.goods.available=='新品'}">
                                                                <span class="text-success">新品</span>
                                                            </s:if>
                                                            <s:elseif test="%{#request.goods.available=='下架'}">
                                                                <span class="text-danger">下架</span>
                                                            </s:elseif>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">
                                                            商品分类:<span class="text-info">${goods.cate}</span>
                                                        </div>

                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-4">

                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
                                                            首次夺宝:${goods.time}
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-12  col-lg-6">
                                                            重返夺宝:${goods.retime}
                                                        </div>
                                                    </div>

                                                </li>
                                            </ul>

                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <span class="text-warning">${goods.remind}</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <%--<div class="text-center">${promptInfo.title}</div>--%>
                                <div class="text-center">滚动图片</div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <ul class="list-group">
                                <s:iterator var="s" value="#request.shapes">
                                    <li class="list-group-item text-center auto">
                                        <img src="<s:property value="#s.image"/>"/>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <%--<div class="text-center">${promptInfo.title}</div>--%>
                                <div class="text-center">商品详情</div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <ul class="list-group">
                                <s:iterator var="s" value="#request.details">
                                    <li class="list-group-item text-center auto">
                                        <img src="<s:property value="#s.image"/>"/>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <label for="goodsExplains">重要说明</label>
                            <p id="goodsExplains">${goods.explains}</p>
                        </li>
                    </ul>
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
