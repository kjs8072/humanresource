import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAO {
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt;
	ResultSet rs;
	EmployeeVO emp = null;
	Scanner sc = new Scanner(System.in);

	public void createEmp() {
		CallableStatement cs;
		try {
			int id = 0;
			cs = conn.prepareCall("{call CREATE_EMP(?,?,?,?,?,?,?,?)}");
			System.out.println("ID를 입력하세요");
			id = sc.nextInt();
			cs.setInt(1, id);
			System.out.println("이름을 입력하세요");
			cs.setString(2, sc.next());
			System.out.println("이메일을 입력하세요");
			cs.setString(3, sc.next());
			System.out.println("고용날짜를 입력하세요");
			cs.setString(4, sc.next());
			System.out.println("JOB_ID를 입력하세요");
			cs.setString(5, sc.next());
			System.out.println("dept_id를 입력하세요");
			cs.setInt(6, sc.nextInt());
			cs.registerOutParameter(7, java.sql.Types.VARCHAR);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.execute();
			if(cs.getInt(8) < 0)
				System.out.println("에러 발생: " + cs.getString(7));
			else
				System.out.println("프로시져를 성공적으로 호출했습니다.");
			
//			String sql = "select * from employees where employee_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				do {
//					System.out.println(rs.getInt("employee_id") + rs.getString("last_name") + rs.getString("email") + 
//							rs.getString("hire_date") + rs.getString("job_id") + rs.getString("department_id"));
//				} while (rs.next());
//			}
			cs.close();
			conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 1. 전체리스트 조회
	public void getEmployeeList(String searchCondition) {
		String sql = "select * from employees where first_name like ? || '%'";
		EmployeeVO emp = null;
		List<EmployeeVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchCondition);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					emp = new EmployeeVO();
					emp.setEmployee_id(rs.getInt("employee_id"));
					emp.setLast_name(rs.getString("last_name"));
					emp.setEmail(rs.getString("email"));
					emp.setHire_date(rs.getString("hire_date"));
					System.out.println(emp);

				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//  전체리스트 조회	return list //2. 전체리스트 조회 return List<EmployeeVO>
	public ArrayList<EmployeeVO> getEmployeesList(String searchConddction) {
		String sql = "select * from employees";
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					emp = new EmployeeVO();
					emp.setLast_name(rs.getString("last_name"));
					emp.setjobid(rs.getString("job_id"));
					emp.setEmail(rs.getString("email"));
					emp.setHire_date(rs.getString("hire_date"));
					emp.setEmployee_id(rs.getInt("employee_id"));
					list.add(emp);
					System.out.println(emp);
				} while (rs.next());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

//1개씩 조회(return EmployeeVO) //3. 다넌조회 return employeeVO
	public EmployeeVO getEmployeeVO(int emp_id) {
		String sql = "select * from employees where employee_id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();
			EmployeeVO emp = null;
			if (rs.next()) {
				emp = new EmployeeVO();
				emp.setLast_name(rs.getString("last_name"));
				emp.setjobid(rs.getString("job_id"));
				emp.setEmail(rs.getString("email"));
				emp.setHire_date(rs.getString("hire_date"));
				emp.setEmployee_id(rs.getInt("employee_id"));
				System.out.println(emp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

// insert 	//4. 입력
	public void insertEmployee(EmployeeVO vo) {
		String sql = "insert into employees(employee_id, last_name, hire_date) values(?, ?, sysdate)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmployee_id());
			pstmt.setString(2, vo.getLast_name());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 입력 되었습니다");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// update
	public void updateEmployee(EmployeeVO vo) {
		String sql = "update employees set last_name = ? where employee_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, vo.getEmployee_id());
			pstmt.setString(1, vo.getLast_name());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 수정 되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//delete
	public void deleteEmployee(int emp_id) {
		String sql = "delete from employees where employee_id in (999,998)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, emp_id);
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제 되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
