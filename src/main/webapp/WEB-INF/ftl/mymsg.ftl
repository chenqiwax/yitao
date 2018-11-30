<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的消息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/assets/css/layui.css" media="all">
    <script src="${rc.contextPath}/js/jquery-3.3.1.js"></script>
</head>

<body>


<#include "personal.ftl">

<div style="margin-left: 558px;">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>来自易淘的通知:</legend>
        </fieldset>
        <ul class="layui-timeline">
             <#if msgList??>
                 <#list msgList as msg>
                  <li class="layui-timeline-item">
                      <i class="layui-icon layui-timeline-axis"></i>
                      <div class="layui-timeline-content layui-text">
                          <h3 class="layui-timeline-title">
                              ${msg.dateTime?string("yyyy年MM月dd日 HH时mm分ss秒")}
                              <#if msg.status==0>
                                    <span class="layui-badge">未读</span>
                                    <#elseif msg.status==1>
                                     <span class="layui-badge layui-bg-orange">已读</span>
                              </#if>
                          </h3>
                          <p>
                              ${msg.content}<i class="layui-icon"></i>
                          </p>
                      </div>
                  </li>
                 </#list>
             </#if>
        </ul>
</div>
<script src="${rc.contextPath}/assets/layui.js" charset="utf-8"></script>
</body>

</html>