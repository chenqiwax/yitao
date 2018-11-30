<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <title>购物车</title>
</head>
<style>
    .input1 {
        width: 300px;
        height: 25px;
        border: 1px solid #FF0099;
        outline: none
    }

    .button1 {
        position: relative;
        top: -1px;
        width: 100px;
        height: 29px;
        background-color: #FF0099;
        border: none;
    }

    #span1 {
        position: relative;
        left: 700px;
    }

    #span2 {
        position: relative;
        top: 130px;
        left: 300px;
    }

    #cdiv {
        position: relative;
        top: 150px;
        left: 610px;
        width: 600px;
        height: 510px;
    }

    #div2 {
        position: relative;
        top: 400px;
    }

    #span3 {
        position: relative;
    }

    a {
        color: #FF0099;
        font-size: 13px;
        text-decoration: none;
    }

    #span4 {
        position: relative;
        left: -405px;
        top: 19px;
    }
</style>

<body>

<!-- 顶部页面 -->
<#include "header.ftl">

<#include "search.ftl">

<div id="cdiv">

    <img src="../img/购物车.png"/>
    <span id="span3">购物车内暂时没有商品，登录后将显示您之前加入的商品</span>

    <span id="span4">
				<a href="${rc.contextPath}/index.do">去购物></a>
			</span>

</div>
<div style="margin-top: 50px">
    <!-- 底部页面 -->
<#include "footer.ftl">
</div>
</body>

</html>