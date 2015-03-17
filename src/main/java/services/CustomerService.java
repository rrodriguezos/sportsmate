
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Customer;
@Service
@Transactional
public class CustomerService {
@Autowired
	private CustomerRepository customerRepository;
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
}