<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>易淘登录</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.min.css">
    <script src="${rc.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${rc.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function upperCase() {
            /* 判断输入的手机号是否格式一致  */
            var phone = document.getElementById('phone').value;

            var xhr = new XMLHttpRequest();

            xhr.open("POST", "/Phonevalidation.do", true);

            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            xhr.send("phone=" + phone);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var resp = xhr.responseText;
                    if (resp == "") {
                        $("#p6").css("display", "inline-block");
                        $("#p2").css("display", "none")
                        $("#p5").css("display", "none");
                        $("#p1").css("display", "none")
                    } else {
                        $("#p6").css("display", "none");
                        $("#p5").css("display", "inline-block");
                        $("#p2").css("display", "none")
                        $("#p1").css("display", "inline-block")
                    }
                }
            }

            if (!(/^1[34578]\d{9}$/.test(phone))) {
                $("#p2").css("display", "inline-block")
                $("#p1").css("display", "none")
                return false;
            } else {
                $("#p2").css("display", "none")
                $("#p1").css("display", "inline-block")
            }

        }
    </script>
    <script>
        function upperCode() {
            var code = document.getElementById('code').value;
            if (code != '') {
                $("#p3").css("display", "none")
                $("#p4").css("display", "inline-block")
                return false;
            } else {
                $("#p3").css("display", "inline-block")
                $("#p4").css("display", "none")
            }
        }
    </script>

    <style type="text/css">
        .card {
            width: 400px;
            height: 325px;
            position: relative;
            right: 17px;
            opacity: 0.5;
        }

        .nav {
            width: 200px;
        }

        .container-fluid {
            position: relative;
            top: 100px;
        }

        body {
            background-image: url(../img/bei.png);
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }

        div:hover {
            opacity: 1.0;
        }

        #phone {
            width: 230px;
            height: 25px;
        }

        #code {
            width: 125px;
            height: 25px;
        }

        .btn {
            width: 90px;
            height: 40px;
            position: relative;
            top: 25px;
            left: 110px;
        }

        .a1 {
            position: relative;
            top: 80px;
            left: 20px;
            font-size: 18px;
            color: #01AAED;
        }

        a {
            text-decoration: none;
        }

        .input1 {
            width: 50px;
            height: 25px;
        }

        #but1 {
            border-radius: 40%;
            color: #040505;
            background: #00CCFF;
        }

        #name {
            width: 240px;
            height: 30px;
        }

        #password {
            width: 240px;
            height: 30px;
        }

        #ucode {
            position: relative;
            top: 18px;
            width: 123px;
            height: 30px;
            font-size: 15px;
        }

        .tr1 {
            margin-top: 5px;
        }

        #but1 {
            border-radius: 20%;
            color: white;
            background: #1E9FFF;
            border: #1E9FFF;
        }

        #button1 {
            width: 93px;
            height: 35px;
            position: relative;
            top: 17px;
            left: 45px;
            background-color: #1E9FFF;
            border-radius: 10px;
            color: white;
            border: none;
        }

        #p2 {

            text-align: center;
            width: 93px;
            height: 35px;
            background-color: #1E9FFF;
            border-radius: 10px;
            position: relative;
            left: 200px;
            top: -18px;
        }

        #uid1 {
            width: 400px;
            height: 450px;
        }

        .a2 {
            position: relative;
            left: 105px;
            top: 60px;
            color: #2AABD2;
        }

        .a3 {
            position: relative;
            right: 223px;
            top: 59px;
            font-size: 17px;
            color: #2AABD2;
        }
    </style>
</head>

