package com.hmsapp.repository;

import com.hmsapp.entity.SQLFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLFileRepository extends JpaRepository<SQLFileEntity, Long> {
}