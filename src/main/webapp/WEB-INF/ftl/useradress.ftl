<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的地址</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <script src="../layui/layui.all.js"></script>
    <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
    <style>

        .outer {
            width: 900px;
            height: 941px;
            margin: 20px auto;
            border: 0px solid gray;
        }

        select {
            padding: 5px 0;
            margin-left: 15px;
            width: 80px;
            font-family: "微软雅黑";
            border-radius: 5px;
            color: gray;
        }

        .outer {
            width: 900px;
            height: 750px;
            margin-left: 560px;
        }

        .span {
            font-family: "微软雅黑";
            font-size: 30px;
            color: red;
        }

        .outer .span {
            color: white;
            font-family: "微软雅黑";
            font-size: 25px;
            margin: auto;
        }

        .outer .query {
            width: 900px;
            height: 35px;
            background-color: lightskyblue;
        }

        .default {
            width: 60px;
            height: 20px;
            background-color: #1E9FFF;
            color: white;
            border: none;
            font-size: 10px;
            border-radius: 5px;
        }

        .outer .table {
            width: 900px;
            margin: auto;
            margin-top: 5px;
            border: 1px solid #ddd;
        }

        .addr {
            color: white;
            font-family: '微软雅黑';
            font-size: 25px;
            height: 35px;
            width: 150px;
            margin-left: 512px;
            text-decoration: none;
        }
    </style>
</head>
<body style="background: url(../static/img/bg-1.png) fixed;">
<div>
<#include  "personal.ftl">
</div>

<div class="outer">
    <!--地址查看-->
    <div style="margin-top: 200px;">
        <div class="query">
            <span class="span">已保存的地址</span>
        </div>
        <table class="table">
            <tr class="tr">
                <td>
                    收货人
                </td>
                <td>
                    联系方式
                </td>
                <td>
                    地区
                </td>
                <td>
                    详细地址
                </td>
                <td>
                    是否为默认地址
                </td>
                <td>
                    操作
                </td>
            </tr>
            <tbody id="tbody">

            </tbody>
        </table>
    </div>

</div>

</div>

</body>
<script>
    $(document).ready(function () {
        $.post("/getUserAddressByUid.do", function (data) {
            //将json字符串转换为JSON对象
            var jsonObject = JSON.parse(data);
            console.log(jsonObject);
            $("#tbody").empty();
            for (var i in jsonObject) {
                var tr = $("<tr>");
                var td1 = $("<td>").html(jsonObject[i].name);
                var td2 = $("<td>").html(jsonObject[i].telephone);
                var td3 = $("<td>").html(jsonObject[i].province + jsonObject[i].city + jsonObject[i].district);
                var td4 = $("<td>").html(jsonObject[i].address);
                var td5 = $("<td>");
                var a = jsonObject[i].isdefault;
                if (a == 1) {
                    td5.html("<p style='font-size: 15px;'>默认地址</p>");
                } else {
                    var button = $("<button type='button' class='default' style='border-radius: 20px;height: 30px'>设为默认</button>")
                    button.click(function (id) {
                        return function () {
                            $.post("/updateAddress.do", {"id": id}, function (data, status) {
                                var fruit = parseInt(data);
                                if (fruit > 0) {
                                    layer.msg("设置默认地址成功",{icon:1});
                                    window.location.href = "/useradress.do";
                                    /*layer.alert("设置默认地址成功！", function () {
                                        /!*window.parent.location.reload();//刷新父页面*!/
                                        window.location.reload();
                                        parent.layer.close(index);//关闭弹出层
                                    });*/
                                } else {
                                    layer.msg("设置失败");
                                }
                            });
                        }
                    }(jsonObject[i].id));
                    td5.append(button);
                }
                var a1 = $("<button style='background-color: #1E9FFF;border: none;color: white;border-radius: 20px;width: 60px;height: 30px'>删除</button>");
                a1.click(function (id) {
                    return function () {
                        layer.confirm('您确认要删除吗？', {
                            btn: ['确认', '取消'] //按钮
                        }, function () {
                            $.post("/deleteUserAddress.do", {"id": id}, function (data, status) {
                                var fruit = parseInt(data);
                                if (fruit > 0) {
                                    layer.msg("删除成功！");
                                    layer.alert("删除成功！", function () {
                                        window.parent.location.reload();//刷新父页面
                                        parent.layer.close(index);//关闭弹出层
                                    });
                                } else {
                                    layer.msg("删除失败");
                                }
                            });
                        }, function () {
                            layer.close;
                        });
                    }
                }(jsonObject[i].id));
                var a2 = $("<button style='background-color: #1E9FFF;border: none;border-radius: 20px;width: 60px;height: 30px'>" +
                        "<a href='selectUserAddress.do?id=" + jsonObject[i].id + "' style='color: white;text-decoration: none'>修改</a></button>");
                var td6 = $("<td>");
                td6.append(a2);
                td6.append(a1);
                tr.append(td1);
                tr.append(td2);
                tr.append(td3);
                tr.append(td4);
                tr.append(td5);
                tr.append(td6);
                $("#tbody").append(tr);
            }
        });
    });
</script>
</html>
