package Default;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {

	public static void main(String[] args) throws IOException{
		System.out.println("Welcome to Student Management App");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("Press 1 to Add Student");
			System.out.println("Press 2 to Delete Student");
			System.out.println("Press 3 to Display Student");
			System.out.println("Press 4 to Update Student");
			System.out.println("Press 5 to Exit");
			
			int c = Integer.parseInt(br.readLine());
			
			if(c == 1) {
				//add student
				
				System.out.println("Enter username: ");
				String name = br.readLine();
				
				System.out.println("Enter contact number: ");
				String phone = br.readLine();
				
				System.out.println("Enter city: ");
				String city = br.readLine();
				
				Student st = new Student(name, phone, city);
				boolean ans =StudentDAO.insertStudentToDB(st);
				
				if(ans) {
					System.out.println("Student added successfully");
				} else {
					System.out.println("Something went wrong.....try again");
				}
				
				System.out.println(st);
				
			} else if(c == 2) {
				//delete student
				System.out.println("Enter student id to delete: ");
				int userId = Integer.parseInt(br.readLine());
				
				boolean ans = StudentDAO.deleteStudent(userId);
				
				if(ans) {
					System.out.println("Deleted");
				} else {
					System.out.println("Something went wrong.....try again");
				}
				
			} else if(c == 3) {
				//display student				
				StudentDAO.showAllStudent();
				
			} else if(c == 4) {
				
				System.out.println("Enter StudentID: ");
				int studentID = Integer.parseInt(br.readLine());
				
				System.out.println("Enter to what change you need to update...");
				String updateValue = br.readLine();
			
				boolean ans = StudentDAO.updateStudent(studentID, updateValue);
				
				if(ans) {
					System.out.println("Student details updated successfully");
				} else {
					System.out.println("Something went wrong.....pls try again");
				}
			
			} else if(c == 5) { 
				//exit
				break;
			} else {
				//Incorrect number
				System.out.println("Please select only above given numbers to proceed....");
			}
			
		}
		
		
		System.out.println("Thank you for using my application");
		System.out.println("See you soon....Sayonara!!");
		

	}

}
