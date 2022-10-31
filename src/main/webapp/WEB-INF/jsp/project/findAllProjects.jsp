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
    <c:if test="${not empty projects}">
        <tr>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Projects name:</td>
            <td style="text-align: center; width:100px; font-weight: bold; margin: 15px">Task difficulty:</td>
            <td style="text-align: center; width:100px; font-weight: bold; margin: 15px">Cost:</td>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Date create:</td>
        </tr>
    </c:if>
    <c:if test="${empty projects}">
        <p>There is not projects</p>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="project" items="${projects}">

        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${project.name}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${project.task_difficulty}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${project.cost}"/>
            </td>
            <td style="text-align: center; width:250px;">
                <c:out value="${project.datePosted}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>