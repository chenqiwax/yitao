<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#include "unavigation.ftl">
<div style="width: 900px;margin-left: 600px;margin-top: -140px">
     <#if nowUser??>
         <table style="text-align: center;" width="100%">
             <tr>
                 <th style="font-weight: 900;font-size: 20px;">头像：</th>
                 <th>
                    <#if nowUser.iconUrl??>
                        <a href="${nowUser.iconUrl}"> <img src="${nowUser.iconUrl}" style="width: 100px;height: 100px;border-radius:50%"/></a>
                    <#else>
                        你还没有上传头像
                    </#if>
                 </th>
             </tr>
             <tr></tr>
             <tr>
                 <td>
                     用户账号：
                 </td>
                 <td>
                     ${nowUser.account}
                 </td>
             </tr>
             <tr></tr>
             <tr>
                 <td>
                     用户昵称：
                 </td>
                 <td>
                     <#if nowUser.nickname??>
                         ${nowUser.nickname}
                     <#else>
                        你还没有完善个人信息
                     </#if>
                 </td>
             </tr>
             <tr></tr>
             <tr>
                 <td>用户性别：</td>
                 <td>
                    <#if nowUser.sex??>
                        ${nowUser.sex}
                    <#else>
                        你还没有完善个人信息
                    </#if>
                 </td>
             </tr>
             <tr></tr>
             <tr>
                 <td>手机号码：</td>
                 <td><#if nowUser.telephone??>${nowUser.telephone}</#if></td>
             </tr>
             <tr>
                 <td>用户邮箱：</td>
                 <td>
                    <#if nowUser.email??>
                        ${nowUser.email}
                    <#else>
                        你还没有完善个人信息
                    </#if>
                 </td>
             </tr>
             <tr></tr>
             <tr>
                 <td>出身日期：</td>
                 <td>
                    <#if nowUser.birthday??>
                        ${nowUser.birthday?string('yyyy年MM月dd日')}
                    <#else>
                        你还没有完善个人信息
                    </#if>
                 </td>
             </tr>
             <tr></tr>
             <tr>
                 <td>注册日期：</td>
                 <td>${nowUser.registerDate?string('yyyy年MM月dd日 HH时mm分ss秒')}</td>
             </tr>
         </table>
     </#if>
</div>
</body>
</html>