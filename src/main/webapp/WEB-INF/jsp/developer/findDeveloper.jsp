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
<form action="/developers">
    <label style="margin: 15px" ; for="developerLastName"> Developer last name: </label><br>
    <input style="border: 2px solid #39c; margin: 15px" ; type="text" id="developerLastName"
           name="developerLastName"><br>
    <button style="margin: 15px" ; type="submit">Find</button>
</form>
<table table-layout:fixed; width:100%;>
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
        <p><strong style="margin: 55px">There is not developer by specified name</strong></p>
    </c:if>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${developers.firstName}"/>
            </td>
            <td style="text-align: center; width:250px;">
                <c:out value="${developers.lastName}"/>
            </td>
            <td style="text-align: center; width:200px;">
                <c:out value="${developers.email}"/>
            </td>
            <td style="text-align: center; width:200px;">
                <c:out value="${developers.phoneNumber}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${developers.salary}"/>
            </td>
        </tr>
    </tbody>
</table>
<br>
<table table-layout:fixed; width:100%;>
    <thead>
    <c:if test="${not empty skills}">
        <tr>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Skill name:</td>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Skill level:</td>
        </tr>
    </c:if>
    <c:if test="${empty developers}">
        <p><strong style="margin: 55px">There is not developers`s skills</strong></p>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="skill" items="${skills}">
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${skill.name}"/>
            </td>
            <td style="text-align: center; width:250px;">
                <c:out value="${skill.level}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>