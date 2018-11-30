<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/demo.css">
</head>

<body>
<header>
    <div class="header-all" style="
    background-color: #F9F9F9;">
        <nav>
            <ul class="area">
                 <img class="img1" style="width: 30px;" src="${rc.contextPath}/img/logo.ico"/>
                <a class="a1" target="_self" href="/index.do" style="font-size: 20px;color: #646464;">首页</a>
            </ul>
            <ul class="navList">
                 <#if Session.nowUser??>
                      <li class="navList-top">欢迎光临易淘,${Session.nowUser.account}
                      </li>
                 <#else>
                      <li class="navList-top">欢迎光临易淘,请
                          <a class="login" href="${rc.contextPath}/login.do" target="_self">登录</a>
                      </li>
                    <li class="navList-top">
                        <a href="${rc.contextPath}/regist.do" target="_self">注册</a>
                    </li>
                 </#if>
                <li class="navList-top shopCar">
                    <a href="${rc.contextPath}/selectCart.do" target="_self">购物车</a><span
                        class="login"><#if mycartSize??>${mycartSize}</#if></span></li>
                <li class="navList-top">
                    <a href="${rc.contextPath}/usermodify.do" target="_self">个人中心</a>
                </li>
                <li class="hov">
                    <p class="navList-top nav-hov">
                        <a href="" target="_blank" onmouseover="showMsgCount()">我的易淘</a><b></b></p>
                    <div class="dangList1">
                        <p>
                            <a href="${rc.contextPath}/showMsg.do" target="_self">消息</a><span id="msgCount" class="login"></span>
                        </p>
                        <p>
                            <a href="${rc.contextPath}/myfavcenter.do" target="_self">我的收藏</a>
                        </p>
                        <p>
                            <a href="${rc.contextPath}/showBooks.do" target="_self">我的足迹</a>
                        </p>
                        <p>
                            <a href="${rc.contextPath}/myorder.do" target="_self">我的订单</a>
                        </p>
                    </div>

                </li>
                 <#if Session.nowUser??>
                    <li class="navList-top">
                        <a href="${rc.contextPath}/exit.do" target="_self">安全退出</a>
                    </li>
                 </#if>
            </ul>
        </nav>
    </div>

</header>
</body>
<script type="text/javascript">
    function sendAjax(url, methodType, params, reFun) {
        var xmlhttp = null;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                reFun(xmlhttp.responseText); // 调用这个函数将返回的数据当作参数传进这个函数
            }
        }
        if (methodType == "get" || methodType == "GET") { // 判断请求类型是get还是post
            xmlhttp.open("GET", url + "?" + params, true);
            xmlhttp.send();
        } else {
            xmlhttp.open("POST", url, true);
            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            xmlhttp.send(params);
        }
    }
</script>
<script type="text/javascript">
    function showMsgCount() {
        sendAjax("/showMsgCount.do", "get", "uid=<#if Session.nowUser??>${Session.nowUser.id}</#if>", function (responseText) { // 这里uid是固定死的   到时记得修改   pyj书.
            $("#msgCount").text(responseText);
        })
    }
</script>

</html>