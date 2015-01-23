<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
<head>
<%@ page isELIgnored="false"%>
</head>
<title>User Form</title>
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> -->

 <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
  <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
  
  <link href="runnable.css" rel="stylesheet" />
  <!-- Load jQuery and the validate plugin -->
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
<script>
$(function() {
  
    // Setup form validation on the #register-form element
    $("#userform").validate({
    
        // Specify the validation rules
        rules: {
        	firstName: "required",
        	lastName: "required",
        	emailId: {
                required: true,
                emailId: true
            },
            password: {
                required: true,
                minlength: 5
            }
        },
        
        // Specify the validation error messages
        messages: {
        	firstName: "Please enter your first name",
        	lastName: "Please enter your last name",
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long"
            },
            emailId: "Please enter a valid email address"
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

  });
  
  </script>
</head>

<body>
	${form.message }
	<form:form action="submit" id="userform" method="post" commandName="form">

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
				<td><form:input path="dob" class="date-picker"  />  </td>
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
	<script>
$(function() {
    $('.date-picker').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'dd/mm/yy',
      
    });
});
</script>
</body>
</html>