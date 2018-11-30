<!DOCTYPE HTML>
<html>

<head>
    <title>删除页面</title>
    <meta charset="utf-8"/>
    <script>
        //定义函数myClose关闭当前窗口
        function myClose() {
            //将id为time的元素的内容转为整数，保存在变量n中
            var n = parseInt(time.innerHTML);
            n--; //将n-1
            //如果n==0,关闭页面
            //否则, 将n+秒钟后自动关闭，再保存回time的内容中
            if (n > 0) {
                time.innerHTML = n + "秒钟后自动关闭";
                timer = setTimeout(myClose, 1000);
            } else {
                location.href = 'userphone.do';
            }
        }

        var timer = null;
        //当页面加载后，启动周期性定时器，每个1秒执行myClose
        window.onload = function () {
            timer = setTimeout(myClose, 1000);
        }
    </script>
</head>

<body>
<div align="center" style="color: #06ed10;margin-top: 320px;">
    <span style="font-size: 30px;">修改成功,</span>
    <span id="time" style="font-size: 30px;">3秒钟后自动关闭</span>
</div>
</body>

</html>