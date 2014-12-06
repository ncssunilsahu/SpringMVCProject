<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>College Form</title>
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

		<b>Add Student</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />
			<tr>
				<td><form:label path="collegeName">
						<spring:message code="label.collegeName" />
					</form:label></td>
				<td><form:input path="collegeName" /></td>
				<td><form:errors path="collegeName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="firstName">
						<spring:message code="label.firstName" />
					</form:label></td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">
						<spring:message code="label.lastName" />
					</form:label></td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="dob">
						<spring:message code="label.dob" />
					</form:label></td>
				<td><form:input path="dob" readonly="true" /> <a
					href="javascript:getCalendar(document.forms[0].dob);"> <img
						src="../resources/images/cal.jpg" width="16" height="15"
						border="0"></a></td>
				<td><form:errors path="dob" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="mobileNo">
						<spring:message code="label.mobileNo" />
					</form:label></td>
				<td><form:input path="mobileNo" /></td>
				<td><form:errors path="mobileNo" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="email">
						<spring:message code="label.email" />
					</form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>


		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
		<a href="/STORSSpringMVC/Student/search">StudentList</a>
		<a href="/STORSSpringMVC/Welcome">Welcome</a>
	</form:form>
</body>
</html>