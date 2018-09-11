<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 27.08.2018
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../libs/bootstrap-4.1.3/css/bootstrap.min.css">

    <title>users</title>
</head>

<body>
<c:set var="List" value="${sessionScope.Users}"/>
<jsp:include page="../header.jsp"/>
<div class="row justify-content-md-center">
<table class="table col-md-6 " >
    <thead class="thead-light" >
    <tr>
        <th scope="col"><ctg:locale message="name"/></th>
        <th scope="col"><ctg:locale message="surname"/></th>
        <th scope="col"><ctg:locale message="username"/></th>
        <th scope="col"><ctg:locale message="role"/></th>
        <th scope="col"><ctg:locale message="status"/></th>
        <th scope="col"><ctg:locale message="blockUnblock"/></th>
        <th scope="col"><ctg:locale message="up"/></th>
    </tr>
    </thead>
    <c:forEach var="var" items="${List}">
        <tr>
            <td> <c:out value="${(var.getFirstName())}"/></td>
            <td> <c:out value="${(var.getLastName())}"/></td>
            <td> <c:out value="${(var.getUserName())}"/></td>
            <td> <c:out value="${(var.getUserRole())}"/></td>
            <td> <c:out value="${(var.getUserStatus())}"/></td>
            <c:choose>
                <c:when test="${(var.getUserStatus() eq 'BLOCKED')}">
            <td>
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn btn-link" type="submit" formaction="/unblockUser" formmethod="get"><ctg:locale message="unblock"/></button>
                            <input type="hidden" value="${var.getUserId()}" name = "id"/>
                </form>
            </td>
                </c:when>
                <c:otherwise>
            <td>
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn btn-link" type="submit" formaction="/blockUser" formmethod="get"><ctg:locale message="block"/></button>
                            <input type="hidden" value="${var.getUserId()}" name = "id"/>
                </form>
            </td>
                </c:otherwise>
            </c:choose>
         <%--   <td>
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn btn-link" type="submit" formaction="/deleteUser" formmethod="get"><ctg:locale message="delete"/></button>
                             <input type="hidden" value="${var.getUserId()}" name = "id"/>
                </form>
            </td>--%>
            <c:if test="${(var.getUserRole()) eq 'CLIENT'}">
                <td>
                    <form class="form-inline my-2 my-lg-0">
                        <button class="btn btn-link" type="submit" formaction="/upClientToManager" formmethod="get"><ctg:locale message="up"/></button>
                        <input type="hidden" value="${var.getUserId()}" name = "id"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>

