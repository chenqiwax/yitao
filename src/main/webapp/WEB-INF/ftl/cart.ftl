<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>购物车</title>
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <link href="../css/gouwuche.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../js/jquery.1.4.2-min.js"></script>
    <script type="text/javascript" src="../js/gouwuche.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            // 全选
            $(".allselect").click(function () {
                $("#jz1").css("display", "none");
                $("#jz2").css("display", "block");
                if ($(this).attr("checked")) {
                    $(".gwc_tb2 input[name=newslist]").each(function () {
                        $(this).attr("checked", true);
                        // $(this).next().css({ "background-color": "#3366cc", "color": "#ffffff" });
                    });
                    $(".gwc_tb2 input[name=new]").each(function () {
                        $(this).attr("checked", true);
                    });
                    GetCount();
                } else {
                    $("#jz1").css("display", "block");
                    $("#jz2").css("display", "none");
                    $(".gwc_tb2 input[name=newslist]").each(function () {
                        if ($(this).attr("checked")) {
                            $(this).attr("checked", false);
                            //$(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
                        } else {
                            $(this).attr("checked", true);
                            //$(this).next().css({ "background-color": "#3366cc", "color": "#000000" });
                        }
                    });
                    $(".gwc_tb2 input[name=new]").each(function () {
                        if ($(this).attr("checked")) {
                            $(this).attr("checked", false);
                            //$(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
                        } else {
                            $(this).attr("checked", true);
                            //$(this).next().css({ "background-color": "#3366cc", "color": "#000000" });
                        }
                    })
                    GetCount();

                }

            })
            ;

            //反选
            $("#invert").click(function () {
                $(".gwc_tb2 input[name=newslist]").each(function () {
                    if ($(this).attr("checked")) {
                        $(this).attr("checked", false);
                        //$(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
                    } else {
                        $(this).attr("checked", true);
                        //$(this).next().css({ "background-color": "#3366cc", "color": "#000000" });
                    }
                });
                GetCount();
            });

            //取消
            $("#cancel").click(function () {
                $(".gwc_tb2 input[name=newslist]").each(function () {
                    $("#jz1").css("display", "block");
                    $("#jz2").css("display", "none");
                    $(this).attr("checked", false);
                    // $(this).next().css({ "background-color": "#ffffff", "color": "#000000" });
                });
                GetCount();
            });

            // 所有复选(:checkbox)框点击事件
            $(".gwc_tb2 input[name=newslist]").click(function () {
                if ($(this).is(":checked")) {
                    $("#jz1").css("display", "none");
                    $("#jz2").css("display", "block");
                    $(this).next().css({"background-color": "#3366cc", "color": "#ffffff"});
                } else {
                    $("#jz1").css("display", "block");
                    $("#jz2").css("display", "none");
                    $(this).next().css({"background-color": "#ffffff", "color": "#000000"});
                }
                GetCount();
            });

            // 输出
            $(".gwc_tb2 input[name=newslist]").click(function () {
                // $("#total2").html() = GetCount($(this));
                GetCount();
                //alert(conts);
            });


        })
        ;

        //******************
        function GetCount() {
            var conts = 0;
            var aa = 0;
            $(".gwc_tb2 input[name=newslist]").each(function () {
                if ($(this).attr("checked")) {
                    for (var i = 0; i < $(this).length; i++) {
                        conts += parseInt($(this).val());
                        aa += 1;
                    }
                }
            });
            $("#shuliang").text(aa);
            $("#zong1").html((conts).toFixed(2));
        }
    </script>

</head>

<body>

<!-- 顶部页面 -->
<#include "header.ftl">
<#include "sear.ftl">

