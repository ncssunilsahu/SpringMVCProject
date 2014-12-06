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
					<th>Address</th>
					<th>State</th>
					<th>City</th>
					<th>Phone</th>
				</tr>

				<s:forEach items="${form.dtoList}" var="user">
					<tr>
						<td><s:out value="${user.id}" /></td>
						<td><s:out value="${user.firstName}" /></td>
						<td><s:out value="${user.lastName}" /></td>
						<td><s:out value="${user.login}" /></td>
						<td><s:out value="${user.gender}" /></td>
						<td><s:out value="${user.mobile}"></s:out></td>
					</tr>
				</s:forEach>
			</s:if>

		</table>

		<form:hidden path="pageNo" />
		<form:hidden path="pageSize" />
		<input type="submit" value="Next" name="operation">
		<input type="submit" value="Previous" name="operation">
		<a href="/STORSSpringMVC/User/display">College</a>
	</form:form>
</body>
</html>