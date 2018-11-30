<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>易淘注册</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico"/>
    <link rel="stylesheet" href="${rc.contextPath}css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.css"/>
    <script src="${rc.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${rc.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
    <style type="text/css">
        .cd {
            width: 400px;
            height: 650px;
        }

        body {
            background-image: url(${rc.contextPath}/img/bei.png);
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }

        .card {
            position: relative;
            top: 60px;
            left: 255px;
            opacity: 1.0;
        }

        #but1 {
            width: 100%;
            height: 36px;
            position: relative;
            top: 14px;
        }

        #a1 {
            position: relative;
            left: 195px;
            top: 83px;
        }

        #name {
            position: relative;
            left: 185px;
        }

        #passw {
            position: relative;
            left: 215px;
        }

        #passw2 {
            position: relative;
            left: 183px;
        }

        #vcode {
            width: 150px;
        }

        #bdiv {
            position: relative;
            left: 187px;
            top: -37px;
        }

        #but2 {
            width: 150px;
            height: 36px;
        }

        #ph {
            position: relative;
            left: 183px;
        }
    </style>
</head>

<body>
<#--<#import "/spring.ftl" as spring/>-->
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="card cd">
                <div class="card-header">
                    易淘注册
                </div>
                <div class="card-body">
                    <form action="/addUser.do" class="layui-form" id="user_add_form" method="post">

                        <div id="udiv"></div>
                        <div class="form-group">
                            <label for="email">用户名:</label>
                            <span id="name">
                            <#--<#if user??>
                                    <@spring.bind "user.userName" />
                                    <@spring.showErrors />
                                </#if>-->
                            </span>
                            <input class="form-control placeholder-no-fix" autocomplete="off" lay-verify="user_account" required lay-verify="required"
                                   type="text" placeholder="请输入用户名(6-10位)" class="form-control" id="userName"
                                   name="account"
                                   value="<#if userError??><#if userError.account??>${userError.account}</#if></#if>"
                                   onblur="username_format()"/>
                        </div>

                        <div class="form-group">
                            <label for="pwd">密码:</label>
                            <span id="passw"></span>
                            <input class="form-control placeholder-no-fix" autocomplete="off" lay-verify="user_pwd" required lay-verify="required"
                                   type="password" placeholder="请输入密码(6-10位)" class="form-control" id="pass"
                                   name="password"
                                   value="<#if userError??><#if userError.password??>${userError.password}</#if></#if>"
                                   onblur="passformat()">
                        </div>
                        <div class="form-group">
                            <label for="repwd">二次密码:</label>
                            <span id="passw2"></span>
                            <input class="form-control placeholder-no-fix" autocomplete="off" lay-verify="user_pwd" required lay-verify="required"
                                   type="password" placeholder="请在次确认密码" class="form-control" id="pass2"
                                   name="repassword" onkeyup="validate()"/>
                        </div>
                        <div class="form-group">
                            <label for="phone">手机号:</label>
                            <span id="ph"></span>
                            <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
                                   placeholder="请输入手机号" name="telephone" id="phone" onkeyup="upperCase()"
                                   <#--onkeyup="value=value.replace(/[^0-9]/g,'')"-->
                                   onpaste="value=value.replace(/[^0-9]/g,'')"
                                   value="<#if userError??><#if userError.telephone??>${userError.telephone}</#if></#if>"
                                  <#-- oncontextmenu="value=value.replace(/[^0-9]/g,'')"-->>
                        </div>
                        <div class="form-group">
                            <label for="code">验证码:</label>
                            <input class="form-control placeholder-no-fix" autocomplete="off" required lay-verify="required"
                                   type="text" placeholder="请输入验证码" class="form-control col-3" id="vcode" name="vcode"
                                   onblur="upvcode()"/>

                            <div id="bdiv">

                                <button type="button" class="btn btn-outline-primary" disabled="true" id="but2">获取验证码</button>
                            </div>
                            <p id="p" style="margin-top: -20px;">
                                <#if yiTaoResult??>
                                    <#if yiTaoResult.status==404>
                                        <font color="red">验证码已过期</font>
                                    <#elseif yiTaoResult.status==400>
                                        <font color="red">未发验证码</font>
                                    <#elseif yiTaoResult.status==406>
                                        <font color="red">验证码不正确</font>
                                    </#if>
                                </#if>
                            </p>
                            <button class="btn btn-outline-primary" lay-submit lay-filter="demouseradd" id="but1">注册</button>
                            <a style="position: relative;top: 80px;" class="font-weight-bold" href="${rc.contextPath}/index.do">返回首页</a>

                            <a class="font-weight-bold" href="${rc.contextPath}/login.do"  id="a1">已有账号?去登陆</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/javascript">
    // 发送ajax的 封装代码
    function sendAjax(url, methodType, params, reFun) { // url是请求的地址,methodType是请求的类型,params是请求的参数(须是键值对的形式),reFun是一个函数
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

    function username_format() {
        var username = document.getElementById('userName').value;
        if (username === "") {
            $("#name").html("<font color='red' class='glyphicon glyphicon-remove'>用户名不能为空</font>");
            return false;
        }
        // 发送ajax请求
        $.post("${rc.contextPath}/validateAccount.do",{"account":username},function (data) {
            if (data.status==200) {
                $("#name").empty();
            }
            if (data.status==300) {
                $("#name").html("<font color='red' class='glyphicon glyphicon-remove'>用户名已被注册</font>");
            }
            if (data.status==400) {
                $("#name").html("<font color='red' class='glyphicon glyphicon-remove'>用户名不能为空</font>");
            }
        });

    }
</script>
<script type="text/javascript">
    function passformat() {
        var passwords = document.getElementById('pass').value;

        var pa = /^[a-zA-Z0-9]{6,10}/;

        if (pa.test(passwords)) {
            $("#passw").html("<font class='glyphicon glyphicon-ok' color='green'></font>")
        } else {
            $("#passw").html("<font color='red' class='glyphicon glyphicon-remove'>密码格式错误</font>")
        }

        if (passwords == "") {
            $("#passw").html("<font color='red' class='glyphicon glyphicon-remove'>密码不能为空</font>");
        }

    }

    function validate() {
        var pwd1 = document.getElementById("pass").value;
        var pwd2 = document.getElementById("pass2").value;
        <!-- 对比两次输入的密码 -->
        if (pwd1 == pwd2) {
            document.getElementById("passw2").innerHTML = "<font color='green'>两次密码相同</font>";
        } else {
            document.getElementById("passw2").innerHTML = "<font color='red'>两次密码不相同</font>";
        }
    }
    //Demo
    layui.use('form', function() {
        var form = layui.form;

        //自定义验证规则
        form.verify({
            user_account: function(value) {
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '账号不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '账号首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)) {
                    return '账号不能全为数字';
                }
            },
            user_pwd: function(value) {
                if(/^\d+\d+\d$/.test(value)) {
                    return '密码不能全为数字';
                }
                if(value.length <=6) {
                    return '密码长度必须大于6';
                }
                var pwd1 = document.getElementById("pass").value;
                var pwd2 = document.getElementById("pass2").value;
                <!-- 对比两次输入的密码 -->
                if (pwd1 == pwd2) {
                    document.getElementById("passw2").innerHTML = "<font color='green'>两次密码相同</font>";
                } else {
                    return "两次输入密码不一致";
                }
            }
        });
        //监听提交
        form.on('submit(demouseradd)', function(data) {
           /* layer.msg(JSON.stringify(data.field));*/
            var username = document.getElementById('userName').value;
            var bool = false;
            // 发送ajax请求
            $.post("${rc.contextPath}/validateAccount.do",{"account":username},function (data) {
                if (data.status==200) {
                    $('#user_add_form').submit();
                    $("#name").empty();
                }
                if (data.status==300) {
                    bool= false;
                    $("#name").html("<font color='red' class='glyphicon glyphicon-remove'>用户名已被注册</font>");
                }
                if (data.status==400) {
                    bool= false;
                    $("#name").html("<font color='red' class='glyphicon glyphicon-remove'>用户名不能为空</font>");
                }
            });
            return bool;
        });
    });

