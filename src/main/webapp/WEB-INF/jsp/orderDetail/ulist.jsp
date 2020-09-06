<%--
  Created by IntelliJ IDEA.
  User: 朱策
  Date: 2020/8/20
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<html>
<head>
    <title>管理员后台</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resources/css/admin.css">
    <script src="${ctx}/resources/js/jquery.js"></script>
    <script src="${ctx}/resources/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <table class="table table-hover text-center">
        <tr>
            <th>商品名称</th>
            <th>商品主图</th>
            <th>商品单价</th>
            <th>购买数量</th>
            <th>小计</th>
            <th>状态</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.item.name}</td>
                <td><img src="${data.item.url1}" alt="" style="width:100px;height:100px;"></td>
                <td>${data.item.price}</td>
                <td>${data.num}</td>
                <td>${data.total}</td>
                <td style="color:red">
                    <c:if test="${data.status==0}">
                        未退货
                    </c:if>
                    <c:if test="${data.status==1}">
                        已退货
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/orderDetail/ulist?orderId=${obj.orderId}" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
                        <pg:last>
                            共${pagers.total}记录，共${pageNumber}页，
                        </pg:last>
                        当前第${curPage}页
                        <pg:first>
                            <a href="${pageUrl}">首页</a>
                        </pg:first>
                        <pg:prev>
                            <a href="${pageUrl}">上一页</a>
                        </pg:prev>
                        <pg:pages>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">[${pageNumber}]</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">${pageNumber}</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:pages>
                        <pg:next>
                            <a href="${pageUrl}">下一页</a>
                        </pg:next>
                        <pg:last>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">尾页</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">尾页</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:last>
                    </pg:pager>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
