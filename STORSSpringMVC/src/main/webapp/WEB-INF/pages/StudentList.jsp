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

		<form:input path="firstName" />
		<input type="submit" value="Go" name="operation">
		<table border="1">
			<s:if test="${!empty form.dtoList}">
				<tr>
					<th>ID</th>
					<th>College</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>DOB</th>
					<th>Mobile</th>
					<th>Email</th>
					<th>Actions on Row</th>
				</tr>

				<s:forEach items="${form.dtoList}" var="student">
					<tr>
						<td><s:out value="${student.id}" /></td>
						<td><s:out value="${student.collegeName}" /></td>
						<td><s:out value="${student.firstName}" /></td>
						<td><s:out value="${student.lastName}" /></td>
						<td><s:out value="${student.dob}" /></td>
						<td><s:out value="${student.mobileNo}"></s:out></td>
						<td><s:out value="${student.email}"></s:out></td>
						<td align="center"><a
							href="/STORSSpringMVC/Student/display?id=${student.id}">Edit</a>
							| <a
							href="/STORSSpringMVC/Student/search?id=${student.id}&operation=Delete">Delete</a></td>
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