<body>
<div class="container-fluid col-sm-5 mr-5 mt-sm-2">
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#accountlogin">账户登录</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#phonelogin">手机登录</a>
        </li>
    </ul>

    <div class="tab-content">
        <div id="accountlogin" class="container tab-pane active"><br>
            <div class="card" id="uid1">
                <div class="card-header">易淘登录</div>
                <div class="card-body">
                    <form action="/userlogin.do" method="post">
                        <table style="border-collapse: separate;border-spacing: 0px 10px;">
                            <tr>
                                <td>
                                    <label>用户名:</label>
                                </td>
                                <td>
                                    <div class="input-icon">
                                        <input class="form-control placeholder-no-fix" type="text" required="required"
                                               autocomplete="off" placeholder="请输入用户名"
                                               name="account" id="name"/>
                                    </div>
                                </td>
                            </tr>
                            <tr class="tr1">
                                <td>
                                    <label>密码:</label>
                                </td>
                                <td>
                                    <div class="input-icon">
                                        <input class="form-control placeholder-no-fix" type="password"
                                               required="required" autocomplete="off" id="password" name="password"
                                               placeholder="请输入密码"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>验证码:</label>
                                </td>
                                <td>
                                    <div class="input-icon">
                                        <!-- 保存正确的验证码 -->
                                        <input type="hidden" id="trueCod" name="trueCode"/>
                                        <input class="form-control placeholder-no-fix" type="text" id="ucode"
                                               name="imgcode"
                                               required="required" autocomplete="off" placeholder="请输入验证码"/>
                                        <img id="vImg" src="/createImg.do" onclick="_change()"
                                             style="position: relative;left: 140px;top: -17px;width: 90px;height: 40px;"/>
                                    </div>
                                <#--<a href="#" style="font-size: 15px">看不清</a>-->
                                <#if error??>
                                     <span id="spans1"
                                           style="color: red;margin-left: 105px;px;font-size: 18px;">${error}</span>
                                </#if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="layui-btn layui-btn-normal layui-btn-radius" type="submit"
                                            id="button1">登录
                                    </button>
                                    <a href="/index.do">
                                        <button class="layui-btn layui-btn-normal layui-btn-radius"
                                                style="height:35px;color: white;border: none; background-color: #1E9FFF;border-radius: 10px;position: relative;left: 213px;bottom: 16px;width: 93px;"
                                                type="button">首页
                                        </button>
                                    </a>
                                </td>
                                <td>
                                    <a href="regist.do" class="a2">没有账号?立即注册</a>


                                    <a href="forgot.do" class="a3">忘记密码?</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div id="phonelogin" class="container tab-pane"><br>
            <div class="card">
                <div class="card-header">易淘登录</div>
                <div class="card-body">
                    <form action="/Phonelogin.do" method="post">
                        <table style="border-collapse: separate;border-spacing: 0px 10px;">
                            <tr>
                                <td>
                                    <label>手机号:&nbsp;&nbsp;</label>
                                </td>
                                <td>
                                    <div class="input-icon">
                                        <input class="form-control placeholder-no-fix" required="required" type="text"
                                               autocomplete="off" placeholder="请输入手机号" name="name" id="phone"
                                               onblur="upperCase()" onkeyup="value=value.replace(/[^0-9]/g,'')"
                                               onpaste="value=value.replace(/[^0-9]/g,'')"
                                               oncontextmenu="value=value.replace(/[^0-9]/g,'')">

                                    </div>
                                    <div style="position: absolute;left: 360px;top: 79px;">
                                        <span id="p2" style="display: none"><img src="../img/错误.png"/></span>
                                        <span id="p1" style="display: none"><img src="../img/对号.png"/></span>
                                    </div>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <label>验证码:&nbsp;&nbsp;</label>
                                </td>
                                <td>
                                    <div>
                                        <input class="form-control placeholder-no-fix" id="code" name="code"
                                               required="required" autocomplete="off"
                                               type="text" placeholder="请输入验证码" onblur="upperCode()"
                                               onkeyup="value=value.replace(/[^0-9]/g,'')"
                                               onpaste="value=value.replace(/[^0-9]/g,'')"
                                               oncontextmenu="value=value.replace(/[^0-9]/g,'')"/>

                                    </div>
                                    <div style="position: absolute;left: 247px;top: 122px;">
                                        <span id="p5" style="width: 100px; margin-right: 12px;display: none;"><button
                                                id="but1">获取验证码</button></span>
                                        <span id="p3" style="display: none"><img src="../img/错误.png"/></span>
                                        <span id="p4" style="display: none"><img src="../img/对号.png"/></span>
                                        <span id="p6"
                                              style="display: none;position: relative;top: -64px;right: 21px;color: red;">此手机号不存在</span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="layui-btn layui-btn-normal layui-btn-radius" type="submit"
                                            id="button1">登录
                                    </button>
                                </td>
                                <td>
                                    <a href="/index.do">
                                        <button class="layui-btn layui-btn-normal layui-btn-radius"
                                                style="height:35px;color: white;border: none; background-color: #1E9FFF;border-radius: 10px;position: relative;left: 80px;top: 17px;width: 93px;"
                                                type="button">首页
                                        </button>
                                    </a>
                                    <a class="a1" href="regist.do">没有账号?立即注册</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/javascript">
    function sendAjax(url, methodType, params, reFun) {
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
    var btn = document.getElementById("but1");

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
<script type="text/javascript">
    function _change() {
        $("#vImg").attr("src", "/createImg.do?a=" + new Date().getTime());
    }
</script>

</html>