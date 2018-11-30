<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <meta name="theme-color" content="#ffc91d"/>
    <link rel="icon" href="${rc.contextPath}/static/img/32.png" sizes="32x32"/>
    <link rel="icon" href="${rc.contextPath}/static/img/192.png" sizes="192x192"/>
    <link href="${rc.contextPath}/static/kico.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/static/moreduo.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1"/>

</head>

<body>
<div>
<#include  "header.ftl">
</div>
<aside class="sidebar">
    <div class="avatar">
            <img style="width: 100%;height: 100%" src="<#if Session.nowUser.iconUrl??>${Session.nowUser.iconUrl}</#if>" data-am-popover="{content: '点击上传头像', trigger: 'hover focus'}">
    </div>
    <nav class="nav">
        <a href="/usermodify.do">个人信息</a>
        <a href="/userpassword.do">修改密码</a>
        <a href="/userphone.do">修改手机号</a>
        <a href="/showMsg.do">我的消息</a>
        <a href="/useradress.do">我的地址</a>
        <a href="/useradd.do">添加地址</a>
        <a href="/myorder.do">我的订单</a>
    </nav>
</aside>
<script src="${rc.contextPath}/static/kico.js"></script>
<script src="${rc.contextPath}/static/moreduo.js"></script>

<script src="static/kico.js"></script>
<script src="static/moreduo.js"></script>

</body>
</html>