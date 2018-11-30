<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/demo.css">
</head>
<style>
    #div1 {
        position: relative;
        left: 730px;
    }

    .input1 {
        width: 300px;
        height: 35px;
        border: 1px solid #1e9fff;
        outline: none;
        margin-left: 150px;
    }

    .button1 {
        width: 100px;
        height: 35px;
        background-color: #1e9fff;
        border: none;
    }
</style>

<body>

<div class="search">
    <a href="/index.do">
        <img src="${rc.contextPath}/img/yitao.jpg" class="yuan">
    </a>
    <span style="position: relative;left:100px;font-size:40px;color:#1E9FFF;top:-18px;">购物车</span>
</div>
<div id="div1">
			<span id="span1">
                 <form action="/retrieve.do" id="search_form" method="get">
			 	    <input class="input1" type="text" placeholder="小伙伴,搜索你想要的书籍吧!"/><button class="button1"><font
                         style="color: white;">搜索</font></button>
			    </form>
            </span>
</div>
</body>

</html>