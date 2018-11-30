<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>layui</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
		<script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js"></script>
		<script type="text/javascript">
			$(function() {

				// 一级分类的操作
				$.extend({
					'upd': function(param1,param2,param3) {
						$("#fcupage").css("display", "block");
                        $("#show_class_div").css("opacity","0.5");
                        $("#fcinput").attr("value", param1);
                        $("#update_class_pid").attr('value', param2);
                        $("#update_class_id").attr("value", param3);
                        console.log("参数1:"+param1);
                        console.log("参数2:" + param2);
                        console.log("参数3:"+param3);
					}
				});
				$.extend({
					'del': function(param1) {
						layer.confirm('确定删除？', {
							btn: ['确定', '取消'] //按钮
						}, function() {
                            console.log("参数1:" + param1);
                            $.post("${rc.contextPath}/admin/category/deletecategory.do",{"id":param1},function (data,status) {
								if(data.status===200) {
                                    layer.msg(data.msg,{icon: 1});
                                    setInterval(function () {
                                        window.location.reload();
                                    }, 2000);
                                }
                                if(data.status===400){
                                    layer.msg(data.msg,{icon: 2});
								}
                            });
						}, function() {
							layer.msg('取消成功');
						});
					}
				});
				$.extend({
					'fcgiveup': function() {
						$("#fcupage").css("display", "none");
                        $("#show_class_div").css("opacity","1")
					}
				});

			});
		</script>
	</head>

	<body>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>查看分类</legend>
		</fieldset>
		<div id="show_class_div" style="padding: 20px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space15">
				<#if allBookCategory??>
				    <#list allBookCategory as bookCategory>
				        <div class="layui-col-md6">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <div class="layui-row">
                                        <div class="layui-col-md10">${bookCategory.name}</div>
                                        <div class="layui-col-md2">
                                            <button class="layui-btn layui-btn-normal layui-btn-sm" onclick="$.upd('${bookCategory.name}','${bookCategory.pid}','${bookCategory.id}')"><i class="layui-icon"></i></button>
                                            <button class="layui-btn layui-btn-normal layui-btn-sm" onclick="$.del('${bookCategory.id}')"><i class="layui-icon"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                                    <ul class="layui-tab-title">
										<#if bookCategory.categoryList??>
										    <#list bookCategory.categoryList as childCategory>
										         <li>
                                                     ${childCategory.name}
                                                     <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="$.upd('${childCategory.name}','${childCategory.pid}','${childCategory.id}')"><i class="layui-icon"></i></button>
                                                     <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="$.del('${childCategory.id}')"><i class="layui-icon"></i></button>
                                                 </li>
										    </#list>
										</#if>

                                    </ul>
                                </div>
                            </div>
                        </div>
				    </#list>
				</#if>
			</div>
		</div>

		<!-- 修改分类的页面-->
		<div id="fcupage" class="layui-container" style="position: absolute;left: 683px;top: 243px;background: #d2d2d2;z-index: 10;width: 400px;height: 300px;border: 1px solid;border-color: lightseagreen;display: none;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
				<legend>修改分类</legend>
			</fieldset>
            <form class="layui-form" id="upd_class_form" action="${rc.contextPath}/admin/category/updatecategory.do" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">输入:</label>
					<div class="layui-input-block">
						<input type="text" hidden  id="update_class_id" required lay-verify="required" name="id" value="" />
						<input type="text" hidden id="update_class_pid" required lay-verify="required" name="pid" value="">
						<input id="fcinput" type="text" name="name" lay-verify="title" required lay-verify="required" autocomplete="off" placeholder="请输入分类名" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-normal" lay-submit lay-filter="demo2">修改</button>
						<button class="layui-btn layui-btn-primary id="upd_giveup">放弃</button>
					</div>
				</div>
            </form>
		</div>

		<script src="${rc.contextPath}/houtai/assets/layui.all.js" charset="utf-8"></script>
		<script>
			layui.use(['element', 'layer'], function() {
				var element = layui.element;
				var layer = layui.layer;
				//监听折叠
				element.on('collapse(test)', function(data) {
					layer.msg('展开状态：' + data.show);
				});
			});
			$(document).ready(function () {
				$("#upd_giveup").click(function (event) {
                    $("#fcupage").css("display", "none");
                    $("#show_class_div").css("opacity", "1");
                    event.preventDefault();
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
                    content: function(value) {
                        layedit.sync(editIndex);
                    }
                });
                //监听提交
                form.on('submit(demo2)', function(data) {
                    /*layer.msg(JSON.stringify(data.field));*/
                    var categoryName=$("#fcinput").val();
                    var bool = false;
                    $.post("${rc.contextPath}/admin/category/checkcategoryname.do",{"child":categoryName},function (data,status) {
                        if (data.status===400) {
                            layer.msg(data.msg,{icon: 5});
                            bool= false;
                        }
                        if (data.status===200) {
                            $('#upd_class_form').submit();
                        }
                    });
                    return bool;
                });
            });
        </script>

	</body>

</html>