<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 28.08.2018
  Time: 15:24
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
    <title><ctg:locale message="Cars"/></title>
</head>

<body>
<jsp:include page="/jsp/header.jsp"/>

<c:set var="List" value="${sessionScope.Cars}"/>

<c:if test="${(User.getUserRole() eq 'CLIENT') and (User.getUserStatus() eq 'BLOCKED')}">
    <div class="container justify-content-md-center">
        <ctg:locale message="blockedUserMassage"/>
    </div>
</c:if>

<div class="row justify-content-md-center">

        <form class="col-md-6" action="${pageContext.request.contextPath}/doSample" method="get">
    <div class="row">
            <div class="form-group col-md-3">
                <select class="custom-select" name = "mark" required>
        <option value=""> <ctg:locale message="chooseMark"/></option>
                    <option value="Mercedes">Mercedes</option>
                    <option value="BMW">BMW</option>
                    <option value="Lada">Lada</option>
                    <option value="Daewoo">Daewoo</option>
                    <option value="Audi">Audi</option>
                    <option value="Nissan">Nissan</option>
                    <option value="Range Rover">Range Rover</option>
        <option selected ="selected"></option>
        </select>
        <div class="invalid-feedback">Example invalid custom select feedback</div>
    </div>
    <div class="form-group col-md-3">
        <select class="custom-select" name = "class" required>
            <option value=""><ctg:locale message="chooseClass"/></option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="E">E</option>
            <option value="F">F</option>
            <option value="Minibus">Minibus</option>
            <option selected ="selected"></option>
        </select>
        <div class="invalid-feedback">Example invalid custom select feedback</div>
    </div>
        <div class="form-group col-md-3">
            <select class="custom-select" name = "sort" required>
                <option value=""><ctg:locale message="ChooseSortMethod"/></option>
                <option value="mark"><ctg:locale message="mark"/></option>
                <option value="rent price"><ctg:locale message="rentPrice"/></option>
                <option selected ="selected"></option>
            </select>
            <div class="invalid-feedback">Example invalid custom select feedback</div>

    </div>
        <div class="form-group col-md-3">
            <button type="submit " class="btn btn-light"><ctg:locale message="doSample"/></button>
        </div>
    </div>
        </form>

    <table class="table col-md-10 " >
        <thead class="thead-light" >
        <tr>
            <th scope="col"><ctg:locale message="mark"/></th>
            <th scope="col"><ctg:locale message="model"/></th>
            <th scope="col"><ctg:locale message="qualityclass"/></th>
            <th scope="col"><ctg:locale message="year"/></th>
            <th scope="col"><ctg:locale message="rentPrice"/></th>
            <c:if test="${User.getUserRole() eq 'ADMIN'}">
                <th scope="col"><ctg:locale message="status"/></th>
            </c:if>
        </tr>
        </thead>
        <c:forEach var="var" items="${List}">
            <tr>
                <td> <c:out value="${(var.getCarName())}"/></td>
                <td> <c:out value="${(var.getModel())}"/></td>
                <td> <c:out value="${(var.getQualityClass())}"/></td>
                <td> <c:out value="${(var.getYear())}"/></td>
                <td> <c:out value="${(var.getRentPrice())}"/></td>
                <c:if test="${User.getUserRole() eq 'ADMIN'}">
                    <td><c:out value="${(var.getStatus())}"/></td>
                </c:if>
                <c:choose>
                    <c:when test="${(User.getUserRole() eq 'CLIENT' and User.getUserStatus() eq 'NONBLOCKING')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/redirectToCreateOrder" formmethod="get"><ctg:locale message="createOrderButton"/></button>
                                <input type="hidden" value="${var.getId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                   <c:when test="${User.getUserRole() eq 'ADMIN' and (var.getStatus()) eq 'IN_STOCK' }">
                       <td>
                           <form class="form-inline my-2 my-lg-0">
                               <button class="btn btn-link" type="submit" formaction="/deleteCar" formmethod="get"><ctg:locale message="deactivateCarButton"/></button>
                               <input type="hidden" value="${var.getId()}" name = "id"/>
                           </form>
                       </td>
                   </c:when>
                </c:choose>
                <c:if test="${User.getUserRole() eq 'ADMIN'}">
                <td>
                    <form class="form-inline my-2 my-lg-0">
                        <button class="btn btn-link" type="submit" formaction="/redirectToEdit" formmethod="get"><ctg:locale message="editButton"/></button>
                        <input type="hidden" value="${var.getId()}" name = "id" />
                    </form>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
