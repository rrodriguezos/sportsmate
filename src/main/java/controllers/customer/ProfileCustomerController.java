package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.EventService;
import services.InvoiceService;
import services.TournamentService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Customer;
import domain.Event;
import domain.Invoice;
import domain.Tournament;


@Controller
@RequestMapping("/profile/customer/")
public class ProfileCustomerController extends AbstractController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	
// List------------------------------------------------------------------------

@RequestMapping(value = "/profile", method = RequestMethod.GET)
public ModelAndView list() 
{

	ModelAndView result;

	Customer profile = customerService.findByPrincipal();
	profile = customerService.findOne(profile.getId());

	Collection<Event> events = eventService.findAllEventsByCustomerId();
	Collection<Tournament> tournaments = tournamentService.
					findAllTournamentsByCustomerId();
	Collection<Invoice> invoices = invoiceService.
					findAllInvoicesByCustomerId();
	

		result = new ModelAndView("profile/list");
		result.addObject("profile", profile);
		result.addObject("actor", "/customer");
		result.addObject("requestURI", "profile/customer/profile.do");
		result.addObject("events", events);
		result.addObject("tournaments", tournaments);
		result.addObject("invoices", invoices);

return result;

}
		
//Edition----------------------------------------------------------------------

@RequestMapping( value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(@RequestParam int customerId)
{
	ModelAndView result;
	Customer customer;
	
	customer = customerService.findOne(customerId);
	Assert.notNull(customer);
	result = createEditModelAndView(customer);
	
	return result;
}

@RequestMapping( value = "/edit", method = RequestMethod.POST, params= "save")
public ModelAndView save(@Valid Customer customer, BindingResult binding)
{
	ModelAndView result;
	
	if(binding.hasErrors()){
		result = createEditModelAndView(customer);
	}else{
		try{
			customerService.save(customer);
			result= new ModelAndView("redirect:profile.do");
		}catch(Throwable oops){
			result = createEditModelAndView(customer, "customer.commit.error");
		}
}
	return result;
}

@RequestMapping( value ="/edit", method = RequestMethod.POST , params = "delete")
public ModelAndView delete(Customer customer, BindingResult binding)
{
	ModelAndView result;
	
	try{
		customerService.delete(customer);
		result = new ModelAndView("redirect:login.do");
	}catch(Throwable oops){
		result = createEditModelAndView(customer, "customer.commit.error");
}
	return result;


}

//The ancillary methods--------------------------------------------------------

protected ModelAndView createEditModelAndView(Customer customer){
	ModelAndView result;
	
	result = createEditModelAndView(customer, null);
	
	return result;
}

protected ModelAndView createEditModelAndView(Customer customer, String message)
{
	ModelAndView result;
	String name;
	String surname;
	String email;
	String phone;
	
	String cif;
	CreditCard creditCard;
	String street;
	Integer zip;
	String provinceCenter;
	String city;
	String nameCenter;
	String phoneCenter;
	String emailCenter;
	String web;
	
	name = customer.getName();
	surname = customer.getSurname();
	email = customer.getEmail();
	phone = customer.getPhone();
	cif = customer.getCif();
	creditCard = customer.getCreditCard();
	street = customer.getStreet();
	zip = customer.getZip();
	provinceCenter = customer.getProvinceCenter();
	city = customer.getCity();
	nameCenter = customer.getNameCenter();
	phoneCenter = customer.getPhoneCenter();
	emailCenter = customer.getEmailCenter();
	web = customer.getWeb();
	
	result = new ModelAndView("customer/edit");
	result.addObject("customer", customer );
	result.addObject("name",name );
	result.addObject("surname",surname );
	result.addObject("email",email );
	result.addObject("phone",phone );
	result.addObject("cif",cif );
	result.addObject("creditCard",creditCard );
	result.addObject("street",street );
	result.addObject("zip", zip);
	result.addObject("provinceCenter",provinceCenter );
	result.addObject("city", city);
	result.addObject("nameCenter",nameCenter );
	result.addObject("phoneCenter", phoneCenter);
	result.addObject("emailCenter",emailCenter );
	result.addObject("web",web );
	result.addObject("message", message);
	
	return result;
}
}


