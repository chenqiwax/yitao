<!DOCTYPE html>
<html>
<#setting number_format="#">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <script src="${rc.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>订单结算页</title>

    <style>
        .span1 {
            font-family: "微软雅黑";
            line-height: 80px;
            padding-left: 145px;
        }

        .checkExistRong {
            color: red;
        }

        .span {
            color: red;
        }

        .chenck {
            color: red;
        }

        .detail {
            width: 280px;
            height: 35px;
            margin-left: 15px;
            border-radius: 5px;
        }

        .outer .table .tr {
            border: 1px dashed orangered;
            text-align: center;
        }

        select {
            padding: 5px 0;
            margin-left: 15px;
            width: 80px;
            font-family: "微软雅黑";
            border-radius: 5px;
            color: gray;
        }

        .error {
            color: red;
        }
    </style>
</head>
<link rel="stylesheet" href="../css/DingDan.css"/>

<body>
<#include "header.ftl">
<div style="width: 80%;margin-left: 165px;">
    <div>
        <img class="yuan" style="width: 166px;" src="${rc.contextPath}/img/yitao.jpg">
        <span style="color: blue;font-size: 30px;position: relative;top: -10px;}">订单结算页</span>
    </div>

    <div class="div2">

        <p class="p1" id="p2">
            等待买家付款
        </p>
        <p class="p1">
            剩余时间：<span id="endtime">180</span>
        </p>
    </div>
    <div class="div3">
        <table>
            <tr>
                <td>
                    <img src="../img/3.png"/>
                </td>
                <td>

                    <select style="margin-left: 15px;width:900px;height: 60px; border: 0px; font-size: 20px"
                            id="span3"
                            required="required">
                        <optgroup label="请选择收货地址">
                            <#if userAddress??>
                                <#list userAddress as userRess>
                                    <#if userRess.isdefault == 1>
                                        <option selected value="${userRess.id}">收货人：${userRess.name}
                                            &nbsp;&nbsp;&nbsp;手机号：${userRess.telephone}&nbsp;&nbsp;&nbsp;收货地址
                                            ：${userRess.province}${userRess.city}${userRess.district}${userRess.address}</option>
                                    <#else>
                                        <option value="${userRess.id}">姓名：${userRess.name}
                                            &nbsp;&nbsp;&nbsp;手机号：${userRess.telephone}&nbsp;&nbsp;&nbsp;收货地址
                                            ： ${userRess.province}${userRess.city}${userRess.district}${userRess.address}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </optgroup>
                    </select>
                    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="background-color: #1e90ff">
                        新增收货地址
                    </button>
                </td>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    新增收货地址
                                </h4>
                            </div>
                            <div>
                                <form action="/addUserAddress.do" method="post" id="addUser">
                                    <div style="margin-left: -30px;">
                                        <label class="span1">收货人:</label>
                                        <input type="text" name="name" class="detail" id="name" autocomplete="off" placeholder="收货人姓名" required/>
                                        <span class="span"></span>
                                        <br/>

                                        <label class="span1" style="">联系电话:</label>
                                        <input type="text" name="telephone" class="detail" autocomplete="off" placeholder="请输入电话号码" id="phone" min="11" required/>
                                        <span class="checkExistRong" id="checkExistPhone"></span>
                                        <br/>

                                        <label class="span1" name="contactType">所在地区:</label>
                                        <select style="margin-left: 15px;width: 90px;" name="province" id="province" required>
                                        </select>
                                        <select style="width: 90px;" name="city" id="city" required>
                                        </select>
                                        <select style="width: 90px;" name="district" id="district" required>
                                            <select style="width: 90px;" name="district" id="district" required>
                                            </select>
                                            <br/>

                                            <label class="span1">详细地址:</label>
                                            <input name="address" id="address" class="detail" autocomplete="off" type="text" value="" placeholder="详细地址" />
                                            <span class="chenck"></span>
                                            <br/>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #009688;color: white;">添加</button>
                                                <button type="reset" class="btn btn-primary" style="background-color: #009688;">重置</button>
                                            </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </tr>
        </table>
    </div>
    <div class="div4">
        <table>
            <tr>
                <td>
                    <img src="../img/4.png"/>
                </td>
                <td>
                    <a href="">易淘商城旗舰店＞</a>
                </td>
            </tr>
        </table>
        <div>
            <table id="table1">
                <tr style="background-color: aquamarine;" align="center">
                    <td>商品</td>
                    <td style="width: 150px;">书名</td>
                    <td>商品详情</td>
                    <td>数量</td>
                    <td>单价</td>
                </tr>
                <#list cartbook as boook>
                <tr align="center">
                    <td>
                        <#list boook.imgurl?split(",") as imgurl>
                            <#if imgurl_index == 0>
                                <a href="#"><img src="${imgurl}" style="width: 160px;height: 195px;"/></a>
                            </#if>
                        </#list>
                    </td>
                    <td>
                        ${boook.name}
                    </td>
                    <td>
                        ${boook.desc}
                    </td>
                    <td>
                        x${bookSize}
                    </td>
                    <td>
                        ￥ ${boook.price/100}
                    </td>
                </tr>
                </#list>
            </table>
            <div class="div5"></div>
            <table id="table2">
                <tr>
                    <td>
                        商品总价
                    </td>
                    <td></td>
                    <td class="td2">
                        ￥<span id="td2">${totalStr/100}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        下单时间
                    </td>
                    <td></td>
                    <td class="td2">
                        <span id="span1">${time?string["yyyy年MM月dd日 HH时mm分ss秒"]}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        订单编号
                    </td>
                    <td></td>
                    <td class="td2">
                        <span id="span2">${size}</span>
                    </td>
                </tr>

            </table>
        </div>
        <div class="div6">
            <button class="bt1"><a href="/myorder.do">取消订单</a></button>
            <form id="orderPay_form" action="/orderpay.do" method="post" style="display:inline">
                <input type="hidden" id="money" name="totalprice" value="${totalStr/100}">
                <input type="hidden" id="Onumber" name="serialnumber" value="${size}">
                <button class="bt1" id="but1">提交订单</button>
            </form>
        </div>
    </div>
</div>

</br>
</br>
<script src="${rc.contextPath}js/area.js"></script>
<script src="${rc.contextPath}js/select.js"></script>
<script>
    $("#but1").click(function () {
        var time = document.getElementById("span1").innerText;
        var aid = document.getElementById("span3").value;
        var totalprice = document.getElementById("td2").innerText;
        var serialnumber = document.getElementById("span2").innerText;
        event.preventDefault();
        $.post("addOrderitems.do", {
            "time": time,
            "totalprice": totalprice,
            "aid": aid,
            "serialnumber": serialnumber
        }, function (data, ststus) {
            if (data == "1") {
                $("#orderPay_form").submit();
                alert("订单提交成功");
            } else if (data == "2") {
                alert("请添加收货地址");
                window.location = "uadress.do"
            }else{
                alert("订单提交失败");
            }
        });

    });
</script>
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
                window.location.href = "#";
            } else {
                Account = setTimeout("RemainTime()", 1000);
            }
            iTime = iTime - 1;
        }
        document.getElementById(CID).innerHTML = sTime;
    }
</script>
</body>
</html>