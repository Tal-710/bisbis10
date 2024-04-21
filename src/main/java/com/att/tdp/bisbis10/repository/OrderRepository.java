package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}

