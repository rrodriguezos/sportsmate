package controllers.customer;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import domain.Customer;
import domain.Invoice;
import services.CustomerService;
import services.InvoiceService;

public class AutomaticInvoiceScheludeTask {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	InvoiceService invoiceService;
	
    @SuppressWarnings("deprecation")
	@Scheduled(cron="*/3600 * * * * ?")
    public void automaticScheludeGenerator()
    {    	
    	
    	
    	Collection<Customer> customers;
    	
    	customers=customerService.findAll();
    	
    	for( Customer e: customers){

	        	Invoice invoice;
	        	
	        	System.out.println("customer numero "+e.getId());
	        	
	          
	        	try{
	        	invoice = customerService.getLastInovice(e.getId());
	        	
	        	if(invoice.getDatePay()!=null){ //la ultima, factura, si esta pagada procedemos a comparar fechas
	        		
	        		
	        		//newInvoiceDate.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
	        		System.out.println("La fecha de la factura es: "+invoice.getDatePay().toString());
	        		System.out.println("La fecha actual es: "+Calendar.getInstance().DAY_OF_MONTH);
	        		long milis1=invoice.getDatePay().getTime();
	        		long milis2=new Date().getTime();
	        		long diff = milis2 - milis1;
	        		long diffDays = diff / (24 * 60 * 60 * 1000);
	        		
	        		
	        		if(diffDays>=31){//han pasado mas de 31 dias, generamos una factura nueva
	        			
	        			Invoice newInvoiceToSave=new Invoice();
	        			
	        			newInvoiceToSave.setCustomer(e);
	        			Date deadLine=new Date();
	        			deadLine.setTime(deadLine.getTime()+172800000);
	        			
	        			newInvoiceToSave.setDeadLine(deadLine);
	        			newInvoiceToSave.setFee(15.0);
	        			invoiceService.save(newInvoiceToSave);
	        			System.out.println("nueva factura generada");
	        			
	        			
	        			
	        		}
	        		
	        	}else{//aun no esta pagada, por lo tango no podemos generar otra factura hasta que pague
	        		
	        		
	        		
	        	}
	        	}catch(NullPointerException a){
	        		System.out.println("usuario con id: "+ e.getId()+ "no tiene facturas creadas (nuevo usuario?)");
	        		Invoice newInvoiceToSave=new Invoice();
        			
        			newInvoiceToSave.setCustomer(e);
        			Date deadLine=new Date();
        			deadLine.setTime(deadLine.getTime()+172800000);
        			
        			newInvoiceToSave.setDeadLine(deadLine);
        			newInvoiceToSave.setFee(15.0);
        			invoiceService.save(newInvoiceToSave);
	        		
	        	}catch (Throwable e2) {
	        		System.out.println("usuario con id: "+ e.getId()+ "no tiene facturas creadas (nuevo usuario?)");
	        		
					// TODO: handle exception
				}
        	
        	
        }
    		
    		
    	}
    	
    	

}
