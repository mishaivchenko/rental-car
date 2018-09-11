<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.08.2018
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title>reject page</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container">
        <dl>
            <dt><ctg:locale message="Order"/> #</dt>
            <dd><c:out value="${(requestScope.Order.getOrderId())}"/></dd>
            <dt><ctg:locale message="name"/></dt>
            <dd><c:out value="${(requestScope.Order.getCustomerName())}"/></dd>
            <dt><ctg:locale message="surname"/></dt>
            <dd><c:out value="${(requestScope.Order.getCustomerSurname())}"/></dd>
            <dt><ctg:locale message="car"/></dt>
            <dd><c:out value="${(requestScope.Order.getCarModelYear())}"/></dd>
            <dt><ctg:locale message="orderDate"/></dt>
            <dd><c:out value="${(requestScope.Order.getDate())}"/></dd>
            <dt><ctg:locale message="rejectionReason"/></dt>
            <dd><c:out value="${(requestScope.Order.getRejectionReason())}"/></dd>
        </dl>
</div>
</body>
</html>
