package domain;

import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity{
	//Constructors----------------------------------------------------------------------
	
	public Actor()
	{
		super();
	}	
			
	//Attributes-------------------------------------------------------------------------
		
	private String name;
	private String surname;
	private String email;
	private String phone;
	
	private byte[] imagen;
	private boolean errorImagen;

	@NotBlank
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
		
	@NotBlank
	public String getSurname() 
	{
		return surname;
	}
	public void setSurname(String surname) 
	{
		this.surname = surname;
	}

	@NotBlank
	@Email
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	@Transient
	public String getCadena(){
		return getName()+" "+getSurname()+" ("+getEmail()+")";
	}
	
	 @Lob
	 public byte[] getImagen() {
	  return imagen;
	 }
	 
	 public void setImagen(byte[] imagen) {
	  this.imagen = imagen;
	 }
	 
	@Transient
	public boolean isErrorImagen() {
		errorImagen = (getImagen() == null || getImagen().length == 0);
		return errorImagen;
	}
	
	
	//Relationships-------------------------------------------------------------------------
	private UserAccount userAccount;
	private Collection<Folder> folders;
		
	@Valid
	@NotNull
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	public UserAccount getUserAccount() 
	{
		return userAccount;
	}
		
	public void setUserAccount(UserAccount userAccount) 
	{
		this.userAccount = userAccount;
	}
		
	@Valid
	@NotNull
	@Size(min=2,max=2)
	@OneToMany(mappedBy = "actor")
	public Collection<Folder> getFolders() 
	{
		return folders;
	}
		
	public void setFolders(Collection<Folder> folders) 
	{
		this.folders = folders;
	}		
	
}
