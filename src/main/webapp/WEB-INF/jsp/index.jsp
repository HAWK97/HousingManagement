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
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    .search{
        width: 1000px;
        height: 210px;
        padding: 20px;
    }
    .search_item{
        height: 50px;
        width: 900px;
    }
    .detail{
        padding-top: 10px;
    }
    .search_span{
        padding-left: 5px;
    }
    .search_span_all{
        font-size: 10px;
        color: orange;
        font-weight: bold;
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
        <div class="search">搜索条件区域
            <div class="search_item">地址:
                <span class="search_span search_span_all">全部</span>
                <span class="search_span">北京</span>
                <span class="search_span">北京</span>
                <span class="search_span">北京</span>
            </div>
            <div class="search_item">价格:
                <span class="search_span search_span_all">全部</span>
                <span class="search_span">500-800元</span>
                <span class="search_span">500-800元</span>
                <span class="search_span">500-800元</span>
            </div>
            <div class="search_item">户型:
                <span class="search_span search_span_all">全部</span>
                <span class="search_span">一室</span>
                <span class="search_span">一室</span>
                <span class="search_span">一室</span>
            </div>
        </div>
        <!--TODO  渲染模板-->
        <div class="img-list">
            <!--媒体对象开始-->
            <div class="media" style="width: 700px;float: left" >
                <div class="media-left media-middle">
                    <a href="#">
                        <img class="media-object" src="<%=request.getContextPath()%>/img/test_house.jpg" alt="房屋">
                    </a>
                </div>
                <div class="media-body" style="padding-left: 20px">
                    <h4 class="media-heading"><strong>精装海景房 只此一套 先到先得!</strong></h4>
                    <div class="detail">三室一厅</div>
                    <div class="detail">情侣入住，水电全免</div>
                </div>
            </div>
            <div style="float: left;padding: 40px;height: 180px">
                <div>
                    <span style="font-size: 20px;font-weight: bold;color: red">1400元/月</span>
                </div>
            </div>
            <div style="clear: left"></div>
            <!--媒体对象结束-->
        </div>
    </div>
</div>

</body>
</html>
