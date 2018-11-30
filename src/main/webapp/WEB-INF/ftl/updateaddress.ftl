<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户地址管理</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <style>
        select {
            padding: 5px 0;
            margin-left: 15px;
            width: 80px;
            font-family: "微软雅黑";
            border-radius: 5px;
            color: gray;
        }

        .outer {
            width: 800px;
            height: 500px;
            margin-top: 280px;
            text-align: center;
        }

        .detail {
            width: 308px;
            height: 35px;
            margin-left: 15px;
            border-radius: 5px;
        }

        .span {
            font-family: "微软雅黑";
            font-size: 30px;
            color: red;
        }

        .span1 {
            font-family: "微软雅黑";
            color: gray;
            line-height: 80px;
            padding-left: 145px;
        }

        .submit {
            width: 85px;
            height: 40px;
            background-color: #1e9fff;
            border: none;
            color: white;
            font-family: "微软雅黑";
            font-size: 20px;
            border-radius: 20px;
        }

        .quxiao {
            width: 85px;
            height: 40px;
            background-color: #1e9fff;
            position: relative;
            left: 125px;
            border: none;
            color: white;
            font-family: "微软雅黑";
            font-size: 20px;
            border-radius: 20px;
        }

        .checkExistRong {
            color: red;
        }
        .error{
            color: red;
        }
        .ling{
            width: 900px;
            height: 800px;
            margin: auto;
        }
    </style>
</head>

<body style="background: url(../static/img/bg-1.png) fixed;">
<div>
<#include  "personal.ftl">
</div>
<div class="ling">
    <form action="/updateUserAddress.do" method="post" id="updateAddress">
        <div class="outer">
            <h2 style="margin-left: 176px">修改收货地址</h2>
            <input type="hidden" name="id" value="${userAddressById.id}">
            <label class="span1" style="padding-left: 160px;">收货人:</label>
            <input type="text" name="name" id="name" class="detail" placeholder="收货人姓名"
                   required="required" autocomplete="off"    value=<#if userAddressById.name??>
                ${userAddressById.name}
            <#else>
                        ""
            </#if> />
            <span class="span"></span>
            <br/>

            <label class="span1">所在地区:</label>
            <select style="margin-left: 15px;width: 90px;" name="province" id="province" required>
                <option value="">请选择</option>
            </select>
            <select style="width: 90px;" name="city" id="city" required>
                <option value="">请选择</option>
            </select>
            <select style="width: 90px;" name="district" id="district" required>
                <option value="">请选择</option>
            </select>
            <br/>

            <label class="span1">详细地址:</label>
            <input class="detail" name="address" id="address" type="text" placeholder="详细地址"
                   required="required" autocomplete="off"     value=<#if userAddressById.address??>
                ${userAddressById.address}
            <#else>
                   ""
            </#if> />
            <span class="chenck"></span>
            <br/>

            <label class="span1">联系电话:</label>
            <input type="text" name="telephone" class="detail" required="required" autocomplete="off"
                   placeholder="请输入电话号码" id="phone" min="11" value="${userAddressById.telephone}"/>
            <span class="checkExistRong" id="checkExistPhone"></span>
            <br/>
            <div>
                <input type="submit" value="修改" class="submit" style="margin: 51px"/>
                <input type="button" onclick="window.history.go(-1)" value="取消" class="quxiao"/>
            </div>
        </div>
    </form>
</div>
</body>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
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

</script>

</html>