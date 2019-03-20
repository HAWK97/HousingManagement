<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/12/26
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>好房通</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/js/template.js"></script>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    .search {
        width: 1000px;
        height: 250px;
        padding: 20px;
    }

    .search_item {
        height: 50px;
        width: 900px;
    }

    .detail {
        padding-top: 10px;
    }

    .search_span {
        padding-left: 5px;
    }

    .search_span_all {
        font-size: 10px;
        color: orange;
        font-weight: bold;
    }

    .choiced {
        background-color: orange;
        color: white;
    }

    .not_choiced {
        background-color: white;
        color: black;
    }
</style>
<body>
<div style="margin: 0 auto;width: 1000px">
    <header class="btn-info">
        <span>好房通</span>
        <!--TODO  shuguang 写-->
        <span style="float: right">注册</span>
        <span style="float: right">登陆&nbsp;</span>
    </header>
    <div>
        <div class="search">
            <div id="search_item01" class="search_item">地址:
                <span class="search_span search_span_all location" onclick="searchWithAddress(this)">全部</span>
                <span class="search_span location" onclick="searchWithAddress(this)">北京1</span>
                <span class="search_span location" onclick="searchWithAddress(this)">北京2</span>
                <span class="search_span location" onclick="searchWithAddress(this)">北京3</span>
            </div>
            <div class="search_item">价格:
                <span class="search_span search_span_all price" onclick="searchWithPrice(this)">全部</span>
                <span class="search_span price" onclick="searchWithPrice(this)">500-800</span>元
                <span class="search_span price" onclick="searchWithPrice(this)">500-800</span>元
                <span class="search_span price" onclick="searchWithPrice(this)">500-800</span>元
            </div>
            <div class="search_item">大小:
                <span class="search_span search_span_all square" onclick="searchWithSquare(this)">全部</span>
                <span class="search_span  square" onclick="searchWithSquare(this)">10-20</span>m2
                <span class="search_span  square" onclick="searchWithSquare(this)">20-40</span>m2
                <span class="search_span  square" onclick="searchWithSquare(this)">40-80</span>m2
            </div>
            <div class="search_item">户型:
                <span class="search_span search_span_all type" onclick="searchWithType(this)">全部</span>
                <span class="search_span type" onclick="searchWithType(this)">一室</span>
                <span class="search_span type" onclick="searchWithType(this)">一室</span>
                <span class="search_span type" onclick="searchWithType(this)">一室</span>
            </div>
        </div>
        <!--TODO  渲染模板-->
        <div class="img-list" id="imgListDiv">
            <script id="imgScript" type="text/html">
                <!--媒体对象开始-->
                {{each data.list}}
                <div class="media" style="width: 700px;float: left">
                    <div class="media-left media-middle">
                        <a href="#">
                            <img class="media-object" src="<%=request.getContextPath()%>/img/test_house.jpg" alt="房屋">
                        </a>
                    </div>
                    <div class="media-body" style="padding-left: 20px">
                        <h4 class="media-heading"><strong>{{$value.description}}</strong></h4>
                        <div class="detail">{{$value.type}}</div>
                        <div class="detail">{{$value.address}}</div>
                    </div>
                </div>
                <div style="float: left;padding: 40px;height: 180px">
                    <div>
                        <span style="font-size: 20px;font-weight: bold;color: red">{{$value.price}}元/月</span>
                    </div>
                </div>
                <div style="clear: left"></div>
                {{/each}}
                <!--媒体对象结束-->
            </script>
        </div>
    </div>
    <form id="search_form" style="display: none">
        地址：<input type="text" value="全部" name="address" id="address">
        价格：<input type="text" value="全部" name="priceRegion" id="priceRegion">
        大小: <input type="text" value="全部" name="squraRegion" id="squraRegion">
        户型:<input type="text" value="全部" name="type" id="type">
        <input type="submit" value="提交">
    </form>
</div>

<script type="application/javascript">


    $(function () {
        //初始化房屋图片列表
        initImgList();

        //添加点击事件
        /**
         * 初始化房屋图片列表
         */
        function initImgList() {
            search();
        }


    });

    function changeColor(dom) {
        var arr = $(dom).siblings();
        $(dom).addClass("choiced");
        $(dom).removeClass("not_choiced");
        console.log(arr);
        for (var i = 0; i < arr.length; i++) {
            if (dom === arr[i]) {
                $(dom).addClass("choiced");
                $(dom).removeClass("not_choiced");
            } else {
                $(arr[i]).addClass("not_choiced");
                $(arr[i]).removeClass("choiced");
            }
        }
    }

    function searchWithAddress(dom) {
        $("#address").val($(dom).text());
        changeColor(dom);
        search();
    }

    function searchWithPrice(dom) {
        $("#priceRegion").val($(dom).text());
        changeColor(dom);
        search();
    }

    function searchWithSquare(dom) {
        $("#squraRegion").val($(dom).text());
        changeColor(dom);
        search();
    }

    function searchWithType(dom) {
        $("#type").val($(dom).text());
        changeColor(dom);
        search();
    }

    /**
     * 搜索操作
     */
    function search() {

        var dom = $("#search_form")[0];
        var formData = new FormData(dom);
        $.ajax({
            url: "<%=request.getContextPath()%>/house/findHouseBySearchVo",
            type: "POST",
            data: formData,
            cache: false, // 不缓存数据
            processData: false, // 不处理数据
            contentType: false, // 不设置内容类型
            success: function (data) { //成功回调
                var html = template("imgScript", data);
                document.getElementById("imgListDiv").innerHTML = html;
            }
        });
    }
</script>
</body>
</html>
