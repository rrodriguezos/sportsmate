package forms;



import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import antlr.debug.Event;
import domain.Folder;
import domain.Tournament;
import domain.Vote;



@Access(AccessType.PROPERTY)
public class CustomerForm 
{
	
	private String name,surname,email;
	private int phone;
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
	private Collection<Folder> folders;
	private Collection<Event> events;
	private Collection<Tournament> tournaments;
	private Collection<Vote> votes;
    
    public Collection<Vote> getVotes() {
		return votes;
	}
	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
	}
	public Collection<Folder> getFolders() {
		return folders;
	}
	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
	public Collection<Event> getEvents() {
		return events;
	}
	public void setEvents(Collection<Event> events) {
		this.events = events;
	}
	public Collection<Tournament> getTournaments() {
		return tournaments;
	}
	public void setTournaments(Collection<Tournament> tournaments) {
		this.tournaments = tournaments;
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
    //@Pattern(regexp = "^[9|6|7][0-9]{8}")
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
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
	
	
	@NotBlank
	@Size(min=9, max=9)
	public String getCif() 
	{
		return cif;
	}
	public void setCif(String cif) 
	{
		this.cif = cif;
	}
	
	
	@NotBlank
	public String getStreet() 
	{
		return street;
	}
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	@Range(min=0, max=99999)
	public int getZip() 
	{
		return zip;
	}
	public void setZip(int zip) 
	{
		this.zip = zip;
	}
	
	@NotBlank
	public String getProvinceCenter() 
	{
		return provinceCenter;
	}
	public void setProvinceCenter(String provinceCenter) 
	{
		this.provinceCenter = provinceCenter;
	}
	
	@NotBlank
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	@NotBlank
	public String getNameCenter() {
		return nameCenter;
	}
	public void setNameCenter(String nameCenter) {
		this.nameCenter = nameCenter;
	}
	
	@NotBlank
	@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
	public String getPhoneCenter() {
		return phoneCenter;
	}
	public void setPhoneCenter(String phoneCenter) {
		this.phoneCenter = phoneCenter;
	}
	
	@NotBlank
	@Email
	public String getEmailCenter() {
		return emailCenter;
	}
	public void setEmailCenter(String emailCenter) {
		this.emailCenter = emailCenter;
	}
	
	@URL
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	
	
	
	

	
}
