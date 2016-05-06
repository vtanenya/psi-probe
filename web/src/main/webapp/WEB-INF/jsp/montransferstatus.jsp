<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Test</title>
</head>
<body>

<h3>Test</h3>
<c:if test="${!empty statusList}">
    <table class="data">
        <c:forEach items="${statusList}" var="status">
            <tr>
                <td>${status.uuid}, ${status.objectUUID}</td>
                <td>${status.transferStatus}</td>
                <td>${status.comment}</td>
                <td><a href="delete/${status.uuid}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>