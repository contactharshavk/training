package org.mindtree.practice.trainingprog.controller;

import java.util.ArrayList;
import java.util.List;

import org.mindtree.practice.trainingprog.Exceptions.IdAlreadyPresentException;
import org.mindtree.practice.trainingprog.Exceptions.IdNotFoundException;
import org.mindtree.practice.trainingprog.dao.EmployeeDataProcess;
import org.mindtree.practice.trainingprog.dto.EmployeeBean;
import org.mindtree.practice.trainingprog.dto.EmployeeStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Employee List Application", description="Employee List CRUD Operation")
public class EmployeeController {
	
	private EmployeeBean emp;
	private List<EmployeeBean> empList;
	private EmployeeStatusMessage employeeStatusMessage;
	
	@Autowired
	private EmployeeDataProcess employeeDataProcess; 

	@ApiResponses(value= {
			@ApiResponse(code=200, message="Successfully retrived"),
			@ApiResponse(code=201, message="Successfully created"),
			@ApiResponse(code=401, message="You are not authorized to view the resource"),
			@ApiResponse(code=403, message="Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code=404, message="The resource you were trying to reach is not found"),
		}
	)
	
	@ApiOperation(value="Adding an Employee", response=EmployeeStatusMessage.class)
	@RequestMapping(value="/EmpMgt/addEmp", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE )
//	@CachePut(value="employee", key="#result.id")
	public ResponseEntity<EmployeeStatusMessage> createEmployee(@RequestBody EmployeeBean emp) throws Exception {
		emp = employeeDataProcess.createEmployee(emp);
		employeeStatusMessage = new EmployeeStatusMessage("Specified Id created successfully");
		empList = new ArrayList<EmployeeBean>();
		empList.add(emp);
		employeeStatusMessage.setEmployeeBeanList(empList);
		return new ResponseEntity<EmployeeStatusMessage>(employeeStatusMessage, HttpStatus.OK);
	}
	
	@ApiOperation(value="Getting an Employee", response=EmployeeStatusMessage.class)
	@RequestMapping(value="/EmpMgt/getEmp/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	@Cacheable(value="employee", key="#id")
	public ResponseEntity<EmployeeStatusMessage> getEmployee(@PathVariable ("id") String id) throws Exception {
		emp = employeeDataProcess.getEmp(id);;
//		System.out.println("inside getEmp method in controller");
		employeeStatusMessage = new EmployeeStatusMessage("Specified Employee Retrieved successfully");
		empList = new ArrayList<EmployeeBean>();
		empList.add(emp);
		employeeStatusMessage.setEmployeeBeanList(empList);
		return new ResponseEntity<EmployeeStatusMessage>(employeeStatusMessage, HttpStatus.OK);
	}
	
	@ApiOperation(value="Getting all Employees", response=EmployeeStatusMessage.class)
	@RequestMapping(value="/EmpMgt/getEmp/getAllEmpDetails", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeStatusMessage> getAllEmployees() {
//		System.out.println("inside getAllEmployee method in controller");
		empList = employeeDataProcess.getAllEmployee();
//		System.out.println("inside controller get All emp next line");
		employeeStatusMessage = new EmployeeStatusMessage("Specified Employees Retrieved successfully");
		employeeStatusMessage.setEmployeeBeanList(empList);
		return new ResponseEntity<EmployeeStatusMessage>(employeeStatusMessage, HttpStatus.OK);
	}
	
	@ApiOperation(value="Updating an Employees", response=EmployeeStatusMessage.class)
	@RequestMapping(value="/EmpMgt/updateEmp/{id}", method=RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeStatusMessage> updateEmployee(@PathVariable ("id") String id, @RequestBody EmployeeBean newEmp) throws NumberFormatException {
		emp = employeeDataProcess.updateEmployee(id, newEmp);
		employeeStatusMessage = new EmployeeStatusMessage("Specified Employee Updated successfully");
		empList = new ArrayList<EmployeeBean>();
		empList.add(emp);
		employeeStatusMessage.setEmployeeBeanList(empList);
		return new ResponseEntity<EmployeeStatusMessage>(employeeStatusMessage, HttpStatus.OK);
	}
	
	@ApiOperation(value="Deleting an Employees", response=EmployeeStatusMessage.class)
	@RequestMapping(value="/EmpMgt/deleteEmp/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeStatusMessage> deleteEmployee(@PathVariable ("id") String id) throws Exception {
		emp = employeeDataProcess.deleteEmployee(id);
		employeeStatusMessage = new EmployeeStatusMessage("Specified Employee Deleted successfully");
		empList = new ArrayList<EmployeeBean>();
		empList.add(emp);
		employeeStatusMessage.setEmployeeBeanList(empList);
		return new ResponseEntity<EmployeeStatusMessage>(employeeStatusMessage, HttpStatus.OK);
	}	
}
