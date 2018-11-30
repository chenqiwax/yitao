<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="img/logos.ico"/>
    <title>搜索结果页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/assets/css/layui.css" media="all">
    <#setting number_format="#">
    <style>
        .caimg1 {
            width: 200px;
            height: 200px;
            border: 0px solid red;
            margin-left: 4px;
        }

        .div1 {
            width: 234px;
            height: 285px;
            border: 0px solid red;
        }

        .caimg1:hover {
            -webkit-animation: doudong 1s .1s ease both;
            -moz-animation: tada 1s .1s ease both;
        }

        @-webkit-keyframes doudong {
            0% {
                -webkit-transform: scale(1)
            }
            10%,
            20% {
                -webkit-transform: scale(0.8) rotate(-2deg)
            }
            30%,
            50%,
            70%,
            90% {
                -webkit-transform: scale(1.1) rotate(2deg)
            }
            40%,
            60%,
            80% {
                -webkit-transform: scale(1.1) rotate(-2deg)
            }
            100% {
                -webkit-transform: scale(1) rotate(0)
            }

        .inputca {
            border: none;
            background-color: red;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.1.4.2-min.js"></script>
    <script type="text/javascript" href="../js/jquery-3.3.1.js"></script>
</head>

<body align="center">

<#include "header.ftl">
<#include "search.ftl">

<hr style="width: 100%;background-color:#1e9fff;">

<div class="" style="width: 1200px;margin: auto;">
    <div class="layui-row">
        <#list listBook as book>
            <#if (book_index)%5 == 0>
                    <div class="layui-col-md1 layui-col-lg3 layui-col-xs1"></div>
            </#if>
            <div class="div1 layui-col-md1 layui-col-lg1 layui-col-xs1" style="width: 234px;height: 285px;border: px solid red;">
                                  <#list book.imgurl?split(",") as img>
                                      <#if img_index == 0>
                                            <a href="${rc.contextPath}/showBook/${book.id}.do"> <img class="caimg1"
                                                                                                     src="${img}"/></a>
                                      </#if>
                                  </#list>
                <div style="color: red;width: 234px;height: 55px;text-align: center; margin: 0 auto;">
                    <a href="${rc.contextPath}/showBook/${book.id}.do">${book.bookName}</a>
                </div>
                <p>
                    <span style="color: red;font-size: 20px">￥${book.price/100}</span>
                </p>
            </div>
             <#if (book_index+1)%10 == 0>
                    <div class="layui-col-md3"></div>
             </#if>
        </#list>
    </div>
</div>
<#--<div style="width: 82%;border: 1px solid black;margin-left: 120px;margin-top:20px;">
    <table style="width: 82%">

            <#list listBook as book>
                <#if (book_index)%5 == 0>
                    <tr>
                </#if>
                <td>
                    <div class="div1" style="width: 234px;height: 285px;border: px solid red;">
                              <#list book.imgurl?split(",") as img>
                                  <#if img_index == 0>
                                        <a href="${rc.contextPath}/showBook/${book.id}.do"> <img class="caimg1"
                                                                                                 src="${img}"/></a>
                                  </#if>
                              </#list>
                        <div style="color: red;width: 234px;height: 55px;text-align: center; margin: 0 auto;">
                            <a href="${rc.contextPath}/showBook/${book.id}.do">${book.name}</a>
                        </div>
                        <p>
                            <span>￥${book.price/100}</span>
                        </p>
                    </div>
                </td>
               <#if (book_index+1)%10 == 0>
                   </tr>
               </#if>
            </#list>
    </table>
</div>-->
<!--分类-->
<div id="demo2"></div>

<script src="${rc.contextPath}/assets/layui.all.js" charset="utf-8"></script>
<script>
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage,
                layer = layui.layer;

        //自定义样式
        laypage.render({
            elem: 'demo2',
            count: ${bookCount}, //数据总数
            limit: 20,
            theme: '#1E9FFF',
            curr: ${page},
            jump: function (obj, first) {
                /*alert(obj.curr);*/ //得到当前页，以便向服务端请求对应页的数据。
                //首次不执行
                if (!first) {
                    location.href = '?page=' + obj.curr + "&keyword=" + '${keyword}';
                }
            }
        });

    });
</script>

</body>

</html>