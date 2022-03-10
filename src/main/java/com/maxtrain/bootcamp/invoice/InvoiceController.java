package com.maxtrain.bootcamp.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceRepository invRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Invoice>> GetInvoices() {
		var invoices = invRepo.findAll();
		return new ResponseEntity<Iterable<Invoice>>(invoices, HttpStatus.OK);
		}
	
	@GetMapping("{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable int id) {
		var invoice = invRepo.findById(id);
		if(invoice.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoice.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Invoice> postInvoice(@RequestBody Invoice invoice) {
		if(invoice == null || invoice.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inv = invRepo.save(invoice);
		return new ResponseEntity<Invoice>(inv, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		if(invoice == null || invoice.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inv = invRepo.findById(invoice.getId());
		if(inv.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.save(invoice);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		var invoice = invRepo.findById(id);
		if(invoice.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.delete(invoice.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
