/**
 * 
 */
package org.pjay.codes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author vijayk
 *
 */
public interface PhoneCodesRepository extends JpaRepository<Phone, Integer> {

	List<Phone> findByIso2CountryCode(String iso2CountryCode);

}
