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

import com.vuan.dto.ProductDTO;
import com.vuan.dto.ResponsePagination;
import com.vuan.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class ProductController {
	private ProductService ProductService;
	
	public ProductController(ProductService ProductService) {
		super();
		this.ProductService = ProductService;
	}

	@PatchMapping("/products")
	public ProductDTO add(@RequestBody ProductDTO ProductDTO) {
		ProductDTO = ProductService.add(ProductDTO);
		return ProductDTO;
	}

	@PutMapping(value = "/products/{id}")
	public ProductDTO update(@RequestBody ProductDTO ProductDTO ,@PathVariable long id) {
		ProductDTO.setId(id);
		ProductService.update(ProductDTO);
		return ProductDTO;
	}

	@DeleteMapping(value = "/products/{id}")
	public void delete(@PathVariable(name = "id") long id) {
		ProductService.deleteById(id);
	}

	@GetMapping(value = "/products/{id}")
	public ProductDTO get(@PathVariable(name = "id") long id) {
		return ProductService.findById(id);
	}

	@GetMapping(value = "/products")
    public ResponseEntity<Map<String, Object>> find(
            @RequestParam(name = "name_like", required = false, defaultValue = "") String name,
            @RequestParam(name = "_page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "_limit", required = true, defaultValue = "100") int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Pageable pageable2 = PageRequest.of(0, 1000);

        List<ProductDTO> classList = ProductService.findByName(name, pageable);
        long recordTotals = ProductService.countFindByName(name, pageable2);
        ResponsePagination pagination = new ResponsePagination(page, limit, recordTotals);

        Map<String, Object> response = new HashMap<>();
        response.put("data", classList);
        response.put("pagination", pagination);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
