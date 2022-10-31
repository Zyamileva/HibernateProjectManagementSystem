<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/jsp/navigation.jsp"/>
<table>
    <thead>
    <c:if test="${not empty developers}">
        <tr>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Developer first name:</td>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Developer last name:</td>
            <td style="text-align: center; width:200px; font-weight: bold; margin: 15px">email:</td>
            <td style="text-align: center; width:200px; font-weight: bold; margin: 15px">Phone number:</td>
            <td style="text-align: center; width:100px; font-weight: bold; margin: 15px">Salary:</td>
        </tr>
    </c:if>
    <c:if test="${empty developers}">
        <p>There is not developers</p>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="developer" items="${developers}">
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${developer.firstName}"/>
            </td>
            <td style="text-align: center; width:250px;">
                <c:out value="${developer.lastName}"/>
            </td>
            <td style="text-align: center; width:200px;">
                <c:out value="${developer.email}"/>
            </td>
            <td style="text-align: center; width:200px;">
                <c:out value="${developer.phoneNumber}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${developer.salary}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>