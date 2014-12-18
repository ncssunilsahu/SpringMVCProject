<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>Forget Password</title>
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

		<b>Forget Password</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />

			<tr>
				<td><form:label path="emailId">
						<spring:message code="label.emailId" />
					</form:label></td>
				<td><form:input path="emailId" /></td>
				<td><form:errors path="emailId" cssClass="error" /></td>
			</tr>

		</table>
		<input type="submit" value="Go" name="operation">
	</form:form>
</body>
</html>