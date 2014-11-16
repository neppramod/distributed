<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Contact</title>
</head>
<body>
	<a href="<c:url value="/contacts/add" />">Add</a>
	<c:if test="${!empty contactList}">
		<div class="table">
			<table border="1" width="50%">
				<tr class="heading">
					<td width="10%">Id</td>
					<td width="20%">Address</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach items="${contactList}" var="contact" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td><a href="view/${contact.id}">${contact.address}</a></td>
						<td><a href="edit/${contact.id}">edit</a> &nbsp;<a
							href="delete/${contact.id}">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>

</body>
</html>