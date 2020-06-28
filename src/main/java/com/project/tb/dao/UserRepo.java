package com.project.tb.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.project.tb.models.User;
@Repository
public interface UserRepo extends CrudRepository <User , Long>{
    public User  findByEmail(String email);
	public User getById(Long id);
	@Query(value="SELECT COUNT(gender) from user where gender=1",nativeQuery = true)
	public int countMaleUsers();
	@Query(value="SELECT COUNT(gender) from user where gender=0",nativeQuery = true)
	public int countFemaleUsers();
	/*List<Object[]>
	 Each Object[] contains one record returned by the database. We then need to iterate through the array, cast each Object to its specific type, and map them to our domain model. */
	//we can also use DTO with converters
	@Query(value="select sum(case when age>=18 and age<30 then 1 else 0 end) as Aclass,\r\n" + 
			"       sum(case when age>=30 and age<40 then 1 else 0 end) as Bclass,\r\n" + 
			"       sum(case when age>=40 and age<50 then 1 else 0 end) as Cclass,\r\n" + 
			"       sum(case when age>=50 and age<60 then 1 else 0 end) as Dclass,\r\n" + 
			"       sum(case when age>=60 and age<70 then 1 else 0 end) as Eclass,\r\n" + 
			"       sum(case when age>=70 then 1 else 0 end) as Fclass,\r\n" + 
			"       sum(1) as total\r\n" + 
			"from user;",nativeQuery = true)
	public List<Object[]> countAgeGroupsOfUsers();
	 Boolean existsByEmail(String email);
}