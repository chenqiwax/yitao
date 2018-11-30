<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的足迹</title>
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <style type="text/css">
        .img {
            width: 150px;
            height: 150px;
        }

        #div {
            width: 264px;
            height: 244px;
        }
    </style>

</head>

<body>
<div>
<#include "header.ftl">
</div>

<div class="search">
    <a href="/index.do">
        <img  class="yuan" src="${rc.contextPath}/img/yitao.jpg">
    </a>
    <span style="position: relative;top: 40px;left: 100px;font-size: 40px;color: #3bb4f2">我的足迹</span>
</div>
<div class="container" style="margin-top: 50px">
    <div class="card" style="width: 100%;">
        <div class="card-header">
        ${nowDate?string["yyyy年MM月dd日"]}
        </div>
        <div class="card-body form-inline">
            <#if userCookies??>
                <#list userCookies as userLook>
                    <div id="div" style="text-align: center">
                        <a href="/showBook/${userLook.gid}.do">
                            <#list userLook.book.imgurl?split(",") as imgurl>
                                <img class="img" src="${imgurl}">
                                <#break >
                            </#list>
                            </a>
                        <p></p>
                        <p align="center" style="font-size: 12px">
                            <a href="${rc.contextPath}/showBook/${userLook.gid}.do">${userLook.book.name}</a>
                        </p>
                        <p align="center"><span style="color: red;font-size: 20px">¥${userLook.book.price/100}</span></p>
                    </div>
                </#list>
            </#if>

        </div>
    </div>
</div>

</body>

</html>