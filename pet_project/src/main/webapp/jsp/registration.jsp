<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 26.08.2018
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"   %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <meta http-equiv='content-type' content='text/html; charset=utf-8' >
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title><ctg:locale message="Registration"/></title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="container">
    <div class="row justify-content-md-center">

    </div>
            <div class="row">
                <form class="col-8" action="${pageContext.request.contextPath}/Registration" method="post">
                    <div class="form-group">
                        <label for="registrationEmail"><ctg:locale message="email"/></label>
                        <input name="email" type="email" class="form-control" id="registrationEmail" aria-describedby="emailHelp" placeholder="Введите email">
                        <small id="registrationEmailHelp" class="form-text text-muted"><ctg:locale message="validEmail"/></small>
                    </div>
                    <div class="form-group">
                        <label for="registrationPassword"><ctg:locale message="password"/></label>
                        <input name="password" type="password" class="form-control" id="registrationPassword">
                    </div>
                    <div class="form-group">
                        <label for="registrationName"><ctg:locale message="name"/></label>
                        <input name="name" type="text" class="form-control" id="registrationName" >
                    </div>

                    <div class="form-group">
                        <label for="registrationSurname"><ctg:locale message="surname"/></label>
                        <input name="secondName" type="text" class="form-control" id="registrationSurname" >
                    </div>
                    <div class="form-group">
                        <label for="registrationUserName"><ctg:locale message="username"/></label>
                        <input name="username" type="text" class="form-control" id="registrationUserName" >
                    </div>
                    <c:out value="${requestScope.IncorrectData}"/>
                    <button type="submit" class="btn btn-light"><ctg:locale message="Registration"/></button>
                </form>
            </div>
</div>

    <script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
    <script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
