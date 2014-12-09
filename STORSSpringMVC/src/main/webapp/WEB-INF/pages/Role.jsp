<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>Role Form</title>
<script type="text/javascript" src="../resources/cal/calendar.js"></script>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>

</head>

<body>
	${form.message }
	<form:form action="submit" method="post" commandName="form">

		<b>Add Role</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />
			<tr>
				<td><form:label path="name">
						<spring:message code="label.name" />
					</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="description">
						<spring:message code="label.description" />
					</form:label></td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssClass="error" /></td>
			</tr>


		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
	</form:form>
</body>
</html>