<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head lang="en">
    <meta charset="UTF-8">
    <title>用户地址管理</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <style>
        .head {
            margin: auto;
            margin-top: 20px;
            width: 80%;
            height: 100px;
            border: 1px solid gray;
        }

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
            margin-left: 490px;
            margin-top: -130px;
        }

        .detail {
            width: 280px;
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
            font-size: 20px;
            color: gray;
            line-height: 80px;
            padding-left: 145px;
        }

        .submit {
            width: 85px;
            height: 40px;
            background-color: red;
            border: none;
            margin-left: 270px;
            color: white;
            font-family: "微软雅黑";
            font-size: 20px;
            border-radius: 5px;
        }

        .quxiao {
            width: 85px;
            height: 40px;
            background-color: red;
            border: none;
            margin-left: 30px;
            color: white;
            font-family: "微软雅黑";
            font-size: 20px;
            border-radius: 5px;
        }

        .checkExistRong {
            color: red;
        }
        .error{
            color: red;
        }
    </style>
</head>

<body>
<div>
    <form action="/updateUserAddress.do" method="post" id="updateAddress">
        <div class="outer">
            <h3 style="padding-left: 300px;">修改地址</h3>
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
            <select style="margin-left: 15px;width: 90px;" name="province" id="province">
            </select>
            <select style="width: 90px;" name="city" id="city">
            </select>
            <select style="width: 90px;" name="district" id="district">
            </select>
            </select>
            <span class="checkExistRong" id="checkExist"></span>
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

            <input type="submit" value="修改" class="submit"/> &nbsp;&nbsp;
            <input type="button" onclick="window.history.go(-1)" value="取消" class="quxiao"/> &nbsp;&nbsp;
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