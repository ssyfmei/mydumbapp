package  com.yifeng.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;
	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void addStudent(Student student) throws SQLException {
		Connection myConn=null;
		PreparedStatement  myStmt=null;
		try {
			myConn = dataSource.getConnection();
			String sql = "insert into student"
						+"(first_name, last_name, email)"
						+"value (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	
	public List<Student> getStudents() throws SQLException { 
		List<Student> students = new ArrayList<>();
		
		Connection myConn=null;
		Statement  myStmt=null;
		ResultSet  myRs  =null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select * from student order by last_name";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);
			}
			return students;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Student getStudent(String theStudentId) throws SQLException {
		Student theStudent = null;
		Connection  		myConn=null;
		PreparedStatement  	myStmt=null;
		ResultSet  			myRs  =null;
		
		try {
			int studentId = Integer.parseInt(theStudentId);
			myConn = dataSource.getConnection();
			
			String sql = "select * from student where id = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				theStudent = new Student(firstName, lastName, email);
			}
			else {
				throw new SQLException("Could not find student id: " + studentId);
			}
			return theStudent;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}
	public void updateStudent(Student student) throws SQLException {
		Connection  		myConn=null;
		PreparedStatement  	myStmt=null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "update student "
						+"set first_name=?, last_name=?, email=? "
						+"where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirstName()+"fdfafafa");
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4, student.getId());
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
}