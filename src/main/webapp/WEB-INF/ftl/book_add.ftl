<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/admin.css">
    <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>

    <title></title>
</head>

<body>
<div class="layui-card">
    <div class="layui-page-header">
        <div class="pagewrap">
					<span class="layui-breadcrumb">
                  <a href="">书籍管理</a>
                  <a><cite>添加书籍</cite></a>
               </span>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>添加书籍</legend>
        <div class="layui-card-body">
            <form class="layui-form" action="/admin/addbook.do" method="post">
                <input type="text" hidden  name="imgurl" lay-verify="book_url_lay" required lay-verify="required" id="book_url" value="" layui-input-inline>
                <div class="layui-form-item">
                    <label class="layui-form-label">请选择分类</label>
                    <div class="layui-input-inline">
                        <select name="lid" lay-verify="required" lay-search="">
                            <option value="">直接选择</option>
                            <#if childBookCategory??>
                                <#list childBookCategory as category>
                                    <option value="${category.id}">${category.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" required lay-verify="required" placeholder="请输入书籍名"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">作者:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="author" required lay-verify="required" placeholder="请输入作者"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">出版社:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="press" required lay-verify="required" placeholder="请输入作者"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="number" name="bookPrice" placeholder="￥" lay-verify="price" required
                               lay-verify="required" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">出版时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="bookPublishDate" placeholder="请选择出版时间" required lay-verify="required" class="layui-input" id="publication_time">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍库存:</label>
                    <div class="layui-input-inline">
                        <input type="number" name="stock" placeholder="" lay-verify="stock" required
                               lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍页数:</label>
                    <div class="layui-input-inline">
                        <input type="number" name="pageNum" lay-verify="stock" required lay-verify="required"
                               placeholder="请输入页数" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍字数:</label>
                    <div class="layui-input-inline">
                        <input type="number" name="wordsNum" lay-verify="stock" required lay-verify="required"
                               placeholder="请输入字数" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">纸张类型:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="paper" required lay-verify="required" placeholder="请输入纸张类型"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍描述:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="desc" required lay-verify="required" placeholder="请输入书籍详情"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书籍图片:</label>
                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-normal" id="testList">选择图片</button>
                        <div class="layui-upload-list">
                            <table class="layui-table" style="margin-left:110px;width: 652px;">
                                <thead>
                                <tr>
                                    <th>文件名</th>
                                    <th>大小</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="demoList"></tbody>
                            </table>
                        </div>
                        <button type="button" style="margin-left: 110px;" class="layui-btn layui-btn-normal" id="testListAction">开始上传
                        </button>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品详情:</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="sf" name="details" width="800px"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">添加</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
</div>
</body>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;
        //自定义验证规则
        form.verify({
            stock: function (value) {
                if (value <= 0) {
                    return '必须大于0啊';
                }
                if (!(/^[-+]?\d*$/.test(value))) {
                    return '不能为小数';
                }
            },
            discount: function (value) {
                if (value <= 0 || value > 10) {
                    return '必须大于0并且小于10';
                }
            },
            price: function (value) {
                if (value <= 0) {
                    return "必须大于0啊";
                }
            },
            content: function (value) {
                layedit.sync(editIndex);
            },
            book_url_lay: function (value) {
                if (value === '') {
                    return "请上传书籍图片,最少一张";
                }
            }
        });
        //监听提交
        form.on('submit(formDemo)', function (data) {
           /* layer.msg(JSON.stringify(data.field));*/
            return true;
        });
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#publication_time' //指定元素
        });
    });
</script>
<link href="${rc.contextPath}/houtai/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
        src="${rc.contextPath}/houtai/js/kindeditor-4.1.10/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${rc.contextPath}/houtai/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
    KindEditor.create("#sf", { //创建 editor
        uploadJson: '/upload/uploadController', //设置文件上传coltroller路径
        filePostName: 'file', //字段名
        imageSizeLimit: '10MB', //每个文件大小的限制
        imageUploadLimit: 40 //每次可以上传多少个文件
    });
    //初始化kindeditor组件
    KindEditor.ready(function (K) {
        var editor = K.editor({
            allowFileManager: true,
            uploadJson: '/upload/uploadController', //后台文件上传处理的路径
            filePostName: 'file' //文件的字段名，一会看到后台代码时会解释怎么使用
        });

        K('#image3').click( //为文件上传按钮绑定事件
                function () {
                    editor.loadPlugin('image', function () { //加载文件上传插件
                        editor.plugin.imageDialog({
                            showRemote: false,
                            imageUrl: K('#url3').val(), //获取本地文件路径:如:c:/doc/ccc.png文件
                            clickFn: function (url, title, width, height,
                                               border, align) { //当上传文件成功时的回调函数
                                K('#url3').val(url); //将路径回显
                                editor.hideDialog(); //隐藏文件上传kuang
                            }
                        });
                    });
                });
    });
</script>

<script type="text/javascript">
    layui.use('upload', function () {
        var $ = layui.jquery,
                upload = layui.upload;
        //多文件列表示例
        var demoListView = $('#demoList'),
                uploadListIns = upload.render({
                    elem: '#testList',
                    url: '/admin/upload/bookimg.do',
                    accept: 'images',
                    multiple: true,
                    auto: false,
                    size: 6000,
                    //只允许上传jpg/png图片
                    exts: 'jpg|png',
                    bindAction: '#testListAction',
                    choose: function (obj) {
                        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                        //读取本地文件
                        obj.preview(function (index, file, result) {
                            var tr = $(['<tr id="upload-' + index + '">', '<td>' + file.name + '</td>', '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>', '<td>等待上传</td>', '<td>', '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>', '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>', '</td>', '</tr>'].join(''));

                            //单个重传
                            tr.find('.demo-reload').on('click', function () {
                                obj.upload(index, file);
                            });

                            //删除
                            tr.find('.demo-delete').on('click', function () {
                                delete files[index]; //删除对应的文件
                                tr.remove();
                                uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                            });

                            demoListView.append(tr);
                        });
                    },
                    done: function (res, index, upload) {
                        if (res.code == 0) { //上传成功
                            var name = res.filename;
                            var oldnameurl = $("#book_url").val();
                            $("#book_url").val(name + "," + oldnameurl);
                            var tr = demoListView.find('tr#upload-' + index),
                                    tds = tr.children();
                            tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                            tds.eq(3).html(''); //清空操作
                            return delete this.files[index]; //删除文件队列已经上传成功的文件
                        }
                        this.error(index, upload);
                    },
                    error: function (index, upload) {
                        var tr = demoListView.find('tr#upload-' + index),
                                tds = tr.children();
                        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                    }
                });
    });
</script>

</html>