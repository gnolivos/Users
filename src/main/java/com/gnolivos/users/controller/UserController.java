package com.gnolivos.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gnolivos.users.common.ApplicationProperties;
import com.gnolivos.users.entity.Users;
import com.gnolivos.users.service.IUserService;
import com.gnolivos.users.vo.GlobalResponse;
import com.gnolivos.users.vo.UserRequest;

/**
 * @author gabriel.nolivos
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Lazy
    @Autowired
    private IUserService userService;
	
	@Lazy
    @Autowired
    private ApplicationProperties applicationProperties;
	
	/**
	 * Get all users
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Users>> getUsers() {
		List<Users> result = this.userService.findAllUsers();
		if(CollectionUtils.isEmpty(result)) {
			return ResponseEntity.noContent().build();
		}
        return ResponseEntity.ok(this.userService.findAllUsers());
    }
	
	/**
	 * Create user
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@PostMapping
    public ResponseEntity<GlobalResponse> create(@RequestBody UserRequest request) {
		try {
			GlobalResponse response = new GlobalResponse();
			response.setMessage("Usuario creado exitosamente.");
			response.setUserResponse(this.userService.save(request));
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			GlobalResponse response = new GlobalResponse();
			response.setMessage(e.getMessage());
			response.setUserResponse(null);
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
    }
	
	/**
	 * Update user
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
    public ResponseEntity<GlobalResponse> update(@RequestBody UserRequest request) {
		try {
			GlobalResponse response = new GlobalResponse();
			response.setMessage("Usuario actualizado exitosamente.");
			response.setUserResponse(this.userService.update(request));
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			GlobalResponse response = new GlobalResponse();
			response.setMessage(e.getMessage());
			response.setUserResponse(null);
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
    }
	
	/**
	 * Delete user
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> delete(@PathVariable("id") String id) {
		try {
			GlobalResponse response = new GlobalResponse();
			response.setMessage("Usuario eliminado exitosamente.");
			this.userService.delete(id);
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			GlobalResponse response = new GlobalResponse();
			response.setMessage(e.getMessage());
			response.setUserResponse(null);
			return new ResponseEntity<GlobalResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
    }

}
