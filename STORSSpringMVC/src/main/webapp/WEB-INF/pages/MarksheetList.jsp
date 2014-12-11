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
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Actions on Row</th>
				</tr>

				<s:forEach items="${form.dtoList}" var="marksheet">
					<tr>
						<td><s:out value="${marksheet.id}" /></td>
						<td><s:out value="${marksheet.rollNo}" /></td>
						<td><s:out value="${marksheet.name}" /></td>
						<td><s:out value="${marksheet.physics}" /></td>
						<td><s:out value="${marksheet.chemistry}" /></td>
						<td><s:out value="${marksheet.maths}"></s:out></td>
						<td align="center"><a
							href="/STORSSpringMVC/Marksheet/display?id=${marksheet.id}">Edit</a>
							| <a
							href="/STORSSpringMVC/Marksheet/search?id=${marksheet.id}&operation=Delete">Delete</a></td>
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