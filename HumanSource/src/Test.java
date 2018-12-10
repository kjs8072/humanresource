public class Test {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
//		System.out.println("=============( 1 )===========");
//		dao.getEmployeesList("Alex");

//		System.out.println("=============( 2 )===========");
//		dao.getEmployeesList(100);

//		System.out.println("=============( 3 )===========");
//		EmployeeVO vo = new EmployeeVO();
//		vo.setEmployee_id(206);
//		vo.setLast_name("ttt");
//		dao.updateEmployee(vo);

//		System.out.println("=============( 4 )===========");
//		EmployeeVO vo = new EmployeeVO();
//		vo.setEmployee_id(997);
//		vo.setLast_name("Lee");
//		vo.setEmail("Leeeee@gmail");
//		//vo.setjobid("AD_PRES");
//		dao.insertEmployee(vo);
		
		dao.createEmp();

	}

}
