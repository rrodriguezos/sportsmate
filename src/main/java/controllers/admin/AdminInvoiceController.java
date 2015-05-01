package controllers.admin;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.InvoiceService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/admin/invoice")
public class AdminInvoiceController extends AbstractController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	


	@RequestMapping("/listCustomers")
	public ModelAndView listCustomers()
	{
		
		ModelAndView result=new ModelAndView("admin/invoice/listCustomers");
		Collection<Customer> customers =new ArrayList<Customer>();
		
		
		customers= customerService.findAll();
		
		if ( customers == null)
			 new Throwable("oopps no found customers in db ");
		
		result.addObject("customers", customers);
		
		return result;
	}
}
