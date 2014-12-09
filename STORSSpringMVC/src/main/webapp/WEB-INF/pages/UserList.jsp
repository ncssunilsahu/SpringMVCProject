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
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email Id</th>
					<th>Gender</th>
					<th>Mobile No</th>
					<th>Actions on Row</th>
				</tr>

				<s:forEach items="${form.dtoList}" var="user">
					<tr>
						<td><s:out value="${user.id}" /></td>
						<td><s:out value="${user.firstName}" /></td>
						<td><s:out value="${user.lastName}" /></td>
						<td><s:out value="${user.emailId}" /></td>
						<td><s:out value="${user.gender}" /></td>
						<td><s:out value="${user.mobileNo}"></s:out></td>
						<td align="center"><a
							href="/STORSSpringMVC/User/display?id=${user.id}">Edit</a> | <a
							href="/STORSSpringMVC/User/search?id=${user.id}&operation=Delete">Delete</a></td>
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