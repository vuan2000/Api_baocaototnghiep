package com.vuan.service;

import java.util.List;

import com.vuan.dto.BillDTO;

public interface BillService {
	<S extends BillDTO> S add(S entity);

    void deleteById(Long id);

    BillDTO findById(Long id);

    List<BillDTO> findAll();

    Long countFindAll();
}
