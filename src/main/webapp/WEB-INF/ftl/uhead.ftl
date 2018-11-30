<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
</head>

<body>
<header>
    <div class="header-all">
        <nav>
            <ul class="area">
                <img class="img1" style="width: 25px;" src="../img/logo.jpg"/>
                <a class="a1" target="_blank" href="index.do" style="font-size: 15px;color: #4f2edb;">易淘首页</a>
            </ul>
            <ul class="navList">
                <li class="navList-top">欢迎光临易淘,请
                    <a class="login" href="login.do" target="_blank">登录</a>
                </li>
                <li class="navList-top">
                    <a href="regist.do" target="_blank">注册</a>
                </li>
                <li class="navList-top shopCar">
                    <a href="carts.do" target="_blank">购物车</a><span class="login">0</span></li>
                <li class="navList-top">
                    <a href="unavigation.do" target="_blank">个人中心</a>
                </li>
                <li class="hov">
                    <p class="navList-top nav-hov">
                        <a href="" target="_blank">我的易淘</a><b></b></p>
                    <div class="dangList1">
                        <p>
                            <a href="#" target="_blank">消息</a><span class="login">3</span>
                        </p>
                        <p>
                            <a href="#" target="_blank">我的收藏</a>
                        </p>
                        <p>
                            <a href="footprint.do" target="_blank">我的足迹</a>
                        </p>
                    </div>

                </li>
            </ul>
        </nav>
    </div>
<#include "usear.ftl">
</header>
</body>

</html>