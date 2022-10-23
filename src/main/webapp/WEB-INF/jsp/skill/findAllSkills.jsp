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
    <c:if test="${not empty skills}">
        <tr>
            <td style="text-align: center; width:250px; font-weight: bold">Skill name:</td>
            <td style="text-align: center; width:100px; font-weight: bold">Level:</td>
        </tr>
    </c:if>
    <c:if test="${empty skills}">
        <p>There is not skills</p>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="skill" items="${skills}">
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${skill.name}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${skill.level}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>