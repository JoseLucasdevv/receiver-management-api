package com.TaskDeveloper.Repository;

import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.TypesPix.TypesPix;
import com.TaskDeveloper.TypesStatus.TypeStatus;
import com.fasterxml.jackson.databind.util.TypeKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ReceiverRepository extends JpaRepository<Receiver,Long> {
    Page<Receiver> findAll(Pageable page);
    Page<Receiver> findAllByName(String name,Pageable page);
    Page<Receiver> findAllByStatus(TypeStatus status,Pageable page);
    @Query("SELECT r FROM Receiver r JOIN r.pix p WHERE p.key_type = :type_pix")
    Page<Receiver> findAllByTypeKey(@Param("type_pix") TypesPix typePix, Pageable page);

    @Query("SELECT r FROM Receiver r JOIN r.pix p WHERE p.key_pix = :key_pix")
    Receiver findByKeyValue(@Param("key_pix") String key_pix);
}
