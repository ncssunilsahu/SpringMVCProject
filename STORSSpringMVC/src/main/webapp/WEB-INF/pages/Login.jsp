<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>Login Form</title>
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

		<h1>Login</h1>

		<table>
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
				<td><form:password path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>

		</table>
		<input type="submit" value="SignIn" name="operation">
	</form:form>
	<input type="submit" value="Sign Up"
		style="margin-left: 300px; margin-top: -63px"
		onclick="location.href='/STORSSpringMVC/User/display'"
		name="operation">
	<a href="/STORSSpringMVC/ForgetPassword/display">ForgetPassword</a>
</body>
</html>