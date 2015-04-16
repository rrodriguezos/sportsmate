package controllers.user;

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
import services.CustomerService;
import services.EventService;
import services.UserService;
import controllers.AbstractController;
import domain.Customer;
import domain.User;
import domain.Vote;
import forms.UserVoteForm;

@Controller
@RequestMapping("/event/user")
public class VoteUserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------
	public VoteUserController() {

		super();

	}

	// Edition---------------------------------------------------------------

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public ModelAndView vote(@RequestParam int eventId, @RequestParam int userId) {
		ModelAndView result;

		UserVoteForm userVoteForm = new UserVoteForm();
		userVoteForm.setId(userId);
		userVoteForm.setName(userService.findByPrincipal().getName());
		result = new ModelAndView();
		result.addObject("userVoteForm", userVoteForm);
		Collection<User> users = userService.findAllUsersByEventId(eventId);
		Boolean yaVotado = false;
		for (User a : users) {
			for (Vote b : a.getVotes()) {
				if (b.getNameUser().equals(
						userService.findByPrincipal().getName()))
					yaVotado = true;
			}
		}
		result.addObject("eventId", eventId);
		result.addObject("userId", userId);
		result.addObject("yaVotado", yaVotado);
		result.addObject("voteCustomer", false);

		return result;
	}

	// Save-----------------------------------------------------------------

	@RequestMapping(value = "/vote", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserVoteForm userVoteForm,
			BindingResult binding) {

		ModelAndView result = null;

		if (binding.hasErrors() || userVoteForm.getScore() == null
				|| userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
			result = new ModelAndView();
			result.addObject(userVoteForm);
			if (userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
				result = new ModelAndView();
				result.addObject("message", "eventVote.commit.error");
				result.addObject("yaVotado", false);
				result.addObject("voteCustomer", false);
			}
		} else {
			try {

				Vote vote = userService.voteReconstruct(userVoteForm);

				User user = userService.findOne(userVoteForm.getId());

				user.getVotes().add(vote);

				userService.saveVote(user);

				result = new ModelAndView("redirect:../user/list.do");

			} catch (Throwable oops) {

				result.addObject("yaVotado", false);
				result.addObject("voteCustomer", false);
				if (userVoteForm.getScore() == null) {
					result = new ModelAndView();
					result.addObject("message", "event.commit.error");
				}
				if (userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
					result = new ModelAndView();
					result.addObject("message", "eventVote.commit.error");
				}
			}
		}

		return result;

	}

	@RequestMapping(value = "/voteCustomer", method = RequestMethod.GET)
	public ModelAndView voteCustomer(@RequestParam String placeString) {
		ModelAndView result;
		Boolean yaVotado = false;
		Customer customer = customerService.findOneFromPlaceString(placeString);
		UserVoteForm userVoteForm = new UserVoteForm();
		userVoteForm.setId(customer.getId());
		userVoteForm.setName(placeString);
		result = new ModelAndView();
		result.addObject("userVoteForm", userVoteForm);

		for (Vote b : customer.getVotes()) {
			if (b.getNameUser().equals(userService.findByPrincipal().getName())) {
				yaVotado = true;
			}
		}

		result.addObject("yaVotado", yaVotado);
		result.addObject("voteCustomer", true);
		return result;
	}

	// Save-----------------------------------------------------------------

	@RequestMapping(value = "/voteCustomer", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCustomer(@Valid UserVoteForm userVoteForm,
			BindingResult binding) {

		ModelAndView result = null;

		if (binding.hasErrors() || userVoteForm.getScore() == null
				|| userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
			result = new ModelAndView();
			result.addObject(userVoteForm);
			if (userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
				result = new ModelAndView();
				result.addObject("yaVotado", false);
				result.addObject("voteCustomer", true);
				result.addObject("message", "eventVote.commit.error");
			}
		} else {
			try {

				Vote vote = customerService.voteReconstruct(userVoteForm);

				Customer customer = customerService.findOne(userVoteForm
						.getId());

				customer.getVotes().add(vote);

				customerService.saveVote(customer);

				result = new ModelAndView("redirect:../user/list.do");

			} catch (Throwable oops) {
				System.out.println(oops.getLocalizedMessage());

				if (userVoteForm.getScore() == null) {
					result = new ModelAndView();
					result.addObject("message", "event.commit.error");
					result.addObject("yaVotado", false);
					result.addObject("voteCustomer", true);
				}
				if (userVoteForm.getScore() < 0 || userVoteForm.getScore() > 5) {
					result = new ModelAndView();
					result.addObject("message", "eventVote.commit.error");
					result.addObject("yaVotado", false);
					result.addObject("voteCustomer", true);
				}
			}
		}

		return result;

	}

}