<!DOCTYPE html>
<html>
<#setting number_format="#">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <script src="${rc.contextPath}/layui/layui.all.js"></script>
    <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/messages_zh.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">

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

<style>
    .book_img {
        width: 80px;
        height: 80px;
    }
</style>

<body>

<#include "header.ftl">
<div style="width: 80%;margin-left: 165px;">
    <div>
        <img class="yuan" style="width: 166px;" src="${rc.contextPath}/img/yitao.jpg">
       <#-- <span style="color: blue;font-size: 30px;position: relative;top: -20px;}">订单结算页</span>-->
    </div>

    <div class="div2">

        <p class="p1" id="p2">
            等待买家付款
        </p>
        <p class="p1">
            剩余时间：<span id="endtime">1800</span>
        </p>
    </div>
    <div class="div3">
        <table>
            <tr>
                <td>
                    <img style="width: 40px" src="../img/3.png"/>
                </td>
                <td>

                    <select style="margin-left: 15px;width:590px;height: 38px; border: 0px; font-size: 15px" id="span3"
                            required="required">
                        <optgroup id="addadress_optgroup" label="请选择收货地址">
                        <#if userAddress??>
                            <#list userAddress as userRess>
                                <#if userRess.isdefault == 1>
                                        <option selected value="${userRess.id}">收货人：${userRess.name}
                                            &nbsp;&nbsp;&nbsp;手机号：${userRess.telephone}&nbsp;&nbsp;&nbsp;收货地址
                                            ：${userRess.province}${userRess.city}${userRess.district}${userRess.address}</option>
                                <#else>
                                        <option value="${userRess.id}">收货人：${userRess.name}
                                            &nbsp;&nbsp;&nbsp;手机号：${userRess.telephone}&nbsp;&nbsp;&nbsp;收货地址
                                            ： ${userRess.province}${userRess.city}${userRess.district}${userRess.address}</option>
                                </#if>
                             </#list>
                        </#if>
                        </optgroup>
                    </select>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="background-color: #1e90ff;border: none">
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
                                    <div style="margin-left: -30px;">
                                        <form id="useraddress_form" action="">
                                            <label class="span1">收货人:</label>
                                            <input type="text" name="name" class="detail" id="name" autocomplete="off" placeholder="收货人姓名"/>
                                            <span class="span"></span>
                                            <br/>

                                            <label class="span1" style="">联系电话:</label>
                                            <input type="text" name="telephone" class="detail" autocomplete="off" placeholder="请输入电话号码" id="phone" min="11"/>
                                            <span class="checkExistRong" id="checkExistPhone"></span>
                                            <br/>

                                            <label class="span1" name="contactType">所在地区:</label>
                                            <select style="margin-left: 15px;width: 90px;" name="province" id="province" >
                                                <option value="">请选择</option>
                                            </select>
                                            <select style="width: 90px;" name="city" id="city">
                                                <option value="">请选择</option>
                                            </select>
                                            <select style="width: 90px;" name="district" id="district">
                                                <option value="">请选择</option>
                                            </select>
                                            <br/>

                                            <label class="span1">详细地址:</label>
                                            <input name="address" id="address" class="detail" autocomplete="off" type="text" value="" placeholder="详细地址"/>
                                            <span class="chenck"></span>
                                            <br/>
                                            <div class="modal-footer">
                                                <!--  模态框底部样式，一般是提交或者确定按钮 -->
                                                <button type="submit" id="add" data-dismiss="modal" class="btn btn-default">确定</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
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
                <tr style="background-color: #00F7DE;height: 40px" align="center">
                    <td>商品</td>
                    <td style="width: 150px;">书名</td>
                    <td>商品详情</td>
                    <td>数量</td>
                    <td>单价</td>
                </tr>
                <#list cartbook as boook>
                <tr align="center"  style="height: 100px">
                    <td>
                        <#list boook.imgurl?split(",") as imgurl>
                            <#if imgurl_index == 0>
                                <a href="#"><img class="book_img" src="${imgurl}"/></a>
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
                        x${boook.quantity}
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
            <a href="javascript:" onclick="self.location=document.referrer;" style="color: white;">
            <button class="bt1" id="quxiaoOrder" style="background-color: #1e9fff;width: 100px">
               取消订单
            </button>
            </a>
            <form id="orderPay_form" action="/orderpay.do" method="post" style="display:inline">
                <input type="hidden" id="money" name="totalprice" value="${totalStr}">
                <input type="hidden" id="Onumber" name="serialnumber" value="${size}">
                <button class="bt1" id="but1" style="color: white;background-color: #1e9fff;width: 100px" >提交订单</button>
            </form>
        </div>
    </div>
