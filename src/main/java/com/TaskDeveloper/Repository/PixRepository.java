package com.TaskDeveloper.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TaskDeveloper.Entity.Pix;

public interface PixRepository extends JpaRepository<Pix,Long>{}