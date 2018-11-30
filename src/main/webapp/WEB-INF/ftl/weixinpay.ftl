<!DOCTYPE html>
<html>
<#setting number_format="#">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${rc.contextPath}/assets/css/layui.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.min.css"/>
    <script src="${rc.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>

<style>.img {
    width: 150px;
    margin-left: -15px;
}

.span1 {
    font-weight: 900;
    margin-left: -40px;
    font-size: 19px;
    line-height: 100px;
}

.a_a {
    margin: auto;
    line-height: 100px;
    padding-left: 10px;
    font-size: 13px;
}

.span_01 {
    font-size: 13px;
    color: black;
}

.span_02 {
    font-size: 13px;
    color: black;
    margin-left: -78px;
}

.span_03 {
    font-size: 15px;
    color: red;
}

.div_zhi {
    border: 1px solid #eeeeee;
    margin-top: 40px;
    height: 555px;
    box-shadow: 4px 4px 4px #eeeeee, 4px -4px 4px #eeeeee, -4px 4px 4px #eeeeee, -4px -4px 4px #eeeeee;
}

.weixin {
    font-size: 18px;
    margin-left: 30px;
    margin-top: 10px;
}

.daxiao {
    height: 300px;
    width: 300px;
    border: 1px solid #DDDDDD;
}

.div_xia {
    margin-top: 20px;
}

.xiaxiao {
    width: 300px;
    height: 60px;
    margin-top: 10px;
}

.shouji {
    width: 290px;
    height: 380px;
}</style>

<script>$(document).ready(function () {
    $("#divma").animate({left: '150px'});
});</script>

<script type="text/javascript">

    $(function(){
        //ajax异步请求获取支付结果
        $.post("/trade/pay/status.do",{"orderId":${PAY_DATA.out_trade_no}},function(res_json){
            /*var res_json = res;*/
            var res_json= eval('(' + res_json + ')');
           /* console.log("服务器返回成功==================>"+res_json);*/
            if (res_json.recode=="TIMEOUT"){
                layer.msg("订单超时了",{icon: 5});
                window.location.reload();
            }
            if(res_json.recode=="SUCCESS"){
                layer.msg("支付成功",{icon: 1});
                setInterval(function () {
                    window.location.href = "/myorder.do";
                },2000)
            }
            //一旦回调得到支付结果，显示支付成功
        });

    });
</script>
<#include "header.ftl">
<body>
<div style=" width: 65%;height: 100px;margin: auto;">

    <div style="margin-top: 20px;">
        <div class="row">
            <div class="col-md-10 col-sm-10">
						<span class="span_01">
							<span>订单提交成功,请尽快付款 ! 订单号:</span>
						<span><#if order??>${order.serialnumber}</#if></span>
						</span>
            </div>
            <div class="col-md-2">
						<span class="span_02">
							<span>应付金额:</span>
						<span class="span_03"><#if order??>${order.totalprice/100}</#if></span>
						</span>
            </div>
        </div>
    </div>

    <div class="div_zhi">
        <div class="weixin"><span><img style="width: 100px;" src="../img/weixin.png"/></span><span
                style="font-size:30px;color:green;">微信支付<span></div>
        <div class="div_xia row">
            <div class="col-md-2"></div>
            <div class="col-md-3 col-sm-3">
                <div class="daxiao">
                    <div style="padding: 17px;">
                        <img alt="图片无法加载" style="width: 100%;height: 100%;" src="${rc.contextPath}/crcode.do?qrcode=${crcode}">
                    </div>
                    <div class="xiaxiao" id="imgma">
                        <img src="../img/sao.jpg" style="width: 290px;"/>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="shouji" id="divma" style="position:absolute;left:-217px;;">
                    <img src="../img/2wei.jpg"/>
                </div>
            </div>

            <div class="col-md-2 col-sm-2"></div>
        </div>
    </div>
</div>
</body>

<script src="../layui/layui.all.js"></script>

</html>