public class EmployeeVO {
	int Employee_id;
	String firstName;
	String Last_name;
	String Email;
	String Hire_date;
	String jobid;

	public int getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(int employee_id) {
		Employee_id = employee_id;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String last_name) {
		Last_name = last_name;
	}
	
	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		firstName = firstName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getHire_date() {
		return Hire_date;
	}

	public void setHire_date(String hire_date) {
		Hire_date = hire_date;
	}

	public String getjobid() {
		return jobid;
	}

	public void setjobid(String jobid) {
		jobid = jobid;
	}

	@Override
	public String toString() {
		return "Employees [Employee_id=" + Employee_id + ", Last_name=" + Last_name + ", Email=" + Email
				+ ", Hire_date=" + Hire_date + ", jobid=" + jobid + "]";
	}

}
