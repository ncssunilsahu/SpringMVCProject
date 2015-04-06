<%@page import="in.co.sunrays.spring.ctl.AppConfig"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page isELIgnored="false"%>

<h2>
	<font color="green">${success}</font>
</h2>
<h2>
	<font color="red">${error}</font>
</h2>

<form:form method="POST" commandName="form">

	<b>College</b>
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
</form:form>
