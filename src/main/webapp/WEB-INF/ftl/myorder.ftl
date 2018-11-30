<!DOCTYPE html>
<html>
<#setting number_format="#">
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/myorder.css"/>
</head>
<style>
    #dfk {
        width: 90%;
        border: 3px solid #f5f5f5;
        -moz-border-radius: 50px;
        -webkit-border-radius: 50px;
        border-radius: 30px;
    }
</style>


<body>
 <#include  "personal.ftl">


<div style="margin-left: 600px;">

    <div class="layui-col-md-offset4" align="center" style="text-align: center;margin: auto;margin-top: 50px;">
    <#--<h1>我的订单</h1>-->
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this" style="font-size: 20px;width: 24%;">所有订单</li>
                <li style="font-size: 20px;width: 24%">待付款</li>
                </span>
                <li style="font-size: 20px;width: 24%">待评价</li>
                </span>
                <li style="font-size: 20px;width: 24%">已完成</li>
                </span>
            </ul>
            <div class="layui-tab-content">
                <!--
                    全部订单
                -->
            <div class="layui-tab-item layui-show">
                <div class="div3">
                    <table class="table1" align="center">
                        <tr class="tr1" >
                          <td>商品</td>
                            <td>信息</td>
                            <td>数量</td>
                            <td>单价</td>
                            <td>状态</td>
                        </tr>
                    </table>
                </div>

                <#if orderlist??>
                    <#list orderlist as lob>
                    <div class="div3" id="dfk" style="overflow-y: auto;margin-left: -20px">
                        <table class="table2" border="0">
                            <tr style="background-color: #f5f5f5">
                                <td>订单号:</td>
                                <td><p id="p_1">${lob.serialnumber}</p></td>
                                <td></td>
                                <td>下单时间:</td>
                                <td>${lob.time?string["yyyy-MM-dd HH:mm:ss"]}</td>
                            </tr>
                            <#if lob.status == 2>
                                <tr>
                                    <td>剩余时间:</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><p id="porder_time"></p></td>
                                </tr>
                            </#if>


                                         <#list lob.orderitemList as orderbook>
                                                <tr class="tr2">
                                                    <td>
                                                         <#list orderbook.book.imgurl?split(",") as imgurl>
                                                                <#if imgurl_index == 0>
                                                                    <a href="${rc.contextPath}/showBook/${orderbook.book.id}.do"><img
                                                                            src="${imgurl}"
                                                                            style="width: 80px;height: 80px;"/></a>
                                                                </#if>
                                                         </#list>
                                                    </td>
                                                    <td style="vertical-align:middle;">${orderbook.book.name}</td>
                                                    <td style="vertical-align:middle;">x${orderbook.quantity}</td>
                                                    <td style="vertical-align:middle;">¥${orderbook.book.price/100}</td>
                                                    <td style="vertical-align:middle;">
                                                        <#if lob.status == 1>
                                                            已完成
                                                        <#elseif lob.status == 2>
                                                            待付款
                                                        <#elseif lob.status == 3>
                                                            待评价
                                                        </#if>
                                                    </td>
                                                </tr>
                                         </#list>
                            <tr></tr>
                            <tr>
                                <td>实付款</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><p id="p_2" style="color: red">¥${lob.totalprice/100}</p></td>
                            </tr>
                            <#if lob.status != 1>
                                <tr>
                                    <td colspan="2"></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                             <#if lob.status == 2>
                                                 <button id="but2" style="width: 70px;background-color: #1e9fff" value="${lob.id}"
                                                         onclick="updateOrder(${lob.id})"
                                                         class="layui-btn layui-btn-sm layui-btn-radius">
                                                     取消
                                                 </button>
                                                 <form id="form" action="/orderpay.do" method="post" style="display:inline">
                                                     <input type="hidden" id="money" name="totalprice"
                                                            value="${lob.totalprice}">
                                                     <input type="hidden" id="Onumber" name="serialnumber"
                                                            value="${lob.serialnumber}">
                                                     <button id="but3" style="width: 70px;background-color: #1e9fff"
                                                             class="layui-btn layui-btn-sm layui-btn-radius">
                                                         立即付款
                                                     </button>
                                                 </form>
                                             <#elseif lob.status == 3>
                                                 <button id="but4" style="width: 70px;background-color: #1e9fff"
                                                         class="layui-btn layui-btn-sm layui-btn-radius">
                                                     <a href="/showComment.do" class="a1" style="color: white">立即评价</a>
                                                 </button>
                                             </#if>
                                    </td>
                                </tr>
                            </#if>
                        </table>
                    </div>
                    </#list>
                <#else>
                            <h2>你还没有订单，快去下单吧</h2>
                </#if>
                </div>

                <!--

                    待付款订单

                -->
                <div class="layui-tab-item">
                    <div class="div3">
                        <table class="table1" border="0">
                            <tr class="tr1">
                                <td>商品</td>
                                <td>信息</td>
                                <td>数量</td>
                                <td>单价</td>
                                <td>状态</td>
                            </tr>
                        </table>
                    </div>

               <#if orderPending??>
                   <#list orderPending as lob>
                    <div class="div3" id="dfk" style="overflow-y: auto;">
                             <table class="table2" border="0">
                                 <tr style="background-color: #f5f5f5">
                                     <td>订单号:</td>
                                     <td><p id="p_1">${lob.serialnumber}</p></td>
                                     <td></td>
                                     <td>下单时间:</td>
                                     <td>${lob.time?string["yyyy-MM-dd HH:mm:ss"]}</td>
                                 </tr>
                                  <#if lob.status == 2>
                                     <tr>
                                         <td>剩余时间</td>
                                         <td></td>
                                         <td></td>
                                         <td></td>
                                         <td><p id="porder_times"></p></td>
                                     </tr>
                                  </#if>
                                     <#list lob.orderitemList as orderbook>
                                            <tr class="tr2">
                                                <td>
                                                     <#list orderbook.book.imgurl?split(",") as imgurl>
                                                            <#if imgurl_index == 0>
                                                                <a href="${rc.contextPath}/showBook/${orderbook.book.id}.do"><img
                                                                        src="${imgurl}"
                                                                        style="width: 80px;height: 80px;"/></a>
                                                            </#if>
                                                     </#list>
                                                </td>
                                                <td style="vertical-align:middle;">${orderbook.book.name}</td>
                                                <td style="vertical-align:middle;">x${orderbook.quantity}</td>
                                                <td style="vertical-align:middle;">¥${orderbook.book.price/100}</td>
                                                <td style="vertical-align:middle;">
                                                      <#if lob.status == 1>
                                                          已完成
                                                      <#elseif lob.status == 2>
                                                            待付款
                                                      <#elseif lob.status == 3>
                                                            待评价
                                                      </#if>
                                                </td>
                                            </tr>
                                            <tr></tr>
                                     </#list>
                                 <tr>
                                     <td>实付款</td>
                                     <td></td>
                                     <td></td>
                                     <td></td>
                                     <td style="color: red">¥${lob.totalprice/100}</td>
                                 </tr>
                                 <tr>
                                     <td colspan="2"></td>
                                     <td></td>
                                     <td></td>
                                     <td>
                                         <#if lob.status == 2>
                                             <button id="but2" style="width: 70px;background-color: #1e9fff"" value="${lob.id}"
                                                     onclick="updateOrder(${lob.id})"
                                                     class="layui-btn layui-btn-sm layui-btn-radius">
                                                 取消
                                             </button>
                                             <form id="form" action="/orderpay.do" method="post" style="display:inline">
                                                 <input type="hidden" id="money" name="totalprice"
                                                        value="${lob.totalprice}">
                                                 <input type="hidden" id="Onumber" name="serialnumber"
                                                        value="${lob.serialnumber}">
                                                 <button id="but3" style="width: 70px;background-color: #1e9fff""
                                                         class="layui-btn layui-btn-sm layui-btn-radius">
                                                     立即付款
                                                 </button>
                                             </form>
                                         <#elseif lob.status == 3>
                                             <button id="but4" style="width: 70px;background-color: #1e9fff""
                                                     class="layui-btn layui-btn-sm layui-btn-radius">
                                                 <a href="" class="a1">立即评价</a>
                                             </button>
                                         </#if>
                                     </td>
                                 </tr>
                             </table>
                    </div>
                         </#list>
                     <#else >
                         <h2>你还没有订单，快去下单吧</h2>
                     </#if>
                </div>
                <!--

                    待评价订单

                -->
                <div class="layui-tab-item">
                    <div class="div3">
                        <table class="table1" border="0">
                            <tr class="tr1">
                                <td>商品</td>
                                <td>信息</td>
                                <td>数量</td>
                                <td>单价</td>
                                <td>状态</td>
                            </tr>
                        </table>
                    </div>
        <#if orderEvaluation??>
            <#list orderEvaluation as lob>
                    <div class="div3" id="dfk" style="overflow-y: auto;">
                         <table class="table2" border="0">
                             <tr style="background-color: #f5f5f5">
                                 <td>订单号:</td>
                                 <td><p id="p_1">${lob.serialnumber}</p></td>
                                 <td></td>
                                 <td>下单时间:</td>
                                 <td>${lob.time?string["yyyy-MM-dd HH:mm:ss"]}</td>
                             </tr>
                                 <#list lob.orderitemList as orderbook>
                                        <tr class="tr2">
                                            <td>
                                                 <#list orderbook.book.imgurl?split(",") as imgurl>
                                                        <#if imgurl_index == 0>
                                                            <a href="${rc.contextPath}/showBook/${orderbook.book.id}.do"><img
                                                                    src="${imgurl}"
                                                                    style="width: 80px;height: 80px;"/></a>
                                                        </#if>
                                                 </#list>
                                            </td>
                                            <td style="vertical-align:middle;">${orderbook.book.name}</td>
                                            <td style="vertical-align:middle;">x${orderbook.quantity}</td>
                                            <td style="vertical-align:middle;">¥${orderbook.book.price/100}</td>
                                            <td style="vertical-align:middle;">
                                                  <#if lob.status == 1>
                                                      已完成
                                                  <#elseif lob.status == 2>
                                                            待付款
                                                  <#elseif lob.status == 3>
                                                            待评价
                                                  </#if>
                                            </td>
                                        </tr>
                                        <tr></tr>
                                 </#list>
                             <tr>
                                 <td>实付款</td>
                                 <td></td>
                                 <td></td>
                                 <td></td>
                                 <td style="color: red">¥${lob.totalprice/100}</td>
                             </tr>
                             <tr>
                                 <td colspan="2"></td>
                                 <td></td>
                                 <td></td>
                                 <td>
                                     <button id="but4" style="width: 70px;background-color: #1e9fff""
                                             class="layui-btn layui-btn-sm layui-btn-radius">
                                         <a href="/showComment.do" class="a1" style="color: white">立即评价</a>
                                     </button>
                                 </td>
                             </tr>
                         </table>
                    </div>
                         </#list>
                     <#else >
                         <h2>你还没有订单，快去下单吧</h2>
                     </#if>
                </div>

            <#--已完成订单-->
                <div class="layui-tab-item">
                    <div class="div3">
                        <table class="table1" border="0">
                            <tr class="tr1">
                                <td>商品</td>
                                <td>信息</td>
                                <td>数量</td>
                                <td>单价</td>
                                <td>状态</td>
                            </tr>
                        </table>
                    </div>
               <#if orderOk??>
                   <#list orderOk as lob>
                    <div class="div3" id="dfk" style="overflow-y: auto;">
                             <table class="table2" border="0">
                                 <tr style="background-color: #f5f5f5">
                                     <td>订单号:</td>
                                     <td><p id="p_1">${lob.serialnumber}</p></td>
                                     <td></td>
                                     <td>下单时间:</td>
                                     <td>${lob.time?string["yyyy-MM-dd HH:mm:ss"]}</td>
                                 </tr>
                                     <#list lob.orderitemList as orderbook>
                                            <tr class="tr2">
                                                <td>
                                                     <#list orderbook.book.imgurl?split(",") as imgurl>
                                                            <#if imgurl_index == 0>
                                                                <a href="${rc.contextPath}/showBook/${orderbook.book.id}.do"><img
                                                                        src="${imgurl}"
                                                                        style="width: 80px;height: 80px;"/></a>
                                                            </#if>
                                                     </#list>
                                                </td>
                                                <td style="vertical-align:middle;">${orderbook.book.name}</td>
                                                <td style="vertical-align:middle;">x${orderbook.quantity}</td>
                                                <td style="vertical-align:middle;">¥${orderbook.book.price/100}</td>
                                                <td style="vertical-align:middle;">
                                                      <#if lob.status == 1>
                                                          已完成
                                                      <#elseif lob.status == 2>
                                                            待付款
                                                      <#elseif lob.status == 3>
                                                            待评价
                                                      </#if>
                                                </td>
                                            </tr>
                                            <tr></tr>
                                     </#list>
                                 <tr>
                                     <td>实付款</td>
                                     <td></td>
                                     <td></td>
                                     <td></td>
                                     <td style="color: red">¥${lob.totalprice/100}</td>
                                 </tr>
                             </table>
                        </div>
                         </#list>
                     <#else >
                         <h2>你还没有订单，快去下单吧</h2>
                     </#if>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script>
    function updateOrder(id) {
        $.post("updateOrderStatus.do", {"id": id}, function (data) {
            if (data != "0") {
                layer.msg("取消成功", {icon: 1});
                window.location.reload();
            } else {
                layer.msg("取消失败", {icon: 1});
            }
        })
        window.location = "myorder.do";
    }


    <#if times??>
       function getTime() {
           var nowTime = new Date(${times?string('yyyy,MM,dd,HH,mm,ss')});
           var s = nowTime.getTime();

           var time = new Date();
           var b = 30; //分钟数
           time.setMinutes(time.getMinutes() + b);
           //根据差值可以计算出秒数(毫秒数/1000)
           var x = time.getTime();

           var dSecond = parseInt((x - s) / 1000);
           //通过现在距离30分钟后的秒数取余 求出剩下的秒数
           var reSecond = dSecond % (24 * 60 * 60);
           //通过计算小时 剩下的秒数 求分钟数
           var reSecond1 = reSecond % 3600;
           var dMinute = parseInt(reSecond1 / 60);
           //通过计算分钟数 剩下的秒数  就是我们想要的描述
           var nowSecond = reSecond1 % 60;
           console.log(dMinute + "--------" + nowSecond);
           if (dMinute == 0 && nowSecond == 0) {
               return 0;
           } else {
               $("#porder_time").html(dMinute + "分钟" + nowSecond + "秒");
               $("#porder_times").html(dMinute + "分钟" + nowSecond + "秒");
           }

       }
        var times = setInterval(function () {
            var m = getTime();
            if (m === 0) {
                clearInterval(times);
                var id = document.getElementById("but2").value;
                alert(id);
                $.post("updateOrderStatus.do", {"id": id}, function (data) {
                    if (data != "0") {
                     /*   alert("成功取消");*/
                        layer.msg("取消成功", {icon: 1});
                        setInterval(function () {
                            window.location.reload();
                        },2000)
                    } else {
                        layer.msg("取消失败", {icon: 1});
                       /* alert("取消失败");*/
                    }
                });
                return window.location = "myorder.do";
            }
        }, 1000);
    </#if>
</script>
<script>
    $("#but3").click(function () {
        var mon = document.getElementById("money").value;
        var s = document.getElementById("Onumber").value;
        $("#but3").submit;
    })

</script>

</html>