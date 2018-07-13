package  com.yifeng.app

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudens() throws Exception { 
		List<Student> students = new ArrayList<>();
		
		Connection myConn=null;
		Statement  myStmt=null;
		ResultSet  myRs  =null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from student order by last_name";
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("lastName");
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