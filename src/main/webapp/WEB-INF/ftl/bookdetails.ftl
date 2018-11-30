<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>书籍详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="${rc.contextPath}/img/logos.ico">
    <link rel="stylesheet" href="../css/bookDetails.css"/>
    <link rel="stylesheet" href="../assets/css/layui.css" media="all">
    <script src="../js/jquery-3.3.1.js"></script>
    <link href="${rc.contextPath}/style/shopdetail.css" rel="stylesheet" type="text/css">
    <script src="${rc.contextPath}/js/common.js"></script>
    <script src="../js/drawImg.js"></script>
    <#setting number_format="#">
    <style>
        .button1 {
            width: 110px;
            height: 40px;
            background-color: #1e9fff;
            color: white;
            border: none;
            border-radius: 5px;
        }

        .p1 {
            margin-top: 15px;
        }

        .li1 {
            width: 160px;
            background-color: #FF2832;
            border-radius: 8px;
            color: white;
            border: none;
        }
    </style>
    <style type="text/css">
        .imgtest {
            width: 100%;
            margin: 10px 5px;
            overflow: hidden;
        }

        .imgtest figure div {
            display: inline-block;
            margin: 5px auto;
            width: 40px;
            height: 40px;
            border-radius: 100px;
            border: 2px solid gray;
            overflow: hidden;
            -webkit-box-shadow: 0 0 3px #ccc;
            box-shadow: 0 0 3px #ccc;
        }

        .imgtest img {
            width: 100%;
            min-height: 100%;
            text-align: center;
        }
    </style>
</head>
<script>
    /*添加收藏*/
    function AddCollection() {
        var id = document.getElementById('bookid').value;
        $.post("/addCollection.do", {"id": id}, function (data, status) {
            if (data == "1") {
                layer.msg("收藏成功", {icon: 1});
               /* alert("收藏成功");*/
            }else if(data=="0"){
                layer.msg("收藏失败,已经收藏该书籍", {icon: 5});
            }else {
                window.location.href = "/login.do";
               /* alert("收藏失败,已经收藏该书籍");*/
            }
        })
    }
</script>
<body>

<#include "header.ftl">
<#include "search.ftl">
<#--<div style="display: inline-block;position: relative;right: 790px;top: 90px;">-->
    <!-------放大镜-------->
<div class="shopdetails">
    <div id="leftbox">
        <div id="showbox">
            <#list "${book.imgurl}"?split(",") as url>
                 <img alt="图片加载失败" src="${url}" width="400" height="550"/>
                <#--<img id="img1" alt="" src="${url}" onload="DrawImg(350,400)"/>-->
            </#list>

        </div><!--展示图片盒子-->
        <div id="showsum"></div><!--展示图片里边-->
        <p class="showpage">
            <a href="javascript:void(0);" id="showlast"> < </a>
            <a href="javascript:void(0);" id="shownext"> > </a>
        </p>

    </div>
    <!----中间----->

    <div class="centerbox">
        <#--<div style="width: 500px;height: 450px;display: inline-block;position: relative;left: 700px;top: -280px;">-->
            <div>
                <input id="bookid" type="hidden" value="${book.id}">
                <p class="p1">
                    <font style="font-size: 20px;font-family: '微软雅黑';"><b>${book.name}</b></font>
                </p>
                <p class="p1">
                    <font>作&nbsp;&nbsp;者:&nbsp;&nbsp;&nbsp;${book.author} 著</font>
                </p>
                <div>
                    <p class="p1">
                        售&nbsp;&nbsp;价:<span
                            style="font-size: 25px;color: red;"><b>&nbsp;&nbsp;&nbsp;&nbsp;¥${book.price/100}</b></span>
                    </p>
                    <p class="p1">
                        出&nbsp;版&nbsp;社:<span style="font-size: 20px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;${book.press}</b></span>
                    </p>
                    <p class="p1">
                        出&nbsp;版&nbsp;时&nbsp;间:<span
                            style="font-size: 20px;"><b>&nbsp;&nbsp;${book.publishDate?string("yyyy年MM月dd日")}</b></span>
                    </p>
                    <p class="p1">
                        库&nbsp;&nbsp;&nbsp;存:<span style="font-size: 20px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;${book.stock}</b></span>
                    </p>
                    <p class="p1">
                        页&nbsp;&nbsp;数:<span
                            style="font-size: 20px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${book.pageNum}</b></span>
                    </p>
                    <p class="p1">
                        字&nbsp;&nbsp;数:<span
                            style="font-size: 20px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${book.wordsNum}</b></span>
                    </p>
                </div>
                <span style="position: relative;top: 20px;">数&nbsp;&nbsp;量: &nbsp;&nbsp;</span>
                <div style="margin-left: 100px;">

                    <input id="min1" name="" style="width:20px; height:25px;margin-left: -30px; border:1px solid #ccc;"
                           type="button" value="-"/>
                    <input id="text_box1" disabled name="" type="text" value="1"
                           style="width:30px; text-align:center; border:1px solid #ccc;height: 25px;"/>
                    <input id="add1" name="" style="width:20px; height:25px;border:0px solid #ccc;" type="button"
                           value="+"/>
                </div>
                <div style="margin-top: 40px">
                    <form id="buyshop" action="/buynows.do" method="post" style="display: inline-block">
                        <input type="hidden" id="bookids" name="id">
                        <input type="hidden" id="bookSize" name="bookSize">
                        <button class="button1" id="buts1">立即购买</button>
                    </form>
                    <button class="button1" id="addCart_btn">加入购物车</button>
                    <button class="button1" id="addCollection_btn" onclick="AddCollection()">添加收藏</button>
                </div>
                <p class="fuwu">服务承诺：</p>
                <p class="pay">支付方式：</p>
            </div>


        <#--</div>-->
    </div>


