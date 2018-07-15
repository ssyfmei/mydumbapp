package com.yifeng.app;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command==null) command="LIST";
		try {
			switch (command) {
			case "ADD":
				addStudents(request, response);
				break;
			case "LIST":
				listStudents(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			default:
				listStudents(request, response);
				break;
			}
		}
		catch(Exception exp) {
			throw new ServletException(exp);
		}
	}
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int theStudentId = Integer.parseInt(request.getParameter("StudentId"));
		String firstName = request.getParameter("firstName");
		String lastName  = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(theStudentId, firstName, lastName, email);
		studentDbUtil.updateStudent(student);
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theStudentId = request.getParameter("StudentId");
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		request.setAttribute("THE_STUDENT", theStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	} 

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-student.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String firstName = request.getParameter("firstName");
		String lastName  = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(firstName, lastName, email);
		studentDbUtil.addStudent(student);
		listStudents(request, response);
	}
}
