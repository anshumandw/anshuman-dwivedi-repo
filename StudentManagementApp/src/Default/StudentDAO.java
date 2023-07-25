package Default;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDAO {
		
	public static boolean insertStudentToDB(Student student) {
		//jdbc code...
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			String q = "insert into students(sname, sphone, scity) values(?, ?, ?)";
			//PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(q);
			
			//
			pstmt.setString(1,  student.getStudentName());
			pstmt.setString(2,  student.getStudentPhone());
			pstmt.setString(3,  student.getStudentCity());
			
			//execute..
			pstmt.executeUpdate();
			
			flag = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}

	public static boolean deleteStudent(int userId) {
		
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			String q = "delete from students where sid = ?";
			//PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(q);
			
			pstmt.setInt(1,  userId);
			
			//execute..
			pstmt.executeUpdate();
			
			flag = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}

	public static void showAllStudent() {
		
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			String q = "select * from students";
			
			Statement stmt = con.createStatement();
			
			ResultSet set = stmt.executeQuery(q);
			
			while(set.next()) {
				
				int studentID = set.getInt(1);
				String studentName = set.getString(2);
				String studentPhone = set.getString(3);
				String studentCity = set.getString(4);
				
				System.out.println("StudentID: " + studentID + ", StudentName: " + studentName + ", StudentPhone=" + studentPhone
						+ ", StudentCity=" + studentCity);
				
				System.out.println();
				
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static boolean updateStudent(int userId, String updatedValue) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			String q = "update students set sname = ? where sid = ?";
//			, sname = ?, sphone = ?, scity = ?
			//PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(q);
			
			pstmt.setString(1, updatedValue);
			pstmt.setInt(2, userId);
			
			pstmt.executeUpdate();
			
			flag = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
}	
