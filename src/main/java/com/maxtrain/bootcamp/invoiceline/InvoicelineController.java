package com.maxtrain.bootcamp.invoiceline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoicelines")
public class InvoicelineController {

	@Autowired
	private InvoicelineRepository invlineRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Invoiceline>> GetInvoiceline() {
		var invoiceline = invlineRepo.findAll();
		return new ResponseEntity<Iterable<Invoiceline>>(invoiceline, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Invoiceline> GetInvoiceline(@PathVariable int id) {
		var invoiceline = invlineRepo.findById(id);
		if(invoiceline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoiceline>(invoiceline.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Invoiceline> postInvoiceline(@RequestBody Invoiceline invoiceline) {
		if(invoiceline == null || invoiceline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var invline = invlineRepo.save(invoiceline);
		return new ResponseEntity<Invoiceline>(invline, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoiceline(@PathVariable int id, @RequestBody Invoiceline invoiceline) {
		if(invoiceline == null || invoiceline.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var invline = invlineRepo.findById(invoiceline.getId());
		if(invline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invlineRepo.save(invoiceline);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoiceline(@PathVariable int id) {
		var invoiceline = invlineRepo.findById(id);
		if (invoiceline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invlineRepo.delete(invoiceline.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
