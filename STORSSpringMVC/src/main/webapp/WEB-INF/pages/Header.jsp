<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@ page isELIgnored="false"%>
</head>
<body>
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td width="80%"><c:if test="${sessionScope.user != null}">
								<a href="#"><b>Welcome </b></a>

								<c:if test="${sessionScope.user == 1}">
									<a href="/STORSSpringMVC/Marksheet/display"><b>Marksheet</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Marksheet/search"><b>MarksheetList</b></a>
									&emsp;
									<a href="/STORSSpringMVC/User/search"><b>UserList</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Role/display"><b>Role</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Role/search"><b>RoleList</b></a>
									&emsp;
									<a href="/STORSSpringMVC/College/display"><b>College</b></a>
									&emsp;
									<a href="/STORSSpringMVC/College/search"><b>CollegeList</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Student/display"><b>Student</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Student/search"><b>StudentList</b></a>
									&emsp;
									<a
										href="/STORSSpringMVC/MyProfile/display?id=${sessionScope.user}"><b>MyProfile</b></a>
									&emsp;
									<a href="/STORSSpringMVC/ChangePassword/display"><b>Change
											Password</b></a>
									&emsp;
									<a href="/STORSSpringMVC/resources/doc/index.html" target="_blank"><b>Java
											Doc</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Login/logout?operation=Logout"><b>Logout</b></a>
									<br>

								</c:if>

								<c:if test="${sessionScope.user != 1}">
									<a href="/STORSSpringMVC/College/display"><b>Get
											Marksheet</b></a>
									&emsp;
									<a
										href="/STORSSpringMVC/MyProfile/display?id=${sessionScope.user}"><b>MyProfile</b></a>
									&emsp;
									<a href="/STORSSpringMVC/ChangePassword/display"><b>Change
											Password</b></a>
									&emsp;
									<a href="/STORSSpringMVC/resources/doc/index.html" target="_blank"><b>Java
											Doc</b></a>
									&emsp;
									<a href="/STORSSpringMVC/Login/logout?operation=Logout"><b>Logout</b></a>
									<br>

								</c:if>

							</c:if> <c:if test="${sessionScope.user == null}">
								<a href="/STORSSpringMVC/index.jsp"><b>Welcome</b></a>
								&emsp;
								<a href="/STORSSpringMVC/Login/display"><b>Login</b></a></td>
						</c:if>
						<td width="20%">
							<h1 align="Right">
								<img src="../resources/images/customLogo_jpg.png" width="318"
									height="90">
							</h1>
						</td>
					</tr>
				</table>
</body>
</html>