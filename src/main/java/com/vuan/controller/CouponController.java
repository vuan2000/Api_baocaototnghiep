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

import com.vuan.dto.CouponDTO;
import com.vuan.dto.ResponsePagination;
import com.vuan.service.CouponService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class CouponController {
	private CouponService CouponService;
	
	public CouponController(CouponService CouponService) {
		super();
		this.CouponService = CouponService;
	}

	@PatchMapping("/coupons")
	public CouponDTO add(@RequestBody CouponDTO CouponDTO) {
		CouponDTO = CouponService.add(CouponDTO);
		return CouponDTO;
	}

	@PutMapping(value = "/coupons/{id}")
	public CouponDTO update(@RequestBody CouponDTO CouponDTO ,@PathVariable long id) {
		CouponDTO.setId(id);
		CouponService.update(CouponDTO);
		return CouponDTO;
	}

	@DeleteMapping(value = "/coupons/{id}")
	public void delete(@PathVariable(name = "id") long id) {
		CouponService.deleteById(id);
	}

	@GetMapping(value = "/coupons/{id}")
	public CouponDTO get(@PathVariable(name = "id") long id) {
		return CouponService.findById(id);
	}

	@GetMapping(value = "/coupons")
    public ResponseEntity<Map<String, Object>> find(
            @RequestParam(name = "name_like", required = false, defaultValue = "") String name,
            @RequestParam(name = "_page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "_limit", required = true, defaultValue = "100") int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Pageable pageable2 = PageRequest.of(0, 1000);

        List<CouponDTO> classList = CouponService.findByName(name, pageable);
        long recordTotals = CouponService.countFindByName(name, pageable2);
        ResponsePagination pagination = new ResponsePagination(page, limit, recordTotals);

        Map<String, Object> response = new HashMap<>();
        response.put("data", classList);
        response.put("pagination", pagination);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}