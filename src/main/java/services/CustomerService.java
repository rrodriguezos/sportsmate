
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Invoice;
import domain.User;
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
}