package com.vuan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vuan.dto.ProductDTO;

public interface ProductService {
	<S extends ProductDTO> S add(S entity);

    <S extends ProductDTO> S update(S entity);

    void deleteById(Long id);

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    List<ProductDTO> findByName(String name, Pageable pageable);

    Long countFindAll();

    Long countFindByName(String name, Pageable pageable);
}
