/**
 * 
 */
package org.pjay.codes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vijayk
 *
 */
@RestController
public class ConcurrentExternalServiceCallerController {

	@Autowired
	ConcurrentExternalService concurrentExternalService;

	// @GetMapping("/countries")
	// @PostMapping("/countries")
	@RequestMapping(value = "/countries", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<List<Country>> getCountries(@RequestBody String[] iso2countrycodes) {
		long start = System.currentTimeMillis();
		List<CompletableFuture<Country>> allCountryFuture = new ArrayList<>();
		List<Country> countries = new ArrayList<>();

		for (String iso2countrycode : iso2countrycodes) {
			allCountryFuture.add(concurrentExternalService.callExternalCountryService(iso2countrycode));
		}

		CompletableFuture.allOf(allCountryFuture.toArray(new CompletableFuture[0]));

		for (CompletableFuture<Country> countryFuture : allCountryFuture) {
			try {
				countries.add(countryFuture.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken by getCountries() method :: " + (end - start)/1000 + " secs");
		return new ResponseEntity<>(countries, HttpStatus.OK);
	}
	
	// @GetMapping("/phones")
	// @PostMapping("/phones")
	@RequestMapping(value = "/phones", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<List<Phone>> getPhones(@RequestBody String[] iso2countrycodes){
		long start = System.currentTimeMillis();
		List<CompletableFuture<Phone>> allPhoneFuture = new ArrayList<>();
		List<Phone> phones = new ArrayList<>();
		
		for (String iso2countrycode : iso2countrycodes) {
			allPhoneFuture.add(concurrentExternalService.callExternalPhoneService(iso2countrycode));
		}
		
		CompletableFuture.allOf(allPhoneFuture.toArray(new CompletableFuture[0]));
		
		for (CompletableFuture<Phone> phoneFuture : allPhoneFuture) {
			try {
				phones.add(phoneFuture.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken by getPhones() method :: " + (end - start)/1000 + " secs");
		return new ResponseEntity<>(phones, HttpStatus.OK);
	}

}
