<%--
  Created by IntelliJ IDEA.
  User: 朱策
  Date: 2020/8/21
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<html>
<head>
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/user/css/style.css">
    <script src="${ctx}/resources/user/js/jquery-1.8.3.min.js"></script>
    <script src="${ctx}/resources/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
    <%@include file="/WEB-INF/common/utop.jsp"%>
<!--导航条-->
<div class="width100" style="height:45px;background:#dd4545;margin-top:40px;position:relative;z-index: 100;">
    <!--中间部分-->
    <div class="width1200 center_yh relative_yh" style="height:45px;">
        <!--列表导航-->
        <div class="left_yh Selected" style="width:230px;height:45px;" id="hiddenShow">
            <!--头部图标-->
            <img src="${ctx}/resources/user/images/cd.png" class="left_yh" style="margin-left:24px;" alt="">
            <span class="block_yh left_yh fff" style="height:45px;line-height:44px;margin-left:10px;">分类</span>
            <!--导航展开部分-->
            <div class="downSlide">
                <c:forEach items="${lbs}" var="data" varStatus="l">
                    <div class="n1Nav">
                        <font>${data.father.name}</font>
                        <img src="${ctx}/resources/user/images/jt.png" alt="">
                        <div class="n2Nav">
                            <div class="n3Nav">
                                <h3>${data.father.name}</h3>
                                <c:forEach items="${data.children}" var="child" varStatus="ll">
                                    <a href="${ctx}/item/shoplist?categoryIdTwo=${child.id}">${child.name}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
            <a href="${ctx}/login/uIndex">首页</a>
            <a href="${ctx}/news/list">公告</a>
            <a href="${ctx}/message/add">留言</a>
        </div>
    </div>
</div>
<!--轮播广告-->
<div class="width1200 center_yh hidden_yh" style="position:relative;z-index: 80;">
    <div class="example2" style="width:1200px;height:490px;overflow:hidden;margin-left:230px;">
        <ul>
            <li><img src="${ctx}/resources/images/a.webp" alt=""></li>
            <li><img src="${ctx}/resources/images/b.webp" alt=""></li>
            <li><img src="${ctx}/resources/images/c.webp" alt=""></li>
            <li><img src="${ctx}/resources/images/d.webp" alt=""></li>
        </ul>
    </div>
    <script>
        $(function () {
            $(".example2").luara({width:"966",height:"490",interval:4500,selected:"selected",deriction:"left"});
        });
    </script>
</div>
<!--折扣商品-->
<div class="width1200 center_yh hidden_yh">
    <div class="width100" style="height:45px;line-height:45px;border-bottom:2px solid #dd4545;margin-top:20px;">
        <font class="left_yh font20">折扣大促销</font>
    </div>
    <div class="width100 hidden_yh" style="height:480px;">
        <div class="normalPic">
            <c:forEach items="${zks}" var="data" varStatus="l">
                <a href="${ctx}/item/view?id=${data.id}">
                    <h3 class="yihang c_33 font14 font100" style="padding-left:10px;padding-right:10px;">${data.name}</h3>
                    <p class="red font14" style="padding-left:10px;">${data.price}</p>
                    <img src="${data.url1}" width="105" height="118" alt="" style="marin:0 auto">
                </a>
            </c:forEach>
        </div>
    </div>
</div>

<!--热销商品-->
    <div class="width1200 center_yh hidden_yh">
        <div class="width100" style="height: 45px;line-height: 45px;border-bottom: 2px solid #dd4545; margin-top: 20px;">
            <font class="left_yh font20">热门商品</font>
        </div>
        <div class="width100 hidden_yh" style="height: 480px;">
            <div class="normalPic">
                <c:forEach items="${rxs}" var="data" varStatus="l">
                    <a href="${ctx}/item/view?id=${data.id}">
                        <h3 class="yihang c_33 font14 font100" style="padding-left: 10px;padding-right: 10px;">${data.name}</h3>
                        <p class="red font14" style="padding-left: 10px;">${data.price}</p>
                        <img src="${data.url1}" width="105" height="118" alt="" style="margin:0 auto">
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/common/ufooter.jsp"%>
</body>
</html>
