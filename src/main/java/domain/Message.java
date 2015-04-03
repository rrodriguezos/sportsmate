package domain;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	
	//Constructors----------------------------------------------------------------------
	public Message(){
		super();
	}
	
	//Attributes------------------------------------------------------------------------
	private String subject;
	private String body;
	private Date sendMoment;	
	
	@NotBlank
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@NotBlank
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSendMoment() {
		return sendMoment;
	}
	
	public void setSendMoment(Date sendMoment) {
		this.sendMoment = sendMoment;
	}
	
	//Relationships------------------------------------------------------------------------
	private Actor sender;
	private Actor recipient;
	private Folder folder;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Actor getSender() {
		return sender;
	}
	
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Actor getRecipient() {
		return recipient;
	}
	
	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Folder getFolder() {
		return folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	

}
