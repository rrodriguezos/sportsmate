package controllers.customer;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.InvoiceService;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import controllers.AbstractController;
import domain.Customer;
import domain.Invoice;



@Controller
@RequestMapping("/customer")
public class InvoiceCustomerController extends AbstractController 
{
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InvoiceService invoiceService;
	

@RequestMapping("/seeInvoices")
public ModelAndView seeInvoices()
{
	ModelAndView result;
	
	Collection<Invoice> invoices=customerService.getAllInvoices();
	
	result=new ModelAndView("customer/seeInvoices");
	result.addObject("invoices", invoices);
	return result;
	
}

@RequestMapping("/invoiceDetails")
public ModelAndView invoiceDetails(@RequestParam int id)
{
	ModelAndView result;
	
	Invoice invoice=invoiceService.findOne(id);
	
	result=new ModelAndView("customer/invoiceDetails");
	result.addObject("invoice", invoice);
	return result;
	
}

@RequestMapping("/makePay")
public ModelAndView makePay(@RequestParam int id)
{
	
	ModelAndView result;
	
	if( id < 0)
		new Throwable("Bad id for invoice");
	
	
	Invoice invoice =invoiceService.findOne(id);
	Customer customer =customerService.findByPrincipal();
	
	if(customer != null)
		new Throwable("Bad customer");
	if(invoice != null)
		new Throwable("Bad invoice");
	
	result = new ModelAndView("customer/makePay");
	result.addObject("invoice", invoice);
	result.addObject("customer", customer);
	
	return result;
	
	
	
}

@RequestMapping("/makePayPaypal")
public ModelAndView makePayPaypal(@RequestParam int id) throws IOException, PayPalRESTException
{
	Map<String, String> sdkConfig = new HashMap<String, String>();
	sdkConfig.put("mode", "sandbox");

	String accessToken = new OAuthTokenCredential("ASEA9iFHqo5dyXBCszPMOEcO7MqbddtDY_CCKdNvWWVqHKYVstl_zISqOT9ZhdPv_8WLHble8L4PQ5H6", "EOIJoOYXdZXCBo4LPXepLL1KursXAFRzaAWqFov0H9nOP_u14UHpZe5lqXPMtY9sZWhyLzOOmb4NoVkP", sdkConfig).getAccessToken();
	APIContext apiContext = new APIContext(accessToken);
	apiContext.setConfigurationMap(sdkConfig);

	Amount amount = new Amount();
	amount.setCurrency("EUR");
	amount.setTotal("15");

	Transaction transaction = new Transaction();
	transaction.setDescription("creating a payment");
	transaction.setAmount(amount);

	List<Transaction> transactions = new ArrayList<Transaction>();
	transactions.add(transaction);

	Payer payer = new Payer();
	payer.setPaymentMethod("paypal");

	Payment payment = new Payment();
	payment.setIntent("sale");
	payment.setPayer(payer);
	payment.setTransactions(transactions);
	RedirectUrls redirectUrls = new RedirectUrls();
	redirectUrls.setCancelUrl("https://devtools-paypal.com/guide/pay_paypal?cancel=true");
	redirectUrls.setReturnUrl("http://caballeroalba.cf:8080/SportsMate/customer/executePayment.do");
	payment.setRedirectUrls(redirectUrls);

	Payment createdPayment = payment.create(apiContext);	
	
	ModelAndView redirect=new ModelAndView("redirect:"+createdPayment.getLinks().get(1).getHref());
	
		return redirect;
	
	
	}

@RequestMapping("/executePayment")
public ModelAndView makePayPaypal(@RequestParam String PayerID, @RequestParam String paymentId) throws IOException, PayPalRESTException
{
	Map<String, String> sdkConfig = new HashMap<String, String>();
	sdkConfig.put("mode", "sandbox");
	String accessToken = new OAuthTokenCredential("ASEA9iFHqo5dyXBCszPMOEcO7MqbddtDY_CCKdNvWWVqHKYVstl_zISqOT9ZhdPv_8WLHble8L4PQ5H6", "EOIJoOYXdZXCBo4LPXepLL1KursXAFRzaAWqFov0H9nOP_u14UHpZe5lqXPMtY9sZWhyLzOOmb4NoVkP", sdkConfig).getAccessToken();
	APIContext apiContext = new APIContext(accessToken);
	
	apiContext.setConfigurationMap(sdkConfig);
	Payer payer = new Payer();
	payer.setPaymentMethod("paypal");
	Payment payment = new Payment();
	payment.setId(paymentId);
	
	
	PaymentExecution paymentExecute = new PaymentExecution();
	paymentExecute.setPayerId(PayerID);
	payment.execute(apiContext, paymentExecute);
	
	Collection<Invoice> invoices=invoiceService.findAllInvoicesByCustomerId();
	
	Invoice invoice=null;
	
	
	for (Invoice c :invoices){
		
		
		if(c.getDatePay()==null){
			
			invoice=c;
		}
	}
	
	invoice.setDatePay(new Date());
	invoiceService.save(invoice);
	
	ModelAndView result =seeInvoices();
	return result;
	
	
	
	
}
}