</div>


<#--<hr style="width: 100%;background-color: red;margin-top: -180px;;">-->
<!-- 下面 -->

<div class="evaluate">
    <#--<div class="classify">
        <div class="shopim">
           &lt;#&ndash; <p class="name">青蛙工艺家居<img src="images/shopdetail/tell01.png" width="22" height="22"></p>
            <img src="images/shopdetail/tellbottom.png">
            <p class="sc"><a href="#">收藏店铺</a></p>
            <p class="sc"><a href="#">进入店铺</a></p>
            <div class="search">
                <input class="left" type="text" />
                <input class="right" type="button" style=" cursor:pointer;" value=""/>
            </div>&ndash;&gt;
        </div>
        <div class="shopfl">
           &lt;#&ndash; <p class="name">本店分类</p>
            <ul>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">全部商品</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">木质商品</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">石制商品</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">陶制商品</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">家居厨房</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">欧式混搭</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">桌面摆件</a></li>
                <li><a href="#"><img src="images/shopdetail/tell02.png" width="10" height="10">书香文房</a></li>
            </ul>&ndash;&gt;
        </div>
        <div class="shopsee">
            &lt;#&ndash;<p class="name">看了又看</p>
            <a href="#" class="ex01">
                <img src="/images/shopdetail/see.jpg" width="170" height="245">
                <p>手绘陶瓷茶壶</p>
                <p>商城价:168元</p>
            </a>&ndash;&gt;

        </div>


    </div>-->


    <div class="tabbedPanels">
        <ul class="tabs">
            <li><a href="#panel01">商品详情</a></li>
            <li><a href="#panel02" class="active">累计评价</a></li>
            <li><a href="#panel03">商品推荐</a></li>
        </ul>

        <div class="panelContainer">
            <div class="panel" id="panel01">
                ${book.details}
            </div>

            <div class="panel" id="panel02">
                <p class="sell">买家评价</p>
                <img src="/images/shopdetail/detail101.png">
                <p class="judge">全部评价(20)<span>晒图(13)</span></p>

               <#-- <div class="judge01">
                    <div class="idimg"><img src="images/shopdetail/detail102.png"></div>
                    <div class="write">
                        <p class="idname">落***1</p>
                        <p>杯子很可爱！就是有两三个杯子后面的小图案有一丢丢斜下来，不过没多大关系，其他的还好。有一点真的特别特别好的就是😂包裹的非常非常非常严实，简直就是里三层外三层！杯子完好无损，赠送的刷子也包装的很好😂让我第一眼以为那是一个棉花糖hhh</p>
                        <p class="which">颜色:创意胡子</p>
                        <img src="images/shopdetail/detail103.jpg" width="40px" height="40px">
                        <img src="images/shopdetail/detail104.jpg" width="40px" height="40px">
                        <img src="images/shopdetail/detail105.jpg" width="40px" height="40px">

                    </div>
                </div>-->

                <div class="clear"></div>
            </div>

            <div class="panel" id="panel03">
                <#--<p class="sell">本店热卖商品</p>
                <div class="com">
                    <a href="#" class="ex01"><figure>
                        <img src="/images/index_img/content_11.jpg"><figcaption>木质花瓶</figcaption></figure>
                        <p>木质简约花瓶 亲近大自然</p>
                        <div class="bottom"><samp>商城价:￥34元</samp><input type="button" style=" cursor:pointer;" value="购买" /></div>
                    </a>
                </div>
                <div class="clear"></div>-->
            </div>


        </div>

    </div>

