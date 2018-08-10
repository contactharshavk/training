package org.mindtree.practice.trainingprog.Repository;

import org.mindtree.practice.trainingprog.dto.EmployeeBean;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeBean, String> {
	
	
}
