<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">

    <style type="text/css">
        .layui-form-label {
            width: 110px;
        }

        .layui-form-item {
            margin-top: 35px;
        }
        .layui-input{
            width: 250px;

        }


    </style>

</head>
<body style="background: url(../static/img/bg-1.png) fixed;">
<div>
    <#include  "personal.ftl">
</div>
<div style="margin-left: 350px">

    <div class="layui-col-md-offset3" style="margin-top: 200px">
        <div class="layui-card-body">
            <h1 style="position: relative;left: 135px;bottom: 20px;">个人信息修改</h1>
            <form class="layui-form" id="upd_UserInfor" role="form" action="/updateUserinfo.do" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="account" id="username"
                               lay-verify="user_account" autocomplete="off"
                               placeholder="请输入用户名" class="layui-input" value=<#if nowUser.account??>
                            ${nowUser.account}
                        <#else>
                                ""
                            </#if>>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别:</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="sex" value="男" title="男" checked="checked"
                        <#if nowUser.sex??>
                            <#if nowUser.sex=='男'>
                                        checked
                            <#else >
                            </#if>
                        <#else >
                        </#if>>
                            <input type="radio" name="sex" value="女" title="女"
                         <#if nowUser.sex??>
                             <#if nowUser.sex=='女'>
                                        checked
                             <#else >
                             </#if>
                         <#else >
                         </#if> />
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">昵称:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="nickname" id="nickname"
                                   lay-verify="user_nickName" autocomplete="off"
                                   placeholder="请输入昵称" class="layui-input"
                                    value=<#if nowUser.nickname??>
                                ${nowUser.nickname}
                            <#else >
                            ""
                            </#if>>
                            <span class=".nicks"></span>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">出生日期:</label>
                        <div class="layui-input-inline">
                            <input  name="birthdays" id="birthday_time"
                                   lay-verify="user_birthday" autocomplete="off"
                                   placeholder="请输入出生日期" class="layui-input"
                                    value=<#if nowUser.birthday??>
                                ${nowUser.birthday?string("yyyy-MM-dd")}
                            <#else >
                                ""
                        </#if>>
                        <span class="date" id="span"></span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" id="email"
                               lay-verify="user_email" autocomplete="off"
                               placeholder="请输入邮箱" class="layui-input"
                                value= <#if nowUser.email??>
                            ${nowUser.email}
                        </#if>>
                        <span class="email" id="span"></span>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-left: 50px">
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal layui-btn-radius" lay-submit
                                lay-filter="sub_upd_userInfo" id="button" type="submit" style="margin-left: 40px;">确认修改
                        </button>
                        <button type="reset" class="layui-btn layui-btn-normal layui-btn-radius"
                                style="width: 95px;margin-left:260px;margin-top: -63px;">重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;
        form.verify({
            user_email: function (value) {
                if (value != '') {
                    if (!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/).test(value)) {
                        return "请输入正确的邮箱地址!!!"
                    }
                }
            }
        });
        //监听提交
        form.on('submit(sub_upd_userInfo)', function (data) {
            /*layer.msg(JSON.stringify(data.field));*/
            var username_val = $("#username").val();
            var nickname_val = $("#nickname").val();
            var email_val = $("#email").val();
            var bool = false;
            $.post("${rc.contextPath}/existusername.do", {"username": username_val}, function (data) {
                if (data.status == 200) {
                    $.post("${rc.contextPath}/existemail.do", {"email": email_val}, function (data) {
                        if (data.status == 200) {
                            $.post("${rc.contextPath}/existnickname.do", {"nickName": nickname_val}, function (data) {
                                if (data.status == 200) {
                                    layer.msg("修改成功", {icon: 1});
                                    setInterval(function () {
                                        $("#upd_UserInfor").submit();
                                    },2000);

                                }
                                if (data.status == 404) {
                                    layer.msg(data.msg, {icon: 5});
                                    bool = false;
                                }
                            });
                        }
                        if (data.status == 404) {
                            layer.msg(data.msg, {icon: 5});
                            bool = false;
                        }
                    });
                }
                if (data.status == 404) {
                    layer.msg(data.msg, {icon: 5});
                    bool = false;
                }
            });
            return false;
        });
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#birthday_time' //指定元素
        });
    });
</script>
</html>
