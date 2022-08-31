package com.gnolivos.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gnolivos.users.common.ApplicationProperties;
import com.gnolivos.users.common.UserNotFoundException;
import com.gnolivos.users.entity.Phones;
import com.gnolivos.users.entity.Users;
import com.gnolivos.users.repository.IUserRepository;
import com.gnolivos.users.vo.PhoneRequest;
import com.gnolivos.users.vo.UserRequest;
import com.gnolivos.users.vo.UserResponse;

@Lazy
@Service
public class UserService implements IUserService{
	
	@Lazy
    @Autowired
    private IUserRepository userRepository;
	
	@Lazy
    @Autowired
    private ApplicationProperties applicationProperties;

	/*
	 * (non-Javadoc)
	 * @see com.gnolivos.users.service.IUserService#findAllUsers()
	 */
	@Override
	public List<Users> findAllUsers() throws ConversionFailedException {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.gnolivos.users.service.IUserService#save(com.gnolivos.users.vo.UserRequest)
	 */
	@Override
	public UserResponse save(UserRequest request) throws UserNotFoundException {
		try {
			// Validate email
			this.validateEmail(request.getEmail());
			
			// Validate password
			this.validatePassword(request.getPassword());
			
			// Create user object
			Users user = this.userRepository.save(this.createUser(request));
			
			// Return a user response
			return this.convertToUserResponse(user);
			
		} catch (Exception e) {
			throw new UserNotFoundException(e.getCause().getMessage());
		}
	}
	
	private void validateEmail(String email) {
		// Validate if email exist
		Users user = userRepository.findByEmail(email);
		if(user != null) {
			throw new UserNotFoundException("El correo ya se encuentra registrado.");
		}
		
		// Validate regular expression of email (aaaaaaa@dominio.cl)
		Pattern pattern = Pattern.compile(applicationProperties.getRegExpEmail());
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			throw new UserNotFoundException("El formato del correo es incorrecto.");
		}
	}
	
	private void validatePassword(String password) {
		// Validate regular expression of password 
		Pattern pattern = Pattern.compile(applicationProperties.getRegExpPassword());
		Matcher matcher = pattern.matcher(password);
        
        if(!matcher.matches()) {
			throw new UserNotFoundException("La contraseña no cumple con los requerimientos solicitados.");
		}
	}
	
	private Users createUser(UserRequest request) {
		// Create user object
		Users user = new Users();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		
		if(!CollectionUtils.isEmpty(request.getPhones())) {
			
			List<Phones> phoneList = new ArrayList();
			for(PhoneRequest phoneRequest : request.getPhones()) {
				Phones phone = new Phones();
				phone.setNumber(phoneRequest.getNumber());
				phone.setCityCode(phoneRequest.getCitycode());
				phone.setCountryCode(phoneRequest.getCountrycode());
				phoneList.add(phone);
			}
			user.setPhones(new ArrayList());
			user.setPhones(phoneList);
		}
		return user;
	}
	
	private UserResponse convertToUserResponse(Users user) {
		// Convert to user response
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setIsActive(user.getIsActive());
		return response;
	}

}
