<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>基本信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="${rc.contextPath}img/logos.ico"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="${rc.contextPath}/bookcss/bootstrap.css"/>
    <script src="${rc.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${rc.contextPath}/js/bootstrap.js"></script>
    <script src="${rc.contextPath}/js/message.js"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">

</head>

<body>

<#include "unavigation.ftl">

<div style="width: 700px;height: 580px;margin-left: 445px;">
    <div style="width: 210px;margin-top: -188px;">
        <ol class="breadcrumb">
            <li>
                <a href="#">我的易淘</a>
            </li>
            <li>
                <a href="#">账户安全</a>
            </li>
            <li class="active">基本信息</li>
        </ol>
    </div>
    <form class="form-horizontal layui-form" id="upd_UserInfor" role="form" action="/updateUserinfo.do" method="post">
        <div class="col-sm-12" style="width: 550px;height: 435px;margin-left: 120px;margin-top: 30px;">
            <div class="form-group" style="margin-bottom: 30px">
                <label class="col-sm-3 control-label" style="font-size: 20px">用户名:</label>
                <div class="col-sm-4">
                    <input style="width: 200px;" type="text" class="form-control" lay-verify="user_account" required lay-verify="required" id="username"  name="account" autocomplete="off"
                           placeholder="请输入用户名"
                            value=<#if findUserByUserId.account??>
                        ${findUserByUserId.account}
                    <#else>
                        ""
                    </#if> />


                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px;">
                <label class="col-sm-3 control-label" style="font-size: 20px">性别:</label>
                <div class="col-sm-4">
                    <label>男</label>
                    <input type="radio" name="sex" id="sex" value="男"
                        <#if findUserByUserId.sex??>
                               <#if findUserByUserId.sex=='男' >
                                        checked
                                    <#else>
                               </#if>
                             <#else>
                        </#if>/>

                    <label style="margin-left: 18px;">女</label>
                    <input type="radio" name="sex" id="sex" value="女"
                            <#if findUserByUserId.sex??>
                                <#if findUserByUserId.sex=='女' >
                                        checked
                                <#else>
                                </#if>
                                <#else>
                            </#if>/
                </div>
            </div>
            <div class="form-group" style="margin-bottom:30px">
                <label class="col-sm-3 control-label" style="font-size: 20px">昵称:</label>
                <div class="col-sm-4">
                    <input style="width: 200px;" type="text" class="form-control" lay-verify="user_nickName" autocomplete="off" id="nickname" name="nickname"
                           placeholder="请输入新昵称"
                           onblur="nickname_format()"
                            value=<#if findUserByUserId.nickname??>
                        ${findUserByUserId.nickname}
                    <#else >
                    ""
                    </#if> />
                    <span class=".nicks"></span>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px">
                <label class="col-sm-3 control-label" style="font-size: 20px">出生日期:</label>
                <div class="col-sm-4">
                    <input style="width: 200px;" type="text" class="form-control" autocomplete="off" id="birthday_time" name="birthdays"
                           placeholder="请输入出生日期"
                           onblur="date_format()"
                            value=<#if findUserByUserId.birthday??>
                        ${findUserByUserId.birthday?string("yyyy-MM-dd")}
                    <#else >
                            ""
                    </#if> />
                    <span class="date" id="span"></span>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px">
                <label class="col-sm-3 control-label" style="font-size: 20px">邮箱:</label>
                <div class="col-sm-4">
                    <input style="width: 200px;" type="text" class="form-control" lay-verify="user_email" autocomplete="off" id="email" name="email"
                           placeholder="请输入邮箱地址"
                           onblur="email_format()"
                           value= <#if findUserByUserId.email??>
                        ${findUserByUserId.email}
                    </#if> />
                    <span class="email" id="span"></span>
                </div>
            </div>
            <div class="form-group">
                <button type="submit"
                        class="btn btn-primary"
                        lay-submit lay-filter="sub_upd_userInfo"
                        id="button" style="width: 85px;height: 35px;font-size: 15px;">确认修改</button>
                <button type="reset" class="btn btn-primary" style="width: 85px;height: 35px;font-size: 15px;margin-left: 230px;">
                    <a href="userinfo.do"
                       style="color: white;text-decoration: none;">重置</a>
                </button>
            </div>
        </div>
    </form>

</div>

<div style="margin-top: -150px;margin-left:150px">
    <#include "bottom.ftl">
</div>
</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/javascript">
    layui.use('form',function () {
        var form=layui.form;
        form.verify({
            user_email:function (value) {
                if (value!='') {
                    if (!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/).test(value)){
                        return "请输入正确的邮箱地址!!!"
                    }
                }
            }
        });
        //监听提交
        form.on('submit(sub_upd_userInfo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            var username_val=$("#username").val();
            var nickname_val=$("#nickname").val();
            var email_val=$("#email").val();
            var bool = false;
            $.post("${rc.contextPath}/existusername.do",{"username":username_val},function (data) {
                if (data.status==200) {
                    $.post("${rc.contextPath}/existemail.do",{"email":email_val},function (data) {
                        if (data.status==200) {
                            $.post("${rc.contextPath}/existnickname.do",{"nickName":nickname_val},function (data) {
                                if (data.status==200) {
                                    $("#upd_UserInfor").submit();
                                }
                                if(data.status==404) {
                                    layer.msg(data.msg,{icon: 5});
                                    bool= false;
                                }
                            });
                        }
                        if(data.status==404) {
                            layer.msg(data.msg,{icon: 5});
                            bool= false;
                        }
                    });
                }
                if(data.status==404) {
                    layer.msg(data.msg,{icon: 5});
                    bool= false;
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