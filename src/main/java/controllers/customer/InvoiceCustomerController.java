package controllers.customer;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;












import controllers.AbstractController;
import services.CustomerService;
import services.InvoiceService;
import domain.Customer;
import domain.Invoice;


@Controller
@RequestMapping("/customer")
public class InvoiceCustomerController extends AbstractController 
{
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InvoiceService invoiceService;
	

@RequestMapping("/seeInvoices")
public ModelAndView seeInvoices()
{
	ModelAndView result;
	
	Collection<Invoice> invoices=customerService.getAllInvoices();
	
	result=new ModelAndView("customer/seeInvoices");
	result.addObject("invoices", invoices);
	return result;
	
}

@RequestMapping("/invoiceDetails")
public ModelAndView invoiceDetails(@RequestParam int id)
{
	ModelAndView result;
	
	Invoice invoice=invoiceService.findOne(id);
	
	result=new ModelAndView("customer/invoiceDetails");
	result.addObject("invoice", invoice);
	return result;
	
}

@RequestMapping("/makePay")
public ModelAndView makePay(@RequestParam int id)
{
	
	ModelAndView result;
	
	if( id < 0)
		new Throwable("Bad id for invoice");
	
	
	Invoice invoice =invoiceService.findOne(id);
	Customer customer =customerService.findByPrincipal();
	
	if(customer != null)
		new Throwable("Bad customer");
	if(invoice != null)
		new Throwable("Bad invoice");
	
	result = new ModelAndView("customer/makePay");
	result.addObject("invoice", invoice);
	result.addObject("customer", customer);
	
	return result;
	
	
	
}



}