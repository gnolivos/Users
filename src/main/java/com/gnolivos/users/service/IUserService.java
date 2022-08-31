package com.gnolivos.users.service;

import java.util.List;

import com.gnolivos.users.entity.Users;
import com.gnolivos.users.vo.UserRequest;
import com.gnolivos.users.vo.UserResponse;

public interface IUserService {
	
	/**
	 * Find all users
	 * @return
	 */
	List<Users> findAllUsers();
	
    
    /**
     * Save user
     * @param request
     * @return
     */
    UserResponse save(UserRequest request);
    
    /**
     * Update user
     * @param request
     * @return
     */
    UserResponse update(UserRequest request);
    
    /**
     * Delete user
     * @param id
     */
    void delete(String id);

}
