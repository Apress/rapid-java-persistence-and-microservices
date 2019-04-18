package com.example.eshop.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eshop.model.history.PurchaseHistory;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long>{ }
