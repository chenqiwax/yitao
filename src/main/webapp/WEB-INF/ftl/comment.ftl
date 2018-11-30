<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>商品评价</title>
    <link rel="shortcut icon" href="../img/logos.ico"/>

    <style>
        .but {
            width: 150px;
            height: 45px;
            margin-left: 400px;
            background-color: #FF5454;
            border: none;
            font-size: 18px;
            color: white;
        }
    </style>
</head>

<body>

<#include "head.ftl">
</br>
</br>
</br>
<hr width="100%" color="red">

<form action="/addBookComment.do" method="post">
    <div>
    <div style="width: 400px;margin-top: 90px;margin-left: 180px;">
            <#if bookComment??>
                <#list bookComment as comment>
                    <a href="/showComment.do?gid=${comment.gid}">
                        <#list comment.book.imgurl?split(",") as imgurl>
                        <#--<img src="../img/把话说到点子上.jpg" class="img"/>-->
                            <img src="${imgurl}" style="width: 200px;height: 220px">
                            <#break>
                        </#list>
                    </a>
                    <input type="hidden" value="${comment.gid}" name="gid">
                    <p align="center">
                        <a href="">${comment.book.name}</a>
                    </p>
        </div>
                    <div style="width: 600px;height: 300px;margin-left: 500px;margin-top: -230px;">
                        <div class="comment">
                            <p>评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价:</p>
                            </br>
                        </div>

                        <div class="check">

                            <textarea name="content" style="width: 500px;height: 200px;margin-left: 50px;"></textarea>
                            </br>
                            </br>
                            <button type="submit" class="but">发表</button>
                        </div>
                    </div>
                </#list>
            </#if>

    </div>
</form>
</body>

</html>