<%--
  Created by IntelliJ IDEA.
  User: 朱策
  Date: 2020/8/6
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<html>
<head>
    <title>管理员登录</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/pintuer.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/admin.css"/>
    <script src="${ctx}/resources/js/jquery.js"></script>
    <script src="${ctx}/resources/js/pintuer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height: 150px;"></div>
            <div class="meida media-y margin-big-bottom"></div>
            <form action="${ctx}/login/toLogin" method="post">
                <div class="panel login-box">
                    <div class="text-center margin-big padding-big-top"><h1>管理员登录</h1></div>
                    <div class="panel-body" style="padding: 30px;padding-bottom: 10px;padding-top: 10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="username" value="admin"
                                       placeholder="登录账号" data-validate="required:请填写账号"/><span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="password" value="111111"
                                       placeholder="登录密码" data-validate="required:请填写密码"/><span class="icon icon-key margin-small"></span>
                            </div>
                        </div>

                    </div>
                    <div style="padding: 30px;">
                        <input type="submit" class="button button-block bg-main text-big input-big" value="登录"/>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
