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
<form action="/companies">
    <label style="margin: 15px"; for="companyName"> Company name: </label><br>
    <input style="border: 2px solid #39c; margin: 15px"; type="text" id="companyName" name="companyName"><br>
    <button style="margin: 15px"; type="submit">Find</button>
</form>
<table table-layout:fixed; width:100%;>
    <thead>
    <c:if test="${not empty companies}">
        <tr>
            <td style="text-align: center; width:250px; font-weight: bold; margin: 15px">Company name:</td>
            <td style="text-align: center; width:100px; font-weight: bold; margin: 15px">Staff:</td>
        </tr>
    </c:if>
    <c:if test="${empty companies}">
        <p><strong style="margin: 55px">There is not companies by specified name</strong></p>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="company" items="${companies}">
        <tr>
            <td style="text-align: center; width:250px;">
                <c:out value="${company.name}"/>
            </td>
            <td style="text-align: center; width:100px;">
                <c:out value="${company.staff}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>