<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>College Form</title>
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

		<b>Add College</b>
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
				<td><form:label path="address">
						<spring:message code="label.address" />
					</form:label></td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="state">
						<spring:message code="label.state" />
					</form:label></td>
				<td><form:input path="state" /></td>
				<td><form:errors path="state" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="city">
						<spring:message code="label.city" />
					</form:label></td>
				<td><form:input path="city" /></td>
				<td><form:errors path="city" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNo">
						<spring:message code="label.phoneNo" />
					</form:label></td>
				<td><form:input path="phoneNo" /></td>
				<td><form:errors path="phoneNo" cssClass="error" /></td>
			</tr>

		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
		<a href="/STORSSpringMVC/College/search">CollegeList</a>
		<a href="/STORSSpringMVC/Welcome">Welcome</a>
	</form:form>
</body>
</html>