
package services;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
@Service
@Transactional
public class ActorService {
	// Managed repository --------------------------------------------------
	@Autowired
	private ActorRepository actorRepository;
		
	// Supporting services -------------------------------------------------
		
	// Constructors---------------------------------------------------------
	public ActorService()
	{
			
		super();
			
	}
	public Collection<Actor>  findAll()
	{
		
		Collection<Actor> all;
		
		all = actorRepository.findAll();
		
		return all;
		
	}
public Actor findOne(Integer valueOf) {
return actorRepository.findOne(valueOf);
}
public Actor save(Actor actor){
return actorRepository.save(actor);
}
	public Actor findByPrincipal()
	{
	
		Actor actor;
		UserAccount userAccount;
		int userAccountId;
	
		userAccount = LoginService.getPrincipal();
		userAccountId = userAccount.getId();
		actor = actorRepository.findActorByUserAccountId(userAccountId);
	
		return actor;
	
	}
	
//	public byte[] findImagenOfActorId()
//	{
//		
//		byte[] arrayImagen;
//		Actor actor;
//		int actorId;
//		
//		int tam;
//		
//		actor = findByPrincipal();
//		actorId = actor.getId();		
//		arrayImagen = actorRepository.findImagenOfActorId(actorId);
//		
//		tam = arrayImagen.length;
//		
//		System.out.println(tam);
//		
//		return arrayImagen;
//		
//	}
	

}