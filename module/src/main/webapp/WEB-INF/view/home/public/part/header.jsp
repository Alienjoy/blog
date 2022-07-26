<%--
    博客顶部部分
    包括：顶部菜单，主要菜单(包括搜索按钮)，面包屑
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<%--导航 start--%>
<header id="masthead" class="site-header">

    <%--主要菜单 satrt--%>
    <div id="menu-box">
        <div id="top-menu">
            <%--搜索框--%>
<%--            <span class="nav-search">--%>
<%--                    <i class="fa fa-search"></i>--%>
<%--            </span>--%>

            <%--logo动态效果的区域--%>
            <div class="logo-site">
                <h1 class="site-title">
                    <a href="/" title="${information.siteTitle}">${information.siteTitle}</a>
                </h1>
                <p class="site-description">${information.siteDescription}</p>
            </div><!-- .logo-site -->

            <div id="site-nav-wrap">
                <div id="sidr-close">
                    <a href="#sidr-close" class="toggle-sidr-close">×</a>
                </div>
                <nav id="site-nav" class="main-nav">
                    <a href="#sidr-main" id="navigation-toggle" class="bars">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="menu-pcmenu-container">
                        <ul id="menu-pcmenu" class="down-menu nav-menu sf-js-enabled sf-arrows ">
                            <li>
                                <a href="/">
                                    <i class="fa-home fa"></i>
                                    <span class="font-text">首页</span>
                                </a>
                            </li>

                            <c:forEach items="${allCategoryList}" var="category">
                                <c:if test="${category.categoryPid==0}">
                                    <li>
                                        <a href="/category/${category.categoryId}">
                                            <i class="${category.categoryIcon}"></i>
                                            <span class="font-text">${category.categoryName}&nbsp;</span>
                                        </a>
                                        <ul class="sub-menu">
                                            <c:forEach items="${allCategoryList}" var="cate">
                                                <c:if test="${cate.categoryPid==category.categoryId}">
                                                    <li>
                                                        <a href="/category/${cate.categoryId}">${cate.categoryName}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>


                            <li>
                                <c:choose>
                                    <%--判断是否登陆，如果没有登陆的话--%>
                                    <c:when test="${sessionScope.user==null}">
                                        <a href="/login">
                                            <i class="fa fa-user"></i>
                                            <span class="font-text">登录|注册&nbsp;</span>

                                        </a>
                                    </c:when>
                                    <%--如果登陆的话--%>
                                    <c:otherwise>
                                        <a href="/admin">
                                            <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">
                                                ${sessionScope.user.userName}
                                        </a>
                                        <ul class="sub-menu">
                                            <li>
                                                <a href="/user/${sessionScope.user.userName}">个人首页</a>
                                                <a href="/admin/article">我的文章</a>
                                                <a href="/admin/comment/receive">我的消息</a>
                                                <a href="/admin/profile">我的信息</a>
                                                <a href="/admin/logout">退出</a>
                                            </li>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="clear"></div>
        </div><!-- #top-menu -->
    </div><!-- #menu-box -->
    <%--主要菜单 satrt--%>

</header>
<%--导航 end--%>

<%--搜索框 start--%>
<div id="search-main">
    <div class="searchbar">
        <form method="get" id="searchform" action="/search" accept-charset="UTF-8">
            <span>
                <input type="text" value="" name="keywords" id="s" placeholder="输入搜索内容" required="">
                <button type="submit" id="searchsubmit">搜索</button>
            </span>
        </form>
    </div>
    <div class="clear"></div>
</div>
<%--搜索框 end--%>


<%--通知栏--%>
<rapid:block name="breadcrumb"></rapid:block>