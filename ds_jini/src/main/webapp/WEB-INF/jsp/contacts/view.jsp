<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Person</title>
</head>
<body>

	<c:if test="${!empty contact}">
		<table>
			<tr>
				<td>Address :</td>
				<td>${contact.address}</td>
			</tr>
		</table>
	</c:if>
	<a href="<c:url value="/contacts/" />">Ok</a>
</body>
</html>