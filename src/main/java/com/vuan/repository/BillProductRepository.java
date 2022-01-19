package com.vuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuan.model.BillProduct;

@Repository
public interface BillProductRepository extends JpaRepository<BillProduct, Long> {

}