</div>

<!-----商品详情评价部结束分------->
<#--<div class="layui-tab" margin-top: -95px;>
    <ul class="layui-tab-title" style="margin-left: 250px;">
        <li class="layui-this"
            style="width: 160px;background-color: #FF2832;border-radius: 8px;color: white;border: none">书籍详情
        </li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show" align="center">
        ${book.details}
        </div>
        <div class="layui-tab-item">
            <div style="padding: 20px; background-color: #F2F2F2;">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-card-header">该商品的用户评论:</div>
                            <div class="layui-card-body">
                                <div class="imgtest layui-row" style="border: 1px solid gainsboro;">
                                    <div class="layui-col-xs2">
                                        时间: 2018-10-25 15:59:50
                                    </div>
                                    </br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show" align="center">
                ${book.details}
                </div>
            </div>-->

            <script>
                $(document).ready(function () {
                    //添加购物车
                    $("#addCart_btn").click(function () {
                        var bookid = document.getElementById('bookid').value;
                        var booknum = document.getElementById('text_box1').value;

                        $.post("/addcart.do", {bookid: bookid, booknum: booknum}, function (data) {
                            if (data.status == 200) {
                                layer.open({
                                    content: '加入购物车成功,是否去购物车结算', //这里content是一个普通的String
                                    btn:['去结算',"再看看"],
                                    yes:function (index, layero) {
                                        window.location.href = "/selectCart.do";
                                    },
                                    btn2: function(index, layero){
                                        window.location.reload();
                                    }
                                });
                            }else if(data.status == 500){
                                layer.msg("加入购物车失败,请稍后再试", {icon: 5});
                            }else {
                                window.location.href = "/login.do";
                            }
                        });
                    });
                    /**
                     * 立即购买
                     * @constructor
                     */
                    $("#buts1").click(function () {
                        var id = document.getElementById("bookid").value;
                        var bookSize = document.getElementById("text_box1").value;

                        $("#bookids").attr("value", id);
                        $("#bookSize").attr("value", bookSize);

                        $("#buts1").submit;
                    });
                });

            </script>
            <script type="text/javascript">
                $(function () {
                    var t = $("#text_box1");
                    $("#add1").click(function () {
                        if (t.val() < ${book.stock}) {
                            t.val(parseInt(t.val()) + 1);
                        }
                    })
                    $("#min1").click(function () {
                        if (t.val() > 1) {
                            t.val(parseInt(t.val()) - 1);
                        }
                    })
                })

            </script>

            <script src="../assets/layui.all.js"></script>
