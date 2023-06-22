package fr.fms.web;

import java.util.List;

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
import fr.fms.entities.Address;
import fr.fms.entities.Cinema;
import fr.fms.entities.Film;

@Controller
public class FilmController {
	@Autowired
	IBusinessImpl ibusinessImpl;
	
	private final Logger logger = LoggerFactory.getLogger(FilmController.class);
	@GetMapping("/films")	
	public String films(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
			 @RequestParam(name="idCinema" , defaultValue = "0") Long idCinema,
			 @ModelAttribute(name="error") String error) {	
Page<Film> films = null;
model.addAttribute("error", model.getAttribute("error"));
try {
	if(idCinema!=0)
	films = ibusinessImpl.getFilmsByCineId(idCinema,page); 
	else films=ibusinessImpl.getAllFilms(page);
	
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
logger.error("[FILM CONTROLLER : INDEX] : {} " , e.getMessage());
}		
return "films";
	}
	
	/////////////////////////////////////////////CRUD////////////////////////////////////////////////
	@GetMapping("/dfilm")//
	public String dfilm(Model model ,Long id, int page,  Long idCinema) {
		ibusinessImpl.deleteFilmById(id);
		return "redirect:/films?page="+page+"&idCity"+idCinema;
	}
	
	@GetMapping("/cfilm")
	public String cfilm(Model model,
			@RequestParam(name="id" , defaultValue = "0") Long id,
			RedirectAttributes redirectAttrs) {
		try {
//			if(id==0)
		model.addAttribute("film", new Film());
			
model.addAttribute("cFilmCinemas", ibusinessImpl.getCinemas());

	}catch (Exception e) {
		model.addAttribute("error",e.getMessage());
		logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
	}
		return "cfilm";
	}
	//TODO
	@GetMapping("/ufilm")
	public String ufilm(Model model, Long id) {
		try {
		Film updtFilm = ibusinessImpl.getFilm(id);
		model.addAttribute("film", updtFilm);
		model.addAttribute("cFilmCinemas", ibusinessImpl.getCinemas());
		}catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
		}
	
		return "cfilm";
	}
	//TODO gérer l'aval pour avoir le contxte d'arrivée et le transmettre ufilm -> cfilm -> sfilm (contexte), cfilm -> sfilm(peut avoir contexte)
	@PostMapping("/sfilm")		
	public String save(@Valid Film film, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs, 
			@RequestParam(name="idCinema" , defaultValue = "0") Long idCinema) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("listCinemas",ibusinessImpl.getCinemas());
				return "cfilm";
			}	
			Cinema cinema = ibusinessImpl.getCinema(idCinema);
			film.setCinema(cinema);

			ibusinessImpl.saveFilm(film);		
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[CINEMA CONTROLLER : SAVE CINEMA] : {} " , e.getMessage());
		}
		return "redirect:/films?idCinema="+idCinema;
	}
	
	
}
