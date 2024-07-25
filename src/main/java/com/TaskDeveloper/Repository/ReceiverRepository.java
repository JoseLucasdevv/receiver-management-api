package com.TaskDeveloper.Repository;

import com.TaskDeveloper.Entity.Receiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReceiverRepository extends JpaRepository<Receiver,Long> {
    Page<Receiver> findAll(Pageable page);
}
