<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../assets/css/layui.css">
    <link rel="stylesheet" href="../assets/css/view.css"/>
    <link rel="stylesheet" href="../js/select2/css/select2.min.css">
    <title></title>

    <script src="../js/jquery-3.3.1.js"></script>

    <script src="../js/select2/js/select2.min.js"></script>
    <script src="../js/select2/js/i18n/*.js"></script>
</head>

<body class="layui-view-body">
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">添加角色</div>
                <div class="layui-card-body">
                    <div class="layui-form-item">
                        <label class="layui-form-label">角色名:</label>
                        <div class="layui-input-block">
                            <input id="roleName" type="text" name="name" lay-verify="title" autocomplete="off"
                                   placeholder="请输入角色名" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">选择权限:</label>
                        <div class="layui-input-block">
                            <select id="perm" class="multiSelect" multiple="multiple" style="width: 280px;border-color: gainsboro;">
                                <#if permList??>
                                    <#list permList as perm>
                                        <option value="${perm.id}">${perm.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <input id="roleBun" type="hidden" name="bun" value="1">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">角色描述:</label>
                        <div class="layui-input-block">
                                <textarea id="roleDesc" placeholder="请输入描述内容" class="layui-textarea"
                                          name="desc"></textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" onclick="add()">立即提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../assets/layui.all.js"></script>
<script type="text/javascript">
    $('#perm').select2();
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
    function add() {
        var name = $("#roleName").val();
        var bun = $("#roleBun").val();
        var desc = $("#roleDesc").val();
        var perms = $("#perm").val();

        sendAjax("/addRole.do", "get", "name=" + name + "&bun=" + bun + "&desc=" + desc + "&perms=" + perms, function (responseText) {
            if (responseText == 1) {
                layer.alert('添加角色成功', {
                    icon: 1,
                    skin: 'layer-ext-moon'
                })
            } else {
                layer.alert('添加角色失败', {
                    icon: 2,
                    skin: 'layer-ext-moon'
                })
            }
        });
    }
</script>
</body>


</html>