<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/demo.css">
    <link rel="stylesheet" href="${rc.contextPath}/css/jquery-ui.css">
    <script src="${rc.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${rc.contextPath}/js/jquery-ui.js"></script>
</head>

<style>
    .ui-menu-item-wrapper{
        width: 565px;
        text-align: left;
    }
</style>
<body>

<div class="search" style=" margin-left: 290px;
">
    <a href="/index.do">
        <img src="${rc.contextPath}/img/yitao.jpg" class="yuandan">
    </a>
    <div class="search-center">
        <form action="/retrieve.do" id="search_form" method="get">
            <input type="text" id="searche_auto" name="keyword" placeholder="小伙伴,搜索你想要的书籍吧!">
        <#-- <div class="select">
             <span>全部分类<b></b></span>
             <ul class="selectList">
                 <li>全部分类</li>
                 <#if childBookCategory??>
                     <#list childBookCategory as childCategory>
                           <li>
                               <a href="#">${childCategory.name}</a>
                           </li>
                     </#list>
                 </#if>
             </ul>
         </div>-->
            <input type="submit" name="sub">
            <input type="buttom" id="search_btn" name="btn" style="width: 53px;">
        </form>
    </div>
    <div class="search-right rightcart">

        <a class="left" target="_self" style="height: 38px;width: 110px;"
           href="${rc.contextPath}/selectCart.do">购物车<span><#if mycartSize??>${mycartSize}</#if></span></a>

        <a class="" style="padding-left: 24px;" href="${rc.contextPath}/myorder.do">我的订单</a>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#search_btn").click(function () {
            $("#search_form").submit();
        });
    })
</script>

<script>
    $(function () {
        /* var availableTags = [
             "ActionScript",
             "AppleScript",
             "Asp",
             "BASIC",
             "C",
             "C++",
             "Clojure",
             "COBOL",
             "ColdFusion",
             "Erlang",
             "Fortran",
             "Groovy",
             "Haskell",
             "Java",
             "JavaScript",
             "Lisp",
             "Perl",
             "PHP",
             "Python",
             "Ruby",
             "Scala",
             "Scheme"
         ];*/
        $("#searche_auto").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/autocomplete.do",
                    type: "POST",
                    dataType: "json",
                    data: {"keywords": $("#searche_auto").val()},
                    cache: true,
                    async: true,
                    success: function (data) {
                        console.log(data);
                        /*  response($.map(data.bookName, function (item) {
                              return {
                                  label: item.label,
                                  value: item.value
                              }
                          }));*/
                        var autoKeywords = [];
                        for (let i = 0; i < data.data.length; i++) {
                            autoKeywords[i] = data.data[i].bookName;
                        }
                        response($.map(autoKeywords, function (item) {
                            console.log("item====" + item);
                            /*return matcher.test( item );*/
                            return {
                                label: item.label,
                                value: item
                            }
                        }));
                    }
                });
            },
            delay: 500
        });
    });
</script>

</html>