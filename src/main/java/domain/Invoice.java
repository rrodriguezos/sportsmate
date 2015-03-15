package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity{
	//Constructors----------------------------------------------------------------------
	public Invoice(){
		super();
	}	
	
	//Attributes-------------------------------------------------------------------------
	private double fee;
	private Date datePay;
	private Date deadLine;
	
	@Min(15)
	public double getFee() 
	{
		return fee;
	}
	public void setFee(double fee) 
	{
		this.fee = fee;
	}
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDatePay() 
	{
		return datePay;
	}
	public void setDatePay(Date datePay) 
	{
		this.datePay = datePay;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDeadLine() 
	{
		return deadLine;
	}
	public void setDeadLine(Date deadLine) 
	{
		this.deadLine = deadLine;
	}	
		
	//Relationships-------------------------------------------------------------------------
	private Customer customer;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
}
