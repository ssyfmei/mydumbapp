package com.yifeng.app



@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionID = 1L;

	private StudentDbUtil StudentDbUtil;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;


	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listStudents(request, response);
	}

}