/**
 * 
 */
package org.pjay.codes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author vijayk
 *
 */
@Component
@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ISO2_COUNTRY_CODE")
	private String iso2CountryCode;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIso2CountryCode() {
		return iso2CountryCode;
	}

	public void setIso2CountryCode(String iso2CountryCode) {
		this.iso2CountryCode = iso2CountryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((iso2CountryCode == null) ? 0 : iso2CountryCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (iso2CountryCode == null) {
			if (other.iso2CountryCode != null)
				return false;
		} else if (!iso2CountryCode.equals(other.iso2CountryCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", iso2CountryCode=" + iso2CountryCode + ", countryName=" + countryName + "]";
	}

}
