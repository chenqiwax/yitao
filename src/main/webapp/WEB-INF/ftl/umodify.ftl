<!DOCTYPE html>
  <#include "unavigation.ftl">
<html>

<head>

    <meta charset="UTF-8">
    <link rel="shortcut icon" href="../img/logos.ico"/>
    <title>修改头像</title>
    <link rel="stylesheet" href="../css/message.css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <script src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script>

    <!-- 引用核心层插件 -->

    <#--<script type="text/javascript" src="../core/zyFile.js"></script>-->


    <!-- 引用初始化JS -->

    <#--<script type="text/javascript" src="../core/jq22.js"></script>-->
    <style type="text/css">
        .a {
            font-family: '黑体';
            font-size: 25px;
            color: #2AABD2;
        }
        #showImg{
            width: 98px;
            height: 118px;
         }
    </style>

</head>

<body>

<div style="display: inline-block;position: relative;left: 450px;top: -140px;">


    <div class="card">
        <div class="card-header">
            修改头像
        </div>
        <div class="card-body">
            <form enctype="multipart/form-data" action="/editPic.do" method="post" >
                <div class="container">
                    <label>
                        图片上传
                        <div style="width: 100px;height: 120px;border: 1px solid;" id="prvid" >

                        </div>
                    </label>
                    <div class="file-loading">
                        <input class="form-control" id="file-fr" name="userPic" type="file" onchange="previewImage(this, 'prvid')">
                    </div>
                    <!-- <hr style="border: 2px dotted">
                    <label>Spanish Input</label>
                        <div class="file-loading">
                            <input id="file-es" name="file-es[]" type="file" multiple>
                        </div> -->
                    <div>
                        <input type="submit" class="btn btn-success" value="提交"/>
                        <input type="reset" class="btn btn-danger" value="重置"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
<script type="text/javascript">
    function previewImage(file, prvid) {
        /* file：file控件
        * prvid: 图片预览容器
        */
        var tip = "Expect jpg or png or gif!"; // 设定提示信息
        var filters = {
            "jpeg" : "/9j/4",
            "gif" : "R0lGOD",
            "png" : "iVBORw"
        }
        var prvbox = document.getElementById(prvid);
        prvbox.innerHTML = "";
        if (window.FileReader) { // html5方案
            for (var i=0, f; f = file.files[i]; i++) {
                var fr = new FileReader();
                fr.onload = function(e) {
                    var src = e.target.result;
                    if (!validateImg(src)) {
                        alert(tip)
                    } else {
                        showPrvImg(src);
                    }
                }
                fr.readAsDataURL(f);
            }
        } else { // 降级处理

            if ( !/\.jpg$|\.png$|\.gif$/i.test(file.value) ) {
                alert(tip);
            } else {
                showPrvImg(file.value);
            }
        }

        function validateImg(data) {
            var pos = data.indexOf(",") + 1;
            for (var e in filters) {
                if (data.indexOf(filters[e]) === pos) {
                    return e;
                }
            }
            return null;
        }

        function showPrvImg(src) {
            var img = document.createElement("img");
            img.src = src;
            img.id = 'showImg';
            prvbox.appendChild(img);
        }
    }
</script>

</html>