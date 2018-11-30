<!DOCTYPE html>
<html>
<head>
</head>
<style>

    .input1 {
        margin-left: 750px;
        width: 300px;
        height: 35px;
        border: 1px solid #FF0099;
        outline: none
    }

    .button1 {
        width: 100px;
        height: 35px;
        background-color: #FF0099;
        border: none;
    }
</style>

<body>

<div class="search">
    <img class="yuan" src="../img/200.png">
    <span style="position: relative;left: 100px;font-size: 40px;color: blue;">购物车</span>
</div>
<div id="div1">
			<span id="span1">
                <form action="/retrieve.do" id="search_form" method="get">
                    <input class="input1" type="submit" placeholder="小伙伴,搜索你想要的书籍吧!"/><button class="button1">搜索</button>
                </form>
			 </span>
</div>
</body>

</html>