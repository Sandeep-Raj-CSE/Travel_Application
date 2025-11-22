package com.project.UBER.TravelApp.repositories;

import com.project.UBER.TravelApp.enums.TransactionType;
import com.project.UBER.TravelApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Transaction> findByUserIdAndTypeOrderByCreatedAtDesc(Long userId, TransactionType type);

    @Query("select t from Transaction t where t.userId = :userId and t.createdAt between :from and :to order by t.createdAt desc")
    List<Transaction> findByUserIdAndDateRange(@Param("userId") Long userId,
                                               @Param("from") LocalDateTime from,
                                               @Param("to") LocalDateTime to);

    @Query("select t from Transaction t where (:type is null or t.type = :type) and t.createdAt between :from and :to")
    List<Transaction> findByTypeAndDateRange(@Param("type") TransactionType type,
                                             @Param("from") LocalDateTime from,
                                             @Param("to") LocalDateTime to);

    @Query("select sum(t.amount) from Transaction t where t.type = :type and t.createdAt between :from and :to")
    Double sumAmountByTypeAndDateRange(@Param("type") TransactionType type,
                                       @Param("from") LocalDateTime from,
                                       @Param("to") LocalDateTime to);

}
