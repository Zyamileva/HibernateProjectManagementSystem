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
<form action="/developers/create/skill/form" method="post">
    <label style="margin: 15px"> Search developer for add skill: </label><br>
    <select  style="text-align: center; width:250px; font-weight: bold; margin: 15px"; name="developers">
        <c:forEach items="${developers}" var="developer">
            <option  style="text-align: center; width:250px; margin: 15px"; value="${developer.id}">${developer.firstName}  ${developer.lastName}</option>
        </c:forEach>
    </select>
    <label style="margin: 15px"> Search skill for add: </label><br>
    <select  style="text-align: center; width:250px; font-weight: bold; margin: 15px"; name="skills">
        <c:forEach items="${skills}" var="skill">
            <option  style="text-align: center; width:250px; margin: 15px"; value="${skill.id}">${skill.name}  :   ${skill.level}</option>
        </c:forEach>
    </select>

    <button style="margin: 15px" ; type="submit">Add skill</button>
</form>
</body>
</html>