package com.vuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuan.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
