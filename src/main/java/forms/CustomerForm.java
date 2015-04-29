package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Lob;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Access(AccessType.PROPERTY)
public class CustomerForm {

	private int id;

	private String name;
	private String surname;
	private String email;
	private String phone;
	private byte[] imagen;

	private String cif;
	private String street;
	private int zip;
	private String provinceCenter;
	private String city;
	private String nameCenter;
	private String phoneCenter;
	private String emailCenter;
	private String web;

	private String password;
	private String password2;
	private String username;

	private boolean terms;

	public CustomerForm() {

		super();

	}

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

	@SafeHtml
	@NotBlank
	@Pattern(regexp = "^[9|6|7][0-9]{8}")
	public String getPhone() {

		return phone;

	}

	public void setPhone(String phone) {

		this.phone = phone;

	}

	@Lob
	public byte[] getImagen() {

		return imagen;

	}

	public void setImagen(byte[] imagen) {

		this.imagen = imagen;

	}

	@Size(min = 5, max = 32)
	@SafeHtml
	@NotBlank
	public String getPassword() {

		return password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	@Size(min = 5, max = 32)
	@SafeHtml
	@NotBlank
	public String getPassword2() {

		return password2;

	}

	public void setPassword2(String password2) {

		this.password2 = password2;

	}

	@NotBlank
	@SafeHtml
	@Range(min = 3, max = 10)
	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	@SafeHtml
	@NotBlank
	@Size(min = 9, max = 9)
	public String getCif() {

		return cif;

	}

	public void setCif(String cif) {

		this.cif = cif;

	}

	@SafeHtml
	@NotBlank
	public String getStreet() {

		return street;

	}

	public void setStreet(String street) {

		this.street = street;

	}

	@Range(min = 0, max = 99999)
	public int getZip() {

		return zip;

	}

	public void setZip(int zip) {

		this.zip = zip;

	}

	@SafeHtml
	@NotBlank
	public String getProvinceCenter() {

		return provinceCenter;

	}

	public void setProvinceCenter(String provinceCenter) {

		this.provinceCenter = provinceCenter;

	}

	@SafeHtml
	@NotBlank
	public String getCity() {

		return city;

	}

	public void setCity(String city) {

		this.city = city;

	}

	@NotBlank
	@SafeHtml
	public String getNameCenter() {

		return nameCenter;

	}

	public void setNameCenter(String nameCenter) {

		this.nameCenter = nameCenter;

	}

	@SafeHtml
	@NotBlank
	@Pattern(regexp = "^[9|6|7][0-9]{8}")
	public String getPhoneCenter() {

		return phoneCenter;

	}

	public void setPhoneCenter(String phoneCenter) {

		this.phoneCenter = phoneCenter;

	}

	@SafeHtml
	@NotBlank
	@Email
	public String getEmailCenter() {

		return emailCenter;

	}

	public void setEmailCenter(String emailCenter) {

		this.emailCenter = emailCenter;

	}

	@URL
	@SafeHtml
	public String getWeb() {

		return web;

	}

	public void setWeb(String web) {

		this.web = web;

	}

	public boolean getTerms() {

		return terms;

	}

	public void setTerms(boolean terms) {

		this.terms = terms;

	}

}
