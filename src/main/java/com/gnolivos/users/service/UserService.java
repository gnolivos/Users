package com.gnolivos.users.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.gnolivos.users.common.UserValidationException;
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
	public UserResponse save(UserRequest request) throws UserValidationException {
		try {
			
			// Validate if email exist
			Users user = userRepository.findByEmail(request.getEmail());
			if(user != null) {
				throw new UserValidationException("El correo ya se encuentra registrado.");
			}
			
			// Validate regular expression email
			this.validateEmail(request.getEmail());
			
			// Validate regular expression password
			this.validatePassword(request.getPassword());
			
			// Create user object
			user = this.userRepository.save(this.createUser(request, Boolean.FALSE));
			
			// Return a user response
			return this.convertToUserResponse(user);
			
		} catch (Exception e) {
			throw new UserValidationException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.gnolivos.users.service.IUserService#saveOrUpdate(com.gnolivos.users.vo.UserRequest)
	 */
	@Override
	public UserResponse saveOrUpdate(UserRequest request) throws UserValidationException {
		try {
			Optional<Users> user = this.userRepository.findById(request.getId());
			if(user.isPresent()) {
				// Validate email
				this.validateEmail(request.getEmail());
				
				// Validate password
				this.validatePassword(request.getPassword());
				
				// Create user object
				Users userResponse = this.userRepository.save(this.createUser(request, Boolean.TRUE));
				
				// Return a user response
				return this.convertToUserResponse(userResponse);
				
			}
			throw new UserValidationException("No existe el usuario ingresado.");
		} catch (Exception e) {
			throw new UserValidationException(e.getMessage());
		}
		
	}
	
	private void validateEmail(String email) {
		// Validate regular expression of email (aaaaaaa@dominio.cl)
		Pattern pattern = Pattern.compile(applicationProperties.getRegExpEmail());
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			throw new UserValidationException("El formato del correo es incorrecto.");
		}
	}
	
	private void validatePassword(String password) {
		// Validate regular expression of password
		Pattern pattern = Pattern.compile(applicationProperties.getRegExpPassword());
		Matcher matcher = pattern.matcher(password);
		
		// Password must contain at least one digit [0-9].
		// Password must contain at least one lowercase Latin character [a-z].
		// Password must contain at least one uppercase Latin character [A-Z].
		// Password must contain at least one special character like ! @ # & ( ).
		// Password must contain a length of at least 8 characters and a maximum of 20 characters.
        
        if(!matcher.matches()) {
			throw new UserValidationException("La contraseña no cumple con los requerimientos solicitados.");
		}
	}
	
	private Users createUser(UserRequest request, Boolean isUpdate) {
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
		
		if(isUpdate) {
			user.setModified(new Date());
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
