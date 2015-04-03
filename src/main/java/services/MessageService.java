
package services;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;
import forms.MessageForm;

@Service
@Transactional
public class MessageService {
	
	//Managed repository -----------------------------------------------------
	@Autowired
	private MessageRepository messageRepository;
	
	// Supporting services ---------------------------------------------------
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	// Constructors-----------------------------------------------------------
	public MessageService()
	{
		
		super();
		
	}
	
	// Simple CRUD methods ---------------------------------------------------
	
	public Collection<Message>  findAll()
	{
		Collection<Message> all;
		
		all = messageRepository.findAll();
		
		return all;
		
	}
	
	public Message findOne(Integer valueOf) 
	{
		
		Message result;
		
		result = messageRepository.findOne(valueOf);
		
		return result;
		
	}
	
	public Message create() {
		Message result;
		Actor sender;
		int actorId;
		Folder outBox;
		Date sendMoment;
		long miliseconds;

		result = new Message();
		miliseconds = System.currentTimeMillis()-3;
		sendMoment = new Date(miliseconds);
		sender = actorService.findByPrincipal();
		actorId = sender.getId();
		outBox = folderService.findOutboxFolderByActorId(actorId);	

		result.setSender(sender);
		result.setFolder(outBox);
		result.setSendMoment(sendMoment);		

		return result;
	}
	
	public void save(Message message)
	{
		
		Date sendMoment;
		long miliseconds;
		
		miliseconds = System.currentTimeMillis()-1000;
		sendMoment = new Date(miliseconds);	
		
		message.setSendMoment(sendMoment);

		messageRepository.save(message);
		
	}
	
	//Other business methods ------------------------------------------------
	
	public void sendMessage(Message message) 
	{
		
		Actor recipient;
		Actor sender;
		Folder inbox;
		Assert.notNull(message);

		save(message);

		recipient = message.getRecipient();
		sender = message.getSender();
		
		Assert.isTrue(!sender.equals(recipient));

		inbox = folderService.findInboxFolderByActorId(recipient.getId());
		
		message.setFolder(inbox);
		save(message);
	}
	
	public void checkPrincipal(Message message) 
	{
		
		Actor actor;
		Actor aux;
		
		actor = message.getFolder().getActor();
		aux = actorService.findByPrincipal();
		
		Assert.isTrue(actor.equals(aux));
	}
	
	public Collection<Message> findAllMessagesByFolderId(int folderId)
	{
		
		Collection<Message> all;
		Folder folder;
		
		folder = folderService.findOne(folderId);
		
		folderService.checkPrincipal(folder);
		
		all = messageRepository.findAllMessagesByFolderId(folderId);
		
		return all;
		
	}
	
	public MessageForm construct (Message message)
	{
		
		MessageForm result;
		
		result = new MessageForm();
		
		result.setId(message.getId());
		result.setSendMoment(message.getSendMoment());
		result.setSubject(message.getSubject());
		result.setBody(message.getBody());
		result.setRecipient(message.getRecipient());
		result.setSenderEmail(message.getSender().getEmail());	
		
		return result;
	}
	
	public Message reconstruct(MessageForm messageForm)
	{
		
		Message message;
		
		if (messageForm.getId() != 0){
			message = messageRepository.findOne(messageForm.getId());
			
			Assert.isTrue(messageForm.getRecipient().equals(message.getRecipient()));
			checkPrincipal(message);
			
		}else{
			message = this.create();
		}
		
		message.setSendMoment(messageForm.getSendMoment());
		message.setSubject(messageForm.getSubject());
		message.setBody(messageForm.getBody());		
		message.setRecipient(messageForm.getRecipient());
		
		return message;
	}
	
	
	
}


	

	

	

	

	