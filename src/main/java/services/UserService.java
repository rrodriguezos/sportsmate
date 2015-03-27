package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
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

public Collection<User>  findAll()
{
	return userRepository.findAll();
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
	Collection<Friendship> shipsUser;
	Collection<Friendship> shipsUserFriend;
	Collection<Event> events;
	Collection<Event> eventsCreated;
	Collection<Team> teams;
	Collection<Team> teamsCreated;
	Collection<Tournament> tournaments;
	
	

	votes = new ArrayList<Vote>();
	shipsUser = new ArrayList<Friendship>();
	shipsUserFriend = new ArrayList<Friendship>();
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
	user.setShipsUser(shipsUser);
	user.setShipsUserFriend(shipsUserFriend);
	user.setTeams(teams);
	user.setTeamsCreated(teamsCreated);
	user.setTournaments(tournaments);
	user.setVotes(votes);
	
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
	result.setShipsUserFriend(new ArrayList<Friendship>());
	result.setShipsUser(new ArrayList<Friendship>());
	result.setTournaments(new ArrayList<Tournament>());
	result.setVotes(new ArrayList<Vote>());
	result.setEventsCreated(new ArrayList<Event>());
	result.setTeams(new ArrayList<Team>());
	
    
    return result;
}

public UserForm construct (User user){
	UserForm userForm = new UserForm();
	
	userForm.setUsername(user.getUserAccount().getUsername());
	userForm.setPassword(user.getUserAccount().getPassword());
	
	userForm.setName(user.getName());
	userForm.setSurname(user.getSurname());
	userForm.setEmail(user.getEmail());
	userForm.setPhone(user.getPhone());
	
	return userForm;
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
	userRepository.delete(user.getId());
	
}

}

