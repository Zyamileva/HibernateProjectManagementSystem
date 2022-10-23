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
<form action="/projects/create/developer/form" method="post">
    <label style="margin: 15px"> Search project for add developers: </label><br>
    <select  style="text-align: center; width:250px; font-weight: bold; margin: 15px"; name="projects">
        <c:forEach items="${projects}" var="project">
            <option  style="text-align: center; width:250px; margin: 15px"; value="${project.id}">${project.name}</option>
        </c:forEach>
    </select><br>
    <label style="margin: 15px"> Search developer for add: </label><br>
        <c:forEach items="${developers}" var="developer">
            <input type="checkbox" style="text-align: center; width:250px; margin: 15px"; name="developerId" value="${developer.id}">${developer.firstName} ${developer.lastName}</input><br/>
        </c:forEach><br>
    <button style="margin: 15px"; type="submit">Add developers</button>
</form>
</body>
</html>