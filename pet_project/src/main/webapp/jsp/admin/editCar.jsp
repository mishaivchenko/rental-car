<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 29.08.2018
  Time: 11:08
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
    <title><ctg:locale message="editButton"/></title>
</head>

<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="row justify-content-md-center">
       <%-- <ctg:locale message="chooseClass"/>--%>
           <%--${sessionScope.car.getCarName()}--%>
    </div>
    <div class="row">
        <form class="col-8" action="${pageContext.request.contextPath}/editCar" method="post">
            <c:out value="${requestScope.car}"/>
            <div class="form-group">
                <select class="custom-select" name = "carName" required>
                    <option selected value="${sessionScope.car.getCarName()}">${sessionScope.car.getCarName()}</option>
                    <option value=""><ctg:locale message="chooseMark"/></option>
                    <option value="Mercedes">Mercedes</option>
                    <option value="BMW">BMW</option>
                    <option value="Lada">Lada</option>
                    <option value="Daewoo">Daewoo</option>
                    <option value="Audi">Audi</option>
                    <option value="Nissan">Nissan</option>
                    <option value="Range Rover">Range Rover</option>
                </select>
                <div class="invalid-feedback">Example invalid custom select feedback</div>
            </div>
            <div class="form-group">
                <label for="model"><ctg:locale message="model"/></label>
                <input name="model" type="text" class="form-control" id="model" placeholder="Enter model" value="${(sessionScope.car.getModel())}" required>
            </div>
            <div class="form-group">
                <select class="custom-select" name = "class" required>
                    <option selected value="${(sessionScope.car.getQualityClass())}">${(sessionScope.car.getQualityClass())}</option>
                    <option value=""><ctg:locale message="chooseClass"/></option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                    <option value="E">E</option>
                    <option value="F">F</option>
                    <option value="Minibus">Minibus</option>
                </select>
                <div class="invalid-feedback">Example invalid custom select feedback</div>
            </div>

            <div class="form-group">
                <label for="year"><ctg:locale message="year"/></label>
                <input name="year" type="number" class="form-control" id="year" placeholder="year" min="1950" max="2018" value="${(sessionScope.car.getYear())}" required>
            </div>
            <div class="form-group">
                <label for="rentPrice"><ctg:locale message="rentPrice"/></label>
                <input name="rentPrice" type="number" class="form-control" id="rentPrice" placeholder="rent per day"  value="${(sessionScope.car.getRentPrice())}" min="5" required>
            </div>
            <input type="hidden" name="id" value="${(sessionScope.car.getId())}">
            <button type="submit" class="btn btn-light"><ctg:locale message="editButton"/></button>
        </form>
    </div>
</div>
</body>
</html>
