<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
        <script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
		<title></title>
	</head>

	<body>

		<body class="layui-view-body">
			<div class="layui-content">
				<div class="layui-page-header">
					<div class="pagewrap">
						<span class="layui-breadcrumb">
                   <a href="">网站内容管理</a>
                  <a><cite>广告管理</cite></a>
               </span>
					</div>
				</div>
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
					<legend>查询广告</legend>
				</fieldset>
				<div class="layui-row">
					<div class="layui-card">
						<div class="layui-card-body">
							<form class="layui-form" action="${rc.contextPath}/admin/ads/queryads.do" method="get">
								<div class="form-box">
									<div class="layui-form-item">
										<div class="layui-inline">
											<div class="layui-form-mid">标题:</div>
											<div class="layui-input-inline" style="width: 100px;">
												<input type="text" name="headline" autocomplete="off" class="layui-input">
											</div>
                                            <div class="layui-form-mid">副标题:</div>
                                            <div class="layui-input-inline" style="width: 100px;">
                                                <input type="text" name="subhead" autocomplete="off" class="layui-input">
                                            </div>
                                            <div class="layui-form-mid">创建时间:</div>
                                            <div class="layui-input-inline" style="width: 100px;">
                                                <input type="text" name="createAdsDate"
                                                       value=""
                                                       class="layui-input" id="create_time">
                                            </div>
											<button class="layui-btn layui-btn-normal" lay-submit lay-filter="querybooks">查询</button>
											<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										</div>
									</div>
									<table id="demo_ads" lay-filter="ads"></table>
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
				var element = layui.element;
				var table = layui.table;
				var form = layui.form;
				var ads=${listDimAds};
				//展示已知数据
				table.render({
					elem: '#demo_ads',
					toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
						,
					totalRow: true //开启合计行
						,
					cols: [
						[ //标题栏
							{
								type: 'checkbox',
								fixed: 'left'
							}, {
								field: 'id',
								title: 'ID',
								width: 80,
								fixed: 'left'
							}, {
								field: 'headline',
								title: '广告标题',
								width: 200
							},
							{
								field: 'subhead',
								title: '副标题',
								width: 200
							},
                            {
                                field: 'createDate',
                                title: '创建时间',
                                width: 200
                            },
                            {
                                field: 'link',
                                title: '链接地址',
                                width: 500
                            }, {
								field: 'url',
								title: '图片',
							    templet: '<div><img src="{{d.url}}"></div>',style:'line-height:48px!important;' //自定义显示图片方法
							}, {
								fixed: 'right',
								width: 200,
								align: 'center',
								toolbar: '#barDemo'
							}
						]
					],
					data: ads,
                    done:function(res,curr,count){
                        hoverOpenImg();//显示大图
                        $('table tr').on('click',function(){
                            $('table tr').css('background','');
                            $(this).css('background','<%=PropKit.use("config.properties").get("table_color")%>');
                        });
                    },
					skin: 'row' //表格风格
						,
					even: true,
					page: true,
					limits: [5, 10, 15],
					limit: 5 //每页默认显示的数量
				});
				layui.use('laydate', function() {
					var laydate = layui.laydate;
					//执行一个laydate实例
					laydate.render({
						elem: '#publication_time' //指定元素
					});
				});

				//监听头工具栏事件
				table.on('toolbar(ads)', function(obj) {
					var checkStatus = table.checkStatus(obj.config.id),
						data = checkStatus.data; //获取选中的数据
					switch(obj.event) {
						case 'add':
							layer.msg('添加');
							break;
						case 'update':
							if(data.length === 0) {
								layer.msg('请选择一行');
							} else if(data.length > 1) {
								layer.msg('只能同时编辑一个');
							} else {
                                console.log(obj.tr);
                                layer.open({
                                    title: '编辑书籍',
                                    type: 2,
                                    area: ['100%', '100%'],
                                    content: ['${rc.contextPath}/admin/ads/toeditad.do' + "?id=" + data.id, 'no'],
                                    yes: function(index, layero) {
                                        //do something
                                        console.log(layero);
                                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                                    }
                                });
							}
							break;
						case 'delete':
							if(data.length === 0) {
								layer.msg('请选择一行');
							} else {
								layer.msg('删除');
                                var checkStatus = table.checkStatus('demo_ads'), data = checkStatus.data;
                                console.log(data);
                                for (data_index in data ){
                                    //向服务端发送删除指令
                                    $.post("${rc.contextPath}/admin/ads/removeads.do", {id: data[data_index].id}, function (data, status) {
                                        if (data.status == 200) {
                                            console.log(obj.data);
                                            layer.msg('删除成功',{icon:1});
                                            setTimeout(function () {
                                                window.location.reload();
                                            }, 1000);
                                        }
                                        if (data.status == 400) {
                                            layer.msg('参数异常',{icon: 5});
                                        }
                                    });
                                    console.log(data[data_index]);
                                }

							}
							break;
					};
				});
				//监听工具条
				table.on('tool(ads)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data; //获得当前行数据
					var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var tr = obj.tr; //获得当前行 tr 的DOM对象

					if(layEvent === 'detail') { //查看
						alert("查看");
						//do somehing
					} else if(layEvent === 'del') { //删除
						layer.confirm('真的删除行么', function(index) {
							obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
							layer.close(index);
                            //向服务端发送删除指令
                            $.post("${rc.contextPath}/admin/ads/removeads.do", {id: data.id}, function (data, status) {
                                if (data.status == 200) {
                                    layer.msg('删除成功',{icon:1});
                                    console.log(obj);
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 2000);
                                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                }
                                if (data.status == 400) {
                                    layer.msg('参数异常',{icon:5});
                                }
                            });
						});
					} else if(layEvent === 'edit') { //编辑
						//window.location = "book_add.ftl";
						//do something
						console.log(obj.tr);
						layer.open({
							title: '编辑书籍',
							type: 2,
							area: ['100%', '100%'],
							content: ['${rc.contextPath}/admin/ads/toeditad.do' + "?id=" + data.id, 'no'],
							yes: function(index, layero) {
								//do something
								console.log(layero);
								layer.close(index); //如果设定了yes回调，需进行手工关闭
							}
						});
					}
				});
                layui.use('laydate', function () {
                    var laydate = layui.laydate;
                    //执行一个laydate实例
                    laydate.render({
                        elem: '#create_time' //指定元素
                    });
                });
                function hoverOpenImg() {
                    var img_show = null; // tips提示
                    $('td img').hover(
                        function () {
							var kd = $(this).width();
							kd1 = kd * 5;          //图片放大倍数
							kd2 = kd * 5 + 30;       //图片放大倍数
							var img = "<img class='img_msg' src='" + $(this).attr('src') + "' style='width:" + kd1 + "px;' />";
							img_show = layer.tips(img, this, {
								tips: [2, 'rgba(41,41,41,.5)']
								, area: [kd2 + 'px']
							});
                    }, function () {
                        layer.close(img_show);
                    });
                }

			</script>

            <script type="text/javascript">
				$(document).ready(function () {
					function flush_page() {
                        window.location.reload();
                    }
                });
			</script>

		</body>

</html>