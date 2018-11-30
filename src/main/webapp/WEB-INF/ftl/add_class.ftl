<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<title>添加分类</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
	</head>

	<body>
		<div class="layui-page-header">
			<div class="pagewrap">
				<span class="layui-breadcrumb">
                  <a href="">网站内容管理</a>
                   <a href="">内容分类管理</a>
                  <a><cite>添加分类</cite></a>
               </span>
			</div>
		</div>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>添加分类</legend>
		</fieldset>
		<div class="layui-container">
			<div class="layui-row">
				<div class="layui-col-xs6 layui-col-md3">
					<div class="grid-demo grid-demo-bg1" style="float: right;">
						<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo">
							<li class="layui-nav-item">
								<a href="javascript:;">请选择父分类</a>
								<dl class="layui-nav-child">
									<#if parentBookcategory??>
										<#list parentBookcategory as category>
											<li class="layui-nav-item">
                                                <a href="javascript:;">${category.name}</a>
                                            </li>
										</#list>
									</#if>
								</dl>
							</li>
						</ul>
					</div>
				</div>
				<div class="layui-col-xs6 layui-col-md8">
					<div class="grid-demo">
						<form class="layui-form" id="add_class_form" action="${rc.contextPath}/admin/category/addbookcategory.do" method="post">
							<div class="layui-form-item">
								<input type="text" hidden lay-verify="parent_calss" id="parent_value" value="" name="parent" />
								<label class="layui-form-label">分类名:</label>
								<div class="layui-inline">
									<input type="text" name="child" id="secondCategory" lay-verify="title" required lay-verify="required" autocomplete="off" placeholder="请输入分类名" class="layui-input">
								</div>
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="demo1">立即添加</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
	<script>
		layui.use('element', function() {
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
			//监听导航点击
			element.on('nav(demo)', function(elem) {
				console.log(elem)
				if (elem.text()==="请选择父分类") {
                    layer.msg("添加一级分类");
                    $("#secondCategory").attr("placeholder", "请输入一级分类名");
                }else {
                    layer.msg("添加二级分类");
                    $("#secondCategory").attr("placeholder", "请输入二级分类名");
                }
				$("#parent_value").val(elem.text());
			});
		});
	</script>
	<script>
		//Demo
		layui.use('form', function() {
			var form = layui.form;

			//自定义验证规则
			form.verify({
				title: function(value) {
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
						return '分类名不能有特殊字符';
					}
					if(/(^\_)|(\__)|(\_+$)/.test(value)) {
						return '分类名首尾不能出现下划线\'_\'';
					}
					if(/^\d+\d+\d$/.test(value)) {
						return '分类名不能全为数字';
					}
				},
				parent_calss: function(value) {
					if(value.length <= 0) {
						return '请选择分类';
					}
				},
				content: function(value) {
					layedit.sync(editIndex);
				}
			});
			//监听提交
			form.on('submit(demo1)', function(data) {
				/*layer.msg(JSON.stringify(data.field));*/
                var categoryName=$("#secondCategory").val();
                var bool = false;
				$.post("${rc.contextPath}/admin/category/checkcategoryname.do",{"child":categoryName},function (data,status) {
                    if (data.status===400) {
                        layer.msg(data.msg,{icon: 5});
                        bool= false;
                    }
                    if (data.status===200) {
                        $('#add_class_form').submit();
                    }
                });
				return bool;
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem: '#publication_time' //指定元素
			});
		});
	</script>

</html>