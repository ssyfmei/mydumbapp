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
		<input type="button" value="Add Student"
			   onclick="window.location.href='add-student-form.jsp';return false;"
		/>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
			<c:forEach var="tempStudent" items="${STUDENT_LIST}">
			
				<c:url var="tempLink" value="ServletControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}"/>
				</c:url>
				<tr>
					<td>${tempStudent.firstName}</td>
					<td>${tempStudent.lastName}</td>
					<td>${tempStudent.email}</td>
					<td><a href="${tempLink}">Update</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
