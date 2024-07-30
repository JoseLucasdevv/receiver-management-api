package com.TaskDeveloper.Repository;

import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.TypesStatus.TypeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReceiverRepository extends JpaRepository<Receiver,Long> {
    Page<Receiver> findAll(Pageable page);
    Page<Receiver> findAllByName(String name,Pageable page);
    Page<Receiver> findAllByStatus(TypeStatus status,Pageable page);
}
