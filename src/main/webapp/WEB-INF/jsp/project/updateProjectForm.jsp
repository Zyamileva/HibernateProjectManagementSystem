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
<form action="/projects/update" method="post">
    <label style="margin: 15px"; for="projectName"> Project name: </label><br>
    <input style="border: 2px solid #99c; margin: 15px"; type="text" id="projectName" name="projectName"><br>
    <label style="margin: 15px"; for="projectNewName"> Project new name: </label><br>
    <input style="border: 2px solid #99c; margin: 15px"; type="text" id="projectNewName" name="projectNewName"><br>
    <label style="margin: 15px"; for="taskDifficulty"> Task difficulty: </label><br>
    <input style="border: 2px solid #39c; margin: 15px"; type="text" id="taskDifficulty" name="taskDifficulty"><br>
    <label style="margin: 15px"; for="Cost"> Cost: </label><br>
    <input style="border: 2px solid #39c; margin: 15px"; type="text" id="Cost" name="Cost"><br>

    <label style="margin: 15px"> Search company: </label><br>
    <select  style="text-align: center; width:250px; font-weight: bold; margin: 15px"; name="newCompany">
        <c:forEach items="${company}" var="newCompany">
            <option  style="text-align: center; width:250px; margin: 15px"; value="${newCompany.id}">${newCompany.name}</option>
        </c:forEach>
    </select><br>
    <label style="margin: 15px"> Search customer: </label><br>
    <select  style="text-align: center; width:250px; font-weight: bold; margin: 15px"; name="newCustomer">
        <c:forEach items="${customer}" var="newCustomer">
            <option  style="text-align: center; width:250px; margin: 15px"; value="${newCustomer.id}">${newCustomer.name}</option>
        </c:forEach>
    </select>
    <button style="margin: 15px" ; type="submit">Update project</button>
</form>
</body>
</html>