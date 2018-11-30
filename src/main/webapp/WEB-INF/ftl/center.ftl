<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.min.css"/>
<#--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <link href="${rc.contextPath}/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${rc.contextPath}/css/layout.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/demo.css">

    <link rel="stylesheet" href="${rc.contextPath}/css/amazeui.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/css/custom_up_img.css">
    <style type="text/css">
        .up-img-cover {
            width: 100px;
            height: 100px;
        }

        a {
            text-decoration: none;
        }

        .up-img-cover img {
            width: 100%;
        }
    </style>
    <style type="text/css">
        .box {
            width: 449px;
            height: 25px;
            line-height: 25px;
            overflow: hidden;
        }

        .box ul {
            margin: 0;
            padding: 0
        }

        .box li {
            height: 25px;
            line-height: 25px;
            font-size: 12px;
            text-align: center;
            list-style-type: none;
        }

    </style>
    <script type="text/javascript" src="${rc.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/index.js"></script>
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">

    <title>易淘</title>
    <style>

        a:hover {
            color: red;
            text-decoration: none;
        }

        .like img {
            width: 100%;
        }

        .ibar_login_box {
            width: 217px;
            height: 300px;
            padding: 10px;
            background: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, .4);
            border-radius: 5px 0 0 5px;
            border-left: 1px solid #ccc \0;
            border-top: 1px solid #ccc \0;
            border-bottom: 1px solid #ccc \0;
            z-index: 3;
            width: 100%;
            height: 200px;
        }

        .img1 {
            width: 247px;
        }

        img:hover {
            margin-left: -15px;
            position: relative;
            -webkit-transition: all .2s ease-out;
            -moz-transition: all .2s ease-out;
            -ms-transition: all .2s ease-out;
            -o-transition: all .2s ease-out;
            transition: all .2s ease-out;
            z-index: 1;

        }

        .img2 {
            width: 122px;
        }

        .img2:hover {
            -webkit-animation: doudong 1s .1s ease both;
            -moz-animation: tada 1s .1s ease both;
        }
        .hover_btn:hover {
            background-color: white;
            color: red;
        }

    </style>
</head>

<body>

<!-- 顶部部页面 -->
<#include "header.ftl">

<!-- 颈部页面 -->
<#include "search.ftl">

<hr style="width: 100%;background-color:#1e9fff;margin-top: 50px">

