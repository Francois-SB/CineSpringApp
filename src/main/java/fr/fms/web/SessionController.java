package fr.fms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Session;

@Controller
public class SessionController {
	@Autowired
	IBusinessImpl businessImpl;
	private final Logger logger = LoggerFactory.getLogger(SessionController.class);
	@GetMapping("/sessions")	
	public String sessions(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
			 @RequestParam(name="idFilm" , defaultValue = "0") long idFilm,
			 @ModelAttribute(name="error") String error) {	
Page<Session> sessions = null;
model.addAttribute("error", model.getAttribute("error"));
try {
	
	sessions = businessImpl.getSessionsByFilmId(idFilm,page); 
	model.addAttribute("nameFilm",businessImpl.getFilm(idFilm).getTitle());
model.addAttribute("idFilm",idFilm);
model.addAttribute("listSessions",sessions.getContent());	
model.addAttribute("pages", new int[sessions.getTotalPages()]);
model.addAttribute("currentPage",page);


//model.addAttribute("nbcart", businessImpl.getNbCart());

//String username;
//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//if (principal instanceof UserDetails) {
//username = ((UserDetails)principal).getUsername();
//} else {
//username = principal.toString();
//if(username.contains("anonymous"))
//username = "";
//}
//model.addAttribute("username", " " +username);
}
catch(Exception e) {
model.addAttribute("error",e.getMessage());
logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
}		
return "sessions";
	}
	@GetMapping("/sessionsRes")	
	public String sessionsRes() {
		return "sessionsRes";
	}
}
