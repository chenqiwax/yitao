<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="stylesheet" href="../assets/css/layui.css">
		<link rel="stylesheet" href="../assets/css/view.css" />
        <link rel="stylesheet" href="../js/select2/css/select2.min.css">
        <script src="../js/jquery-3.3.1.js"></script>
		<title></title>
        <script src="../js/select2/js/select2.min.js"></script>
        <script src="../js/select2/js/i18n/*.js"></script>
	</head>

	<body class="layui-view-body">
		<div style="padding: 20px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md8">
					<div class="layui-card">
						<div class="layui-card-header">查看角色</div>
						<div class="layui-card-body">
							<table class="layui-table">
								<thead>
									<tr>
										<th>角色id</th>
										<th>角色名</th>
										<th>是否禁用</th>
										<th>角色描述</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<#if roleList??>
									    <#list roleList as role>
									        <tr>
                                                <td>${role.id}</td>
                                                <td>${role.name}</td>
												<#if role.bun == 1>
												    <td>否</td>
													<#else >
														<td>是</td>
												</#if>
                                                <td>${role.desc}</td>
                                                <td>
                                                    <div>
                                                        <button class="layui-btn layui-btn-normal layui-btn-sm" onclick="load('${role.id}')"><i class="layui-icon"></i></button>
                                                        <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="del('${role.id}')"><i class="layui-icon"></i></button>
                                                        <button class="layui-btn layui-btn-warm layui-btn-sm" onclick="unUse('${role.id}')">禁用</button>
                                                        <button class="layui-btn layui-btn-sm" onclick="use('${role.id}')">启用</button>
                                                    </div>
                                                </td>
                                            </tr>
									    </#list>
									</#if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

        <div id="updRolePage" style="padding: 20px; background-color: #F2F2F2;width: 40%;display: none;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">修改角色:</div>
                        <div class="layui-card-body">
                            <div class="layui-form-item">
                                <label class="layui-form-label">角色名:</label>
                                <div class="layui-input-block">
                                    <input id="roleName" type="text" lay-verify="title" autocomplete="off"
                                            class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">选择权限:</label>
                                <div class="layui-input-block">
                                    <select id="perm" class="multiSelect" multiple="multiple" style="width: 350px;border-color: gainsboro;">
                                    </select>
                                </div>
                            </div>
                            <input id="roleId" type="hidden">
                            <input id="roleBun" type="hidden">
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">角色描述:</label>
                                <div class="layui-input-block">
                                <textarea id="roleDesc" class="layui-textarea"
                                          name="desc"></textarea>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" onclick="upd()">立即提交</button>
                                    <button class="layui-btn" onclick="document.getElementById('updRolePage').style.display='none'">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="../assets/layui.all.js" charset="utf-8"></script>
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
			function load(id) {
				sendAjax("/loadRole.do","get","id="+id,function (responseText) {
				    var strs = responseText.split('+');
                    var roleBean = JSON.parse(strs[0]);
                    var permBean = JSON.parse(strs[1]);


                    $("#roleName").attr("value",roleBean.name);
                    $("#roleBun").attr("value",roleBean.bun);
                    $("#roleId").attr("value",roleBean.id);
                    $("#roleDesc").text(roleBean.desc);

                    for (var a=0;a<permBean.length;a++){
                        var option = document.createElement("option");
                        option.value = permBean[a].id;
                        option.text = permBean[a].name;
                        $("#perm").append(option);
                    }
                    document.getElementById('updRolePage').style.display='block';
                    $("#perm").select2();
                });
            }
		</script>
        <script type="text/javascript">
            function del(id) {
                sendAjax("/delRole.do","get","id="+id,function (responseText) {
                    if (responseText == 1) {
                        layer.alert('删除角色成功', {
                            icon: 1,
                            skin: 'layer-ext-moon'
                        });
                        setTimeout(st, 2000);
                        /*location.reload();*/
                    } else {
                        layer.alert('删除角色失败', {
                            icon: 2,
                            skin: 'layer-ext-moon'
                        })
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function unUse(id) {
                sendAjax("/unUseRole.do","get","id="+id,function (responseText) {
                    if (responseText == 1) {
                        layer.alert('禁用角色成功', {
                            icon: 1,
                            skin: 'layer-ext-moon'
                        });
                        setTimeout(st, 2000);
                    /*    location.reload();*/
                    } else {
                        layer.alert('禁用角色失败', {
                            icon: 2,
                            skin: 'layer-ext-moon'
                        })
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function use(id) {
                sendAjax("/useRole.do","get","id="+id,function (responseText) {
                    if (responseText == 1) {
                        layer.alert('启用角色成功', {
                            icon: 1,
                            skin: 'layer-ext-moon'
                        });
                        setTimeout(st, 2000);
                       /* location.reload();*/
                    } else {
                        layer.alert('启用角色失败', {
                            icon: 2,
                            skin: 'layer-ext-moon'
                        })
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function upd() {
                var name = $("#roleName").val();
                var id = $("#roleId").val();
                var bun = $("#roleBun").val();
                var desc = $("#roleDesc").val();

                var perms = $("#perm").val();

                sendAjax("/updRole.do","get","id="+id+"&name="+name+"&bun="+bun+"&desc="+desc+"&perms="+perms,function (responseText) {
                    if (responseText == 1) {
                        layer.alert('修改角色成功', {
                            icon: 1,
                            skin: 'layer-ext-moon'
                        });
                        setTimeout(st, 2000);
                       /* location.reload();*/
                    } else {
                        layer.alert('修改角色失败', {
                            icon: 2,
                            skin: 'layer-ext-moon'
                        })
                    }
                });
            }
        </script>
        <script>
            function st(){
                window.location.reload();
            }
        </script>
	</body>

</html>