<div class="container-fluid">
    <div class="row" style="margin-top: 60px; ">
        <div class="col-lg-3 col-md-2">
            <!--左侧菜单开始-->
            <div id="catList" style="float: right;width: 260px;">
                <!--图书商品分类开始-->
                <div class="book_sort">
                    <div class="book_sort_bg">
                        <img src="/images/dd_book_cate_icon.gif" alt="图书 "/> 图书商品分类
                    </div>
                    <#if allBookCategory??>
                        <#list allBookCategory as bookCategory>
                         <div class="book_cate">
                             <a href="#" style="color: black">
                                 ${bookCategory.name}
                             </a>
                         </div>
                         <div class="book_sort_bottom">
                          <#list bookCategory.categoryList as chirdBook >
                              <a href="${rc.contextPath}/classification.do?keyword=${chirdBook.name}">
                              <a href="${rc.contextPath}/classification.do?keyword=${chirdBook.name}">
                                  ${chirdBook.name} |
                              </a>
                          </#list>
                         </div>
                        </#list>
                    <#else>
                    </#if>
                </div>
                <!--图书商品分类结束-->
            </div>
            <!--左侧菜单结束-->
        </div>
    <#--中间部分开始-->
        <div class="col-md-8 col-lg-6">
            <div class="row">

                <div class="layui-carousel col-lg-12" id="test10">
                    <div carousel-item="">
                          <#if aDList??>
                              <#list aDList as ad>
                                    <div>
                                        <a href="<#if ad.link??>${ad.link}<#else >#</#if>" target="_blank">
                                        <img style="width: 952px;height: 454px;" src="${ad.url}">
                                        </a>
                                        <div class="carousel-caption d-none d-md-block">
                                            <h5 style="color: black">${ad.headline}</h5>
                                            <p style="color: black">${ad.subhead}</p>
                                        </div>
                                    </div>
                              </#list>
                          </#if>
                    </div>
                </div>
                <div class="row">
                <#--猜你喜欢开始-->
                    <div class="row like hotbook">
                            <#if userFavorityList??>
                                <div class="col-md-12">
                                    <h3>猜你喜欢<font style="font-size: 17px; ">根据你的喜好精心为你推荐!</font></h3>
                                </div>
                                <#list userFavorityList as favoriteBook>
                                    <div class="col-md-3 col-sm-6">
                                        <a href="${rc.contextPath}/showBook/${favoriteBook.id}.do">
                                            <#list "${favoriteBook.imgurl}"?split(",") as imgurl>
                                                <img src="${imgurl}" style="width: 200px;height: 200px">
                                                <#break>
                                            </#list>
                                            <p>${favoriteBook.name}</p>
                                        </a>
                                        <p class="p2 "><span style="color: red;font-size: 20px">￥${favoriteBook.price/100}</span></p>
                                    </div>
                                </#list>
                            </#if>
                    </div>
                <#--猜你喜欢结束-->
                <#--热销书籍开始-->
                    <div class="row">
                            <#if hotBookList??>
                                <div class="col-md-12">
                                    <h3>热销书籍<font style="font-size: 17px; ">根据销量排行精心为你推荐!</font></h3>
                                </div>
                                <#list hotBookList as hotBook>
                                    <div class="col-md-3 col-sm-6 hotbook">
                                            <#list hotBook.imgurl?split(",") as fileNameUrl>
                                                    <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${hotBook.id}.do">
                                                            <img src="${fileNameUrl}" style="width: 200px;height: 200px">
                                                            <p>${hotBook.name}</p>
                                                            <p class="p2"><span style="color: red;font-size: 20px">￥${hotBook.price/100}</span></p>
                                                        </a>
                                                    </#if>
                                            </#list>
                                    </div>
                                </#list>
                            </#if>
                    <#--热销书籍结束-->
                    </div>
                </div>
            </div>
        </div>
    <#--中间部分结束-->
        <div class="col-md-2 col-lg-3">
            <div class="" style="float: left">
                <#if Session.nowUser??>
                    <div class="ibar_login_box status_login">
                        <div class="avatar_box">
                            <div class="up-img-cover"  id="up-img-touch" >
                                <img class="am-circle" style="height: 100%;margin-left: 45px;" id="header_img" alt="点击图片上传" src="<#if Session.nowUser.iconUrl??>${Session.nowUser.iconUrl}</#if>" data-am-popover="{content: '点击上传', trigger: 'hover focus'}" >
                            </div>
                            <div><a style="text-align: center; display: block;"  id="pic"></a></div>

                            <!--图片上传框-->
                            <div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
                                <div class="am-modal-dialog up-frame-parent up-frame-radius">
                                    <div class="am-modal-hd up-frame-header">
                                        <label>修改头像</label>
                                        <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                                    </div>
                                    <div class="am-modal-bd  up-frame-body">
                                        <div class="layui-upload">
                                            <div class="layui-upload-list">
                                                <img class="layui-upload-img" style="width: 200px;height: 200px" id="demo1">
                                                <p id="demoText"></p>
                                            </div>
                                            <button type="button" class="layui-btn layui-btn-normal" id="test1">上传图片</button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <#-- <p class="avatar_imgbox"><img src="" style="padding-left: 41px,width: 145px;height: 85px;"/></p>-->
                                <ul class="user_info" style="text-align: center;font-size: 12px;">
                            <#if Session.nowUser??>
                                <li>用户名：${Session.nowUser.account}</li>
                            </#if>
                                </ul>
                            </div>
                            <div class="login_btnbox">
                                <button class="layui-btn layui-btn-warm" style="margin-top:20px;width:80px;background-color: #1E9FFF;border-radius: 5px;">
                                    <a href="${rc.contextPath}/myorder.do" class="login_order" style="color: white;" target="_self">我的订单</a>
                                </button>
                                <button class="layui-btn layui-btn-warm" style="margin-top:20px;width:80px;background-color: #1E9FFF;border-radius: 5px;">
                                    <a href="${rc.contextPath}/myfavcenter.do" class="login_favorite" style="color: white;" target="_self">我的收藏</a>
                                </button>
                            </div>
                            <i class="icon_arrow_white"></i>
                        </div>
                <#else>
                    <div class="ibar_login_box status_login">
                        <div class="avatar_box">
                            <p class="avatar_imgbox">
                                <#--<img src="http://192.168.4.254:8888/three/img/userPicture/cda8699739994aae8faa30b4b6675476.jpg" style="padding-left: 41px,width: 145px;height: 85px;"/>-->
                                <img class="am-circle" style="height: 100%;margin-left: 45px;" id="header_img" alt="点击图片上传" src="http://192.168.4.254:8888/three/img/userPicture/cda8699739994aae8faa30b4b6675476.jpg" >
                            </p>
                        </div>
                        <div class="login_btnbox" style="text-align: center; font-size: 17px;">
                            <button class="layui-btn layui-btn-warm hover_btn" style="margin-top:15px;width:80px;background-color:#1E9FFF;border-radius:5px;">
                                <a href="${rc.contextPath}/login.do" class="login_order" style="color:white;text-decoration:none;">登录</a>
                            </button>
                            <button class="layui-btn layui-btn-warm hover_btn" style="margin-top:15px;width:80px;background-color:#1E9FFF;border-radius:5px;">
                                <a href="${rc.contextPath}/regist.do" class="login_favorite" style="color: white;text-decoration: none;">注册</a>
                            </button>
                        </div>
                        <i class="icon_arrow_white"></i>
                    </div>
                </#if>

            </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-12">
                            <div style="display: inline-block">
                                <h3>励志与成功</h3>
                            </div>
                            <div class="box" id="marqueebox1" style="display: inline-block;padding-top: 4px;">
                                <ul>
                                    <li style="font-size: 17px;color:#08ACEE">教育的真正目的就是让人不断地提出问题、思索问题!</li>
                                    <li style="font-size: 17px;color:#08ACEE">提问题比回答问题更启发人的智慧!</li>
                                    <li style="font-size: 17px;color:#08ACEE">很好的导师不是告知答案，而是向人提问!</li>
                                    <li style="font-size: 17px;color:#08ACEE">人可以拒绝任何东西，但绝对不可以拒绝成熟!</li>
                                    <li style="font-size: 17px;color:#08ACEE">“你不解决问题，你就会成为问题。”!</li>
                                </ul>
                            </div>
                        </div>
                        <div class="row like">
                            <#list liZhiBooks as lizhiBook>
                                <#if lizhiBook_index==0>
                                     <div class="col-md-6 col-sm-6">
                                         <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                             <#list lizhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${lizhiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${lizhiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#elseif lizhiBook_index==1>
                                    <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                        <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                                 <#list lizhiBook.imgurl?split(",") as fileNameUrl>
                                                     <#if fileNameUrl_index==0>
                                                            <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                                                <img src="${fileNameUrl}">
                                                            </a>
                                                     </#if>
                                                 </#list>
                                            <p>${lizhiBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${lizhiBook.price/100}</span></p>
                                        </a>
                                    </div>
                                    <#elseif lizhiBook_index==2>
                                     <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                         <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                             <#list lizhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${lizhiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${lizhiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#else>
                                    <div class="col-md-3 col-sm-6">
                                        <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                             <#list lizhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${lizhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                            <p>${lizhiBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${lizhiBook.price/100}</span></p>
                                        </a>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-12 ">
                            <div style="display: inline-block">
                                <h3>心理学</h3>
                            </div>
                            <div class="box" id="marqueebox2" style="display: inline-block;padding-top: 4px;">
                                <ul>
                                    <li style="font-size: 17px;color:#08ACEE">疯子和天才，有的时候就是一步之遥!</li>
                                    <li style="font-size: 17px;color:#08ACEE">每个人看待世界是不一样的!</li>
                                    <li style="font-size: 17px;color:#08ACEE">认识到意志力的局限性对培养自控力至关重要!</li>
                                    <li style="font-size: 17px;color:#08ACEE">先寻求理解对方，再争取被对方理解!</li>
                                    <li style="font-size: 17px;color:#08ACEE">应该由心来操纵舌头，而不是由舌头来操纵心!</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row like">
                         <#list xinLiBooks as xinLiBook>
                                <#if xinLiBook_index==0>
                                     <div class="col-md-6 col-sm-6">
                                         <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                             <#list xinLiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${xinLiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${xinLiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                     <#elseif xinLiBook_index==1>
                                     <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                         <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                                 <#list xinLiBook.imgurl?split(",") as fileNameUrl>
                                                     <#if fileNameUrl_index==0>
                                                            <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                                                <img src="${fileNameUrl}">
                                                            </a>
                                                     </#if>
                                                 </#list>
                                             <p>${xinLiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${xinLiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                     <#elseif xinLiBook_index==2>
                                     <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                         <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                             <#list xinLiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${xinLiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${xinLiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#else>
                                    <div class="col-md-3 col-sm-6">
                                        <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                             <#list xinLiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${xinLiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                            <p>${xinLiBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${xinLiBook.price/100}</span></p>
                                        </a>
                                    </div>
                                </#if>
                         </#list>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="col-md-12 ">
                        <div style="display: inline-block">
                            <h3>政治军事</h3>
                        </div>
                        <div class="box" id="marqueebox3" style="display: inline-block;padding-top: 4px;">
                            <ul>
                                <li style="font-size: 17px;color:#08ACEE">弘扬伟大长征精神，走好今天的长征路!</li>
                                <li style="font-size: 17px;color:#08ACEE">人民有信仰，民族有希望，国家有力量!</li>
                                <li style="font-size: 17px;color:#08ACEE">推进生态文明建设，改革我国环保管理体制!</li>
                                <li style="font-size: 17px;color:#08ACEE">铭记历史、缅怀先烈、珍爱和平、开创未来!</li>
                                <li style="font-size: 17px;color:#08ACEE">文化自信，应不忘初心!</li>
                            </ul>
                        </div>
                    </div>
                    <div class="row like">
                        <#list zenZhiList as zheZhiBook>
                                <#if zheZhiBook_index==0>
                                     <div class="col-md-6 col-sm-6">
                                         <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                             <#list zheZhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${zheZhiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${zheZhiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#elseif zheZhiBook_index==1>
                                    <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                        <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                             <#list zheZhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                            <p>${zheZhiBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${zheZhiBook.price/100}</span></p>
                                        </a>
                                    </div>
                                    <#elseif zheZhiBook_index==2>
                                     <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                         <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                             <#list zheZhiBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${zheZhiBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${zheZhiBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#else>
                                    <div class="col-md-3 col-sm-6">
                                        <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                                 <#list zheZhiBook.imgurl?split(",") as fileNameUrl>
                                                     <#if fileNameUrl_index==0>
                                                            <a href="${rc.contextPath}/showBook/${zheZhiBook.id}.do">
                                                                <img src="${fileNameUrl}">
                                                            </a>
                                                     </#if>
                                                 </#list>
                                            <p>${zheZhiBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${zheZhiBook.price/100}</span></p>
                                        </a>
                                    </div>
                                </#if>
                        </#list>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-12 ">
                            <div style="display: inline-block">
                                <h3>科普百科</h3>
                            </div>
                            <div class="box" id="marqueebox4" style="display: inline-block;padding-top: 4px;">
                                <ul>
                                    <li style="font-size: 17px;color:#08ACEE">儿童对世界充满了好奇，喜欢探索、进而认识这个世界!</li>
                                    <li style="font-size: 17px;color:#08ACEE">保护地球，是一个激发好奇心和不断探索的过程!</li>
                                    <li style="font-size: 17px;color:#08ACEE">你幽默的语言告诉孩子，探索世界!</li>
                                    <li style="font-size: 17px;color:#08ACEE">读史可以明智”。!</li>
                                    <li style="font-size: 17px;color:#08ACEE">让孩子热爱大自然，在大自然中汲取能量。!</li>
                                </ul>
                            </div>
                        </div>
                        <div class="row like">
                             <#list kePuBooks as kePuBook>
                                <#if kePuBook_index==0>
                                     <div class="col-md-6 col-sm-6">
                                         <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                             <#list kePuBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${kePuBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${kePuBook.price/100}</span></p>
                                         </a>
                                     </div>
                                    <#elseif kePuBook_index==1>
                                    <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                             <#list kePuBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                            <p>${kePuBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${kePuBook.price/100}</span></p>
                                        </a>
                                    </div>
                                    <#elseif kePuBook_index==2>
                                    <div class="col-md-3 col-sm-6" style="margin-top: 40px;">
                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                             <#list kePuBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                            <p>${kePuBook.name}</p>
                                            <p class="p2"><span style="color: red;font-size: 20px">￥${kePuBook.price/100}</span></p>
                                        </a>
                                    </div>
                                    <#else>
                                     <div class="col-md-3 col-sm-6">
                                         <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                             <#list kePuBook.imgurl?split(",") as fileNameUrl>
                                                 <#if fileNameUrl_index==0>
                                                        <a href="${rc.contextPath}/showBook/${kePuBook.id}.do">
                                                            <img src="${fileNameUrl}">
                                                        </a>
                                                 </#if>
                                             </#list>
                                             <p>${kePuBook.name}</p>
                                             <p class="p2"><span style="color: red;font-size: 20px">￥${kePuBook.price/100}</span></p>
                                         </a>
                                     </div>
                                </#if>
                             </#list>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <hr style="width: 90%;background-color: #1e9fff;margin-left: 100px;">

    <#include "footer.ftl">
    <script type="text/javascript">
        function startmarquee(lh, speed, delay, index) {
            var t;
            var p = false;
            var o = document.getElementById("marqueebox" + index);
            o.innerHTML += o.innerHTML;
            o.onmouseover = function () {
                p = true
            }
            o.onmouseout = function () {
                p = false
            }
            o.scrollTop = 0;

            function start() {
                t = setInterval(scrolling, speed);
                if (!p) {
                    o.scrollTop += 1;
                }
            }

            function scrolling() {
                if (o.scrollTop % lh != 0) {
                    o.scrollTop += 1;
                    if (o.scrollTop >= o.scrollHeight / 2) o.scrollTop = 0;
                } else {
                    clearInterval(t);
                    setTimeout(start, delay);
                }
            }

            setTimeout(start, delay);
        }

        startmarquee(25, 40, 1000, 1);
        startmarquee(25, 50, 1000, 2);
        startmarquee(25, 60, 1000, 3);
        startmarquee(25, 30, 1000, 4);
    </script>
</body>
<script src="${rc.contextPath}/js/amazeui.min.js" charset="utf-8"></script>

<script src="${rc.contextPath}/js/custom_up_img.js" charset="utf-8"></script>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>

<script>
    layui.use('upload', function() {
        var $ = layui.jquery
                , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            url: '${rc.contextPath}/admin/upload/bookimg.do',
            accept:'images',
            size: 6000,
            //只允许上传jpg/png图片
            exts: 'jpg|png',
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                if (res.code == 0) { //上传成功
                    var name = res.filename;
                    $("#header_img").attr("src", name);
                    $("#doc-modal-1").css("display", "none");
                    return layer.msg('修改头像成功',{icon:1}); //删除文件队列已经上传成功的文件
                }
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
                ,form = layui.form;
        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '952px'
            ,height: '454px'
            ,anim:'fade'
            ,interval: 2000
        });
    });
</script>
</html>
