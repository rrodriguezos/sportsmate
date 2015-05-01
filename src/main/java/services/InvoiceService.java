
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import security.Authority;
import security.LoginService;
import domain.Customer;
import domain.Invoice;
@Service
@Transactional
public class InvoiceService {
@Autowired
	private InvoiceRepository invoiceRepository;
@Autowired
	private CustomerService customerService;
@Autowired
	private AdministratorService administratorService;
@Autowired
	private LoginService loginService;

public Collection<Invoice>  findAll(){
return invoiceRepository.findAll();
}
public Invoice findOne(Integer valueOf) {
	Authority authority= new Authority();
	authority.setAuthority("ADMIN");
	Invoice i = invoiceRepository.findOne(valueOf);
	Assert.isTrue(i.getCustomer().equals(customerService.findByPrincipal()) || loginService.getPrincipal().getAuthorities().contains(authority) );
return invoiceRepository.findOne(valueOf);
}
public Invoice save(Invoice invoice){
return invoiceRepository.save(invoice);
}
public Collection<Invoice> findAllInvoicesByCustomerId()
{	
	Collection<Invoice> all;
	Customer customer;
	int customerId;
	
	customer = customerService.findByPrincipal();
	customerId = customer.getId();
	all = invoiceRepository.findAllInvoicesByCustomerId(customerId);
	
	return all;
	
}
}