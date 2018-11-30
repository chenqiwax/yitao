<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <!-- Iconos -->
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="../css/leftnav.css" media="screen" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<!-- Contenedor -->
<div>
<#include "header.ftl">
<#include "usear.ftl">
</div>
<hr color="red" width="100%" style="margin-top: 60px;">
<div class="account-l fl" style="position: relative;top: 170px;left: 200px;">
    <a class="list-title">账户概览</a>
    <ul id="accordion" class="accordion">
        <li>
            <div class="link"><i class="fa fa-leaf"></i>个人信息<i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li>
                    <a href="/showuserinfo.do">基本信息</a>
                </li>
                <li id="">
                    <a href="/userinfo.do">修改信息</a>
                </li>
                <li id="">
                    <a href="/uadress.do">我的地址</a>
                </li>

            </ul>
        </li>

        <li>
            <div class=" link"><i class="fa fa-unlock-alt"></i>账户安全<i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <li id="">
                    <a href="/usecurity.do">密码修改</a>
                </li>
                <li id="">
                    <a href="/uphone.do">手机/邮箱修改</a>
                </li>
            </ul>
        </li>
        <li>
            <div class="link"><i class="fa fa-bell"></i>消息管理<i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li id="">
                    <a href="/showMsg.do">我的消息</a>
                </li>
            </ul>
        </li>
    </ul>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src='../js/leftnav.js'></script>
</div>

</body>
<style>
    td {
        width: 50%;
        height: 60px;
        font-family: 楷体;
        font-weight: 900;
        font-size: 20px;
    }

    th {
        width: 50%;
        height: 120px;
    }
</style>
</html>