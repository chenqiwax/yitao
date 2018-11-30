<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="shortcut icon" href="img/logos.ico"/>
    <link rel="stylesheet" type="text/css" href="../bookcss/bootstrap.min.css"/>
    <link rel="stylesheet" href="../bookcss/body.css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
</head>
<body style="background: url(../static/img/bg-2.png) fixed;">
<div>
<#include  "personal.ftl">
</div>
<div>
    <div style="width: 600px;height: 450px;margin-top: 110px;margin-left:780px;text-align: center;">
        <h1 style="position: relative;right: 50px;top: 75px;">修改密码</h1>
        <form action="/Modify.do" method="post" class="form-horizontal" role="form"
              style="position: relative;top: 115px;">
            <div class="form-group">
                <label for="username" class="col-sm-3 control-label">手机号/邮箱</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="username" name="name"
                           onblur="upper()" autocomplete="off" required="required"
                           placeholder="请输入手机号/邮箱">
                    <span id="usernameTip"></span>
                </div>
                <span id="p6" style="display: none;position: relative;top: 4px;color: red;">此手机号/邮箱不存在</span>
                <span id="p5" style="display: none;position: relative;top: 4px;color: red;">输入格式有误!</span>
            </div>
            <div class="form-group">
                <label for="newpass" class="col-sm-3 control-label">新密码</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="newpass" name="password" autocomplete="off"
                           required="required" placeholder="输入你修改的密码">
                    <span id="newpassTip"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="newpassAgain" class="col-sm-3 control-label">再次确认新密码</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="newpassAgain" name="topassword" autocomplete="off"
                           required="required" placeholder="请再次输入密码" onkeyup="validate()">
                </div>
                <span id="newpassAgainTip"></span>
            </div>
            <div class="form-group">
                <label for="oldpass" class="col-sm-3 control-label">验证码</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" style="width: 115px;" id="oldpass" name="code"
                           required="required" autocomplete="off"
                           placeholder="请输入验证码">
                    <span id="oldpassTip"></span>
                </div>
                <div>
                    <button class="layui-btn layui-btn-normal layui-btn-radius" id="but" style="width: 94px;position: relative;right: 180px;">获取验证码</button>
                    <#--<button type="button" id="but" class="btn btn-primary"
                            style="">获取短信验证码
                    </button>-->
                </div>
                <span id="phonepass" style="display: none;position:relative;;top: -23px;">手机号验证码已发送</span>
                <span id="emilpass" style="display: none;position:relative;;top: -23px;">邮箱验证码已发送</span>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"> </label>

                <button type="submit" class="layui-btn layui-btn-normal layui-btn-radius" id="submit"
                        style="position: relative;right: 190px;top: 30px;">确认修改
                </button>

                <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"
                        style="position: relative;right: 64px;width: 92px;top: 30px;">重置
                </button>
            </div>
        </form>
    </div>
</div>

</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/javascript">
    var btn = document.getElementById("but");
    btn.onclick = function () {
        test.init(btn);

        var name = document.getElementById("username").value;
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if ((/^1[34578]\d{9}$/.test(name))) {
            //这里手机发送验证码
            var xhr = new XMLHttpRequest();

            xhr.open("POST", "/validation.do", true);

            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            xhr.send("phone=" + name);

            $("#phonepass").css("display", "inline-block");

        } else if (myreg.test(name)) {
            //这里邮箱发送验证码
            var xhr = new XMLHttpRequest();

            xhr.open("POST", "/emilcode.do", true);

            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            xhr.send("emil=" + name);

            $("#emilpass").css("display", "inline-block");
        }
        else {
            //这里格式报错
            $("#p5").css("display", "inline-block");
        }

    }
    ;
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
</script>
<script type="text/javascript">

    function validate() {
        var pwd1 = document.getElementById("newpass").value;
        var pwd2 = document.getElementById("newpassAgain").value;
        <!-- 对比两次输入的密码 -->
        if (pwd1 == pwd2) {
            document.getElementById("newpassAgainTip").innerHTML = "<font color='green'>两次密码相同</font>";
        } else {
            document.getElementById("newpassAgainTip").innerHTML = "<font color='red'>两次密码不相同</font>";
        }
    }
</script>

<script type="text/javascript">

    function upper() {

        /* 判断输入的手机号或者邮箱是否存在*/
        var name = document.getElementById('username').value;

        if (name == null) {

        }

        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/verify.do", true);

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        xhr.send("name=" + name);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resp = xhr.responseText;
                if (resp == "") {
                    $("#p6").css("display", "inline-block");
                } else {
                    $("#p6").css("display", "none");
                }
            }
        }
    }
</script>
</html>