</script>

<script type="text/javascript">
    var btn = document.getElementById("but2");
    btn.onclick = function () {
        test.init(btn);
        var phone = document.getElementById("phone").value;
        $.post("${rc.contextPath}/validation.do", {"phone": phone}, function (data) {
            if(data.status===200) {
                layer.msg(data.msg,{icon: 1});
            }
            if (data.status===404) {
                layer.msg(data.msg,{icon: 5});
            }
            if (data.status===400) {
                layer.msg(data.msg,{icon: 5});
            }
            if (data.status===500) {
                layer.msg(data.msg,{icon: 5});
            }
        })
    };
    var test = {
        node: null,
        count: 60,
        start: function () {
            if (this.count > 0) {
                this.node.innerHTML = this.count-- + "秒后获取";
                var _this = this;
                setTimeout(function () {
                    _this.start();
                }, 1000);
            } else {
                this.node.removeAttribute("disabled");
                this.node.innerHTML = "重新获取";
                //60秒读完，变回开始背景颜色，在这里添加。

                this.count = 60;
            }
        },
        //初始化
        init: function (node) {
            this.node = node;
            this.node.setAttribute("disabled", true);
            this.start();
        }
    };

    function upperCase() {
        var phone = document.getElementById('phone').value;

        // 发送ajax请求 验证手机号是否重复
        if (!(/^1[34578]\d{9}$/.test(phone))) {
            $("#but2").attr("disabled","true");
            $("#ph").html("<font style='color: red' class='glyphicon glyphicon-remove'>手机格式不正确</font>")
            return false;
        } else {
            $("#but2").removeAttr("disabled");
            $("#ph").empty();
        }
        sendAjax("/validateTelephone.do", "post", "telephone=" + phone, function (responseText) {
            var result = responseText;
            if (result ==0) {
                $("#but2").removeAttr("disabled");
            } else if(result==1) {
                $("#but2").attr("disabled","true");
                $("#ph").html("<font color='red' class='glyphicon glyphicon-remove'>手机已被注册</font>");
            }else {
                $("#but2").attr("disabled","true");
            }
        })
    }

    function upvcode() {
        var code = document.getElementById('vcode').value;
        var phone = document.getElementById('phone').value;
        if (code == "") {
            $("#p").html("<font color='red' class='glyphicon glyphicon-remove'>验证码不能为空</font>");
        } else {
            $("#p").empty();
        }
    }
</script>


</html>