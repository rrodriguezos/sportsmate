
package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Event;
import domain.Folder;
import domain.Invoice;
import domain.Tournament;
import domain.Vote;
import forms.CustomerForm;
@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired 
	private LoginService loginService;
public Collection<Customer>  findAll(){
return customerRepository.findAll();
}
public Customer findOne(Integer valueOf) {
return customerRepository.findOne(valueOf);
}
public Customer save(Customer customer){
return customerRepository.save(customer);
}
public Customer create() 
{
	Customer customer = new Customer();

	UserAccount useraccount = new UserAccount();
	Authority authority = new Authority();
	authority.setAuthority("CUSTOMER");
	useraccount.addAuthority(authority);
	customer.setUserAccount(useraccount);

return customer;
}
public Collection<Invoice> getAllInvoices() {
	// TODO Auto-generated method stub
	
	int id=customerRepository.getIdFromUserAccount(loginService.getPrincipal().getId());
	return customerRepository.getAllInvoices(id);
}

	//Other business methods ------------------------------------------------
	public Customer findByPrincipal()
	{
	
		Customer customer;
		UserAccount userAccount;
		int userAccountId;
	
		userAccount = LoginService.getPrincipal();
		userAccountId= userAccount.getId();		
		customer = customerRepository.findCustomerByUserAccountId(userAccountId);
	
		return customer;
	
	}

	public Invoice getLastInovice(int id) 
	{
		// TODO Auto-generated method stub
		Invoice i;
		
		i=customerRepository.getlastInvoice(id);
		if(i==null){
			new Throwable().printStackTrace();
		}
		return i;
	}




	public Customer reconstruct(CustomerForm customerForm) {
		Customer result = new Customer();
		result.setCreditCard(customerForm.getCreditCard());
		result.setCif(customerForm.getCif());
		result.setStreet(customerForm.getStreet());
		result.setZip(customerForm.getZip());
		result.setProvinceCenter(customerForm.getProvinceCenter());
		result.setCity(customerForm.getCity());
		result.setNameCenter(customerForm.getNameCenter());
		result.setPhoneCenter(customerForm.getPhoneCenter());
		result.setEmailCenter(customerForm.getEmailCenter());
		result.setWeb(customerForm.getWeb());
		result.setPhone(String.valueOf(customerForm.getPhone()));
		
		result.setName(customerForm.getName());
		result.setSurname(customerForm.getSurname());
		result.setEmail(customerForm.getEmail());
		List<Vote> votes=new ArrayList<Vote>();
		List<Tournament> tournaments=new ArrayList<Tournament>();
		List<Folder> folders=new ArrayList<Folder>();
		List<Event> events=new ArrayList<Event>();
		List<Invoice> invoices =new ArrayList<Invoice>();
		Folder folder1 =new Folder();
		folder1.setActor(result);
		folder1.setName("Recividos");
		Folder folder2 =new Folder();
		folder2.setActor(result);
		folder2.setName("Enviados");
		folders.add(folder1);
		folders.add(folder2);
		
		result.setVotes(votes);
		result.setTournaments(tournaments);
		result.setFolders(folders);
		result.setEvents(events);
		result.setInvoices(invoices);
		
		
		
		 UserAccount userAccount = new UserAccount();
	     userAccount.setUsername(customerForm.getUsername());

	    String password = customerForm.getPassword();

	    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    String md5 = encoder.encodePassword(password, null);
	    
	    userAccount.setPassword(md5);
	    Authority authority = new Authority();
	    authority.setAuthority("CUSTOMER");
	    Collection<Authority> authorities = new ArrayList<Authority>();
	    authorities.add(authority);
	    userAccount.setAuthorities(authorities);
	    result.setUserAccount(userAccount);

		return result;

	}
	
	public boolean userRegistered(String username) {
		Boolean res = true;
		if (customerRepository.getCustomerByUserName(username) == null) {
			res = false;
		}
		return res;
	}
}

