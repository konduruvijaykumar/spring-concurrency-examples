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
public interface CountryCodesRepository extends JpaRepository<Country, Integer> {

	List<Country> findByIso2CountryCode(String iso2CountryCode);

}
