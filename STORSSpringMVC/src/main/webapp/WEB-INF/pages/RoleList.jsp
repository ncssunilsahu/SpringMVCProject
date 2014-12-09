<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<head>
<%@ page isELIgnored="false"%>
</head>

</head>

<body>

	<form:form action="search" commandName="form" method="post">

		<form:input path="name" />
		<input type="submit" value="Go" name="operation">
		<table border="1">
			<s:if test="${!empty form.dtoList}">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
				</tr>

				<s:forEach items="${form.dtoList}" var="role">
					<tr>
						<td><s:out value="${role.id}" /></td>
						<td><s:out value="${role.name}" /></td>
						<td><s:out value="${role.description}" /></td>
					</tr>
				</s:forEach>
			</s:if>

		</table>

		<form:hidden path="pageNo" />
		<form:hidden path="pageSize" />
		<input type="submit" value="Next" name="operation">
		<input type="submit" value="Previous" name="operation">
	</form:form>
</body>
</html>