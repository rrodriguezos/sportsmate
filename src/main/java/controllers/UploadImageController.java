package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.UserService;
import domain.Customer;
import domain.User;

@Controller
@RequestMapping("/upload")
public class UploadImageController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
		
	// Showing ----------------------------------------------------------------
	
	@RequestMapping(value = "/imageUser", method =RequestMethod.GET)
	public ModelAndView fileUser(HttpServletResponse response, @RequestParam int userId) throws IOException 
	{	
		
		User user;
		byte[] imagen;
		InputStream inputStream;
		
		user = userService.findOne(userId);

		response.setContentType("image/jpeg");	
		
		imagen = user.getImagen();
		inputStream = new ByteArrayInputStream(imagen);
		
		IOUtils.copy(inputStream, response.getOutputStream());

		return null;
		
	}
	
	@RequestMapping(value = "/imageCustomer", method =RequestMethod.GET)
	public ModelAndView fileCustomer(HttpServletResponse response, @RequestParam int customerId) throws IOException 
	{	
		
		Customer customer;
		byte[] imagen;
		InputStream inputStream;
		
		customer = customerService.findOne(customerId);

		response.setContentType("image/jpeg");	
		
		imagen = customer.getImagen();
		inputStream = new ByteArrayInputStream(imagen);
		
		IOUtils.copy(inputStream, response.getOutputStream());

		return null;
		
	}
	
}
