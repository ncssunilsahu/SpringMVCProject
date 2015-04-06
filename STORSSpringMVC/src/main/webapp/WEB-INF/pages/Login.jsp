<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page isELIgnored="false"%>

<h1>Login</h1>

<h2>
	<font color="red">${error}</font>
</h2>

<form:form method="post" commandName="form">

	<br>
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
	<input type="reset" value="Reset">
</form:form>
