<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<script src="${rc.contextPath}/houtai/js/jquery-3.3.1.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css" media="all">
		<title></title>
	</head>

	<body>
		<div class="layui-card">
			<div class="layui-page-header">
				<div class="pagewrap">
					<span class="layui-breadcrumb">
                  <a href="">网站内容管理</a>
                  <a><cite>广告管理</cite></a>
               </span>
				</div>
			</div>
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>编辑广告</legend>
			</fieldset>
			<div class="layui-card-body">
				<form class="layui-form" action="${rc.contextPath}/admin/ads/updateads.do" method="post">
                    <input type="text" hidden  name="id" required lay-verify="required" layui-input-inline value="<#if adsById.id??>${adsById.id}</#if>">
                    <input type="text" hidden name="url" lay-verify="ads_url_lay" required lay-verify="required" id="ads_url" value="<#if adsById.url??>${adsById.url}</#if>" layui-input-inline>
					<div class="layui-form-item">
						<label class="layui-form-label">广告标题:</label>
						<div class="layui-input-inline">
							<input type="text" value="<#if adsById.headline??>${adsById.headline}</#if>" class="layui-input"   name="headline" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">副标题:</label>
						<div class="layui-input-inline">
							<input type="text" value="<#if adsById.subhead??>${adsById.subhead}</#if>" class="layui-input"  name="subhead" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">链接地址:</label>
						<div class="layui-input-inline">
							<input type="text" value="<#if adsById.link??>${adsById.link}</#if>" class="layui-input" lay-verify="http_url"  required lay-verify="required" name="link" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">广告图片:</label>
						<div class="layui-upload-drag" id="test10">
							<i class="layui-icon"></i>
							<p>点击上传，或将文件拖拽到此处</p>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemoAds">添加</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script src="${rc.contextPath}/houtai/assets/layui.all.js"></script>
	<script type="text/javascript">
		layui.use('upload', function() {
			var $ = layui.jquery,
				upload = layui.upload;
			//拖拽上传
			upload.render({
				elem: '#test10',
                accept:'images',
                multiple:false,
				url: '/admin/upload/bookimg.do',
				size: '6000',
				exts: 'jpg|png',
				done: function(res) {
                    var name = res.filename;
                    var oldnameurl = $("#ads_url").val(name);
                    layer.msg("上传成功",{icon: 1});
					console.log(res);
				},
                error: function(index, upload){
                    layer.msg("上传失败",{icon: 5});
                }
			});
		});
		layui.use('form',function () {
			var form=layui.form;
			form.verify({
                http_url:function (value) {
					if (!(/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/).test(value)){
					    return "请输入正确的链接地址!!!"
					}
                },
                ads_url_lay: function (value) {
                    if (value === '') {
                        return "请上传广告图片!!!";
                    }
                }
			});
            //监听提交
            form.on('submit(formDemoAds)', function (data) {
                /*layer.msg(JSON.stringify(data.field));*/
                return true;
            });
        });
	</script>

</html>