package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FolderService;
import domain.Folder;

@Controller
@RequestMapping("/folder/actor")
public class FolderActorController {
	// Services-----------------------------------------------
	@Autowired
	private FolderService folderService;

	// Constructors-------------------------------------------
	public FolderActorController() 
	{
		
		super();
		
	}
			
	// Listing-------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() 
	{
		
		ModelAndView result;
		Collection<Folder> folders;

		folders = folderService.findAllFoldersByActorId();

		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);

		return result;
		
	}

}
