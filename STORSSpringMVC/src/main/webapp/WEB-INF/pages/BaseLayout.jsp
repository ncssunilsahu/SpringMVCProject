<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<style type="text/css">
.errorMessage {
	color: red;
	font-size: 16px;
}
</style>
</head>
<body>
	<table width="100%">
		<tr>
			<td align="center" colspan="2"><tiles:insertAttribute
					name="header" /></td>
		</tr>
		<tr>
			<td height="400" valign="top" align="center"><tiles:insertAttribute
					name="body" /></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><tiles:insertAttribute
					name="footer" /></td>
		</tr>
	</table>
</body>
</html>