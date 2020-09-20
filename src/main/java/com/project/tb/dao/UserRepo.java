package com.project.tb.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.tb.models.User;
import com.project.tb.payload.CreditRequest;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findById(Long id);
	@Query(value = "SELECT ticket_id from user_tickets where user_id=:id", nativeQuery = true)
	public List<BigInteger> getTicketsByUserId(int id);
	@Query(value="select id from user where user.email=:email", nativeQuery = true)
     public Long getIdFromEmail(@Param("email") String email);
	@Query(value = "SELECT COUNT(gender) from user where gender=1", nativeQuery = true)
	public int countMaleUsers();
	@Query(value = "SELECT COUNT(gender) from user where gender=0", nativeQuery = true)
	public int countFemaleUsers();
	@Query(value = "select id from roles where name=:name", nativeQuery = true)
	public int getRoleIdFromRoleName(String name);

	/*
	 * List<Object[]> Each Object[] contains one record returned by the database. We
	 * then need to iterate through the array, cast each Object to its specific
	 * type, and map them to our domain model.
	 */
	// we can also use DTO with converters
	@Query(value = "select sum(case when age>=18 and age<30 then 1 else 0 end) as Aclass,\r\n"
			+ "       sum(case when age>=30 and age<40 then 1 else 0 end) as Bclass,\r\n"
			+ "       sum(case when age>=40 and age<50 then 1 else 0 end) as Cclass,\r\n"
			+ "       sum(case when age>=50 and age<60 then 1 else 0 end) as Dclass,\r\n"
			+ "       sum(case when age>=60 and age<70 then 1 else 0 end) as Eclass,\r\n"
			+ "       sum(case when age>=70 then 1 else 0 end) as Fclass,\r\n" + "       sum(1) as total\r\n"
			+ "from user;", nativeQuery = true)
	public List<Object[]> countAgeGroupsOfUsers();

	

	@Transactional
	@Modifying
	@Query("UPDATE User s  set s.credit = s.credit+:credit where s.id = :id")
	void addCredit(int credit, Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User s  set s.password =:newPass where s.id = :id")
	void changePassword(String newPass, Long id);
	@Transactional
	@Modifying
	@Query("UPDATE User s  set s.credit =s.credit-:price where s.id = :id")
	void decreaseCredit(int price,Long id);
	@Transactional
	@Modifying
	@Query("UPDATE User s  set s.credit =s.credit+:price where s.id = :id")
	void increaseCredit(int price,Long id);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);
}