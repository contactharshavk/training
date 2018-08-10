/*	
 *	Checked with my sql data base as H2 data base was unable to load 
 */
package org.mindtree.practice.trainingprog;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindtree.practice.trainingprog.Exceptions.IdAlreadyPresentException;
import org.mindtree.practice.trainingprog.Exceptions.IdNotFoundException;
import org.mindtree.practice.trainingprog.Repository.EmployeeRepository;
import org.mindtree.practice.trainingprog.dao.EmployeeDataProcess;
import org.mindtree.practice.trainingprog.dto.EmployeeBean;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeDataServiceTest {

	private EmployeeBean emp;
	private List<EmployeeBean> empList;

	@Autowired
	private EmployeeDataProcess employeeDataProcess;

	@Autowired
	private EmployeeRepository employeeRepository; 

	@Test
	public void testCreateEmployeeDataProcess() throws Exception {
		EmployeeBean employee = new EmployeeBean("4", "user5", "abcd");
		EmployeeBean createdEmployee = employeeDataProcess.createEmployee(employee);
		Assert.assertNotNull("failure Expected not null", createdEmployee);
		Assert.assertNotNull("expected ID not null", createdEmployee.getId());

		//		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		//		Assert.assertEquals(employee, employeeDataProcess.createEmployee(employee));
		//		System.out.println(employeeDataProcess);
		//		Mockito.when(employeeDataProcess.createEmployee(employee)).thenReturn(createdEmployee);
		//		Assert.assertNotNull("failure Expected not null", createdEmployee);
		//		Assert.assertNotNull("expected ID not null", createdEmployee.getId());

	}

	@Test
	public void testCreateEmployeeDataProcessNegativeScenarion() throws Exception {
		EmployeeBean employee = new EmployeeBean("4", "user5updateNS", "abcdupdateNS");
		try {
			EmployeeBean createdEmployee = employeeDataProcess.createEmployee(employee);
		}catch (IdAlreadyPresentException exception) {
			Assert.assertEquals("Specified Id " + employee.getId() + " Already Present", exception.getMessage());
		}
	}

	@Test
	public void testGetAllEmployeeDataProcess() throws Exception{
		List<EmployeeBean> employeeList = employeeDataProcess.getAllEmployee();
		System.out.println(employeeList.size() + "list");
		Assert.assertNotNull("failure Expected not null", employeeList);
		Assert.assertEquals("failure - Expected Size ", 3, employeeList.size());
	}

	@Test
	public void testGetOneEmployeeDataProcess() throws Exception{
		EmployeeBean employee = employeeDataProcess.getEmp("1");
		Assert.assertNotNull("failure - expected Employee", employee);
	}

	@Test
	public void testGetOneEmployeeDataProcessNegativeScenarion() throws Exception{
		EmployeeBean employee = new EmployeeBean("199", "user5", "abcd");
		try {
			EmployeeBean createdEmployee = employeeDataProcess.getEmp(employee.getId());
		}catch (IdNotFoundException exception) {
			Assert.assertEquals("Specified Id " + employee.getId() + " Not Present", exception.getMessage());
		}
	}

	@Test
	public void testUpdateEmployeeDataProcess() throws Exception{
		EmployeeBean newEmployee = new EmployeeBean("1.00", "ranga1updated", "abcdupdated");
		EmployeeBean newEmployee1 = new EmployeeBean("1.00", "ranga1updated", "abcdupdated");
		EmployeeBean employee = employeeDataProcess.updateEmployee("1", newEmployee);
		System.out.println(employee.getId() + " " + employee.getUserName() + " " + employee.getPassword() + "update test case");
		Assert.assertEquals(newEmployee, employee);
	}

	@Test
	public void testDeleteEmployeeDataProcess() throws Exception{
		EmployeeBean newEmployee = new EmployeeBean("3", "RANGA3", "password");
		EmployeeBean employee = employeeDataProcess.deleteEmployee("3");
		Assert.assertNotSame(newEmployee, employee);
		System.out.println(employee.getId() + " " + employee.getUserName() + " " + employee.getPassword() + "delete test case");
		Assert.assertEquals(newEmployee, employee);		
		/* commenting for git */
	}

	@Test
	public void testDeleteEmployeeDataProcessNegativeScenarion() throws Exception{
		EmployeeBean employee = new EmployeeBean("199", "user5", "abcd");
		try {
			employeeDataProcess.deleteEmployee(employee.getId());
		}catch (IdNotFoundException exception) {
			Assert.assertEquals("Specified Id " + employee.getId() + " Not Present", exception.getMessage());
		}
	}
	
}
