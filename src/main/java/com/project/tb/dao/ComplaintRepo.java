package com.project.tb.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tb.models.Complaint;
@Repository
public interface ComplaintRepo extends CrudRepository <Complaint , Long>{
}
