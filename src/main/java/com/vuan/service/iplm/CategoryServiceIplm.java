package com.vuan.service.iplm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuan.dto.CategoryDTO;
import com.vuan.model.Category;
import com.vuan.repository.CategoryRepository;
import com.vuan.service.CategoryService;

@Service
@Transactional
public class CategoryServiceIplm implements CategoryService {
	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;

    public CategoryServiceIplm(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
    public <S extends CategoryDTO> S add(S entity) {
        Category category = categoryRepository.save(convertToEntity(entity));
        entity.setId(category.getId());
        return entity;
    }

    @Override
    public <S extends CategoryDTO> S update(S entity) {
    	Category category = categoryRepository.save(convertToEntity(entity));
        entity.setId(category.getId());
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO findById(Long id) {
        Optional<Category> classOptional = Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found")));
        return convertToDTO(classOptional.get());
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findByName(String name, Pageable pageable) {
        Page<Category> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = categoryRepository.findAll(pageable);
        }else {
            pageClass = categoryRepository.findByNameContaining(name, pageable);
        }
        return pageClass.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFindByName(String name, Pageable pageable) {
        Page<Category> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = categoryRepository.findAll(pageable);
        }else {
            pageClass = categoryRepository.findByNameContaining(name, pageable);
        }
        return (long) pageClass.getContent().size();
    }

    @Override
    public Long countFindAll() {
        return (long) categoryRepository.findAll().size();
    }

    private CategoryDTO convertToDTO(Category category) {
    	CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
    
    private Category convertToEntity(CategoryDTO categoryDTO) {
    	Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }
}
