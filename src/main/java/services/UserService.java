package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import utilities.HashPassword;
import domain.Event;
import domain.Folder;
import domain.Friendship;
import domain.RequestTeam;
import domain.Team;
import domain.Tournament;
import domain.User;
import domain.Vote;
import forms.UserForm;
import forms.UserVoteForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository userRepository;

	// Supporting services ---------------------------------------------------
	@Autowired
	private FriendshipService friendshipService;

	@Autowired
	private FolderService folderService;
	
	
	// Constructor ------------------------------------------------------------
	public UserService() 
	{

		super();

	}

	// Simple CRUD methods ----------------------------------------------------

	public User findOne(int userId) 
	{

		User result;

		result = userRepository.findOne(userId);

		return result;
		
	}

	public Collection<User> findAll() 
	{

		Collection<User> all;

		all = userRepository.findAll();

		return all;
		
	}

	public User create() 
	{
		
		User user;
		UserAccount useraccount;
		Authority authority;

		Collection<Vote> votes;
		Collection<Friendship> friendships;
		Collection<Event> events;
		Collection<Event> eventsCreated;
		Collection<Team> teams;
		Collection<Team> teamsCreated;
		Collection<Tournament> tournaments;
		Collection<Folder> folders;
		Collection<RequestTeam> requests;

		user = new User();
		useraccount = new UserAccount();
		authority = new Authority();

		folders = new ArrayList<Folder>();
		votes = new ArrayList<Vote>();
		friendships = new ArrayList<Friendship>();
		events = new ArrayList<Event>();
		eventsCreated = new ArrayList<Event>();
		teams = new ArrayList<Team>();
		teamsCreated = new ArrayList<Team>();
		tournaments = new ArrayList<Tournament>();
		requests = new ArrayList<RequestTeam>();

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
		user.setRequests(requests);

		return user;
		
	}

	public User save(User user) 
	{

		User result;
		String passwordCoded;
		String password;
		UserAccount userUserAccount;
		Folder inbox;
		Folder outbox;
		Collection<Folder> folders;
		
		if(user.getId() == 0){

			folders = new ArrayList<Folder>();
	
			
			password = user.getUserAccount().getPassword();
			passwordCoded = HashPassword.encode(password);
	
			userUserAccount = user.getUserAccount();
			userUserAccount.setPassword(passwordCoded);
	
			user.setUserAccount(userUserAccount);
	
			inbox = folderService.create(user, "Inbox");
			outbox = folderService.create(user, "Outbox");
	
			folders.add(inbox);
			folders.add(outbox);
	
			user.setFolders(folders);
			
			byte[] imagen = user.getImagen();
					
			if (user.getId() != 0 && (imagen.equals(null) || user.getImagen().length==0))
				user.setImagen(findOne(user.getId()).getImagen());
	
			result = userRepository.save(user);
	
			inbox.setActor(result);
			outbox.setActor(result);
	
			folderService.save(inbox);
			folderService.save(outbox);
		
		}else{
			
			result = userRepository.save(user);
		}
		

		return result;
		
	}
	
	// Other business methods ------------------------------------------------

	public UserForm construct() 
	{
		
		UserForm userForm;
		User user;

		userForm = new UserForm();
		user = create();

		userForm.setUsername(user.getUserAccount().getUsername());
		userForm.setPassword(user.getUserAccount().getPassword());

		userForm.setName(user.getName());
		userForm.setSurname(user.getSurname());
		userForm.setEmail(user.getEmail());
		userForm.setPhone(user.getPhone());
		userForm.setImagen(user.getImagen());

		return userForm;
		
	}
	
	public UserForm construct(User user) 
	{
		
		UserForm userForm;		

		userForm = new UserForm();

		userForm.setUsername(user.getUserAccount().getUsername());
		userForm.setPassword(user.getUserAccount().getPassword());

		userForm.setId(user.getId());
		userForm.setName(user.getName());
		userForm.setSurname(user.getSurname());
		userForm.setEmail(user.getEmail());
		userForm.setPhone(user.getPhone());		
		userForm.setImagen(user.getImagen());		

		return userForm;
		
	}

	public User reconstruct(UserForm userForm) 
	{
		
		User user;
		String password;
		
		if(userForm.getId()!= 0){
			user = findByPrincipal();
			user.getUserAccount().setUsername(userForm.getUsername());
			password = HashPassword.encode(userForm.getPassword());
			userForm.setPassword2(password);
			user.getUserAccount().setPassword(password);
			userForm.setTerms(true);
			
		}else{
			
			user = create();
			user.getUserAccount().setUsername(userForm.getUsername());
			user.getUserAccount().setPassword(userForm.getPassword());			
		}

		user.setName(userForm.getName());
		user.setSurname(userForm.getSurname());
		user.setEmail(userForm.getEmail());
		user.setPhone(userForm.getPhone());

		user.setImagen(userForm.getImagen());	
		
		if (user.getId() != 0 && !(userForm.getImagen().equals(null) || userForm.getImagen().length==0))
			user.setImagen(userForm.getImagen());
		
		Assert.isTrue(userForm.getTerms());
		Assert.isTrue(user.getUserAccount().getPassword().equals(userForm.getPassword2()));

		return user;
		
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

	public User findByPrincipal() 
	{

		User user;
		UserAccount userAccount;
		int userAccountId;

		userAccount = LoginService.getPrincipal();
		userAccountId = userAccount.getId();
		user = userRepository.findUserByUserAccountId(userAccountId);

		return user;

	}
	
	public void checkPrincipal(User user)
	{
		
		Assert.notNull(user);
		
		User aux;
		
		aux = findByPrincipal();
		
		Assert.isTrue(aux.equals(user));
		
	}

	public boolean userRegistered(String username)
	{
		
		Boolean res = true;
		User user;
		
		user = userRepository.isRegistered(username);
		
		if (user == null) {
			res = false;
		}
		
		return res;
	}

	public void delete(User user) 
	{
		
		Assert.notNull(user);
		checkPrincipal(user);
		
		userRepository.delete(user);

	}

	public Collection<User> findAllUsersByEventId(int eventId)
	{
		
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

	public Vote voteReconstruct(UserVoteForm userVoteForm) 
	{
		
		Vote vote;
		
		vote = new Vote();
		
		vote.setNameUser(userVoteForm.getName());
		vote.setScore(userVoteForm.getScore());
		
		return vote;
		
	}

	public void saveVote(User user) 
	{
		
		Double aux = 0.0;
		Double rating = 0.0;
		
		for (Vote a : user.getVotes()) {
			rating += a.getScore();
			aux++;
		}
		
//		user.setRating(rating / aux);
		userRepository.save(user);

	}
	
	public User findOneUserToEdit(int userId)
	{
		
		User user;
		
		user = findOne(userId);
		
		checkPrincipal(user);
		
		return user;
		
	}
	
	public Collection<Tournament> getAllTournamentsActive() {
		Collection<Tournament> all = userRepository.findAllTournaments(this
				.findByPrincipal().getId());
		Collection<Tournament> res = new HashSet<Tournament>();
		Date now = new Date(System.currentTimeMillis());
		for (Tournament a : all) {
			if (a.getStartMoment().after(now)) {
				res.add(a);
			}
		}
		return res;
	}
}
