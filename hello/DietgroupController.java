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

import com.cybermate.model.Dietgroup;
import com.cybermate.service.DietService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("diet")
public class DietgroupController {
	
	@Autowired
	private DietService dietService;
	@GetMapping("dietgroup/{id}")
	public ResponseEntity<Dietgroup> getById(@PathVariable("id") Integer id) {
		Dietgroup dietgroup=dietService.getById(id);
		return new ResponseEntity<Dietgroup>(dietgroup, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("dietname/{name}")
	public ResponseEntity<Dietgroup> getByName(@PathVariable("name") String name) {
		Dietgroup dietgroup=dietService.getByName(name);
		return new ResponseEntity<Dietgroup>(dietgroup, HttpStatus.OK);
	}
	
	
	
	@GetMapping("dietgroups")
	public ResponseEntity<List<Dietgroup>> getAllDietgroups() {
		List<Dietgroup> list = dietService.getAllDietgroups();
		return new ResponseEntity<List<Dietgroup>>(list, HttpStatus.OK);
	}
	@PostMapping("dietgroup")
	public ResponseEntity<Void> addDietgroup(@RequestBody Dietgroup dietgroup, UriComponentsBuilder builder) {
                boolean flag = dietService.addDietgroup(dietgroup);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/dietgroup/{id}").buildAndExpand(dietgroup.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("dietgroup/put")
	public ResponseEntity<Dietgroup> updateDietgroup(@RequestBody Dietgroup dietgroup) {
		dietService.updateDietgroup(dietgroup);
		return new ResponseEntity<Dietgroup>(dietgroup, HttpStatus.OK);
	}
	@DeleteMapping("dietgroup/{id}")
	public ResponseEntity<Void> deleteDietgroup(@PathVariable("id") Integer id) {
		dietService.deleteDietgroup(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}		

}//class
