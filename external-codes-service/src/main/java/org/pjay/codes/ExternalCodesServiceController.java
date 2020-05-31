/**
 * 
 */
package org.pjay.codes;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
public class ExternalCodesServiceController {

	Random random = new Random();

	@GetMapping("/country/{iso2countrycode}")
	public ResponseEntity<Country> getCountryName(@PathVariable("iso2countrycode") String iso2countrycode) {
		return null;
	}

	@GetMapping("/phone/{iso2countrycode}")
	public ResponseEntity<Country> getPhoneCode(@PathVariable("iso2countrycode") String iso2countrycode) {
		return null;
	}

}
