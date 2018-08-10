package org.mindtree.practice.trainingprog.dto;

import java.util.List;

public class EmployeeStatusMessage {
	
	private List<EmployeeBean> employeeBeanList;
	private String Status;

	public EmployeeStatusMessage(String status) {
		super();
		Status = status;
	}

	public EmployeeStatusMessage() {
		super();
	}

	public List<EmployeeBean> getEmployeeBeanList() {
		return employeeBeanList;
	}

	public void setEmployeeBeanList(List<EmployeeBean> employeeBeanList) {
		this.employeeBeanList = employeeBeanList;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}	
	
}
