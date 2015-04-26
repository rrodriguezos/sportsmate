package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.HashPassword;
import domain.Customer;
import domain.Event;
import domain.Folder;
import domain.Invoice;
import domain.Tournament;
import domain.User;
import domain.Vote;
import forms.CustomerForm;
import forms.UserVoteForm;

@Service
@Transactional
public class CustomerService {
	
	// Managed repository -----------------------------------------------------
	@Autowired
	private CustomerRepository customerRepository;
	
	// Supporting services ---------------------------------------------------
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FolderService folderService;
	
	// Constructor ------------------------------------------------------------
	public CustomerService()
	{
		
		super();
		
	}	

	// Simple CRUD methods ----------------------------------------------------
	public Collection<Customer> findAll() 
	{
		
		Collection<Customer> all;
		
		all = customerRepository.findAll();
		
		return all;
		
	}

	public Customer findOne(int customerId) 
	{

		Customer result;

		result = customerRepository.findOne(customerId);

		return result;
		
	}
	
	public Customer create() 
	{
		
		Customer customer; 
		UserAccount useraccount;
		Authority authority;
		
		Collection<Vote> votes;
		Collection<Folder> folders;
		Collection<Event> events;
		Collection<Tournament> tournaments;
		Collection<Invoice> invoices;

		customer = new Customer();
		useraccount = new UserAccount();
		authority = new Authority();
		
		votes = new ArrayList<Vote>();
		folders = new ArrayList<Folder>();
		events = new ArrayList<Event>();
		tournaments = new ArrayList<Tournament>();
		invoices = new ArrayList<Invoice>();
		
		
		authority.setAuthority("CUSTOMER");
		useraccount.addAuthority(authority);
		customer.setUserAccount(useraccount);
		
		customer.setVotes(votes);
		customer.setFolders(folders);
		customer.setEvents(events);
		customer.setTournaments(tournaments);
		customer.setInvoices(invoices);

		return customer;
		
	}

	public Customer save(Customer customer) 
	{
		
		Customer result;
		String passwordCoded;
		String password;
		UserAccount userUserAccount;
		Folder inbox;
		Folder outbox;
		Collection<Folder> folders;

		folders = new ArrayList<Folder>();

		password = customer.getUserAccount().getPassword();
		passwordCoded = HashPassword.encode(password);
		
		userUserAccount = customer.getUserAccount();
		userUserAccount.setPassword(passwordCoded);

		customer.setUserAccount(userUserAccount);
		
		inbox = folderService.create(customer, "Inbox");
		outbox = folderService.create(customer, "Outbox");

		folders.add(inbox);
		folders.add(outbox);

		customer.setFolders(folders);

		result = customerRepository.save(customer);

		inbox.setActor(result);
		outbox.setActor(result);

		folderService.save(inbox);
		folderService.save(outbox);
		
		return result;
		
	}	

	@SuppressWarnings("static-access")
	public Collection<Invoice> getAllInvoices() 
	{
		
		Collection<Invoice> all;
		UserAccount userAccount;
		int userAccountId;
		
		userAccount = loginService.getPrincipal();
		userAccountId = userAccount.getId();
		all = customerRepository.getAllInvoices(userAccountId);
		
		return all;
		
	}

	// Other business methods ------------------------------------------------
	public Customer findByPrincipal() 
	{

		Customer customer;
		UserAccount userAccount;
		int userAccountId;

		userAccount = LoginService.getPrincipal();
		userAccountId = userAccount.getId();
		customer = customerRepository.findCustomerByUserAccountId(userAccountId);

		return customer;

	}
	
	public void checkPrincipal(Customer customer)
	{
		Assert.notNull(customer);
		
		Customer aux;
		
		aux = findByPrincipal();
		
		Assert.isTrue(aux.getId()==customer.getId());
		
	}

	public Invoice getLastInovice(int customerId) 
	{
		
		Invoice invoice;

		invoice = customerRepository.getlastInvoice(customerId);
		
		if (invoice == null) {
			
			new Throwable().printStackTrace();
			
		}
		
		return invoice;
		
	}

	public CustomerForm construct(Customer customer) 
	{
		
		CustomerForm customerForm;
		
		customerForm = new CustomerForm();

		customerForm.setUsername(customer.getUserAccount().getUsername());
		customerForm.setPassword(customer.getUserAccount().getPassword());

		customerForm.setName(customer.getName());		
		customerForm.setSurname(customer.getSurname());
		customerForm.setEmail(customer.getEmail());
		customerForm.setPhone(customer.getPhone());
		customerForm.setImagen(customer.getImagen());
		
		customerForm.setCif(customer.getCif());
		customerForm.setStreet(customer.getStreet());
		customerForm.setZip(customer.getZip());
		customerForm.setProvinceCenter(customer.getProvinceCenter());
		customerForm.setCity(customer.getCity());
		customerForm.setNameCenter(customer.getNameCenter());
		customerForm.setPhoneCenter(customer.getPhoneCenter());
		customerForm.setEmailCenter(customer.getEmailCenter());
		customerForm.setWeb(customer.getWeb());	
		
		return customerForm;
	}

