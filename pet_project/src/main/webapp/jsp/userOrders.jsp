<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 29.08.2018
  Time: 18:46
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
    <title><ctg:locale message="Orders"/></title>
</head>
<body>
<c:set var="List" value="${sessionScope.Orders}"/>
<jsp:include page="/jsp/header.jsp"/>
<c:if test="${User.getUserRole() eq 'MANAGER'}">
<div class="row justify-content-md-center">
    <%-- <div class="row">--%>
    <form class="col-md-6" action="${pageContext.request.contextPath}/doSampleOrders" method="get">
        <div class="row">
            <div class="form-group col-md-3">
                <select class="custom-select" name = "status" required>
                    <option value=""><ctg:locale message="ChooseSampleType"/></option>
                    <option value="ORDERED"><ctg:locale message="Ordered"/></option>
                    <option value="ORDER_CONFIRMED"><ctg:locale message="Order_CONFIRMED"/></option>
                    <option value="NEGATIVE"><ctg:locale message="Negative"/></option>
                    <option value="ORDER_HAS_BEEN_PAID"><ctg:locale message="Order_has_been_paid"/></option>
                    <option value="ORDER_RETURNED"><ctg:locale message="Order_returned"/></option>
                    <option value="RETURN_CONFIRMED"><ctg:locale message="Return_confirmed"/></option>
                    <option value="RETURN_WITH_CRASH"><ctg:locale message="Return_with_crash"/></option>
                    <option selected ="selected"></option>
                </select>
                <div class="invalid-feedback">Example invalid custom select feedback</div>
            </div>
            <div class="form-group">
                <button type="submit " class="btn btn-light"><ctg:locale message="doSample"/></button>
            </div>
        </div>

    </form>
</div>
</c:if>
<div class="row justify-content-md-center">

    <table class="table col-md-10 " >
        <thead class="thead-light" >
        <tr>
            <th scope="col"><ctg:locale message="name"/></th>
            <th scope="col"><ctg:locale message="surname"/></th>
            <th scope="col"><ctg:locale message="car"/></th>
            <th scope="col"><ctg:locale message="status"/></th>
            <th scope="col"><ctg:locale message="orderDate"/></th>
            <th scope="col"><ctg:locale message="leaseTerm"/></th>
            <th scope="col"><ctg:locale message="driver"/></th>
            <th scope="col"><ctg:locale message="sum"/></th>
            <th scope="col"><ctg:locale message="action"/></th>
            <th scope="col"><ctg:locale message="action"/></th>

        </tr>
        </thead>
        <c:forEach var="var" items="${List}">
                <td> <c:out value="${(var.getCustomerName())}"/></td>
                <td> <c:out value="${(var.getCustomerSurname())}"/></td>
                <td> <c:out value="${var.getCarModelYear()}"/></td>
                <td> <c:out value="${(var.getStatus())}"/></td>
                <td> <c:out value="${(var.getDate())}"/></td>
                <td> <c:out value="${(var.getLeaseTerm())}"/></td>
                <td> <c:out value="${(var.isDriver())}"/></td>
                <c:choose>
                <c:when test="${var.getSum()>0}">
                    <td><c:out value="${var.getSum()}"/></td>
                </c:when>
                <c:otherwise>
                    <td><ctg:locale message="AmountNotCalc"/> </td>
                </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${(User.getUserRole() eq 'MANAGER' and var.getStatus() eq 'ORDERED')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/confirmOrder" formmethod="get"><ctg:locale message="approveAnOrder"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>

                    </c:when>

                    <c:when test="${(User.getUserRole() eq 'MANAGER' and var.getStatus() eq 'ORDER_RETURNED')}">
                    <td>
                        <form class="form-inline my-2 my-lg-0">
                            <button class="btn btn-link" type="submit" formaction="/returnConfirm" formmethod="get"><ctg:locale message="ConfirmRefund"/></button>
                            <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                        </form>
                    </td>
                    </c:when>

                    <c:when test="${(User.getUserRole() eq 'CLIENT' and var.getStatus() eq 'ORDER_CONFIRMED')}">
                    <td>
                        <form class="form-inline my-2 my-lg-0">
                            <button class="btn btn-link" type="submit" formaction="/payAnOrder" formmethod="get"><ctg:locale message="payAnOrder"/></button>
                            <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                        </form>
                    </td>
                    </c:when>
                    <c:when test="${(User.getUserRole() eq 'CLIENT' and var.getStatus() eq 'ORDER_HAS_BEEN_PAID')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/returnCar" formmethod="get"><ctg:locale message="return"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${(User.getUserRole() eq 'CLIENT' and var.getStatus() eq 'ORDER_HAS_BEEN_PAID')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/redirectToCreateOrder" formmethod="get"><ctg:locale message="return"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${(User.getUserRole() eq 'CLIENT' and var.getStatus() eq 'RETURN_WITH_CRASH')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/redirectToPayCrash" formmethod="get"><ctg:locale message="seeAndPayFine"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${(User.getUserRole() eq 'MANAGER' and var.getStatus() eq 'ORDERED')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/redirectToReject" formmethod="get"><ctg:locale message="reject"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${(User.getUserRole() eq 'MANAGER' and var.getStatus() eq 'ORDER_RETURNED')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/redirectToCreateCrash" formmethod="get"><ctg:locale message="NotifyDamage"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${(User.getUserRole() eq 'CLIENT' and var.getStatus() eq 'NEGATIVE')}">
                        <td>
                            <form class="form-inline my-2 my-lg-0">
                                <button class="btn btn-link" type="submit" formaction="/showReject" formmethod="get"><ctg:locale message="rejectReason"/></button>
                                <input type="hidden" value="${var.getOrderId()}" name = "id"/>
                            </form>
                        </td>
                    </c:when>
                </c:choose>

            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript" src="../libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="../libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
