
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

import com.vuan.dto.ResponsePagination;
import com.vuan.dto.UserDTO;
import com.vuan.service.UserService;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class UserController {
private UserService UserService;
	private UserService userService;

	public UserController (UserService UserService) {
		super();
		this.UserService = UserService;
	}

	@PatchMapping("/users")
	public UserDTO add(@RequestBody UserDTO UserDTO) {
		UserDTO = UserService.add(UserDTO);
		return UserDTO;
	}

	@PutMapping(value = "/users/{id}")
	public UserDTO update(@RequestBody UserDTO UserDTO ,@PathVariable long id) {
		UserDTO.setId(id);
		UserService.update(UserDTO);
		return UserDTO;
	}

	@DeleteMapping(value = "/users/{id}")
	public void delete(@PathVariable(name = "id") long id) {
		UserService.deleteById(id);
	}

	@GetMapping(value = "/users/{id}")
	public UserDTO get(@PathVariable(name = "id") long id) {
		return UserService.findById(id);
	}

	@GetMapping(value = "/users")
    public ResponseEntity<Map<String, Object>> find(
            @RequestParam(name = "name_like", required = false, defaultValue = "") String name,
            @RequestParam(name = "_page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "_limit", required = true, defaultValue = "100") int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Pageable pageable2 = PageRequest.of(0, 1000);

        List<UserDTO> classList = UserService.findByName(name, pageable);
        long recordTotals = UserService.countFindByName(name, pageable2);
        ResponsePagination pagination = new ResponsePagination(page, limit, recordTotals);

        Map<String, Object> response = new HashMap<>();
        response.put("data", classList);
        response.put("pagination", pagination);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
