<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.08.2018
  Time: 00:03
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
    <title>create reject</title>
</head>

<body>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row justify-content-md-center">

    </div>
    <div class="row">
        <form class="col-8" action="${pageContext.request.contextPath}/createReject" method="get">
            <div class="form-group">
                <label for="comment"><ctg:locale message="enterRejectReason"/></label>
                <textarea class="form-control" rows="5" id="comment" name="text"></textarea>
                <input type="hidden" value="${id}" name = "id"/>
            </div>
            <button type="submit" class="btn btn-light"><ctg:locale message="createReject"/></button>
        </form>
    </div>
    <c:out value="${requestScope.IncorrectData}"/>
    </div>

</body>

<script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>
</html>
