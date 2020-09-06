<%--
  Created by IntelliJ IDEA.
  User: 朱策
  Date: 2020/8/17
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
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
    <form action="${ctx}/item/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li>
                    <a class="button border-main icon-plus-square-0"
                       href="${ctx}/item/add">添加商品</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入商品名称" name="name"
                           class="input" value="${obj.name}" style="width:250px;line-height:17px;display:inline-block;"/>
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>商品名称</th>
            <th>商品主图</th>
            <th>商品价格</th>
            <th>商品一级分类</th>
            <th>商品二级分类</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="1">
            <tr>
                <td>${data.name}</td>
                <td><img src="${data.url1}" alt="" style="width:100px;height:100px;"></td>
                <td>${data.price}</td>
                <td>${data.yiji.name}</td>
                <td>${data.erji.name}</td>
                <td>
                    <a class="button border-main" href="${ctx}/item/update?id=${data.id}"><span class="icon-edit">修改</span></a>
                    <a class="button border-red" href="${ctx}/item/delete?id=${data.id}"><span class="icon-trash-o">下架</span></a>

                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <pg:pager url="${ctx}/item/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
                        <pg:last>
                            共${pagers.total}记录，共${pageNumber}页，
                        </pg:last>
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
<script>
    function changeSearch() {
        $("#listform").submit();
    }
</script>
</body>
</html>
