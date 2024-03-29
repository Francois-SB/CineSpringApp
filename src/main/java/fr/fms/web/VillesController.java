package fr.fms.web;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.collections.List;
import fr.fms.business.IBusinessImpl;
import fr.fms.dao.FilmRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.Film;
import fr.fms.entities.Session;

@Controller
public class VillesController {
	@Autowired
	IBusinessImpl businessImpl;
	@Autowired
	FilmRepository filmRepository;
	private final Logger logger = LoggerFactory.getLogger(VillesController.class);

	@GetMapping("/indexVilles")
	public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
									 @RequestParam(name="keyword" , defaultValue = "") String kw,
									 @RequestParam(name="idCity" , defaultValue = "0") Long idCity,
									 @RequestParam(name="nbcart" , defaultValue = "0") int cart,
									 RedirectAttributes redirectAttributes,
									 @ModelAttribute(name="error") String error) {	
		Page<Cinema> cinemas = null;
		model.addAttribute("error", model.getAttribute("error"));
		try {

			if(idCity > 0)	{	
				cinemas = businessImpl.getCinemasByCityPage(idCity,page); 
//				if(cinemas.isEmpty()) {
//					model.addAttribute("error", ManageErrors.STR_ERROR);
//			}
			}else cinemas = businessImpl.getCinemasPage(kw,page); 		
			model.addAttribute("idCity",idCity);
			model.addAttribute("listCinema",cinemas.getContent());
//			redirectAttributes.addFlashAttribute("listCinemas", cinemas.getContent());
//			System.out.println("cinemas list from index    "+ cinemas.getContent().get(3));
			model.addAttribute("pages", new int[cinemas.getTotalPages()]);
			model.addAttribute("currentPage",page);
			model.addAttribute("keyword",kw);
			model.addAttribute("cities",businessImpl.getCities());
//			model.addAttribute("nbcart", businessImpl.getNbCart());
			
//			String username;
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if (principal instanceof UserDetails) {
//				username = ((UserDetails)principal).getUsername();
//			} else {
//				username = principal.toString();
//				if(username.contains("anonymous"))
//					username = "";
//			}
//			model.addAttribute("username", " " +username);
		}
		catch(Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
		}		
		return "indexVilles";
	}

	
	
	
	
	
	
}
