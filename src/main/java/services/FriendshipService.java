package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FriendshipRepository;
import domain.Friendship;
import domain.Friendship;
import domain.RequestTeam;
import domain.Team;
import domain.User;

@Service
@Transactional
public class FriendshipService {
	
	//Managed repository -----------------------------------------------------
	@Autowired
	private FriendshipRepository friendshipRepository;
	
	//Supported services -----------------------------------------------------
	@Autowired
	private UserService userService;
	
	// Constructors-----------------------------------------------------------
	public FriendshipService() 
	{

		super();

	}
	
	// Simple CRUD methods ---------------------------------------------------
	
	public Friendship create(User userFriend) 
	{
		Friendship friendship;
		Date date;
		User principal;

		friendship = new Friendship();
		date = new Date();
		principal = userService.findByPrincipal();
		friendship.setUserFriend(userFriend);
		friendship.setUser(principal);
		friendship.setDate(date);
		friendship.setRequest(false);

		return friendship;
	}

	public Friendship save(Friendship friendship) 
	{
		Friendship result;

		result = friendshipRepository.save(friendship);

		return result;
	}
	
	//Other business methods ------------------------------------------------

	public Collection<Friendship> findAll() 
	{
		return friendshipRepository.findAll();
	}

	public Friendship findOne(Integer valueOf) 
	{
		return friendshipRepository.findOne(valueOf);
	}

	public Collection<Friendship> findAllFriendshipsByUserId() 
	{
		Collection<Friendship> all;
		User user;
		int userId;

		user = userService.findByPrincipal();
		userId = user.getId();
		all = friendshipRepository.findAllFriendshisByUserId(userId);

		return all;
	}
	
	public Collection<Friendship> findAllfriendships()
	{
		
		Collection<Friendship> all;
		Collection<Friendship> result;
		User user;
		
		user = userService.findByPrincipal();
		result = new ArrayList<Friendship>();
		all = findAll();
		
		for(Friendship f: all){
			if(f.getUser().equals(user)||f.getUserFriend().equals(user)){
				result.add(f);
			}
		}
		
		return result;
		
	}
	
	public void sendRequest(int userFriendId)
	{
		User principal;
		User userFriend;
		Friendship request;
		
		userFriend = userService.findOne(userFriendId);
		request =create(userFriend);
		principal = userService.findByPrincipal();
		
		
		
		request = save(request);
		
		userFriend.getFriendships().add(request);
		principal.getFriendships().add(request);
		
		userService.save(userFriend);
		userService.save(principal);
		
	}
	
	public void acceptRequest(User userFriend, Friendship friendship)
	{
		
		User user;
		
		user = friendship.getUser();

		friendship.setRequest(true);
		
		
		friendship = save(friendship);
		
		
		user.getFriendships().add(friendship);
		userFriend.getFriendships().add(friendship);
		
		userService.save(userFriend);
		userService.save(user);
		
	}
	
	public Collection<Friendship> findAllRequestFriendship(int userId)
	{
		
		Collection<Friendship> result;
		result = friendshipRepository.findAllRequestFriendship(userId);
		return result;
		
	}
	
	

}
