package com.project.tb.services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.tb.dao.CreditRequestRepo;
import com.project.tb.payload.CreditRequest;
public class CreditRequestServices {
	@Autowired
	private CreditRequestRepo creditRequestRepo;
	@Autowired
	private CreditRequestServices creditRequestServices;
	public CreditRequest saveOrUpdate(final CreditRequest creditRequest) {
			 creditRequestRepo.save(creditRequest);
				creditRequestServices.addUser(creditRequest.getId(), creditRequest.getDistuId());
			return creditRequest;
	}
public ArrayList<CreditRequest> findAll() {
	Iterable<CreditRequest> it = creditRequestRepo.findAll();
	ArrayList<CreditRequest> creditRequests = new ArrayList<CreditRequest>();
	it.forEach(e -> creditRequests.add(e));
	return creditRequests;
}
public void addUser(Long credit_id,Long user_id) {
	creditRequestRepo.addUser(credit_id, user_id);
}

}
