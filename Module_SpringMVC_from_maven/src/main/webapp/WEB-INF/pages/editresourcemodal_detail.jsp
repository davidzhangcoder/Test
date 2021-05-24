<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="resouceformid" method="post">

<%--    <c:out value='${requestScope.resource}'></c:out>--%>
<%--    ok<br/>--%>
<%--    <c:out value="${requestScope.resource.id}"></c:out>--%>
<%--    ok1<br/>--%>
<%--    <c:out value="${requestScope.resource.id == null}"></c:out>--%>

    <div class="row">

        <div class="form-group col-md-4">
            <label for="id">ID</label>

            <%--      another way, also working      --%>
            <%--            <input id="id" name="id" type="text" placeholder="ID" class="form-control input-sm"--%>
            <%--                   value="<c:if test='${requestScope.resource != null}'> <c:out value='${requestScope.resource.id}'></c:out> </c:if>--%>
            <%--                          <c:if test='${requestScope.resource == null}'><c:out value='New'></c:out></c:if>" readonly>--%>

            <input id="id" name="id" type="text" placeholder="ID" class="form-control input-sm"
                   value="<c:out value="${requestScope.resource.id}" default="NEW"></c:out> " readonly>
        </div>

        <div class="form-group col-md-4">
            <label for="title">Title</label>
            <input id="title" name="title" type="text" placeholder="Title" class="form-control input-sm" value="${requestScope.resource.title}">
        </div>

        <div class="form-group col-md-4">
            <label for="content">Content</label>
            <input id="content" name="content" type="text" placeholder="Content" class="form-control input-sm" value="${requestScope.resource.content}">
        </div>

    </div>

    <div class="row">

        <div class="form-group col-md-4">
            <label for="link">Link</label>
            <input id="link" name="link" type="text" placeholder="Link" class="form-control input-sm" value="${requestScope.resource.link}">
        </div>

        <div class="form-group col-md-4">
            <label for="password">Password</label>
            <input id="password" name="password" type="text" placeholder="Password" class="form-control input-sm" value="${requestScope.resource.password}">
        </div>

        <%--                            <div class="form-group col-md-4">--%>
        <%--                                <label for="addtime">Added Time</label>--%>
        <%--                                <input id="addtime" name="addtime" type="text" placeholder="Added Time" class="form-control input-sm" value="${requestScope.resource.addtime}" readonly>--%>
        <%--                            </div>--%>

    </div>

    <div class="row">
        <div class="form-group col-md-4">
            <label for="status">status</label>
<%--            <input id="status" name="status" type="text" placeholder="Status" class="form-control input-sm" value="${requestScope.resource.status}">--%>
            <select id="status" class="form-control input-lg">...</select>
        </div>

<%--        <input type="submit" value="doAction2 - Put data into Java Bean">--%>

    </div>

</form>
