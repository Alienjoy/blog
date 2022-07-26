<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%--<%@ page import="com.liuyanzhao.ssm.blog.util.MyUtils" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 我收到的评论
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-table {
            margin-top: 0;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">后台</a>
              <a><cite>我收到的评论</cite></a>
        </span>
    </blockquote>
    <div class="layui-tab layui-tab-card">
        <table class="layui-table" lay-even lay-skin="nob">
            <colgroup>
                <col width="100">
                <col width="300">
                <col width=200">
                <col width="100">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>用户</th>
                <th>评论内容</th>
                <th>评论的文章</th>
                <th>提交时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="c">
                <tr>
                    <td>
                        <a href="/user/${c.commentUserName}">
<%--                        <img src="${c.commentAuthorAvatar}" alt="" width="64px">--%>
                        <strong>${c.commentAuthorName}</strong>
                        </a>
                    </td>
                    <td class="dashboard-comment-wrap">
                        <c:if test="${c.commentPid!=0}">
                            <span class="at">@ </span><a>${c.commentPname}</a>
                        </c:if>
                            ${c.commentContent}

                    </td>
                    <td>
                        <a href="/article/${c.commentArticleId}"
                           target="_blank">${c.commentArticleTitle}</a>
                    </td>
                    <td>
                        <fmt:formatDate value="${c.commentCreateTime}" pattern="yyyy年MM月dd日 HH:dd:ss"/>
                    </td>
                    <td>
                        <span class="">
                            <a href="/admin/comment/reply/${c.commentId}">
                                回复
                            </a>
                        </span>
                        <span class="">
                            |<a href="javascript:void(0)" onclick="deleteComment(${c.commentId})">删除</a>
                        </span>
                    </td>

                </tr>
            </c:forEach>
            </tbody>

        </table>

        <div id="nav" style="">
            <%@ include file="../Public/paging.jsp" %>
        </div>
    </div>


</rapid:override>

<%@ include file="../Public/framework.jsp" %>
