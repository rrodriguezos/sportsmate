package forms;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import domain.Actor;

public class MessageForm {

	private int id;
	private String subject;
	private String body;
	private Date sendMoment;
	private Actor recipient;
	
	public int getId() 
	{
		
		return id;
		
	}
	public void setId(int id) 
	{
		this.id = id;
		
	}
	
	@NotBlank
	public String getSubject() 
	{
		return subject;
		
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
		
	}
	
	@NotBlank
	public String getBody() 
	{
		return body;
		
	}
	public void setBody(String body)
	{
		this.body = body;
		
	}
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSendMoment() 
	{
		return sendMoment;
		
	}
	public void setSendMoment(Date sendMoment) 
	{
		this.sendMoment = sendMoment;
		
	}
	
	@NotNull
	@Valid
	public Actor getRecipient() 
	{
		return recipient;
		
	}
	public void setRecipient(Actor recipient)
	{
		this.recipient = recipient;
		
	}	
	
	
	
}
