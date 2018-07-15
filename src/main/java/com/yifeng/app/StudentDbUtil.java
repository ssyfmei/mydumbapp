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
		ResultSet  myRs  =null;
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
			close(myConn,myStmt,myRs);
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
				
				Student tempStudent = new Student(firstName, lastName, email);
				
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
}