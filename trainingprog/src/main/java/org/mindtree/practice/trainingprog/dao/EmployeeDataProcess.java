package org.mindtree.practice.trainingprog.dao;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.mindtree.practice.trainingprog.Exceptions.IdAlreadyPresentException;
import org.mindtree.practice.trainingprog.Exceptions.IdNotFoundException;
import org.mindtree.practice.trainingprog.Exceptions.OtherException;
import org.mindtree.practice.trainingprog.Repository.EmployeeRepository;
import org.mindtree.practice.trainingprog.dto.EmployeeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDataProcess {

	private EmployeeBean emp;
	private List<EmployeeBean> empList;

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeBean createEmployee(EmployeeBean emp) throws IdAlreadyPresentException,OtherException {
//		System.out.println("add emp start in EmployeeDataProcess save");
		empList = getAllEmployee();
		Iterator<EmployeeBean> it = empList.iterator();
		while(it.hasNext()) {
			System.out.println("inside while loop");
			if(emp.getId().equals(it.next().getId())) {
//				System.out.println("inside while loop condition");
				throw new IdAlreadyPresentException("Specified Id " + emp.getId() + " Already Present");
			}
		}
//		System.out.println("add emp end in EmployeeDataProcess save");
		return employeeRepository.save(emp);
	}

	public EmployeeBean getEmp(String id) throws IdNotFoundException,OtherException {
//		System.out.println("add emp start in EmployeeDataProcess getEmp");
		try {
			emp = employeeRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new IdNotFoundException("Specified Id " + id + " Not Present");
		}
//		System.out.println("add emp end in EmployeeDataProcess getEmp");
		return emp;
	}	

	@SuppressWarnings("rawtypes")
	public List<EmployeeBean> getAllEmployee() {
//		System.out.println("add emp start in EmployeeDataProcess getAllEmployee");
		empList =  (List<EmployeeBean>) employeeRepository.findAll();
//		System.out.println("add emp end in EmployeeDataProcess getAllEmployee");
		return empList;
	}

	public EmployeeBean updateEmployee(String id, EmployeeBean newEmp) {
		return employeeRepository.save(newEmp);
	}

	public EmployeeBean deleteEmployee(String id) throws IdNotFoundException, OtherException{
//		System.out.println("add emp start in EmployeeDataProcess deleteEmployee" + id);	
		try {
//			System.out.println("after getting id");
			emp = employeeRepository.findById(id).get();
		} catch(NoSuchElementException | NullPointerException e){
//			System.out.println("after exception");
//			e.printStackTrace();
			throw new IdNotFoundException("Specified Id " + id + " Not Present");
		} 
//		System.out.println("just before delete");
		employeeRepository.delete(emp);
//		System.out.println("add emp end in EmployeeDataProcess updateEmployee");
		return emp;
	}

}
