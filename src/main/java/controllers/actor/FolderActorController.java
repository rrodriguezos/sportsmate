package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FolderService;
import domain.Customer;
import domain.Folder;
import domain.Invoice;

@Controller
@RequestMapping("/folder/actor")
public class FolderActorController {
	// Services-----------------------------------------------
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private CustomerService customerService;

	// Constructors-------------------------------------------
	public FolderActorController() 
	{
		
		super();
		
	}
			
	// Listing-------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() 
	{
		/* for debtors */
		
		Customer e = customerService.findByPrincipal();
		
		if (e != null )
		{
			if( e.isDebtor()){
				
				ModelAndView result;
				
				Collection<Invoice> invoices=customerService.getAllInvoices();
				
				result=new ModelAndView("customer/seeInvoices");
				result.addObject("invoices", invoices);
				return result;
			}
		}
		
		
		
		/* -------------*/
		
		ModelAndView result;
		Collection<Folder> folders;

		folders = folderService.findAllFoldersByActorId();

		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);

		return result;
		
	}

}
