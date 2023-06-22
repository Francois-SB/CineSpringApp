package fr.fms.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Cinema;
import fr.fms.entities.Film;
import fr.fms.entities.Session;

@Controller
public class SessionController {
	@Autowired
	IBusinessImpl ibusinessImpl;
	private final Logger logger = LoggerFactory.getLogger(SessionController.class);
	@GetMapping("/sessions")	
	public String sessions(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
			 @RequestParam(name="idFilm" , defaultValue = "0") long idFilm,
			 @ModelAttribute(name="error") String error) {	
Page<Session> sessions = null;
model.addAttribute("error", model.getAttribute("error"));
try {
	
	sessions = ibusinessImpl.getSessionsByFilmId(idFilm,page); 
	model.addAttribute("nameFilm",ibusinessImpl.getFilm(idFilm).getTitle());
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
	
	/////////////////////////////////////////////CRUD////////////////////////////////////////////////
	@GetMapping("/dsession")
	public String dsession(Model model ,Long id, int page,  Long idFilm) {
		ibusinessImpl.deleteSessionById(id);
		return "redirect:/sessions?page="+page+"&idFilm="+idFilm;
	}
	
	@GetMapping("/csession")
	public String csession(Model model,
			@RequestParam(name="id" , defaultValue = "0") Long id) {
		try {

		model.addAttribute("session", new Session());
			
model.addAttribute("cSessionFilms", ibusinessImpl.getAllFilms());

	}catch (Exception e) {
		model.addAttribute("error",e.getMessage());
		logger.error("[session CONTROLLER : INDEX] : {} " , e.getMessage());
	}
		return "csession";
	}
	//TODO
	@GetMapping("/usession")
	public String usession(Model model, Long id, int page, Long idFilm) {
		try {
		Session updtsession = ibusinessImpl.getSession(id);
		model.addAttribute("session", updtsession);
		model.addAttribute("cSessionFilms", ibusinessImpl.getAllFilms());
		}catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[session CONTROLLER : INDEX] : {} " , e.getMessage());
		}
	
		return "csession";
	}
	//TODO gérer l'aval pour avoir le contxte d'arrivée et le transmettre ufilm -> cfilm -> sfilm (contexte), cfilm -> sfilm(peut avoir contexte)
	@PostMapping("/ssession")		
	public String savesession(@Valid Session session, BindingResult bindingResult, Model model,int page ,RedirectAttributes redirectAttrs, 
			@RequestParam(name="idFilm" , defaultValue = "0") Long idFilm) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("listFilms",ibusinessImpl.getAllFilms());
				return "csession";
			}	
			Film film = ibusinessImpl.getFilm(idFilm);
			session.setFilm(film);

			ibusinessImpl.saveSession(session);		
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[CINEMA CONTROLLER : SAVE CINEMA] : {} " , e.getMessage());
		}
		return "redirect:/sessions?page="+page+"&idFilm="+idFilm;
	}
	
}
