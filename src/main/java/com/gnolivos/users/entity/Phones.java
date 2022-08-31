package com.gnolivos.users.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PHONES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phones {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NUMBER")
    private Integer number;
	
	@Column(name = "CITYCODE")
    private Integer cityCode;
	
	@Column(name = "COUNTRYCODE")
    private Integer countryCode;
	
	@CreatedDate
	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@PrePersist
	public void prePersist() {
    	id = UUID.randomUUID().toString();
		created = new Date();
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
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the cityCode
	 */
	public Integer getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the countryCode
	 */
	public Integer getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
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
	
	

}
