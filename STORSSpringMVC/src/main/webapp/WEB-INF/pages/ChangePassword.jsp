<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>Change Password</title>
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

		<b>Change Password</b>
		<br>
		<br>

		<table>
			<form:hidden path="id" />

			<tr>
				<td><form:label path="oldPassword">
						<spring:message code="label.oldPassword" />
					</form:label></td>
				<td><form:password path="oldPassword" /></td>
				<td><form:errors path="oldPassword" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="newPassword">
						<spring:message code="label.newPassword" />
					</form:label></td>
				<td><form:password path="newPassword" /></td>
				<td><form:errors path="newPassword" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="confirmPassword">
						<spring:message code="label.confirmPassword" />
					</form:label></td>
				<td><form:password path="confirmPassword" /></td>
				<td><form:errors path="confirmPassword" cssClass="error" /></td>
			</tr>

		</table>
		<input type="submit" value="Save" name="operation">
		<input type="reset" value="Reset">
	</form:form>
</body>
</html>