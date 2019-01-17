package com.cybermate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybermate.model.Incomegroup;
import com.cybermate.service.IncomeService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("income")
public class IncomegroupController {
	
	@Autowired
	private IncomeService incomeService;
	@GetMapping("incomegroup/{id}")
	public ResponseEntity<Incomegroup> getById(@PathVariable("id") Integer id) {
		Incomegroup incomegroup = incomeService.getById(id);
		return new ResponseEntity<Incomegroup>(incomegroup, HttpStatus.OK);
	}
	@GetMapping("incomegroups")
	public ResponseEntity<List<Incomegroup>> getAllIncomegroups() {
		List<Incomegroup> list = incomeService.getAllIncomegroups();
		return new ResponseEntity<List<Incomegroup>>(list, HttpStatus.OK);
	}
	@PostMapping("incomegroup")
	public ResponseEntity<Void> addIncomegroup(@RequestBody Incomegroup incomegroup, UriComponentsBuilder builder) {
                boolean flag = incomeService.addIncomegroup(incomegroup);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/incomegroup/{id}").buildAndExpand(incomegroup.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("incomegroup/put")
	public ResponseEntity<Incomegroup> updateIncomegroup(@RequestBody Incomegroup incomegroup) {
		incomeService.updateIncomegroup(incomegroup);
		return new ResponseEntity<Incomegroup>(incomegroup, HttpStatus.OK);
	}
	@DeleteMapping("incomegroup/{id}")
	public ResponseEntity<Void> deleteIncomegroup(@PathVariable("id") Integer id) {
		incomeService.deleteIncomegroup(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}		

}//class
