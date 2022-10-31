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
<form action="/projects">
    <label style="margin: 15px" ; for="projectsName"> Projects name: </label><br>
    <input style="border: 2px solid #39c; margin: 15px" ; type="text" id="projectsName" name="projectsName"><br>
    <button style="margin: 15px" ; type="submit">Find</button>
</form>
<table table-layout:fixed; width:100%;>
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
        <p><strong style="margin: 55px">There is not project by specified name</strong></p>
    </c:if>
    </thead>
    <tbody>
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${projects.name}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${projects.task_difficulty}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${projects.cost}"/>
            </td>
            <td style="text-align: center; width:250px;">
                <c:out value="${projects.datePosted}"/>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>