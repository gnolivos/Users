package com.gnolivos.users.vo;

/**
 * @author gabriel.nolivos
 *
 */
public class PhoneRequest {
	
	private Integer number;
    private Integer citycode;
    private Integer countrycode;
    
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
	 * @return the citycode
	 */
	public Integer getCitycode() {
		return citycode;
	}
	/**
	 * @param citycode the citycode to set
	 */
	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}
	/**
	 * @return the countrycode
	 */
	public Integer getCountrycode() {
		return countrycode;
	}
	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(Integer countrycode) {
		this.countrycode = countrycode;
	}
    
    

}