	public Customer reconstruct(CustomerForm customerForm) {
		
		Customer customer;
		Collection<Invoice> invoices;
		Invoice invoice;
		
		customer = create();
		invoices = new ArrayList<Invoice>();
		invoice = new Invoice();
		
		
		customer.setName(customerForm.getName());
		customer.setSurname(customerForm.getSurname());
		customer.setEmail(customerForm.getEmail());
		customer.setPhone(customerForm.getPhone());
		customer.setImagen(customerForm.getImagen());
		
		customer.setCif(customerForm.getCif());
		customer.setStreet(customerForm.getStreet());
		customer.setZip(customerForm.getZip());
		customer.setProvinceCenter(customerForm.getProvinceCenter());
		customer.setCity(customerForm.getCity());
		customer.setNameCenter(customerForm.getNameCenter());
		customer.setPhoneCenter(customerForm.getPhoneCenter());
		customer.setEmailCenter(customerForm.getEmailCenter());
		customer.setWeb(customerForm.getWeb());
		
		invoice.setCustomer(customer);
		invoice.setDeadLine(new Date(new Date().getTime() + 864000000));
		invoice.setFee(15);

		invoices.add(invoice);
		customer.setInvoices(invoices);
		
		customer.getUserAccount().setUsername(customerForm.getUsername());
		customer.getUserAccount().setPassword(customerForm.getPassword());
		
		Assert.isTrue(customerForm.getTerms());
		Assert.isTrue(customer.getUserAccount().getPassword().equals(customerForm.getPassword2()));

		return customer;

	}

	public Customer reconstructEdit(CustomerForm customerForm) {
		Customer result = findByPrincipal();
		result.setCif(customerForm.getCif());
		result.setStreet(customerForm.getStreet());
		result.setZip(customerForm.getZip());
		result.setProvinceCenter(customerForm.getProvinceCenter());
		result.setCity(customerForm.getCity());
		result.setNameCenter(customerForm.getNameCenter());
		result.setPhoneCenter(customerForm.getPhoneCenter());
		result.setEmailCenter(customerForm.getEmailCenter());
		result.setWeb(customerForm.getWeb());
		result.setPhone(customerForm.getPhone());

		result.setName(customerForm.getName());
		result.setSurname(customerForm.getSurname());
		result.setEmail(customerForm.getEmail());

		String password = customerForm.getPassword();

		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);

		result.getUserAccount().setPassword(md5);

		return result;

	}

	public boolean userRegistered(String username) 
	{
		
		Boolean res = true;
		Customer customer;
		
		customer = customerRepository.getCustomerByUserName(username);
		
		if (customer == null) {
			
			res = false;
			
		}
		
		return res;
	}

	public Customer findOneToEdit(int customerId) 
	{
		
		Assert.notNull(customerId);
		
		Customer customer;
		
		customer = customerRepository.findOne(customerId);
		
		checkPrincipal(customer);

		return customer;
		
	}

	public void delete(Customer customer) 
	{
		
		Assert.notNull(customer);
		checkPrincipal(customer);
		
		customerRepository.delete(customer);

	}

	public Collection<Customer> findByKeyword(String keywords) 
	{
		
		Assert.notNull(keywords);
		Collection<Customer> allCustomers;
		Collection<Customer> allByKeyword;

		allCustomers = findAll();
		allByKeyword = new HashSet<Customer>();
		keywords = keywords.toUpperCase();

		for (Customer p : allCustomers) {
			if (p.getNameCenter().toUpperCase().contains(keywords)
					|| p.getProvinceCenter().toUpperCase().contains(keywords)
					|| p.getEmailCenter().toUpperCase().contains(keywords)) {
				allByKeyword.add(p);
			}
		}

		return allByKeyword;
		
	}

	public Vote voteReconstruct(UserVoteForm userVoteForm) 
	{
		
		Vote vote;
		User user;
		String name;
		
		vote = new Vote();
		user = userService.findByPrincipal();
		name = user.getName();
		
		vote.setNameUser(name);
		vote.setScore(userVoteForm.getScore());
		
		return vote;
		
	}

	public void saveVote(Customer customer) 
	{
		
		Double aux = 0.0;
		Double rating = 0.0;
		
		for (Vote a : customer.getVotes()) {
			rating += a.getScore();
			aux++;
		}
		
		customer.setRating(rating / aux);
		customerRepository.save(customer);

	}

	public Customer findOneFromPlaceString(String placeString) 
	{
		
		Customer customer = null;
		Collection<Customer> customers;
		
		customers = customerRepository.findAll();
		
		for (Customer itero : customers) {
			if (itero.getNameCenter().equals(placeString)) {
				customer = itero;
				break;
			}
		}
		
		return customer;
	}
}