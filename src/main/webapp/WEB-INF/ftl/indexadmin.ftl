<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/layui.css">
		<link rel="stylesheet" href="${rc.contextPath}/houtai/assets/css/admin.css">
		<link rel="icon" href="${rc.contextPath}/img/logos.ico">
		<title>管理后台</title>
	</head>

	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header custom-header">

				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item slide-sidebar" lay-unselect>
						<a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
					</li>
				</ul>

				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;">
							<img src="<#if nowUser??><#if nowUser.iconUrl??>${nowUser.iconUrl}</#if></#if>" class="layui-nav-img"> <#if nowUser??><#if nowUser.account??>${nowUser.account}</#if></#if>
						</a>
					<#--	<dl class="layui-nav-child">
							<dd>
								<a href="">基本资料</a>
							</dd>
							<dd>
								<a href="">安全设置</a>
							</dd>
						</dl>-->
					</li>
                    <li class="layui-nav-item">
                        <a href="${rc.contextPath}/index.do" target="_blank">首页</a>
                    </li>
					<li class="layui-nav-item">
						<a href="${rc.contextPath}/exit.do">安全退出</a>
					</li>
					<li class="layui-nav-item">
						<span style="font-size: 17px;" id="nowTime"></span>
					</li>
					<script type="text/javascript">
						setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
					</script>
				</ul>
			</div>

			<div class="layui-side custom-admin">
				<div class="layui-side-scroll">

					<div class="custom-logo">
						<img src="${rc.contextPath}/houtai/img/200.png" alt="" />
						<h1>易淘后台管理系统</h1>
					</div>
					<ul id="Nav" class="layui-nav layui-nav-tree">
						<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon">&#xe68e;</i>
								<em>主页</em>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${rc.contextPath}/houtai/views/console.html">控制台</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon">&#xe705;</i>
								<em>书籍管理</em>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${rc.contextPath}/admin/bookadd.do">添加书籍</a>
								</dd>
								<dd>
									<a href="${rc.contextPath}/admin/booklist.do">查询书籍</a>
								</dd>
								<!--<dd>
									<a href="javascript:;">页面</a>
									<dl class="layui-nav-child">
										<dd>
											<a href="login.html">登录页</a>
										</dd>
									</dl>
								</dd>-->
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon">&#xe857;</i>
								<em>网站内容管理</em>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;">
										<i class="layui-icon">&#xe857;</i>
										<em>内容分类管理</em>
									</a>
									<dl class="layui-nav-child">
										<dd>
											<a href="${rc.contextPath}/admin/category/toaddbookcategory.do">添加分类</a>
										</dd>
										<dd>
											<a href="${rc.contextPath}/admin/category/querybookcategory.do">查询分类</a>
										</dd>
									</dl>
								</dd>
								<dd>
									<a href="javascript:;">
										<i class="layui-icon">&#xe857;</i>
										<em>广告管理</em>
									</a>
									<dl class="layui-nav-child">
										<dd>
											<a href="${rc.contextPath}/admin/ads/toaddads.do">添加广告</a>
										</dd>
										<dd>
											<a href="${rc.contextPath}/admin/ads/queryads.do">查询广告</a>
										</dd>
									</dl>
								</dd>
							</dl>
						</li>
						<#--<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon">&#xe62c;</i>
								<em>统计</em>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${rc.contextPath}/houtai/usertime_count.html">根据用户注册时间统计</a>
								</dd>
								<dd>
									<a href="${rc.contextPath}/houtai/monthclass_count.html">根据分类统计每个月的总销量</a>
								</dd>
								<dd>
									<a href="${rc.contextPath}/houtai/class_count.html">根据分类统计总销量</a>
								</dd>
							</dl>
						</li>-->
					<#--	<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon">&#xe612;</i>
								<em>用户</em>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${rc.contextPath}/houtai/views/users.html">用户组</a>
								</dd>
								<dd>
									<a href="${rc.contextPath}/houtai/views/operaterule.html">权限配置</a>
								</dd>
							</dl>
						</li>-->
                        <li class="layui-nav-item">
                            <a href="javascript:;">
                                <i class="layui-icon">&#xe612;</i>
                                <em>角色管理</em>
                            </a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="/showAddRolePage.do">添加角色</a>
                                </dd>
                                <dd>
                                    <a href="/showAllRole.do">查看角色</a>
                                </dd>
                            </dl>
                        </li>
					</ul>
				</div>
			</div>

			<div class="layui-body">
				<div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
					<ul id="appTabs" class="layui-tab-title custom-tab"></ul>
					<div id="appTabPage" class="layui-tab-content"></div>
				</div>
			</div>
			<div class="mobile-mask"></div>
		</div>
		<script src="${rc.contextPath}/houtai/assets/layui.js"></script>
		<script src="${rc.contextPath}/houtai/js/index.js" data-Main="home"></script>
	</body>

</html>