<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
<title>Student Tracker App</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Foo University</h2>
		</div>
	</div>
	<div id="content">
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
			</tr>
			<c:forEach var="tempStudent" items="${STUDENT_LIST}">
			<tr>
				<td>${tempStudent.firstName}</td>
				<td>${tempStudent.lastName}</td>
				<td>${tempStudent.email}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
