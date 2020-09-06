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
    <div class="panel-head" id="add">
        <strong><span class="icon-pencil-square-o">新增类目</span> </strong>
    </div>
    <div class="body-content">
        <form action="${ctx}/itemCategory/exAdd2" method="post" class="form-x">
            <input type="hidden" name="pid" value="${pid}"/>
            <div class="form-group">
                <div class="label"><label>二级类目名称：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="name" data-validate="required:请输入名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"></div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
