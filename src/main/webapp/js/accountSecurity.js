$(document).ready(function() {
	var error = false;
	/*设置焦点事件blur，当检点离开时执行该方法*/
	$("#username").blur(function() {
		var username = $("#username").val();

		var re = /[1][3,4,5,7,8][0-9][0-9]{8}/; //正则表达式
		
		if(!(re.test(username))) {
			showError('username', '手机号格式不正确！');
			error = true;
			return;
		} else {
			$("#username").css({
				"border-color": "green"
			});
			$("#username").css({
				"display": "none"
			});
		}
		if(username == '') {
			showError('username', '手机号/邮箱不能为空');
			error = true;
			return;
		}

		$.post("modifyPassProcess.php", {
			flag: 1,
			username: username
		}, function(data) {
			if(data) {
				$("#username").css({
					"border-color": "green"
				});
				$("#usernameTip").css({
					"display": "none"
				});
			} else {
				showError('username', '手机号/邮箱不存在');
				error = true;
			}
		});
	});
	/*设置焦点事件blur，当焦点离开时执行该方法*/
	$("#oldpass").blur(function() {
		var username = $("#username").val();
		if(username == '') {
			showError('username', '手机号/邮箱不能为空');
			error = true;
			return;
		}

		var oldpass = $("#oldpass").val();
		if(oldpass == '') {
			showError('oldpass', '验证码不能为空');
			error = true;
			return;
		}

		$.post("modifyPassProcess.php", {
			flag: 2,
			username: username,
			oldpass: oldpass
		}, function(data) {
			if(data) {
				$("#oldpass").css({
					"border-color": "green"
				});
				$("#oldpassTip").css({
					"display": "none"
				});
			} else {
				showError('oldpass', '验证码错误');
				error = true;
			}
		});
	});

	$("#newpass").blur(function() {
		var newpass = $("#newpass").val();

		if(!(newpass.length >= 6 && newpass.length <= 12)) {
			showError('newpass', '密码格式不正确，请重新输入');
			error = true;
			return;
		} else {
			$("#newpass").css({
				"border-color": "green"
			});
			$("#newpass").css({
				"display": "none"
			});
		}

		if(newpass == '') {
			showError('newpass', '新密码不能为空');
			error = true;
		} else {
			$("#newpass").css({
				"border-color": "green"
			});
			$("#newpassTip").css({
				"display": "none"
			});
		}
	});

	$("#newpassAgain").blur(function() {
		var newpass = $("#newpass").val();
		if(newpass == '') {
			showError('newpass', '新密码不正确');
			error = true;
			return;
		}

		var newpassAgain = $("#newpassAgain").val();
		if(newpassAgain != newpass) {
			showError('newpassAgain', '与输入的新密码不一致,请重新输入');
			error = true;
		} else {
			$("#newpassAgain").css({
				"border-color": "green"
			});
			$("#newpassAgainTip").css({
				"display": "none"
			});
		}
	});

	$("#submit").click(function(event) {
		$("#username").blur();
		$("#oldpass").blur();
		$("#newpass").blur();
		$("#newpassAgain").blur();

		if(!error) {
			var username = $("#username").val();
			var newpass = $("#newpass").val();
			$.post('modifyPassProcess.php', {
				flag: 3,
				username: username,
				newpass: newpass
			}, function(data) {
				$("#modifySuccess").css({
					'display': 'inline'
				});
			});
		}

		event.preventDefault();
		return false;
	});
});

function showError(formSpan, errorText) {
	$("#" + formSpan).css({
		"border-color": "red"
	});
	$("#" + formSpan + "Tip").empty();
	$("#" + formSpan + "Tip").append(errorText);;
	$("#" + formSpan + "Tip").css({
		"display": "inline"
	});
}