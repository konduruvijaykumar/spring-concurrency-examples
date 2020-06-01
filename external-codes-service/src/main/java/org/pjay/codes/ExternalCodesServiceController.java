/**
 * 
 */
package org.pjay.codes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
// Not following proper code structure, as this is just for testing and demo
@RestController
public class ExternalCodesServiceController {

	Random random = new Random();

	@Autowired
	PhoneCodesRepository phoneCodesRepository;

	@Autowired
	CountryCodesRepository countryCodesRepository;

	@GetMapping("/country/{iso2countrycode}")
	public ResponseEntity<Country> getCountryName(@PathVariable("iso2countrycode") String iso2countrycode)
			throws InterruptedException {
		Thread.sleep(4000);
		List<Country> findByIso2CountryCode = countryCodesRepository.findByIso2CountryCode(iso2countrycode);
		if (null != findByIso2CountryCode && !findByIso2CountryCode.isEmpty()) {
			return new ResponseEntity<>(findByIso2CountryCode.get(0), HttpStatus.OK);
		}
		findByIso2CountryCode = new ArrayList<>();
		Country country = new Country();
		country.setCountryName("");
		country.setIso2CountryCode(iso2countrycode);
		findByIso2CountryCode.add(country);
		return new ResponseEntity<>(findByIso2CountryCode.get(0), HttpStatus.OK);
	}

	@GetMapping("/phone/{iso2countrycode}")
	public ResponseEntity<Phone> getPhoneCode(@PathVariable("iso2countrycode") String iso2countrycode)
			throws InterruptedException {
		if (random.nextBoolean()) {
			Thread.sleep(6000);
		} else {
			Thread.sleep(3000);
		}
		List<Phone> findByIso2CountryCode = phoneCodesRepository.findByIso2CountryCode(iso2countrycode);
		if (null != findByIso2CountryCode && !findByIso2CountryCode.isEmpty()) {
			return new ResponseEntity<>(findByIso2CountryCode.get(0), HttpStatus.OK);
		}
		findByIso2CountryCode = new ArrayList<>();
		Phone phone = new Phone();
		phone.setPhoneCode("");
		phone.setIso2CountryCode(iso2countrycode);
		findByIso2CountryCode.add(phone);
		return new ResponseEntity<>(findByIso2CountryCode.get(0), HttpStatus.OK);
	}

}
