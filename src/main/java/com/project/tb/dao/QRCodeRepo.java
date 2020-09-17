package com.project.tb.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tb.models.QRCode;

@Repository
public interface QRCodeRepo extends CrudRepository <QRCode , Long>{
List<QRCode> findByEmail(String email);
}
