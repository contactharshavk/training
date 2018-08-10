/*package org.mindtree.practice.trainingprog.dao;

import org.mindtree.practice.trainingprog.Repository.CricketRepository;
import org.mindtree.practice.trainingprog.dto.CricketBeanMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CricketDataProcess {

	@Bean
	private CricketRepository repository;
	
	public void createBean(CricketBeanMain cricketBeanMain) {
		repository.save(cricketBeanMain);
	}
	
}
*/