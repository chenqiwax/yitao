<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico"/>
    <title>用户密码修改</title>
    <link rel="stylesheet" type="text/css" href="../css/link.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/css/layui.css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
    <style>
        .rebinding-box .twobox-form .twoform-input2 input {
            width: 100%;
        }

        .button-submit {
            width: 30%;
            color: #f2eeee;
            background: #1E9FFF;
            margin-left: 40%;
            margin-top: 8%;
        }

        .a-forget {
            color: #1E9FFF;
            position: absolute;
            margin-left: 45%;
            margin-top: -8%;
        }

        .newpassword {
            margin-top: 5%;
        }

        .password {
            margin-top: 5%;
        }
    </style>
</head>

<body>
<#include "header.ftl">

<div class="rebinding-box">
    <div class="box-title">
        <h2 class="mtb5">修改用户密码</h2>
        <i>为了保障您的帐户安全，请先进行安全验证</i>
    </div>
    <div class="box-timeline">
        <ul class="text-center" style="width: 800px;margin-left: 120px;">
            <li class="ml45">
                修改新密码
                <div class="box-outside1" id="outside1abs">
                    <div class="box-num1 num1ab">
                        1
                    </div>
                </div>
            </li>
            <li class="ml45">
                完成
                <div class="box-outside2 outside2a" id="outside2as">
                    <div class="box-num3 num3a">
                        2
                    </div>
                </div>
            </li>
            <div class="clear">

            </div>
        </ul>

    </div>

    <!--第二步-->
    <div class="twobox-form" id="twoform" style="display: block;">
        <div class="twoform-box">
            <div class="newtel">
                <label class="twoform-label">手机号</label>
                <div class="twoform-input">
                    <input type="text" id="ntel" name="name" autocomplete="off" placeholder="请输入新手机号"
                           onkeyup="newphone()"/>
                    <span id="newerro" style="color: red;"></span>
                </div>
            </div>
            <div class="validatecode" style="margin-top: 40px">
                <label class="twoform-label2">验证码</label>
                <div class="twoform-input2">
                    <input type="text" autocomplete="off" id="code" name="code" placeholder="请输入验证码"
                           style="width: 120px"
                           onkeyup="verification()"/>
                    <span id="codeerro" style="color: red;"></span>
                    <span id="vicode" style="display: none;width: 250px;"><button
                            class="sendcode" id="codebut">发送验证码</button></span>
                </div>

            </div>
            <div class="newpassword" style="margin-top: 40px">
                <label class="twoform-label">新密码</label>
                <div class="twoform-input">
                    <input type="password" id="newpassword" autocomplete="off" placeholder="请输入新的密码" value=""
                           onkeyup="newPassword()"/>
                    <span id="erro" style="color: red;"></span>
                </div>
            </div>
            <div class="password" style="margin-top: 50px">
                <label class="twoform-label2">确认密码</label>
                <div class="twoform-input2">
                    <input type="password" id="ntels" autocomplete="off" name="password" placeholder="请再次输入密码"
                           value=""
                           onkeyup="validate()"/>
                    <span id="erro1"></span>

                </div>
            </div>
        </div>
        <div class="twobtn-box">

            <input type="submit" class="twobtn" value="下一步" id="twobtn" onclick="fun1()"/>
        </div>

    </div>
    <!--第三步-->
    <div class="threebox-form" id="threeform">
        <div class="successr">
            <div class="symbol">
            </div>
            <p>恭喜您，修改密码成功！</p>
            <button class="confirm"><a href="/login.do">确认</a></button>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    var onebtns = document.getElementById("onebtn");
    var twobtns = document.getElementById("twobtn");
    var soutside1ab = document.getElementById("outside1abs");
    var soutside2as = document.getElementById("outside2as");
    var oneforms = document.getElementById("oneform");
    var twoforms = document.getElementById("twoform");
    var threeforms = document.getElementById("threeform");

    function onblue_fun() {
        var pasvals = document.getElementById("pasval").value;
        isPasswd(pasvals);

        function isPasswd(s) {
            var patrn = /^[a-zA-Z0-9_-]{4,12}$/;
            if (!patrn.exec(s)) {
                $('#olderro').html("请核对当前密码!");
                return false
            } else {
                $('#olderro').html("");
            }
        }
    }

    /*输入的密码与数据库中的对比*/
    function fun() {
        var pasvals = document.getElementById("pasval").value;
        isPasswd(pasvals);

        function isPasswd(s) {
            var patrn = /^[a-zA-Z0-9_-]{4,12}$/;
            if (!patrn.exec(s)) {
                $('#olderro').html("请核对当前密码!");
                return false
            } else {
                soutside1ab.classList.remove("outside1ab");
                oneforms.style.display = "none";
                twoforms.style.display = "block";
            }
        }
    }

    /*/验证手机号是否可用*/
    function newphone() {
        var pasvals = $('#ntel').val();
        var patrn = /0?(13|14|15|18|17)[0-9]{9}/;
        if (!patrn.test(pasvals)) {
            $('#newerro').html("请输入正确的手机号!");
            return false
        } else {
            $('#newerro').html("");
        }

        $.post("/Phonevalidation.do", {phone: pasvals}, function (data) {
            if (data == "") {
                $('#newerro').html("请输入已注册的手机号!");
                $('#vicode').css("display", "none");
            } else {
                $('#vicode').css("display", "inline-block");
            }
        })

    }

    /*验证验证码 是否正确*/
    function verification() {
        var newpassword = $('#newpassword').val();
        var pwd2 = $('#ntels').val();
        var newphone = $('#ntel').val();
        var code = $('#code').val();

        if (code != '') {
            if (newpassword != '' && pwd2 != '') {
                document.getElementById("twobtn").disabled = false;
                $('#twobtn').css('background-color', 'red');
            } else {
                document.getElementById("twobtn").disabled = true;
            }
        } else {
            document.getElementById("twobtn").disabled = true;
        }
    }

    /*验证新密码格式*/

    function newPassword() {
        var names = $('#newpassword').val();
        var pattern = /^[a-zA-Z0-9_-]{4,12}$/;
        if (pattern.test(names)) {
            $('#erro').html("");
        } else {
            $('#erro').html("x 密码格式有误");
        }
    }

    /*验证两次输入的密码是否一致*/
    function validate() {
        var newpassword = $('#newpassword').val();
        var pwd2 = $('#ntels').val();
        var newphone = $('#ntel').val();
        var code = $('#code').val();

        if (newpassword == pwd2) {
            if (newphone == '' && code == '') {
                document.getElementById("erro1").innerHTML = "";
                document.getElementById("twobtn").disabled = true;
            } else {
                document.getElementById("erro1").innerHTML = "";
                document.getElementById("twobtn").disabled = false;
                $('#twobtn').css('background-color', '#1E9FFF');
            }
        } else {
            document.getElementById("twobtn").disabled = true;
            document.getElementById("erro1").innerHTML = "<font color='red'>两次密码不相同</font>";

        }
    }

    /*提交*/
    function fun1() {
        var newphone = $('#ntel').val();
        var code = $('#code').val();
        var newpassword = $('#newpassword').val();
        var verifyntels = $('#ntels').val();

        if (newphone == '' || code == '' || newpassword == '' || verifyntels == '') {
            return false;
        } else {
            //发送异步请求
            $.post("/Forgotpassword.do", {name: newphone, password: verifyntels, code: code}, function (data) {
                if (data.status == 200) {
                    twoform.style.display = "none";
                    threeforms.style.display = "block";
                    soutside2as.classList.remove("outside2a");
                }
            });


        }
    }

</script>


<script type="text/javascript">
    var btn = document.getElementById("codebut");

    btn.onclick = function () {
        test.init(btn);
        var phone = $('#ntel').val();
        $.post("/validation.do", {phone: phone}, function () {

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
</script>
</html>