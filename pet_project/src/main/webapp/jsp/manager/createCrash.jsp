<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.08.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../libs/bootstrap-4.1.3/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row">

            <div class="form-group">
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
    </dl>
            </div>
        <form class="col-8" action="${pageContext.request.contextPath}/createDamage" method="get">
            <div class="form-group">
                <label for="crashDesc"><ctg:locale message="enterCrashDesc"/></label>
                <textarea class="form-control" rows="5" id="crashDesc" name="crashDesc" required></textarea>
            </div>

            <div class="form-group">
                <label for="CrashSum"><ctg:locale message="sum"/></label>
                <input name="sum" type="number" class="form-control" id="CrashSum" placeholder="crashDesc" min="5" required>
            </div>
            <input type="hidden" value="${id}" name = "id"/>
            <button type="submit" class="btn btn-light"><ctg:locale message="NotifyDamage"/></button>
        </form>
        <c:out value="${requestScope.IncorrectData}"/>
</div>
</div>
<script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
