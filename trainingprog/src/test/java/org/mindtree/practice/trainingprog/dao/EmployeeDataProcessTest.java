package org.mindtree.practice.trainingprog.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindtree.practice.trainingprog.Exceptions.IdAlreadyPresentException;
import org.mindtree.practice.trainingprog.Exceptions.IdNotFoundException;
import org.mindtree.practice.trainingprog.Repository.EmployeeRepository;
import org.mindtree.practice.trainingprog.dto.EmployeeBean;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeDataProcessTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeDataProcess employeeDataProcess;

	@Before
	public void setUp() {
		// employeeDataProcess.evictCache();
	}

	@After
	public void tearDown() {

	}

	// @Test
	// public void testCreateEmployee() throws Exception {
	//// employeeBeanList.add(employeeBean1);
	//// employeeBeanList.add(employeeBean2);
	//// Mockito.when(employeeDataProcess.getAllEmployee()).thenReturn(employeeBeanList);
	//// RequestBuilder requestBuilder =
	// MockMvcRequestBuilders.get("/EmpMgt/getEmp/getAllEmpDetails").accept(MediaType.APPLICATION_JSON);
	//// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	//// String expected = "{id:id,userName;username,password:abcd";
	//// JSONAssert.assertEquals(expected,
	// result.getResponse().getContentAsString(), false);
	// }

	@Test
	public void testCreateEmployee() throws Exception {
		EmployeeBean mockEmployee = new EmployeeBean("1234", "user5", "abcd");
		// Mockito.doReturn(mockEmployee).when(employeeDataProcess).createEmployee(mockEmployee);
		Mockito.when(employeeDataProcess.createEmployee(Matchers.any(EmployeeBean.class))).thenReturn(mockEmployee);
		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmp")
				.content("{\r\n" + "	\"id\":\"1234\",\r\n" + "	\"userName\":\"user5\",\r\n"
						+ "	\"password\":\"abcd\"\r\n" + "}")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.content().json(
						"{\"employeeBeanList\":[{\"id\":\"1234\",\"userName\":\"user5\",\"password\":\"abcd\"}],\"status\":\"Specified Id created successfully\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testCreateEmployeeNegativeScenario() throws Exception {
		Mockito.when(employeeDataProcess.createEmployee(Matchers.any(EmployeeBean.class)))
				.thenThrow(new IdAlreadyPresentException("Specified Id 1234 Already Present"));
		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmp")
				.content("{\r\n" + 
						"	\"id\":\"1234\",\r\n" + 
						"	\"userName\":\"user5\",\r\n" + 
						"	\"password\":\"abcd\"\r\n" + 
						"}")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.content()
						.json("{\"errorMessage\": \"Specified Id 1234 Already Present\"}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testGetAllEmployee() throws Exception {
		EmployeeBean mockEmployee = new EmployeeBean("1234", "user5", "abcd");
		List<EmployeeBean> myList = new ArrayList<>();
		myList.add(mockEmployee);

		// Mockito.when(employeeDataProcess.getAllEmployee()).thenReturn(myList);
		System.out.println("awfefew" + employeeDataProcess);
		// Mockito.when(employeeDataProcess.getAllEmployee()).thenReturn(myList);
		Mockito.doReturn(myList).when(employeeDataProcess).getAllEmployee();
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getEmp/getAllEmpDetails")).andExpect(MockMvcResultMatchers
				.content()
				.json("{\"employeeBeanList\":[{\"id\":\"1234\",\"userName\":\"user5\",\"password\":\"abcd\"}],\"status\":\"Specified Employees Retrieved successfully\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		/*
		 * .accept(MediaType.APPLICATION_JSON); MvcResult result =
		 * mockMvc.perform(requestBuilder).andReturn(); String expected = ;
		 * JSONAssert.assertEquals(expected,
		 * result.getResponse().getContentAsString(), false);
		 */
	}

	@Test
	public void testGetOne() throws Exception {
		/*
		 * EmployeeBean mockEMployee = new EmployeeBean("12", "user5", "abcd");
		 * List<EmployeeBean> myList = new ArrayList<>();
		 * myList.add(mockEMployee);
		 * Mockito.doReturn(mockEMployee).when(employeeDataProcess).getEmp("12")
		 * ; mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getEmp/{id}"))
		 * .andExpect(MockMvcResultMatchers.content()
		 * .json("{\"employeeBeanList\":[{\"id\":\"12\",\"userName\":\"user5\",\"password\":\"abcd\"}],\"status\":\"Specified Employees Retrieved successfully\"}"
		 * )) .andExpect(MockMvcResultMatchers.status().isOk());
		 */

		EmployeeBean mockEmployee = new EmployeeBean();
		mockEmployee.setId("1");
		mockEmployee.setUserName("Ranju");
		mockEmployee.setPassword("test");
		Mockito.doReturn(mockEmployee).when(employeeDataProcess).getEmp(mockEmployee.getId());
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getEmp/1")).andExpect(MockMvcResultMatchers.content().json(
				"{\"employeeBeanList\":[{\"id\":\"1\",\"userName\":\"Ranju\",\"password\":\"test\"}],\"status\":\"Specified Employee Retrieved successfully\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetOneNegativeScenario() throws Exception {
		Mockito.when(employeeDataProcess.getEmp(Matchers.anyString()))
				.thenThrow(new IdNotFoundException("Specified Id 12.00 Not Present"));
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getEmp/12.00"))
				.andExpect(
						MockMvcResultMatchers.content().json("{\"errorMessage\": \"Specified Id 12.00 Not Present\"}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testUpdate() throws Exception {
//		EmployeeBean mockEmployee = new EmployeeBean("1234", "user5", "abcd");
		EmployeeBean mockEmployeeUpdated = new EmployeeBean();
		mockEmployeeUpdated.setId("1234");
		mockEmployeeUpdated.setUserName("ranga3update");
		mockEmployeeUpdated.setPassword("abcdupdated");
		// Mockito.doReturn(mockEmployee).when(employeeDataProcess).createEmployee(mockEmployee);
		Mockito.when(employeeDataProcess.updateEmployee(Matchers.anyString(), Matchers.any(EmployeeBean.class))).thenReturn(mockEmployeeUpdated);
		mockMvc.perform(MockMvcRequestBuilders.put("/EmpMgt/updateEmp/1234")
				.content("{ \"id\":\"1234\", \"userName\":\"ranga3update\", \"password\":\"abcdupdated\" }")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.content().json(
						"{ \"employeeBeanList\": [ { \"id\": \"1234\", \"userName\": \"ranga3update\", \"password\": \"abcdupdated\" }], \"status\": \"Specified Employee Updated successfully\" }"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		//,\"userName\":\"user5updated\",\"password\":\"abcdupdated\"
	}
	
	@Test
	 public void testDelete() throws Exception {
		EmployeeBean mockEmployee = new EmployeeBean("1234", "user5", "abcd");
	 	Mockito.when(employeeDataProcess.deleteEmployee(mockEmployee.getId())).thenReturn(mockEmployee);
	 	mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/1234"))
	 	.andExpect(MockMvcResultMatchers.content().json(
	 			"{\"employeeBeanList\":[{\"id\":\"1234\",\"userName\":\"user5\",\"password\":\"abcd\"}],\"status\":\"Specified Employee Deleted successfully\"}"))
	 	.andExpect(MockMvcResultMatchers.status().isOk());
	 }
	
	@Test
	public void testDeleteNegativeScenario() throws Exception {
		Mockito.when(employeeDataProcess.deleteEmployee(Matchers.anyString()))
		.thenThrow(new IdNotFoundException("Specified Id 12.00 Not Present"));
		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/12.00"))
		.andExpect(MockMvcResultMatchers.content().json("{\"errorMessage\": \"Specified Id 12.00 Not Present\"}"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
}
