package com.vuan.service;

import java.util.List;

import com.vuan.dto.BillProductDTO;

public interface BillProductService {
	<S extends BillProductDTO> S add(S entity);

    void deleteById(Long id);

    BillProductDTO findById(Long id);

    List<BillProductDTO> findAll();

    Long countFindAll();
}
