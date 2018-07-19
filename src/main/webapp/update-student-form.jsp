<!DOCTYPE html>
<html>
<head>
	<title>Update Student</title>
</head>
<body>
	<div id = "wrapper">
		<h2>Foo University</h2>
	</div>
	<div id = "container">
		<h3>Update Student</h3>
		<form action="StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
			<table>
			  <tbody>
				<tr>
					<td><label>First Name:</label>
					<td><input type="text" name="firstName"
							   value="${THE_STUDENT.firstName}"/></td>
				</tr>
				<tr>
					<td><label>Last Name:</label>
					<td><input type="text" name="lastName"
					           value="${THE_STUDENT.lastName}"/></td>
				</tr>
				<tr>
					<td><label>Email:</label>
					<td><input type="text" name="email"
							   value="${THE_STUDENT.email}"/></td>
				</tr>
				<tr>
					<td><label></label>
					<td><input type="submit" value="Update"/></td>
				</tr>
			  </tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>
</html>