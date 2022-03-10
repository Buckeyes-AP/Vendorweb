package com.maxtrain.bootcamp.invoiceline;

import javax.persistence.*;

import com.maxtrain.bootcamp.invoice.Invoice;
import com.maxtrain.bootcamp.product.Product;

@Entity
public class Invoiceline {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private int id;
	@Column(columnDefinition="int")
	private int quantity;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="invoiceId", columnDefinition="int")
	private Invoice invoice;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="productId", columnDefinition="int")
	private Product product;
	
	public Invoiceline() {}
}
