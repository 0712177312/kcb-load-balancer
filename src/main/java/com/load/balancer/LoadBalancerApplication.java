package com.load.balancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RestController
@RibbonClient(name = "biometrics", configuration = LoadBalancerConfiguration.class)
public class LoadBalancerApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/ping")
	public String ping() {
		String greeting = this.restTemplate.getForObject("http://biometrics/ping", String.class);
		return greeting;
	}
	@PostMapping(value = "/AbisClient/Enroll")
	public ResponseEntity<?> enroll(@RequestBody String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		
		 ResponseEntity<String> response = this.restTemplate.postForEntity("http://biometrics/AbisClient/Enroll", entity, String.class);
		
		return response;
	}
	@PostMapping(value = "/AbisClient/verify")
	public ResponseEntity<?> verify(@RequestBody String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		
		 ResponseEntity<String> response = this.restTemplate.postForEntity("http://biometrics/AbisClient/verify", entity, String.class);
		
		return response;
	}
	@PostMapping(value = "/AbisClient/verifyMultiple")
	public ResponseEntity<?> verifymutiple(@RequestBody String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		
		 ResponseEntity<String> response = this.restTemplate.postForEntity("http://biometrics/AbisClient/verifyMultiple", entity, String.class);
		
		return response;
	}
	@PostMapping(value = "/AbisClient/identify")
	public ResponseEntity<?> identify(@RequestBody String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		
		 ResponseEntity<String> response = this.restTemplate.postForEntity("http://biometrics/AbisClient/identify", entity, String.class);
		
		return response;
	}
	@PostMapping(value = "/AbisClient/delete")
	public ResponseEntity<?> delete(@RequestBody String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		
		 ResponseEntity<String> response = this.restTemplate.postForEntity("http://biometrics/AbisClient/delete", entity, String.class);
		
		return response;
	}
	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerApplication.class, args);
	}
}
