package org.mindtree.practice.trainingprog.dto;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeBean {
	
	@Id
	private String id;
	private String userName;
	private String password;
//	private String fullName;
//	private String eMailId;
//	private String dob;
//	private String gender;
//	private String securityQusetion;
//	private String securityAnswer;
	
	public EmployeeBean() {
		super();
	}
	
	public EmployeeBean(String id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

//	public EmployeeBean(String id, String userName, String password, String fullName, String eMailId, String dob, String gender,
//			String securityQusetion, String securityAnswer) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.password = password;
//		this.fullName = fullName;
//		this.eMailId = eMailId;
//		this.dob = dob;
//		this.gender = gender;
//		this.securityQusetion = securityQusetion;
//		this.securityAnswer = securityAnswer;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getFullName() {
//		return fullName;
//	}
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//	public String geteMailId() {
//		return eMailId;
//	}
//	public void seteMailId(String eMailId) {
//		this.eMailId = eMailId;
//	}
//	public String getDob() {
//		return dob;
//	}
//	public void setDob(String dob) {
//		this.dob = dob;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getSecurityQusetion() {
//		return securityQusetion;
//	}
//	public void setSecurityQusetion(String securityQusetion) {
//		this.securityQusetion = securityQusetion;
//	}
//	public String getSecurityAnswer() {
//		return securityAnswer;
//	}
//	public void setSecurityAnswer(String securityAnswer) {
//		this.securityAnswer = securityAnswer;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if(EmployeeBean.class == obj.getClass()) {
			EmployeeBean employee = (EmployeeBean) obj;
			try{
				if(this.id.equals(employee.getId()) && this.userName.equals(employee.getUserName()) && this.getPassword().equals(employee.getPassword())) {
					return true;
				} else {
					return false;
				}
			}catch(NullPointerException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}
