<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>Marksheet Form</title>
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

		<b>Add Marksheet</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />
			<tr>
				<td><form:label path="rollNo">
						<spring:message code="label.rollNo" />
					</form:label></td>
				<td><form:input path="rollNo" /></td>
				<td><form:errors path="rollNo" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="name">
						<spring:message code="label.name" />
					</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="physics">
						<spring:message code="label.physics" />
					</form:label></td>
				<td><form:input path="physics" /></td>
				<td><form:errors path="physics" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="chemistry">
						<spring:message code="label.chemistry" />
					</form:label></td>
				<td><form:input path="chemistry" /></td>
				<td><form:errors path="chemistry" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="maths">
						<spring:message code="label.maths" />
					</form:label></td>
				<td><form:input path="maths" /></td>
				<td><form:errors path="maths" cssClass="error" /></td>
			</tr>

		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
	</form:form>
</body>
</html>