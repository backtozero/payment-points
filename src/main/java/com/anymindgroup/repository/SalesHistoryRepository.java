package com.anymindgroup.repository;

import com.anymindgroup.repository.entity.SalesHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SalesHistoryRepository extends JpaRepository<SalesHistoryEntity, LocalDateTime> {
}