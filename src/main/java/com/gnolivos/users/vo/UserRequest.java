package com.gnolivos.users.vo;

import java.util.Collection;
import java.util.List;

/**
 * @author gabriel.nolivos
 *
 */
public class UserRequest {
	
	private String name;
	private String email;
	private String password;
	private List<PhoneRequest> phones;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phones
	 */
	public List<PhoneRequest> getPhones() {
		return phones;
	}
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<PhoneRequest> phones) {
		this.phones = phones;
	}

}
