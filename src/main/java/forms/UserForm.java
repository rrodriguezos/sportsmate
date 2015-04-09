package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Access(AccessType.PROPERTY)
public class UserForm {
	private int id;

	private String name,surname,email;
	private String phone;
    private String password;
	private String password2;
    private String username;
    
    private boolean terms;
    
    
    
    
    
    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
    
    
    @NotBlank
    @SafeHtml
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
    @SafeHtml
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@NotBlank
    @SafeHtml
    @Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    @Pattern(regexp = "^[9|6|7][0-9]{8}")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	 @Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 @Size(min = 5, max = 32)
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	@NotBlank
	@SafeHtml
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getTerms() {
		return terms;
	}
	public void setTerms(boolean terms) {
		this.terms = terms;
	}
    
    
	
	
}
