/**
 * 
 */
package org.pjay.codes;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author vijayk
 *
 */
@Service
public class ConcurrentExternalService {

	@Autowired
	RestTemplate restTemplate;

	final String EXTERNAL_SERVICE_URL = "http://localhost:8181";

	@Async
	public CompletableFuture<Country> callExternalCountryService(String iso2countrycode) {
		String countryURL = EXTERNAL_SERVICE_URL + "/country/" + iso2countrycode;
		ResponseEntity<Country> responseEntity = restTemplate.exchange(countryURL, HttpMethod.GET, null, Country.class);
		return CompletableFuture.completedFuture(responseEntity.getBody());
	}

	@Async
	public CompletableFuture<Phone> callExternalPhoneService(String iso2countrycode) {
		String phoneURL = EXTERNAL_SERVICE_URL + "/phone/" + iso2countrycode;
		ResponseEntity<Phone> responseEntity = restTemplate.exchange(phoneURL, HttpMethod.GET, null, Phone.class);
		return CompletableFuture.completedFuture(responseEntity.getBody());
	}

}
