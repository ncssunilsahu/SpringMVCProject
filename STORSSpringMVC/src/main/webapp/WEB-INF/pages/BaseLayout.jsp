<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
</head>
<body>
	<table border="1" cellspacing="2" align="center" bordercolor="black">

		<tr>
			<td colspan="2" height="120px"><tiles:insertAttribute
					name="header"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td colspan="1" height="400px" width="300"><tiles:insertAttribute
					name="menu"></tiles:insertAttribute></td>
			<td colspan="1" height="400px" width="700"><tiles:insertAttribute
					name="body"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td colspan="2" height="80px" width="1000" align="center"><tiles:insertAttribute
					name="footer"></tiles:insertAttribute></td>
		</tr>
	</table>
</body>
</html>