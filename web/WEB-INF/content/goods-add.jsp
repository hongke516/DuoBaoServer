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
    <title>添加</title>
    <script>
        $(document).ready(function() {
            // 回到顶部
            $.goup({
                trigger: 100,
                bottomOffset: 150,
                locationOffset: 100,
                title: '顶部',
                titleAsText: true
            });
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
        <%--待写--%>
        <div class="col-xs-12 col-sm-10">
            <%--使用一元夺宝网站的图片地址--%>
            <s:form action="/goods/create" method="POST" class="form-horizontal" role="form" id="addGoodsNet">
                <%--待写--%>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <div class="text-center">添加商品</div>
                        </div>
                    </div>
                    <div class="panel-body">

                        <ul class="list-group">
                                <%-- intro --%>
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-xs-12 col-lg-9">
                                        <label for="goodsIntro">名称</label>
                                        <input name="goods.intro" type="text" class="form-control" id="goodsIntro"
                                               placeholder="请输入商品名称"/>
                                    </div>
                                    <div class="col-xs-12 col-lg-3">
                                        <label for="goodsCate">商品分类</label>
                                        <select class="form-control" name="goods.cate" id="goodsCate">
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

                                <%-- image --%>
                            <li class="list-group-item">
                                <label for="goodsImage">图片</label>
                                <input name="goods.image" type="text" class="form-control" id="goodsImage"
                                       placeholder="请输入http://1.163.com/对应商品的图片地址"/>
                            </li>


                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-lg-3">
                                            <%-- total --%>
                                        <label for="goodsTotal">总需人次</label>
                                        <input name="goods.total" type="text" class="form-control"
                                               id="goodsTotal"
                                               placeholder="请输入商品的总需人次"/>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-lg-3">
                                            <%-- per --%>
                                        <label for="goodsPer">每份需多少人次</label>
                                        <input name="goods.per" type="text" class="form-control" id="goodsPer"
                                               placeholder="请输入商品每份需多少人次"/>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-lg-3">
                                            <%-- price --%>
                                        <label for="goodsPrice">价格</label>
                                        <input name="goods.price" type="text" class="form-control"
                                               id="goodsPrice"
                                               placeholder="请输入商品的价格"/>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-lg-3">
                                        <label for="goodsAvailable">是否参与夺宝</label>
                                        <select class="form-control" name="goods.available" id="goodsAvailable">
                                            <option class="form-select-button" value="新品">新品</option>
                                            <option class="form-select-button" value="下架">下架</option>
                                        </select>
                                    </div>
                                </div>

                            </li>


                                <%-- trait --%>
                            <li class="list-group-item">
                                <label for="goodsTrait">额外描述</label>
                                <input name="goods.trait" type="text" class="form-control" id="goodsTrait"
                                       placeholder="请输入额外描述,如十元商品、百元商品、海购商品等,如果没有就不填写"/>
                            </li>

                                <%-- remind --%>
                            <li class="list-group-item">
                                <label for="goodsRemain">额外的红字提醒</label>
                                <input name="goods.remind" type="text" class="form-control" id="goodsRemain"
                                       placeholder="请输入额外的红字提醒"/>
                            </li>

                            <li class="list-group-item">
                                <label for="goodsExplains">重要说明</label>
                                        <textarea name="goods.explains" rows="6" class="form-control"
                                                  id="goodsExplains"
                                                  placeholder="请输入重要说明（在详情图片最后有一段重要说明）"></textarea>
                            </li>
                        </ul>

                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="shapes">滚动图片</label>
                                        <textarea name="inputShapes" rows="7" class="form-control" id="shapes"
                                                  placeholder="请输入该商品的滚动图片地址(与http://1.163.com对应商品的滚动图片地址),多个图片换行输入"></textarea>
                            </li>
                        </ul>

                        <ul class="list-group">
                            <li class="list-group-item">
                                <label for="details">详情图片</label>
                                        <textarea name="inputDetails" rows="10" class="form-control" id="details"
                                                  placeholder="请输入该商品的详情图片地址(与http://1.163.com对应商品的详情图片地址),多个图片换行输入"></textarea>
                            </li>
                        </ul>
                    </div>
                </div>

                <%--待写--%>
                <div class="form-group text-center" style="margin: 0 15px;">
                    <div class="row">
                        <div class="col-xs-12 col-md-5">

                        </div>
                        <div class="col-xs-4 col-md-2">
                            <s:submit class="form-control btn btn-success" id="issueNow" value="添加商品"/>
                        </div>

                        <div class="col-xs-12 col-md-5">
                        </div>
                    </div>
                </div>
            </s:form>
            <div class="col-xs-12 col-sm-1">
                <%--布局居中--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
