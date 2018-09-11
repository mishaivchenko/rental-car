<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 26.08.2018
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title><ctg:locale message="Home"/></title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>
<h1>${Welcome}</h1>
<c:if test="${requestScope.result != null}">
<h1><ctg:locale message="succesAdd"/>  ${requestScope.result}</h1>
</c:if>
<script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>
    </body>
</html>
