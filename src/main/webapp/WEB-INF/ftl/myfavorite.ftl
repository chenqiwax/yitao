<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的收藏</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../layui/layui.all.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<script>

    /*取消收藏*/
    function All(id) {
        $.post("/updateUserCollection.do", {"id": id}, function (data, status) {
            if (data != "0") {
                layer.msg("取消成功",{icon:1});
                setTimeout(function () {
                    window.location.reload();
                }, 3000);
            } else {
                layer.msg("取消失败,已经取消该书籍或你");
            }
            window.location = "myfavcenter.do";
        })
    }

    /*添加收藏*/
    function AddCollection(id) {
        $.post("/addCollection.do", {"id": id}, function (data, status) {
            if (data != "0") {
                layer.msg("收藏成功",{icon:1});
                setTimeout(function () {
                    window.location.reload();
                }, 3000);
            } else {
                layer.msg("收藏失败,已经收藏该书籍");
            }
            window.location = "myfavcenter.do";
        })
    }
</script>


<body>

<#include "header.ftl">
<#include "search.ftl">
<div style="margin-top: 50px;">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <ul class="nav navbar-nav" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#tishi"><img src=""/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#quanbu">全部宝贝</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#shixiao">失效宝贝</a>
                </li>
            </ul>
        </nav>

        <!-- Tab panes -->
        <div class="tab-content">
            <#--<div class="container tab-pane active" style="position: relative;top: 151px;left: 290px;font-family: 楷体;"><br>
                <h2>欢迎来到收藏界面</h2>
                <p style="font-size: 25px">无论商品是否有无,不管从前还是未来,它们永远都在这里,像永恒的战士一般,坚毅不屈!!!</p>
                <a href="#" style="text-decoration: none;color: white;font-size: 25px">快去收藏你喜爱的书籍吧!</a>
            </div>-->
            <div id="quanbu" class="container-fluid tab-pane active "><br>
                <div class="card">
                    <div class="card-body form-inline" id="div1">
                         <#if collectionList??>

                            <#list collectionList as userCollection>

                                <div style="width: 245px;">
                                    <input id="bookid" type="hidden" value="${userCollection.book.id}">
                                    <#list userCollection.book.imgurl?split(",") as imgurl>
                                        <#if imgurl_index == 0>
                                            <a href="${rc.contextPath}/showBook/${userCollection.book.id}.do"><img src="${imgurl}" style="width: 160px;height: 195px;"/></a>
                                        </#if>
                                    </#list>

                                    <p>
                                        <small>书名:&nbsp;&nbsp;&nbsp;<a href="${rc.contextPath}/showBook/${userCollection.book.id}.do">${userCollection.book.name}</a></small>
                                    </p>
                                    <p>价格:&nbsp;&nbsp;&nbsp;<span style="color: red;font-size: 20px">￥${userCollection.book.price/100}</span></p>
                                    <button id="buttons" onclick="All(${userCollection.book.id})"
                                            name="id" value="${userCollection.book.id}"
                                            style="border-radius: 25px;background-color: #1e9fff;color: white;width: 100px;height: 30px;border-color: #1e9fff;">
                                        取消收藏
                                    </button>
                                </div>
                            </#list>
                         <#else >
                              <h2>你没有收藏书籍</h2>
                         </#if>
                    </div>
                </div>
            </div>


            <div id="shixiao" class="container-fluid tab-pane fade"><br>
                <div class="card">
                    <div class="card-body form-inline">
                        <#if collectionLists??>
                            <#list collectionLists as userCollections>

                                <div style="width: 177px;">
                                    <#list userCollections.book.imgurl?split(",") as imgurl>
                                        <#if imgurl_index == 0>
                                            <a href="${rc.contextPath}/showBook/${userCollections.book.id}.do"><img src="${imgurl}" style="width: 160px;height: 195px;"/></a>
                                        </#if>
                                    </#list>

                                    <p>
                                        <small>书名:&nbsp;&nbsp;&nbsp;<a href="${rc.contextPath}/showBook/${userCollections.book.id}.do">${userCollections.book.name}</a></small>
                                    </p>
                                    <p>价格:&nbsp;&nbsp;&nbsp;<span style="color: red;font-size: 20px">￥${userCollections.book.price/100}</span></p>
                                    <button id="buts" onclick="AddCollection(${userCollections.book.id})" name="id"
                                            style="border-radius: 25px;background-color: #1e9fff;color: white;width: 100px;height: 30px;border-color: #1e9fff;"
                                            value="${userCollections.book.id}">添加收藏
                                    </button>
                                </div>
                            </#list>
                        <#else >
                              <h2>你没有取消收藏书籍</h2>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div style="margin-top: 200px;margin-left: 220px">
<#include "bottom.ftl">
</div>
</body>

</html>