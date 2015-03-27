package controllers;



import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;







import services.CustomerService;
import services.InvoiceService;
import domain.Invoice;


@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController 
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



}