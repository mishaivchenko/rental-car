<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 29.08.2018
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title><ctg:locale message="CreateOrder"/></title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>

<div class="container">
    <div class="row justify-content-md-center">
    </div>
    <div class="row">
        <form class="col-8" action="${pageContext.request.contextPath}/createOrder" method="post">

            <div class="form-group">
                <label for="firstName"><ctg:locale message="enterYourName"/></label>
                <input name="customerName" type="text" class="form-control" id="firstName" placeholder="name" value="${sessionScope.User.getFirstName()}" required>
            </div>
            <div class="form-group">
                <label for="surname"><ctg:locale message="enterYourSurname"/></label>
                <input name="customerSurname" type="text" class="form-control" id="surname" placeholder="surname" value="${sessionScope.User.getLastName()}" required>
            </div>
            <div class="form-group">
                <label class="checkbox-inline"><input type="checkbox" name="driver" value="true"> <ctg:locale message="driver"/></label>
            </div>
            <div class="form-group">
                <input type="number" name="leaseTerm" min="1" max="30" required><label><ctg:locale message="leaseTerm"/></label>
            </div>
            <button type="submit" class="btn btn-light"><ctg:locale message="CreateOrder"/></button>
        </form>

    </div>

</div>
    <c:out value="${requestScope.IncorrectData}"/>
</body>
</html>
