package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;
import domain.Event;
import domain.Folder;
import domain.Friendship;
import domain.Team;
import domain.Tournament;
import domain.User;
import domain.Vote;
import forms.UserForm;
@Service
@Transactional
public class UserService {
@Autowired
private UserRepository userRepository;

@Autowired
private FriendshipService friendshipService;

	public Collection<User>  findAll()
	{
		
		Collection<User> all;
		
		all = userRepository.findAll();
		
		return all;
	}
public User findOne(Integer userId) 
{
	
	User result;
	
	result = userRepository.findOne(userId);
	
	return result;
}
public User save(User user)
{
	return userRepository.save(user);
}

public User create()
{
	User user= new User();
	Collection<Vote> votes;
	Collection<Friendship> friendships;
	Collection<Event> events;
	Collection<Event> eventsCreated;
	Collection<Team> teams;
	Collection<Team> teamsCreated;
	Collection<Tournament> tournaments;
	Collection<Folder> folders;
	
	
	folders = new ArrayList<Folder>();
	votes = new ArrayList<Vote>();
	friendships = new ArrayList<Friendship>();
	events = new ArrayList<Event>();
	eventsCreated = new ArrayList<Event>();
	teams = new ArrayList<Team>();
	teamsCreated = new ArrayList<Team>();
	tournaments = new ArrayList<Tournament>();
	UserAccount useraccount = new UserAccount();
	Authority authority = new Authority();
	
	authority.setAuthority("USER");
	useraccount.addAuthority(authority);
	user.setUserAccount(useraccount);
	user.setEventsCreated(eventsCreated);
	user.setEvents(events);
	user.setFriendships(friendships);
	user.setTeams(teams);
	user.setTeamsCreated(teamsCreated);
	user.setTournaments(tournaments);
	user.setVotes(votes);
	user.setFolders(folders);
	
return user;
}

public 	User reconstruct(UserForm userForm)
{
	User result=new User();
	result.setName(userForm.getName());
	result.setSurname(userForm.getSurname());
	result.setEmail(userForm.getEmail());
	
	 UserAccount userAccount = new UserAccount();
     userAccount.setUsername(userForm.getUsername());

    String password = userForm.getPassword();

    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    String md5 = encoder.encodePassword(password, null);
    
    userAccount.setPassword(md5);
    Authority authority = new Authority();
    authority.setAuthority("USER");
    Collection<Authority> authorities = new ArrayList<Authority>();
    authorities.add(authority);
    userAccount.setAuthorities(authorities);
    result.setUserAccount(userAccount);
    
    Collection<Folder> folders=new ArrayList<Folder>();
	Folder recibidos=new Folder();
	Folder enviados=new Folder();
	enviados.setActor(result);
	recibidos.setActor(result);
	recibidos.setName("Recibidos");
	enviados.setActor(result);
	enviados.setName("Enviados");
	folders.add(enviados);
	folders.add(recibidos);
	result.setFolders(folders);
	
	result.setPhone(userForm.getPhone());
	
	result.setEvents(new ArrayList<Event>());
	result.setTeamsCreated(new ArrayList<Team>());
	result.setFriendships(new ArrayList<Friendship>());
	result.setTournaments(new ArrayList<Tournament>());
	result.setVotes(new ArrayList<Vote>());
	result.setEventsCreated(new ArrayList<Event>());
	result.setTeams(new ArrayList<Team>());
	
    
    return result;
}

public User reconstructEdit(UserForm userForm) 
{
		User result = findByPrincipal();
		
		result.setPhone(userForm.getPhone());		
		result.setName(userForm.getName());
		result.setSurname(userForm.getSurname());
		result.setEmail(userForm.getEmail());


	    String password = userForm.getPassword();

	    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    String md5 = encoder.encodePassword(password, null);
	    
	    result.getUserAccount().setPassword(md5);


		return result;

	}

public UserForm construct (User user){
	UserForm result;
	result = new UserForm();
	
	result.setUsername(user.getUserAccount().getUsername());
	result.setPassword(user.getUserAccount().getPassword());
	
	result.setName(user.getName());
	result.setSurname(user.getSurname());
	result.setEmail(user.getEmail());
	result.setPhone(user.getPhone());
	
	return result;
}

	//Other business methods ------------------------------------------------
	public User findByPrincipal()
	{
		
		User user;
		UserAccount userAccount;
		int userAccountId;
		
		userAccount = LoginService.getPrincipal();
		userAccountId= userAccount.getId();		
		user = userRepository.findUserByUserAccountId(userAccountId);
		
		return user;
		
	}
	
public boolean userRegistered(String username) 
{
	Boolean res = true;
		if (userRepository.isRegistered(username) == null) {
			res = false;
		}
		return res;
	}
public void delete(User user) 
{
	userRepository.delete(user);
	
}

	public Collection<User> findAllUsersByEventId(int eventId){
		Set<User> all;
		
		all = new HashSet<User>(userRepository.findAllUsersByEventId(eventId));
		
		return all;
	}

	public Collection<User> findByKeyword(String keywords) 
	{
		Assert.notNull(keywords);
		Collection<User> allUsers;
		Collection<User> allByKeyword;

		allUsers = findAll();
		allByKeyword = new HashSet<User>();
		keywords = keywords.toUpperCase();

		for (User p : allUsers) {
			if (p.getName().toUpperCase().contains(keywords)
					|| p.getSurname().toUpperCase().contains(keywords)
					|| p.getEmail().toUpperCase().contains(keywords)) {
				allByKeyword.add(p);
			}
		}

		return allByKeyword;
	}
	
	public Collection<User> findFriendshipFromUser()
	{
		
		Collection<User> result;
		Collection<Friendship> friendships;
		User principal;

		principal = findByPrincipal();
		friendships = friendshipService.findAllfriendships();
		result = new ArrayList<User>();

		for (Friendship f : friendships) {
			if (f.getRequest() == true && f.getUser().equals(principal)) {
				result.add(f.getUserFriend());
			}
			if (f.getRequest() == true && f.getUserFriend().equals(principal)) {
				result.add(f.getUser());
			}
		}
		
		return result;
		
	}
	
}

