<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>User Form</title>
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

		<b>Add User</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />
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
				<td><form:label path="emailId">
						<spring:message code="label.emailId" />
					</form:label></td>
				<td><form:input path="emailId" /></td>
				<td><form:errors path="emailId" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="password">
						<spring:message code="label.password" />
					</form:label></td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">
						<spring:message code="label.gender" />
					</form:label></td>
				<td><form:radiobutton path="gender" value="Male" />Male<form:radiobutton
						path="gender" value="Female" />Female</td>
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

		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
	</form:form>
</body>
</html>