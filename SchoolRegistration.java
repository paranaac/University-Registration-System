
/**************************************************************************
*                                                                         *
*     Program Filename:  SchoolRegistration.java                          *
*     Author          :  Alejandro Parana                                 *
*     Date Written    :  April 4, 2016                                    *
*     Purpose         :  Create university registration system            *
*                                                                         *
**************************************************************************/
import java.sql.*;
import java.util.Scanner;

public class SchoolRegistration {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String choice;
		Scanner in = new Scanner(System.in);
		Connection conn = DriverManager.getConnection("jdbc:odbc:SCHOOL_DSN");
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		do {
			greet();
			choice = in.next();
			switch (choice) {
			case "1":
				addCourse(conn);
				break;
			case "2":
				deleteCourse(conn);
				break;
			case "3":
				addStudent(conn);
				break;
			case "4":
				deleteStudent(conn);
				break;
			case "5":
				registerCourse(conn);
				break;
			case "6":
				dropCourse(conn);
				break;
			case "7":
				checkRegistration(conn);
				break;
			case "8":
				uploadGrades(conn);
				break;
			case "9":
				checkGrades(conn);
				break;
			case "10": {
				System.out.println("Goodbye.");
				break;
			}
			default: {
				System.out.println("Invalid choice.");
				break;
			}
			}
		} while (!choice.equals("10"));

