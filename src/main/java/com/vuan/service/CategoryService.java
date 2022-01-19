package com.vuan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vuan.dto.CategoryDTO;

public interface CategoryService {
	<S extends CategoryDTO> S add(S entity);

    <S extends CategoryDTO> S update(S entity);

    void deleteById(Long id);

    CategoryDTO findById(Long id);

    List<CategoryDTO> findAll();

    List<CategoryDTO> findByName(String name, Pageable pageable);

    Long countFindAll();

    Long countFindByName(String name, Pageable pageable);
}
