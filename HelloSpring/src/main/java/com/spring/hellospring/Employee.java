package com.spring.hellospring;


public class Employee {
	
	private int empNo;
	private String empName;
	private String email;
	private Department dept;
	
	public Employee() {
	}

	public Employee(int empNo, String empName, String email, Department dept) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.email = email;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", email=" + email + ", dept=" + dept + "]";
	}
	
	
	
	

}
