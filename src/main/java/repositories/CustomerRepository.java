package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Invoice;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select p.invoices from Customer p where p.id=?1")
	Collection<Invoice> getAllInvoices(int id);
	
	@Query("select p.id from Customer p where p.userAccount.id=?1")
	int getIdFromUserAccount(int id);
	
	@Query("select c from Customer c where c.userAccount.id =?1")
	Customer findCustomerByUserAccountId(int userAccountId);

	@Query("select max(c) from Invoice c where c.customer.id=?1 ORDER BY deadLine DESC")
	Invoice getlastInvoice(int id);

	@Query("select c from Customer c where c.userAccount.username=?1")
	Customer getCustomerByUserName(String username);
	

}