		conn.close();
	}

	public static void addCourse(Connection conn) {
		Scanner in = new Scanner(System.in);
		System.out.println("-> ADD A COURSE");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter course code:\n> ");
			String code = in.nextLine().trim();
			System.out.print("Enter course title:\n> ");
			String title = in.nextLine().trim();
			// query
			st.executeUpdate("insert into course values('" + code + "','" + title + "')");
			st.close();
                        System.out.println("Course added successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void deleteCourse(Connection conn) {
		Scanner in = new Scanner(System.in);
		System.out.println("-> DELETE A COURSE");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter course code:\n> ");
			String code = in.nextLine().trim();
			// query
			st.executeUpdate("delete from registered where code = '" + code + "'");
			st.executeUpdate("delete from course where code = '" + code + "'");
			st.close();
                        System.out.println("Course deleted successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	private static void addStudent(Connection conn) {
		Scanner intIn = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		System.out.println("-> ADD A STUDENT");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter student SSN:\n> ");
			int ssn = intIn.nextInt();
			System.out.print("Enter student name:\n> ");
			String name = in.nextLine().trim();
			System.out.print("Enter student address:\n> ");
			String addr = in.nextLine().trim();
			System.out.print("Enter student major:\n> ");
			String major = in.nextLine().trim();
			// query
			st.executeUpdate("insert into student values('" + ssn + "','" + name + "','" + addr + "','" + major + "')");
			st.close();
                        System.out.println("Student added successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void deleteStudent(Connection conn) {
		Scanner in = new Scanner(System.in);
		Scanner intIn = new Scanner(System.in);
		System.out.println("-> DELETE A STUDENT");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter student SSN:\n> ");
			int ssn = intIn.nextInt();
			// query
			st.executeUpdate("delete from registered where ssn = '" + ssn + "'");
			st.executeUpdate("delete from student where ssn = '" + ssn + "'");
			st.close();
                        System.out.println("Student deleted successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void registerCourse(Connection conn) {
		Scanner intIn = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		System.out.println("-> REGISTER A COURSE");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter student SSN:\n> ");
			int ssn = intIn.nextInt();
			System.out.print("Enter course code:\n> ");
			String code = in.nextLine().trim();
			System.out.print("Enter course year:\n> ");
			String year = in.nextLine().trim();
			System.out.print("Enter course semester:\n> ");
			String semester = in.nextLine().trim();
			int registered = 0;
			// query
			ResultSet rs = st.executeQuery("select count(*) from registered where ssn = '" + ssn + "' and code = '"
					+ code + "' and year = '" + year + "' and semester = '" + semester + "'");
			if (rs.next())
				registered = ((Number) rs.getObject(1)).intValue();

			if (registered > 0) {
				System.out.println("The student is already registered for this course.");
			} else {
				// query
				st.executeUpdate("insert into registered values(" + ssn + ", '" + code + "', '" + year + "', '"
						+ semester + "', '')");
				System.out.println("Course registered successfully.");
			}
			st.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void dropCourse(Connection conn) {
		Scanner in = new Scanner(System.in);
		Scanner intIn = new Scanner(System.in);
		System.out.println("-> DROP A COURSE");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter course code:\n> ");
			String code = in.nextLine().trim();
			System.out.print("Enter student SSN:\n> ");
			int ssn = intIn.nextInt();
			System.out.print("Enter course year:\n> ");
			String year = in.nextLine().trim();
			System.out.print("Enter course semester:\n> ");
			String semester = in.nextLine().trim();
			// query
			st.executeUpdate("delete from registered where ssn = '" + ssn + "' and code = '" + code + "' and year = '"
					+ year + "' and semester = '" + semester + "'");
			st.close();
                        System.out.println("Course dropped successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void checkRegistration(Connection conn) {
		Scanner in = new Scanner(System.in);
		Scanner intIn = new Scanner(System.in);
		System.out.println("-> CHECK REGISTRATION");
		try {
			Statement st = conn.createStatement();
			String choice;
			OUTER: do {
				System.out.print("To Enter Name Type \"1\" or Type \"2\" to Enter SSN\n> ");
				choice = in.nextLine();
				switch (choice) {
				case "2": {
					System.out.print("Enter student SSN:\n> ");
					int ssn = intIn.nextInt();
					// query
					ResultSet rs = st
							.executeQuery("select code, year, semester from registered where ssn = '" + ssn + "'");
					String code = "", year = "", semester = "";
					while (rs.next()) {
						code = rs.getString(1);
						year = rs.getString(2);
						semester = rs.getString(3);
						System.out.println("Code: " + code + "\tYear: " + year + "\tSemester: " + semester);
					}
					rs.close();
					break OUTER;
				}
				case "1": {
					System.out.print("Enter student name:\n> ");
					String name = in.nextLine().trim();
					// query
					ResultSet rs2 = st.executeQuery(
							"select code, year, semester from registered r, student s where r.ssn = s.ssn and s.name = '"
									+ name + "'");
					String code = "", year = "", semester = "";
					while (rs2.next()) {
						code = rs2.getString(1);
						year = rs2.getString(2);
						semester = rs2.getString(3);
						System.out.println("Code: " + code + "\tYear: " + year + "\tSemester: " + semester);
					}
					rs2.close();
					break OUTER;
				}
				default:
					System.out.println("Invalid choice");
					break;
				}
			} while (!(choice.equals("1")) || (choice.equals("2")));
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void uploadGrades(Connection conn) {
		Scanner in = new Scanner(System.in);
		System.out.println("-> UPLOAD GRADES");
		try {
			Statement st = conn.createStatement();
			System.out.print("Enter course code::\n> ");
			String code = in.nextLine().trim();
			System.out.print("Enter course year:\n> ");
			String year = in.nextLine().trim();
			System.out.print("Enter course semester:\n> ");
			String semester = in.nextLine().trim();
			// query
			ResultSet rs = st.executeQuery("select name from registered r, student s where r.ssn = s.ssn and r.code = '"
					+ code + "' and r.year = '" + year + "' and r.semester = '" + semester + "'");
			String sName, grade;
			while (rs.next()) {
				sName = rs.getString(1);
				System.out.print("Enter a grade for " + sName.trim() + ": ");
				grade = in.nextLine();
				updateGrade(grade, sName, code, year, semester, conn);
			}
			rs.close();
                        System.out.println("Grades uploaded successfully.");
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void updateGrade(String grade, String sName, String code, String year, String semester,
			Connection conn2) {
		try {
			Statement st2 = conn2.createStatement();
			// query
			st2.executeUpdate("update registered set grade = '" + grade
					+ "' from registered inner join student on registered.ssn = student.ssn" + " where code = '" + code
					+ "' and year = '" + year + "' and semester = '" + semester + "' and name = '" + sName + "'");
			st2.close();
			conn2.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void checkGrades(Connection conn) {
		Scanner in = new Scanner(System.in);
		Scanner intIn = new Scanner(System.in);
		System.out.println("-> CHECK GRADES");
		try {
			Statement st = conn.createStatement();
			String choice;
			OUTER: do {
				System.out.print("To Use Name Type \"1\" or Type \"2\" to Use SSN\n> ");
				choice = in.nextLine();
				switch (choice) {
				case "2": {
					System.out.print("Enter student SSN:\n> ");
					int ssn = intIn.nextInt();
					System.out.print("Enter course code::\n> ");
					String code = in.nextLine().trim();
					// query
					ResultSet rs = st.executeQuery(
							"select grade from registered where ssn = '" + ssn + "' and code = '" + code + "'");
					if (rs.next()) {
						String grade = rs.getString(1);
						System.out.println("The grade for " + code + " is: " + grade);
					}
					rs.close();
					break OUTER;
				}
				case "1": {
					System.out.print("Enter student name:\n> ");
					String name = in.nextLine().trim();
					System.out.print("Enter course code:\n> ");
					String code = in.nextLine().trim();
					System.out.print("Enter year:\n> ");
					String year = in.nextLine().trim();
					System.out.print("Enter semester:\n> ");
					String semester = in.nextLine().trim();
					// query
					ResultSet rs = st.executeQuery("select grade from registered r, student s where r.ssn = s.ssn and"
							+ " s.name = '" + name + "' and r.code = '" + code + "' and r.year = '" + year
							+ "' and r.semester = '" + semester + "'");
					if (rs.next()) {
						String grade = rs.getString(1);
						System.out.println("The grade for " + code + " is: " + grade);
					}
					rs.close();
					break OUTER;
				}
				default:
					System.out.println("Invalid choice");
					break;
				}
			} while (!(choice.equals("1")) || !(choice.equals("2")));
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Press \"ENTER\" to continue...");
		in.nextLine();
	}

	public static void greet() {
		System.out.print("*********************************************************\n"
				+ "                                                         \n"
				+ "          Welcome to Online Registration System          \n"
				+ "                                                         \n"
				+ "*********************************************************\n");
		System.out.println("1.   Add a course.");
		System.out.println("2.   Delete a course.");
		System.out.println("3.   Add a student.");
		System.out.println("4.   Delete a student.");
		System.out.println("5.   Register a course.");
		System.out.println("6.   Drop a course.");
		System.out.println("7.   Check student registration.");
		System.out.println("8.   Upload grades.");
		System.out.println("9.   Check grade.");
		System.out.println("10.  Quit.\n");
		System.out.print("Input > ");
	}
}
