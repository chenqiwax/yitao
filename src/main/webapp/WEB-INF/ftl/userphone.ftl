<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <link rel="stylesheet" type="text/css" href="../bookcss/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/accountSecurity.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="../js/phoneUpdate.js"></script>
</head>
<body style="background: url(../static/img/bg-3.png) fixed;">
<div>
<#include  "personal.ftl">
</div>
<div style="width: 500px;height: 500px;margin-left: 910px;margin-top: 245px;">
    <h1 style="position: relative;left: 70px;bottom: 20px;">修改手机号</h1>
    <form class="form-horizontal" role="form" action="/uptel.do" method="post">
        <div class="row">
            <div class="form-group col-sm-12" style="margin-bottom: -60px;">
                <div class="col-sm-3">
                    <label class="control-label">手机号</label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="phone" name="phone" autocomplete="off"
                           required="required" placeholder="请输入手机号"
                           onblur="phone_format()">
                    <span class="span1" style="position: relative;left: 240px;top: -61px;"></span>
                    <span id="p6"
                          style="display: none;position: relative;top: -28px;left: 120px;color: red;">此手机号不存在</span>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <div class="form-group col-sm-12" style="margin-bottom: -60px;">
                <div class="col-sm-3">
                    <label class="control-label">新的手机号</label>
                </div>

                <div class="col-sm-7">
                    <input type="text" class="form-control" id="newphone" name="name" autocomplete="off"
                           required="required" placeholder="请输入修改后的手机号"
                           onblur="newphone_format()">
                    <span class="span2" style="position: relative;left: 240px;top: -61px;"></span>
                    <span id="p5"
                          style="display: none;position: relative;top: -28px;left: 120px;color: red;">此手机号已存在</span>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <div class="form-group col-sm-12">
                <label class="col-sm-3 control-label">验证码</label>
                <div align="center" class="col-sm-3">
                    <input type="password" class="form-control" style="width: 120px;" id="pass" name="code"
                           autocomplete="off" required="required"
                           placeholder="请输入验证码"
                           onblur="message_format()">
                    <span class="spans" style="position: relative;left: 337px;top: -65px;"></span>
                </div>
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-radius" id="button" onclick=""
                            style="width:94px;position: relative;right: 196px;">
                        获取验证码
                    </button>
                </div>
            </div>
            <div class="form-group col-sm-12">
                <div class="col-sm-2">
                    <label class="control-label"> </label>
                </div>

                <div class="col-sm-4">
                    <button type="submit" class="layui-btn layui-btn-normal layui-btn-radius" id="sub"
                            style="position: relative;right: 80px;margin-top: -84px;">
                        确认修改
                    </button>
                </div>

                <div class="col-sm-4">
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"
                            style="position: relative;right: 18px;width: 92px;;
    margin-top: -84px;">
                        重置
                    </button>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
    </form>
</div>

</body>

<script>

    var btn = document.getElementById("button");

    btn.onclick = function () {
        test.init(btn);
        var phone = document.getElementById("phone").value;
        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/validation.do", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        xhr.send("phone=" + phone);
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
</script>
</html>
