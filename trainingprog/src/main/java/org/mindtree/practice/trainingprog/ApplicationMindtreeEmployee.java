package org.mindtree.practice.trainingprog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */

@EnableEurekaClient
@SpringBootApplication
@EnableCaching
public class ApplicationMindtreeEmployee 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        SpringApplication.run(ApplicationMindtreeEmployee.class, args);
    }
  /*  
    @Bean
    public CacheManager cacheManager() {
    	ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager("EmployeeBean");
    	return manager;
    }*/

}
