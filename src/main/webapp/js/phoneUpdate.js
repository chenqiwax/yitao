function phone_format() {
    var phones = $("#phone").val();

    var phone = /[1][3,4,5,7,8][0-9][0-9]{8}/; //正则表达式


    if (phone.test(phones)) {

        $(".span1").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")

        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/verify.do", true);

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        xhr.send("name=" + phones);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resp = xhr.responseText;
                if (resp == "") {
                    $("#p6").css("display", "inline-block")
                } else {
                    $("#p6").css("display", "none");
                }
            }
        }
    } else {
        $(".span1").html("<font color='red' class='glyphicon glyphicon-remove'>手机号格式错误</font>")
    }

    if (phones == "") {
        $(".span1").html("<font color='red' class='glyphicon glyphicon-remove'>手机号不能为空</font>");
    }

}

function newphone_format() {
    var phones = $("#newphone").val();

    var phone = /[1][3,4,5,7,8][0-9][0-9]{8}/; //正则表达式

    if (phone.test(phones)) {

        $(".span2").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")

        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/verify.do", true);

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        xhr.send("name=" + phones);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resp = xhr.responseText;
                if (resp == "") {
                    $("#p5").css("display", "none");
                } else {
                    $("#p5").css("display", "inline-block")
                    $(".span2").html("<font color='red' class='glyphicon glyphicon-remove'></font>")
                }
            }
        }
    } else {
        $(".span2").html("<font color='red' class='glyphicon glyphicon-remove'>手机号格式错误</font>")
    }

    if (phones == "") {
        $(".span2").html("<font color='red' class='glyphicon glyphicon-remove'>手机号不能为空</font>");
    }
}


function message_format() {
    var pass = $("#pass").val();

    var mesage = /[0-9]{6}/;


    if (mesage.test(pass)) {
        $(".spans").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")
    } else {
        $(".spans").html("<font color='red' class='glyphicon glyphicon-remove'>验证码格式错误</font>")
    }

    if (pass == "") {
        $(".spans").html("<font color='red' class='glyphicon glyphicon-remove'>验证码不能为空</font>")
    }
}
