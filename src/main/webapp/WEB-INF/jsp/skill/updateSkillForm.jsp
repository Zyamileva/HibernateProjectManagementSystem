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
<form action="/skills/update" method="post">
    <label style="margin: 15px"; for="skillName"> Skill name: </label><br>
    <input style="border: 2px solid #99c; margin: 15px"; type="text" id="skillName" name="skillName"><br>
    <label style="margin: 15px"; for="newSkillName"> New Skill name: </label><br>
    <input style="border: 2px solid #39c; margin: 15px"; type="text" id="newSkillName" name="newSkillName"><br>
    <button style="margin: 15px" ; type="submit">Update skill name</button>
</form>
</body>
</html>