</div>

</br>
</br>
<script src="${rc.contextPath}js/area.js"></script>
<script src="${rc.contextPath}js/select.js"></script>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script>
    $(document).ready(function () {
        $("#useraddress_form").validate({
            rules: {
                name: {
                    required: true
                },
                telephone: "required",
                province: {
                    required: true
                },
                city: {
                    required: true
                },
                district: {
                    required: true
                },
                address:{
                    required:true
                }
            },
            messages: {
                name: {
                    required: "请输入收货人姓名"
                },
                telephone: "请输入手机号",
                province: {
                    required: "请选择省份"
                },
                city: {
                    required: "请选择城市"
                },
                district: {
                    required: "请选择区域"
                },
                address: "请输入详细地址"
            }

        });
    });
    $().ready(function () {
        $("#useraddress_form").validate({
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
</script>
<script>

    // 验证手机号
    function isPhoneNo(phone) {
        var pattern = /^1(3|4|5|7|8)\d{9}$/;
        return pattern.test(phone);
    }

    /*手机号判断*/
    function userTel(inputid, spanid) {
        $(inputid).blur(function () {
            if ($.trim($(inputid).val()).length == 0) {
                $(spanid).html("× 手机号没有输入");
            } else {
                if (isPhoneNo($.trim($(inputid).val())) == false) {
                    $(spanid).html("× 手机号码不正确");
                }
            }
            $(inputid).focus(function () {
                $(spanid).html("");
            });
        });
    };
    userTel('#phone', "#checkExistPhone");

    $("#add").click(function () {
        var name = $("#name").val();
        var telephone = $("#phone").val();
        var province = $("#province").val();
        var city = $("#city").val();
        var district = $("#district").val();
        var address = $("#address").val();
        var date = {
            name: name,
            telephone: telephone,
            province: province,
            city: city,
            district: district,
            address: address
        };
        if (name=="") {
            layer.msg("请输入收货人姓名", {icon: 5});
            return
        }
        if (telephone=="") {
            layer.msg("请输入手机号", {icon: 5});
            return
        }
        if (province=="") {
            layer.msg("请选择省份", {icon: 5});
            return
        }
        if (city=="") {
            layer.msg("请选择城市", {icon: 5});
            return
        }
        if (district=="") {
            layer.msg("请选择区域", {icon: 5});
            return
        }
        if (address=="") {
            layer.msg("请输入详细地址", {icon: 5});
            return
        }
        $.post("/addAddress.do",date,function (data) {
            if(data.status==200) {
                /*console.log(data.data);*/
                var obj = JSON.parse(data.data);
                $("#addadress_optgroup").empty();
                for (index_adress in obj) {
                    var adress=obj[index_adress];
                    $("#addadress_optgroup").append("<option value='"+adress.id+"'>姓名:"+adress.name+"&nbsp;&nbsp;&nbsp;手机号:"+adress.telephone+"&nbsp;&nbsp;&nbsp;收货地址:"+adress.province+adress.city+adress.district+adress.address+"</option>")
                }
            }
        });
    });
</script>
<script>
    $("#but1").click(function () {
        var time = document.getElementById("span1").innerText;
        var aid = document.getElementById("span3").value;
        var totalprice = document.getElementById("td2").innerText;
        var serialnumber = document.getElementById("span2").innerText;
        event.preventDefault();
        $.post("addOrderitem.do", {
            "time": time,
            "totalprice": totalprice,
            "aid": aid,
            "serialnumber": serialnumber
        }, function (data, ststus) {
            if (data == "1") {
                $("#orderPay_form").submit();
                layer.msg("订单提交成功", {icon: 1});
            } else if (data == "2") {
                layer.msg("请添加收货地址", {icon: 5});
            }else{
                layer.msg("请添加收货地址", {icon: 2});
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
                window.location.href = "/myorder.do";
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