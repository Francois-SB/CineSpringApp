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
import fr.fms.entities.Film;

@Controller
public class FilmController {
	@Autowired
	IBusinessImpl businessImpl;
	
	private final Logger logger = LoggerFactory.getLogger(FilmController.class);
	@GetMapping("/films")	
	public String films(Model model, @RequestParam(name="page" , defaultValue = "0") int page,

			 @RequestParam(name="idCinema" , defaultValue = "0") Long idCinema,
			 @RequestParam(name="nbcart" , defaultValue = "0") int cart,
			 @ModelAttribute(name="error") String error) {	
Page<Film> films = null;
model.addAttribute("error", model.getAttribute("error"));
try {
	
	films = businessImpl.getFilmsByCineId(idCinema,page); 

model.addAttribute("idCinema",idCinema);
model.addAttribute("listfilms",films.getContent());	
model.addAttribute("pages", new int[films.getTotalPages()]);
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
return "films";
	}
}
