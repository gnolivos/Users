package com.gnolivos.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gnolivos.users.common.ApplicationProperties;
import com.gnolivos.users.common.UserNotFoundException;
import com.gnolivos.users.entity.Users;
import com.gnolivos.users.service.IUserService;
import com.gnolivos.users.vo.UserRequest;
import com.gnolivos.users.vo.UserResponse;

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
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
	public String hello() {
		return "Hola Mundo";
	}
	
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
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
		try {
			return new ResponseEntity<UserResponse>(this.userService.save(request), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
    }

}
