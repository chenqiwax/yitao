<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>支付订单</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico"/>

    <link rel="stylesheet" href="${rc.contextPath}/css/style.css" type="text/css"/>

    <script type="text/javascript" src="${rc.contextPath}/js/jquery-1.8.2.min.js"></script>
    <#setting number_format="#">
</head>

<body>
<#include "header.ftl">
<div style="position: relative;left: 165px;top: 60px;">
    <img class="yuan" style="width: 166px;" src="${rc.contextPath}/img/yitao.jpg">
   <#-- <span style="position: relative;top: 10px;color: blue;font-size: 30px;}">订单支付页</span>-->
</div>

<!--头部  star-->
<header style="color:#fff">
    <a href="javascript:history.go(-1);">
        <div class="_left"><img src="${rc.contextPath}/images/left.png"></div>
    </a>
</header>
<!--头部 end-->
<!--内容 star-->
<div class="contaniner fixed-cont">

    <div class="payTime">
        <li><span>剩余时间：<span id="endtime">180</span></span>
        </li>
        <li><strong>¥${money/100}</strong></li>
        <li>订单号:<font id="orderId_font">${number}</font></li>
    </div>

    <!--支付 star-->
    <div class="pay">
        <div class="show">
            <li><label><img src="${rc.contextPath}/images/weixin.png"><font size="4">微信支付</font><input name="Fruit" type="radio"
                                                                                        value="" checked/><span></span></label>
            </li>
            <li><label><img src="../images/zhifubao.png"><font size="4">支付宝支付</font><input name="Fruit" type="radio"
                                                                                           value=""/><span></span></label>
            </li>
            <li><label><img src="../images/yue.png"><font size="4">余额支付</font><input name="Fruit" type="radio"
                                                                                     value=""/><span></span></label>
            </li>
            <li class="center">
                <a href="#" onClick="showHideCode()">
                    <font size="5">查看更多支付方式↓</font>
                </a>
            </li>
        </div>
        <div class="showList" id="showdiv" style="display:none;">
            <li><label><img src="${rc.contextPath}/images/yinhang.png"><font size="4">银行卡</font><input name="Fruit" type="radio"
                                                                                        value=""/><span></span></label>
            </li>
            <li><label><img src="${rc.contextPath}/images/weixin.png"><font size="4">添加更多</font><input name="Fruit" type="radio"
                                                                                        value=""/><span></span></label>
            </li>

            <li style="background:none"></li>
        </div>
    </div>
    <form action="/wxpay.do" id="wxpay_form" method="post">
        <input type="hidden" id="orderId_ipt" name="orderId">
    </form>
    <!--支付 end-->

</div>

<div class="book-recovery-bot2" id="footer">
        <div class="payBottom" id="pay_div">
            <li class="textfr">确认支付:</li>
            <li class="textfl"><span>¥${money/100}</span></li>
        </div>
</div>
<!--内容 end-->
<script type="text/javascript">
    $(document).ready(function () {
       $("#pay_div").click(function () {
           var a=$("#orderId_font").text();
           $("#orderId_ipt").val(a);
           if("" === $("#orderId_ipt").val().trim()){
               alert("订单号有误");
           }else {
               $("#wxpay_form").submit();
           }
       });
    });
</script>
<script type="text/javascript">
    function showHideCode() {
        $("#showdiv").toggle();
    }
</script>

</body>

<script type="text/javascript">
    var CID = "endtime";
    if (window.CID != null) {
        var iTime = document.getElementById(CID).innerHTML;
        var Account;
        RemainTime();
    }

    function RemainTime() {
        var iDay, iHour, iMinute, iSecond;
        var sDay = "",
                sTime = "";
        if (iTime >= 0) {

            iMinute = parseInt((iTime / 60) % 60);
            iSecond = parseInt(iTime % 60);

            sTime = iMinute + "分钟" + iSecond + "秒";

            if (iTime == 0) {
                clearTimeout(Account);
                sTime = "<span style='color:green'>时间到了！此订单已取消</span>";
                window.location.href = "/myorder.do";
            } else {
                Account = setTimeout("RemainTime()", 1000);
            }
            iTime = iTime - 1;
        }
        document.getElementById(CID).innerHTML = sTime;
    }
</script>


</html>