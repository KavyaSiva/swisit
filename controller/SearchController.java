package com.stackroute.swisit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.swisit.domain.QueryBean;
import com.stackroute.swisit.domain.SwisitBean;
import com.stackroute.swisit.exception.SearcherServiceException;
import com.stackroute.swisit.searchservice.SearchService;

@RestController
public class SearchController {
	
	
	
	@Autowired
	QueryBean queryBean;
	
	
	@Autowired
	private SearchService searchService;
	
	
	@RequestMapping(value="urlget", method=RequestMethod.GET)
	public Iterable<SwisitBean> get()
	{
		return searchService.getAll();
	}
	
	@RequestMapping(value="urlpost", method=RequestMethod.POST)
	public ResponseEntity save()
	{
		try
		{
		searchService.save();
		}
		catch (SearcherServiceException e) {
            
            return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Map msgMap = new HashMap<String,String>();
	    msgMap.put("message","Data Inserted successsfully");
	    return new ResponseEntity<Map<String,String>>(msgMap, HttpStatus.OK);
	
	}
	
	
	@RequestMapping(value="urlpostquery", method=RequestMethod.POST)
	public ResponseEntity saveQuery(@RequestBody QueryBean queryBean)
	{
		searchService.saveQuery(queryBean);
		
		
		Map msgMap = new HashMap<String,String>();
	    msgMap.put("message","Data Inserted successsfully");
	    return new ResponseEntity<Map<String,String>>(msgMap, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="urlgetquery", method=RequestMethod.GET)
	public Iterable<QueryBean> getQuery()
	{
		
		return searchService.getQuery();
	}
	
	
}
	