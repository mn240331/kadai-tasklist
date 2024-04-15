<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク一覧</h2>
        <ul>
            <c:forEach var="Tasks_kanri" items="${tasks}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${Tasks_kanri.id}">
                        <c:out value="${Tasks_kanri.id}" />
                    </a>
                    ： <c:out value="${Tasks_kanri.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規タスクの投稿</a></p>

    </c:param>
</c:import>