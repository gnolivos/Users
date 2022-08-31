package com.gnolivos.users.entity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
    private String name;

	@NotBlank(message = "Email should not be blank")
	@Column(name = "EMAIL")
    private String email;

	@Column(name = "PASSWORD")
    private String password;
	
	@Column(name = "ISACTIVE")
    private Boolean isActive;
	
	@Column(name = "TOKEN")
    private String token;
	
	@Column(name = "LASTLOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

	@CreatedDate
	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="USERID", nullable = false)
	private List<Phones> phones;
    
    @PrePersist
	public void prePersist() {
    	Date fecha = new Date();
    	Instant now = Instant.now();
    	String jwtToken = Jwts.builder()
    	        .claim("name", name)
    	        .claim("email", email)
    	        .setSubject(name)
    	        .setId(id)
    	        .setIssuedAt(Date.from(now))
    	        .setExpiration(Date.from(now.plus(2, ChronoUnit.DAYS)))
    	        .compact();
    	
    	id = UUID.randomUUID().toString();
		token = jwtToken;
		created = fecha;
		lastLogin = fecha;
		isActive = Boolean.TRUE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the phones
	 */
	public List<Phones> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}

}