<div class="gwc" style=" margin:auto; margin-top: 30px">
    <table cellpadding="0" cellspacing="0" class="gwc_tb1">
        <tr>
            <td class="tb1_td1">
                <input id="Checkbox1" type="checkbox" class="allselect"/></td>
            <td class="tb1_td1">全选</td>
            <td class="tb1_td3">书籍</td>
            <td class="tb1_td4">书籍详情</td>
            <td class="tb1_td5">数量</td>
            <td class="tb1_td6">总价</td>
            <td class="tb1_td7">操作</td>
        </tr>
    </table>

     <#list mycart as my>
    <!-- 商品加减算总数-->
    <script type="text/javascript">
        $(function () {
            var i = 0;
            var t = $("#text_box${my_index}");
            $("#add${my_index}").click(function () {
                if (t.val() < ${my.stock}) {
                    t.val(parseInt(t.val()) + 1)
                    setTotal();
                    GetCount();
                    //执行购物车的数量
                    $.post("updacount.do", {quantity: t.val(), gid: ${my.gid}}, function () {

                    })
                } else {
                    $("#sp${my_index}").css("display", "block");
                }

            })
            $("#min${my_index}").click(function () {
                if (t.val() > 1) {
                    t.val(parseInt(t.val()) - 1)
                    setTotal();
                    GetCount();
                    $.post("updacount.do", {quantity: t.val(), gid: ${my.gid}}, function () {

                    })
                    $("#sp${my_index}").css("display", "none");
                }
            })

            function setTotal() {
                $("#total${my_index}").html((t.val()) * ${my.price?c}/100);
                $("#newslist-${my_index}").val((t.val()) * ${my.price?c}/100);
            }

            setTotal();


            function updatecount() {
                //执行更新函数
            }
        })
    </script>


    <table cellpadding="0" cellspacing="0" class="gwc_tb2" id="table${my_index}">
        <tr>
            <td class="tb2_td1">
                <input type="hidden" id="rid${my_index}" value="${my.id}"/>
                <input type="checkbox" name="newslist" id="newslist-${my_index}"/>

            </td>
            <td class="tb2_td2">
                <input type="checkbox" name="new" id="raid${my_index}" value="${my.id}"
                       style="display: none;position: relative;left: -17px;"/>
            <#list my.imgurl?split(",") as img>

                <#if img_index == 0>
                <a href="#"><img src="${img}"/></a>
                </#if>
            </#list>
            </td>
            <td class="tb2_td3">
                <a href="#">${my.name}</a>
            </td>
            <td class="tb1_td4">${my.desc}</td>
            <td class="tb1_td5">
                <input id="min${my_index}" name="" style=" width:20px; height:18px;border:1px solid #ccc;" type="button"
                       value="-"/>
                <input id="text_box${my_index}" disabled="disabled" name="" type="text" value="${my.quantity}"
                       style=" width:30px; text-align:center; border:1px solid #ccc;"/>
                <input id="add${my_index}" name="" style=" width:20px; height:18px;border:1px solid #ccc;" type="button"
                       value="+"/>

                </br>
                <span id="sp${my_index}" style="display: none;position: relative; top: 20px;color: red">库存不足!</span>
            </td>
            <td class="tb1_td6">
                <label id="total${my_index}" class="tot"
                       style="color:#ff5500;font-size:14px; font-weight:bold;"></label>
            </td>
            <td class="tb1_td7">
                <button class="layui-btn layui-btn-blue" id="del_${my_index}"
                        style="width: 85px;height: 31px;background-color: #1e9fff;"/>
                <font style="color: white;">删除</font>
            </td>
        </tr>
    </table>
        <script type="text/javascript">
            $("#newslist-${my_index}").click(function () {
                if ($(this).is(":checked")) {
                    $("#raid${my_index}").attr("checked", true);
                } else {
                    $("#raid${my_index}").attr("checked", false);
                }
            })
        </script>
    <script>
        $("#del_${my_index}").click(function () {
            //询问框
            layer.confirm('您确认要删除吗？', {
                btn: ['确认', '取消'] //按钮
            }, function () {
                var id = $("#rid${my_index}").val();
                $.post("/delCart.do", {id: id}, function (data) {
                    console.log(data);
                    if (data == 0) {
                        layer.msg('删除成功', {icon: 1});
                        $("#table${my_index}").remove();
                        setInterval(function () {
                            window.location.href="";
                        },2000)
                    } else {
                        layer.msg('删除失败', {icon: 1});
                    }
                });
            }, function () {
                layer.close;
            });
        })
    </script>
    <!---总数---->
    <script type="text/javascript">
        $(function () {
            $(".quanxun").click(function () {
                0
                setTotal();
                //alert($(lens[0]).text());
            });

            function setTotal() {
                var len = $(".tot");
                var num = 0;
                for (var i = 0; i < len.length; i++) {
                    num = parseInt(num) + parseInt($(len[i]).text());

                }
                //alert(len.length);
                $("#zong${my_index}").text(parseInt(num).toFixed(2));
                $("#shuliang").text(len.length);
            }

            //setTotal();
        })
    </script>

     </#list>

    <form action="/settlement.do" method="post" id="from">
        <table cellpadding="0" cellspacing="0" class="gwc_tb3">
            <tr>
                <td class="tb1_td1">&nbsp;
                    <input type="hidden" name="price" id="price"/>
                </td>
                <td class="tb1_td1">&nbsp;
                    <input type="hidden" name="rid" id="jisuanid"/>
                </td>

                <td class="tb3_td1">&nbsp;</td>
                <td class="tb3_td2">已选商品
                    <label id="shuliang" style="color:#ff5500;font-size:14px; font-weight:bold;">0</label> 件
                </td>
                <td class="tb3_td3">合计(不含运费):<span>￥</span><span style=" color:#ff5500;">
                <label id="zong1" style="color:#ff5500;font-size:14px; font-weight:bold;"></label></span>
                </td>
                <td class="tb3_td4">
                    <span id="jz1">结算</span>
                    <button style="display:none;" class="jz2" id="jz2">结算</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<div style="margin-top: 50px">
</div>
<script src="../assets/layui.all.js"></script>
<script>


    //结算页面
    $("#jz2").click(function (event) {
        //拿到选中的购物车id
        var str = "";
        var id = "";

        $(".gwc_tb2 input[name=newslist]:checked").each(function () {
            str += $(this).val() + ",";
        })
        $(".gwc_tb2 input[name=new]:checked").each(function () {
            id += $(this).val() + ",";
        })

        $("#jisuanid").attr("value", id);
        $("#price").attr("value", str)

        $("#jz2").submit;
    });


</script>

</body>

</html>