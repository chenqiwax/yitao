
function nickname_format() {
	var nickname = $("#nickname").val();
	var nick =/[\u4e00-\u9fa5_a-zA-Z0-9_]{4,10}/;
	if(nick.test(nickname)) {
		$(".nicks").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")
	} else {
		$(".nicks").html("<font color='red' class='glyphicon glyphicon-remove'>昵称格式错误</font>")
	}

}

function date_format() {
	var dates = $("#date").val();
	var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	if(reg.test(dates)) {
		$(".date").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")
	} else {
		$(".date").html("<font color='red' class='glyphicon glyphicon-remove'>日期格式错误</font>")
	}

}

function email_format() {
	var emails = $("#email").val();
	var mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(mail.test(emails)) {
		$(".email").html("<font class='glyphicon glyphicon-ok' color='green' style='margin-left: 17px;'></font>")
	} else {
		$(".email").html("<font color='red' class='glyphicon glyphicon-remove'>邮箱格式错误</font>")
	}


}


