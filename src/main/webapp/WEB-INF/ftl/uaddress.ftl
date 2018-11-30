<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>添加收货地址</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico"/>
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

        .submit {
            width: 80px;
            height: 40px;
            background-color: red;
            border: none;
            margin-left: 270px;
            color: white;
            font-family: "微软雅黑";
            font-size: 20px;
            border-radius: 5px;
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
        .error{
            color: red;
        }
    </style>
</head>

<body>

<#include "unavigation.ftl">

<div>
    <div>

        <form action="/addUserAddress.do" method="post" id="addUser">
            <div style="margin-left: 480px;margin-top: -142px;">
                <h3 style="padding-left: 300px;">添加地址</h3>
                <label class="span1" style="padding-left: 160px;">收货人:</label>
                <input type="text" name="name" class="detail" id="name" autocomplete="off"
                       placeholder="收货人姓名"/>
                <span class="span"></span>
                <br/>

                <label class="span1">联系电话:</label>
                <input type="text" name="telephone" class="detail" autocomplete="off"
                       placeholder="请输入电话号码" id="phone" min="11"/>
                <span class="checkExistRong" id="checkExistPhone"></span>
                <br/>

                <label class="span1" name="contactType">所在地区:</label>
                <select style="margin-left: 15px;width: 90px;" name="province" id="province" required="required">
                </select>
                <select style="width: 90px;" name="city" id="city">
                </select>
                <select style="width: 90px;" name="district" id="district">
                </select>
                <br/>

                <label class="span1">详细地址:</label>
                <input name="address" id="address" class="detail" autocomplete="off" type="text"
                       value="" placeholder="详细地址"/>
                <span class="chenck"></span>
                <br/>

                <input type="submit" style="width: 92px;" value="添加" class="submit"/> &nbsp;&nbsp;
            </div>
        </form>
    </div>

</div>
</body>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/area.js"></script>
<script src="js/select.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
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


    $(document).ready(function () {
        $("#addUser").validate({
            rules: {
                name: "required",
                province: "required",
                city:{
                    required: true
                },
                district:{
                    required:true
                },
                address: "required"
            },
            messages: {
                name:"请输入收货人姓名",
                province:"请输入省份",
                city:"请输入城市",
                district:"请输入区/县",
                address: "请输入详细收货地址"
            }
        });
    });
</script>
</html>