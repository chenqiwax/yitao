<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css">
    <script type="text/javascript" src="${rc.contextPath}/houtai/js/jquery-3.3.1.js"></script>
    <title>管理后台</title>
</head>

<body class="layui-view-body">
<div class="layui-content">
    <div class="layui-page-header">
        <div class="pagewrap">
					<span class="layui-breadcrumb">
                  <a href="">书籍管理</a>
                  <a><cite>查询书籍</cite></a>
               </span>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>查询书籍</legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form" action="${rc.contextPath}/admin/booklist.do" method="get">
                    <div class="form-box">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-form-mid">书籍名:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <input type="text" name="name" id="book_query_name" autocomplete="off"
                                           value="<#if book.name??>${book.name}</#if>"
                                           class="layui-input">
                                </div>
                                <div class="layui-form-mid">出版社:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <input type="text" name="press" id="book_query_press" autocomplete="off"
                                           value="<#if book.press??>${book.press}</#if>"
                                           class="layui-input">
                                </div>
                                <div class="layui-form-mid">作者:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <input type="text" name="author" id="book_query_author" autocomplete="off"
                                           value="<#if book.author??>${book.author}</#if>"
                                           class="layui-input">
                                </div>
                                <div class="layui-form-mid">出版时间:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <input type="text" name="bookPublishDate"
                                           value="<#if book.publishDate??>${book.publishDate?string("yyyy-MM-dd")}</#if>"
                                           class="layui-input" id="publication_time">
                                </div>
                                <button class="layui-btn layui-btn-normal" id="book_query" lay-submit
                                        lay-filter="querybooks">查询
                                </button>
                                <button type="reset" id="res_booklist" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                        <table id="demo" lay-filter="books"></table>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
<script type="text/html" id="barDemo">
    <#--<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>-->
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{# if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{# } }}
</script>
<script>
    $.ready(function () {
        $("#book_query").click(function () {
            var name = $("#book_query_name").val();
            var press = $("#book_query_press").val();
            var author = $("#book_query_author").val();
            var date = $("#book_query_date").val();
            console.log(name + ' ==' + press + "==" + author + "==" + date);
        });
    });
</script>
<script>
    var element = layui.element;
    var table = layui.table;
    var form = layui.form;
    var books =${booklist};
    console.log(books);
    //展示已知数据
    table.render({
        elem: '#demo',
        toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,
        totalRow: true //开启合计行
        ,
        skin: 'row' //表格风格
        ,
        even: true,
        cols: [
            [ //标题栏
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                field: 'id',
                title: 'ID',
                width: 80,
                sort: true,
                fixed: 'left',
                totalRowText: '合计：'
            }, {
                field: 'name',
                title: '书籍名',
                width: 600
            }, {
                field: 'author',
                title: '作者',
                width: 80
            }, {
                field: 'press',
                title: '出版社',
                width: 150
            }, {
                field: 'publishDate',
                title: '出版日期',
                width: 150
            }, {
                field: 'sales',
                title: '销售量',
                width: 80,
                sort: true,
                totalRow: true
            }, {
                field: 'stock',
                title: '库存',
                width: 80,
                sort: true,
                totalRow: true
            }, {
                field: 'price',
                title: '价格',
                width: 80,
                sort: true,
                totalRow: true
            }, {
                field: 'paper',
                title: '纸张类型',
                width: 120,
            }, {
                field: 'categoryName',
                title: '分类名',
                width: 120,
            }, {
                fixed: 'right',
                width: 200,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ],
        data: books,
        page: true,
        limits: [5, 10, 15],
        limit: 10 //每页默认显示的数量
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#publication_time' //指定元素
        });
    });

    //监听头工具栏事件
    table.on('toolbar(books)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id),
                data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                window.location.href = "${rc.contextPath}/admin/bookadd.do";
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：' + checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    var checkStatus = table.checkStatus('demo'), data = checkStatus.data;
                    console.log(data);
                    for (data_index in data ){
                        //向服务端发送删除指令
                        $.post("${rc.contextPath}/admin/reomveBook.do", {id: data[data_index].id}, function (data, status) {
                            if (data.code == 200) {
                                console.log(obj.data);
                                layer.msg('删除成功',{time: 5000});

                                window.location.reload();
                            }
                            if (data.code == 400) {
                                layer.msg('参数异常');
                            }
                        });
                        console.log(data[data_index]);
                    }
                }
                break;
        }
        ;
    });
    //监听工具条
    table.on('tool(books)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'detail') { //查看
            alert("查看");
            //do somehing
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                console.log(data.id);
                console.log(data);
                layer.close(index);
                //向服务端发送删除指令
                $.post("${rc.contextPath}/admin/reomveBook.do", {id: data.id}, function (data, status) {
                    if (data.code == 200) {
                        layer.msg('删除成功');
                        console.log(obj);
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    }
                    if (data.code == 400) {
                        layer.msg('参数异常');
                    }
                });
            });
        } else if (layEvent === 'edit') { //编辑
            //window.location = "book_add.ftl";
            //do something
            console.log(obj.tr);
            layer.open({
                title: '编辑书籍',
                type: 2,
                area: ['100%', '100%'],
                content: [
                    '${rc.contextPath}/admin/editbook.do'
                    + "?id=" + data.id
                  ],
                success: function(layero, index){
                    console.log(layero, index);
                },
                yes: function (index, layero) {
                    //do something
                    console.log(layero);
                    var  name_vaue=$(layero).find("form").val();
                    console.log("name========" + name_vaue);
                   /* var body = layui.layer.getChildFrame('body', index);*/
                   /* var stock=body.find(".stock").val();*/
                   /* console.log("库存====" + stock);
                    console.log("书籍编辑的表单=======" + edit_book.length);*/
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                },
                cancel: function(index, layero){
                    if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index);
                    }
                    return false;
                }
            });

        }
    });
</script>
<script>
    $(document).ready(function () {
        function flush_page(){
            window.location.reload();
        }
        $("#res_booklist").click(function () {
            window.location.href = "/admin/booklist.do";
        });
    });
</script>

</body>

</html>