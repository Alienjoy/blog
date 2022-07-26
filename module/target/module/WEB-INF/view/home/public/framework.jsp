<%--
  Created by IntelliJ IDEA.
  User: zhangshuheng
  Date: 2022/2/9
  Time: 2:07 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--使用这下面三个JSTL的标签库，来使用jsp的标签--%>
<%--core核心标签--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--Formatting tags格式化标签--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--function标签--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <%--meta都是用来规定页面的配置信息的--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Cache-Control" content="max-age=72000"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="applicable-device" content="pc,mobile">
    <meta name="MobileOptimized" content="width"/>
    <meta name="HandheldFriendly" content="true"/>
    <%-- link表示链接，rel=relationship，表示当前文档与被链接文档/资源之间的关系。--%>
    <link rel="shortcut icon" href="/img/logo.png">


    <%--可以重写的部分，用来设置页面的描述信息，关键词，标题--%>
    <rapid:block name="description">
        <meta name="description" content="${information.siteMetaDescription}"/>
    </rapid:block>
    <rapid:block name="keywords">
        <meta name="keywords" content="${information.siteMetaKeywords}"/>
    </rapid:block>
    <rapid:block name="title">
        <title>${information.siteTitle}-${information.siteDescription}</title>
    </rapid:block>

    <%--导入外部的CSS文件--%>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <%--这是写CSS的地方--%>
    <rapid:block name="header-style"></rapid:block>

</head>
<body>
<%--头部的组件--%>
<%@ include file="part/header.jsp" %>

<%--中间内容的组件--%>
<div id="content" class="site-content" style="transform: none;">
    <rapid:block name="left"></rapid:block>
    <rapid:block name="right">
        <%@ include file="part/sidebar-1.jsp" %>
    </rapid:block>
</div>
<div class="clear"></div>


<%--底部链接的组件--%>
<rapid:block name="link"></rapid:block>
<%--最下面的版权信息的组件--%>
<%@ include file="part/footer.jsp" %>


<script src="/js/jquery.min.js"></script>
<script src="/js/superfish.js"></script>
<script src='/js/sticky.js'></script>
<script src="/js/script.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script src="/plugin/layui/layer.js"/>
<script src="https://kit.fontawesome.com/523fb036fc.js" crossorigin="anonymous"></script>

<%--这下面是写java script脚本的地方--%>
<rapid:block name="footer-script"></rapid:block>
</body>
</html>
