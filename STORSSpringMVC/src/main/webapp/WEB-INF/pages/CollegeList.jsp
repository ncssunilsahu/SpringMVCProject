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

				<s:forEach items="${form.dtoList}" var="college">
					<tr>
						<td><s:out value="${college.id}" /></td>
						<td><s:out value="${college.name}" /></td>
						<td><s:out value="${college.address}" /></td>
						<td><s:out value="${college.state}" /></td>
						<td><s:out value="${college.city}" /></td>
						<td><s:out value="${college.phoneNo}"></s:out></td>
					</tr>
				</s:forEach>
			</s:if>

		</table>

		<form:hidden path="pageNo" />
		<form:hidden path="pageSize" />
		<input type="submit" value="Next" name="operation">
		<input type="submit" value="Previous" name="operation">
		<a href="/Test/College/display">College</a>
	</form:form>
</body>
</html>