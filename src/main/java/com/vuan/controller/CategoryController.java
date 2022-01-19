package com.vuan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vuan.dto.CategoryDTO;
import com.vuan.dto.ResponsePagination;
import com.vuan.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class CategoryController {
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PatchMapping("/categorys")
	public CategoryDTO add(@RequestBody CategoryDTO categoryDTO) {
		categoryDTO = categoryService.add(categoryDTO);
		return categoryDTO;
	}

	@PutMapping(value = "/categorys/{id}")
	public CategoryDTO update(@RequestBody CategoryDTO categoryDTO ,@PathVariable long id) {
		categoryDTO.setId(id);
		categoryService.update(categoryDTO);
		return categoryDTO;
	}

	@DeleteMapping(value = "/categorys/{id}")
	public void delete(@PathVariable(name = "id") long id) {
		categoryService.deleteById(id);
	}

	@GetMapping(value = "/categorys/{id}")
	public CategoryDTO get(@PathVariable(name = "id") long id) {
		return categoryService.findById(id);
	}

	@GetMapping(value = "/categorys")
    public ResponseEntity<Map<String, Object>> find(
            @RequestParam(name = "name_like", required = false, defaultValue = "") String name,
            @RequestParam(name = "_page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "_limit", required = true, defaultValue = "100") int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Pageable pageable2 = PageRequest.of(0, 1000);

        List<CategoryDTO> classList = categoryService.findByName(name, pageable);
        long recordTotals = categoryService.countFindByName(name, pageable2);
        ResponsePagination pagination = new ResponsePagination(page, limit, recordTotals);

        Map<String, Object> response = new HashMap<>();
        response.put("data", classList);
        response.put("pagination", pagination);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}