package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.EventService;
import services.UserService;
import controllers.AbstractController;
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

	// Constructors -----------------------------------------------------------
	public VoteUserController() {

		super();

	}

	// Edition---------------------------------------------------------------

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public ModelAndView vote(@RequestParam int eventId, @RequestParam int userId) {
		ModelAndView result;

		User user = userService.findOne(userId);
		UserVoteForm userVoteForm = new UserVoteForm();
		userVoteForm.setId(userId);
		userVoteForm.setName(user.getName());
		result = new ModelAndView();
		result.addObject("userVoteForm", userVoteForm);

		result.addObject("eventId", eventId);
		result.addObject("userId", userId);

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
			}
		} else {
			try {

				Vote vote = userService.voteReconstruct(userVoteForm);

				User user = userService.findOne(userVoteForm.getId());

				user.getVotes().add(vote);

				userService.saveVote(user);

				result = new ModelAndView("redirect:../user/list.do");

			} catch (Throwable oops) {

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

}