</body>
<script type="text/javascript">
    function pcGlasses(_obj) {
        var _box = $("#" + _obj.boxid);
        var _sum = $("#" + _obj.sumid);
        var _last, _next;
        var _imgarr = _box.find("img");
        var _length = _imgarr.length;
        var _index = 0;
        var _arr = new Array();
        _sum.append("<p style='position:absolute;left:0;top:0;'></p>");
        var _sumbox = _sum.find("p");

        for (var i = 0; i < _length; i++) {
            _arr[i] = new Array();
            _arr[i][0] = _imgarr.eq(i).attr("src");
            _arr[i][1] = _imgarr.eq(i).attr("width");
            _arr[i][2] = _imgarr.eq(i).attr("height");
            var _scale = _arr[i][1] / _arr[i][2];
            if (_scale == 1) {
                _arr[i][3] = _obj.boxw;//width
                _arr[i][4] = _obj.boxh;//height
                _arr[i][5] = 0;//top
                _arr[i][6] = 0;//left
                _arr[i][7] = _obj.boxw / 2;
                _arr[i][8] = _obj.boxw * 2;//width
                _arr[i][9] = _obj.boxh * 2;//height
                _sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' height='" + _obj.sumh + "' /></span>");
            }
            if (_scale > 1) {
                _arr[i][3] = _obj.boxw;//width
                _arr[i][4] = _obj.boxw / _scale;
                _arr[i][5] = (_obj.boxh - _arr[i][4]) / 2;
                _arr[i][6] = 0;//left
                _arr[i][7] = _arr[i][4] / 2;
                _arr[i][8] = _obj.boxh * 2 * _scale;//width
                _arr[i][9] = _obj.boxh * 2;//height
                var _place = _obj.sumh - (_obj.sumw / _scale);
                _place = _place / 2;
                _sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' style='top:" + _place + "px;' /></span>");
            }
            if (_scale < 1) {
                _arr[i][3] = _obj.boxh * _scale;//width
                _arr[i][4] = _obj.boxh;//height
                _arr[i][5] = 0;//top
                _arr[i][6] = (_obj.boxw - _arr[i][3]) / 2;
                _arr[i][7] = _arr[i][3] / 2;
                _arr[i][8] = _obj.boxw * 2;//width
                _arr[i][9] = _obj.boxw * 2 / _scale;
                var _place = _obj.sumw - (_obj.sumh * _scale);
                _place = _place / 2;
                _sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' height='" + _obj.sumh + "' style='left:" + _place + "px;' /></span>");
            }
        }
        _imgarr.remove();

        _sum.append("<div style='clear:both;width:100%;'></div>");
        var _sumarr = _sum.find("span");
        var _sumimg = _sum.find("img");
        _sumarr.eq(_index).addClass(_obj.sumsel);
        var _border = _obj.sumborder * 2 + _obj.sumh;
        var _sumwidth = (_border + _obj.sumi) * _obj.sums;
        var _sumboxwidth = (_border + _obj.sumi) * _length;
        _sum.css({
            "overflow": "hidden",
            "height": _border + "px",
            "width": _sumwidth + "px",
            "position": "relative"
        });
        _sumbox.css({
            "width": _sumboxwidth + "px"
        });
        _sumarr.css({
            "float": "left",
            "margin-left": _obj.sumi + "px",
            "width": _obj.sumw + "px",
            "height": _obj.sumh + "px",
            "overflow": "hidden",
            "position": "relative"
        });
        _sumimg.css({
            "max-width": "100%",
            "max-height": "100%",
            "position": "relative"
        });

        _box.append("<div style='position:relative;'><b style='display:block;'><img style='display:block;' src='' /></b><span style='position:absolute;left:0;top:0;display:none;z-index:5;'></span></div><p style='position:absolute;overflow:hidden;top:0;display:none;'><img style='max-width:none;max-height:none;position:relative;left:0;top:0;' src='' /></p>");
        var _glass = _box.find("span");
        var _boximg = _box.find("b img");
        var _imgout = _box.find("div");
        var _showbox = _box.find("p");
        var _showimg = _box.find("p img");

        _box.css({
            "width": _obj.boxw + "px",
            "height": _obj.boxh + "px",
            "position": "relative"
        });
        var _showboxleft = _obj.boxw + 10;
        _showbox.css({
            "width": _obj.boxw + "px",
            "height": _obj.boxh + "px",
            "left": _showboxleft + "px"
        });

        var imgPlaces = function () {
            _showimg.attr("src", _arr[_index][0]);
            _boximg.attr("src", _arr[_index][0]);
            _boximg.css({
                "width": _arr[_index][3] + "px",
                "height": _arr[_index][4] + "px"
            });
            _imgout.css({
                "width": _arr[_index][3] + "px",
                "height": _arr[_index][4] + "px",
                "top": _arr[_index][5] + "px",
                "left": _arr[_index][6] + "px",
                "position": "relative"
            });
            _glass.css({
                "width": _arr[_index][7] + "px",
                "height": _arr[_index][7] + "px"
            });
            _showimg.css({
                "width": _arr[_index][8] + "px",
                "height": _arr[_index][9] + "px"
            });

        };
        imgPlaces();

        _imgout.mousemove(function (e) {
            var _gl_w = _glass.width() / 2;
            var _maxX = _imgout.width() - _gl_w;
            var _maxY = _imgout.height() - _gl_w;
            var _moveX = 0, _moveY = 0;
            var _nowX = e.pageX - _imgout.offset().left;
            var _nowY = e.pageY - _imgout.offset().top;
            var _moveX = _nowX - _gl_w, _moveY = _nowY - _gl_w;

            if (_nowX <= _gl_w) {
                _moveX = 0;
            }
            if (_nowX >= _maxX) {
                _moveX = _maxX - _gl_w;
            }
            if (_nowY <= _gl_w) {
                _moveY = 0;
            }
            if (_nowY >= _maxY) {
                _moveY = _maxY - _gl_w;
            }
            _glass.css({"left": _moveX + "px", "top": _moveY + "px"});

            var _imgX = -_moveX * _showbox.width() / _glass.width();
            var _imgY = -_moveY * _showbox.width() / _glass.width();
            _showimg.css({"left": _imgX + "px", "top": _imgY + "px"});

        });//mouse END

        _imgout.mouseenter(function () {
            _glass.css("display", "block");
            _showbox.css("display", "block");
        });
        _imgout.mouseleave(function () {
            _glass.css("display", "none");
            _showbox.css("display", "none");
        });

        //列表部分
        var _nextbtn = $("#" + _obj.nextid);
        var _lastbtn = $("#" + _obj.lastid);
        var _moveindex = 0;//索引移动

        var _sumListMove = function () {
            var _leftmove = -_moveindex * (_border + _obj.sumi);
            if (_sumbox.is(":animated")) {
                _sumbox.stop(true, true);
            }
            _sumbox.animate({left: _leftmove + "px"}, 300);
            _sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
            imgPlaces();
        };//fun END

        if (_length <= _obj.sums) {
            var _place = (_obj.sums - _length) * _border / 2;
            _sumbox.css("left", _place + "px");
            _nextbtn.click(function () {
                _index++;
                if (_index >= _length) {
                    _index = _length - 1;
                }
                _sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
                imgPlaces();
            });
            _lastbtn.click(function () {
                _index--;
                if (_index <= 0) {
                    _index = 0;
                }
                _sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
                imgPlaces();
            });
        } else {
            var _maxNum = _length - _obj.sums;
            _nextbtn.click(function () {
                _moveindex++;
                if (_moveindex >= _maxNum) {
                    _moveindex = _maxNum;
                }
                if (_index <= _moveindex) {
                    _index = _moveindex;
                }
                _sumListMove();
            });
            _lastbtn.click(function () {
                _moveindex--;
                if (_moveindex <= 0) {
                    _moveindex = 0;
                }
                if (_index >= _moveindex + _obj.sums) {
                    _index = _moveindex + _obj.sums - 1;
                }
                _sumListMove();
            });
        }//if END

        _sumarr.hover(function () {
            _index = $(this).index();
            _sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
            imgPlaces();
        });

    }//pcGlasses END
</script>
<script type="text/javascript">
    $(document).ready(function () {
        var showproduct = {
            "boxid": "showbox",
            "sumid": "showsum",
            "boxw": 400,
            "boxh": 550,
            "sumw": 60,//列表每个宽度,该版本中请把宽高填写成一样
            "sumh": 60,//列表每个高度,该版本中请把宽高填写成一样
            "sumi": 7,//列表间隔
            "sums": 5,//列表显示个数
            "sumsel": "sel",
            "sumborder": 1,//列表边框，没有边框填写0，边框在css中修改
            "lastid": "showlast",
            "nextid": "shownext"
        };//参数定义
        pcGlasses(showproduct);
        $(function () {
            $('.tabs a').click(function () {

                var $this = $(this);
                $('.panel').hide();
                $('.tabs a.active').removeClass('active');
                $this.addClass('active').blur();
                var panel = $this.attr("href");
                $(panel).show();
                return fasle;  //告诉浏览器  不要纸箱这个链接
            });//end click


            $(".tabs li:first a").click()   //web 浏览器，单击第一个标签吧


        });//end ready

        $(".centerbox li").click(function () {
            $("li").removeClass("now");
            $(this).addClass("now")

        });


    });

</script>
</html>