package controllers.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.InvoiceService;
import controllers.AbstractController;
import domain.Customer;
import domain.Invoice;

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
	
	@RequestMapping("/manageInvoices")
	public ModelAndView manageInvoices(@RequestParam int idCustomer)
	{
		
		ModelAndView result= new ModelAndView("admin/invoice/manageInvoices");
		
		Customer c = customerService.findOne(idCustomer);
		
		if (c == null)
			new Throwable("oppps you are try to hack? or bad id?");
		
		result.addObject("customer", c);
		result.addObject("invoices", c.getInvoices());
		
		return result;
	}
	
	@RequestMapping("/makeAsPayed")
	public ModelAndView makeAsPayed( @RequestParam int id)
	{
		
		Invoice i = invoiceService.findOne(id);
		
		if (i == null)
			new Throwable("opps you are try to hack with bad id?");
		
		i.setDatePay(new Date());
		
		invoiceService.save(i);
		
		return manageInvoices(i.getCustomer().getId());
		
	}
}
