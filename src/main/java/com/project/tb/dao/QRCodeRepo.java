package com.project.tb.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.tb.models.QRCode;

@Repository
public interface QRCodeRepo extends CrudRepository <QRCode , Long>{
List<QRCode> findByEmail(String email);
@Transactional
@Modifying
@Query(value="DELETE from QRCode where book_id=:bookId",nativeQuery = true)
void deleteByBookId(@Param("bookId") Long bookId);
@Query(value="select case when (count(\"scen\") > 0)  then true else false end from QRCode scen where scen.book_id=:bookId",nativeQuery = true)
Integer findByBookId(Long bookId);
@Query(value="select id from QRCode where book_id=:bookId", nativeQuery = true)
public Long getIdFromBookId(@Param("bookId") String bookId);
}
