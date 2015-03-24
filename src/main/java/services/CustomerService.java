
package services;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Invoice;
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
		
		result.setName(customerForm.getName());
		result.setSurname(customerForm.getSurname());
		result.setEmail(customerForm.getEmail());
		
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