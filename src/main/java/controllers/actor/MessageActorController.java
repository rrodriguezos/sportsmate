package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController{
	//Services----------------------------------------------------------------
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ActorService actorService;

	//Constructors -----------------------------------------------------------
	public MessageActorController()
	{
		
		super();
		
	}

	//Listing-----------------------------------------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId)
	{
		
		ModelAndView result;
		Collection<Message> messages;
		
		messages = messageService.findAllMessagesByFolderId(folderId);
		
		result = new ModelAndView("message/list");
		
		result.addObject("messages", messages);
		
		return result;
		
	}
	
	// Display------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int messageId) {

		ModelAndView result;
		Message message;
		MessageForm messageForm;
		int folderId;
		

		message = messageService.findOne(messageId);
		messageForm = messageService.construct(message);
		folderId = message.getFolder().getId();

		result = new ModelAndView("message/display");

		result.addObject("messageForm", messageForm);
		result.addObject("folderId", folderId);
		result.addObject("from", message.getSender().getName()+" "+
								   message.getSender().getSurname()+" ("+
				                   message.getSender().getEmail()+")");
		
		result.addObject("to", message.getRecipient().getName()+" "+
				   				   message.getRecipient().getSurname()+" ("+
				   				   message.getRecipient().getEmail()+")");

		return result;

	}
		
	// Creation----------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Message message;
		MessageForm messageForm;

		message = messageService.create();
		messageForm = messageService.construct(message);

		result = createEditModelAndView(messageForm);

		return result;

	}			
	// Edition-----------------------------------------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="send")
	public ModelAndView send(@Valid MessageForm messageForm, BindingResult binding)
	{
		
		ModelAndView result;
		Message message;
		Folder outBox;
		
		if (binding.hasErrors()){
			result = createEditModelAndView(messageForm);
		}else{
			try{
				message = messageService.reconstruct(messageForm);
				outBox = message.getFolder();
				messageService.sendMessage(message);
				result = new ModelAndView("redirect:list.do?folderId="+outBox.getId());
			}catch(Throwable oops){
				result = createEditModelAndView(messageForm, "message.commit.error");
			}
		}
		return result;
		
	}
		
	//Ancillary methods--------------------------------------------------------
	protected ModelAndView createEditModelAndView(MessageForm messageForm)
	{
		
		ModelAndView result;
		
		result = createEditModelAndView(messageForm, null);
		
		return result;
		
	}
	
	protected ModelAndView createEditModelAndView(MessageForm messageForm, String message)
	{
		
		ModelAndView result;
		Collection<Actor> actors;
		Actor sender;
		
		actors = actorService.findAll();
		sender = actorService.findByPrincipal();
		
		actors.remove(sender);
		
		result = new ModelAndView("message/edit");
		
		result.addObject("messageForm", messageForm);
		result.addObject("actors", actors);
		result.addObject("sender", sender.getName()+"-"+sender.getSurname()+" "+sender.getEmail());
		result.addObject("message",message);

		return result;
		
	}
}
	
