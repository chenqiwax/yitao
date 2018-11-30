<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加地址</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
    <script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
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
            width: 308px;
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
    <div>

        <form action="/addUserAddress.do" method="post" id="addUser">
            <div style="margin-top: 213px;margin-left: 60px;">
                <label class="span1" style="padding-left: 160px;">收货人:</label>
                <input type="text" name="name" class="detail" id="name" autocomplete="off"
                       placeholder="收货人姓名" required/>
                <span class="span"></span>
                <br/>

                <label class="span1" style="">联系电话:</label>
                <input type="text" name="telephone" class="detail" autocomplete="off"
                       placeholder="请输入电话号码" id="phone" min="11" required/>
                <span class="checkExistRong" id="checkExistPhone"></span>
                <br/>

                <label class="span1" name="contactType">所在地区:</label>
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
                <input name="address" id="address" class="detail" autocomplete="off" type="text"
                       value="" placeholder="详细地址" required/>
                <span class="chenck"></span>
                <br/>
                <div class="layui-form-item" style="padding-bottom: 30px;">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal layui-btn-radius" lay-submit="" lay-filter="demo1" style="width: 92px;margin: 51px">添加</button>
                        <button type="reset" class="layui-btn layui-btn-normal layui-btn-radius" lay-filter="demo1" style=" width: 92px;margin-left: 80px">重置</button>
                    </div>
                </div>
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
</script>
</html>
