package com.cybermate.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybermate.model.TestDetailTxnDtl;
import com.cybermate.model.TestDetailsTxn;
import com.cybermate.service.TestDetailsTxnService;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestDetailsTxnService testTxnService;

	private TestDetailsTxnService testdetailsService;
	
	@GetMapping("/editfamilyhistory/{mrno}")
	public ResponseEntity<TestDetailsTxn> getAllTxnByMrno(@PathVariable("mrno") String mrno) {
		TestDetailsTxn txnlist = testTxnService.getByMrNo(mrno);
		return new ResponseEntity<TestDetailsTxn>(txnlist, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/newfamilyhistorypost", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Void> addTxnDtls(@RequestBody TestDetailsTxn testdetailtxn,
			UriComponentsBuilder builder) {
		TestDetailsTxn txn = new TestDetailsTxn();
		txn.setMedicalRecordNo(testdetailtxn.getMedicalRecordNo());
		txn.setNotes(testdetailtxn.getNotes());
		
		TestDetailTxnDtl txndtl;

		List<TestDetailTxnDtl> list = new ArrayList<>();
		for (int i = 0; i < testdetailtxn.getTestDetailTxnDtls().size(); i++) {
			txndtl = new TestDetailTxnDtl();
			txndtl.setTestDetailsTxn(txn);
			txndtl.setId(testdetailtxn.getTestDetailTxnDtls().get(i).getId());
			txndtl.setTestname(testdetailtxn.getTestDetailTxnDtls().get(i).getTestname());
			list.add(txndtl);
			
		}
		txn.setTestDetailTxnDtls(list);

		testTxnService.addTxn(txn);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updatefamilyhistoryput", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTxnDtls(@RequestBody TestDetailsTxn testdetailstxns,UriComponentsBuilder builder) {
		TestDetailsTxn txnData=new TestDetailsTxn();
		txnData.setNotes(testdetailstxns.getNotes());
		TestDetailTxnDtl txndtl;
		
		
		
				return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	
	
	
	

}