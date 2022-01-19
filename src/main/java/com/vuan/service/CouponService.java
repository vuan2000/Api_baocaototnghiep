package com.vuan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vuan.dto.CouponDTO;

public interface CouponService {
	<S extends CouponDTO> S add(S entity);

    <S extends CouponDTO> S update(S entity);

    void deleteById(Long id);

    CouponDTO findById(Long id);

    List<CouponDTO> findAll();

    List<CouponDTO> findByName(String name, Pageable pageable);

    Long countFindAll();

    Long countFindByName(String name, Pageable pageable);
}
