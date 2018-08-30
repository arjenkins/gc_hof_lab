package com.grandcircus.hof;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.grandcircus.hof.model.Results;


@Controller
public class HofController {
	
	@RequestMapping("/")
	public ModelAndView tiny() {
		ModelAndView mav = new ModelAndView("tiny");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";
	//	Two different ways to get data to the view. This is 1st way.
		ResponseEntity<Results> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(null), Results.class);

		// Extract body from response.
		Results results = response.getBody();
		
		mav.addObject("results", results.getTiny());
		return mav;
		
	}
	
	@RequestMapping("/complete")
	public ModelAndView complete() {
		ModelAndView mav = new ModelAndView("complete");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";
	//  This is 2nd way to get data to the view.
		Results results = restTemplate.getForObject(url, Results.class);
		mav.addObject("results", results.getComplete());
		return mav;
	}	

}
