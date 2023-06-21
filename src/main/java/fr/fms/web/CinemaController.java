package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Address;

import fr.fms.entities.Cinema;

@Controller
public class CinemaController {
	@Autowired
	IBusinessImpl ibusinessImpl;
	private final Logger logger = LoggerFactory.getLogger(CinemaController.class);
//	@GetMapping("/delete")
//	public String delete(Long id, int page, String keyword, Long catId) {
//		articleRepository.deleteById(id);
//		return "redirect:/index?page="+page+"&keyword="+keyword+"&catId"+catId;
//	}
	
	@GetMapping("/ccinema")
	public String ccinema(Model model,
			@RequestParam(name="id" , defaultValue = "0") Long id) {
		try {
			if(id==0)
		model.addAttribute("cinema", new Cinema());
			
		List<Address> listAddresses = ibusinessImpl.getAddresses();
		model.addAttribute("listAddresses", listAddresses);
	}catch (Exception e) {
		model.addAttribute("error",e.getMessage());
		logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
	}
		return "ccinema";
	}
	//TODO
	@GetMapping("/ucinema")
	public String ucinema(Model model, Long id) {
		try {
		Cinema updtCinema = ibusinessImpl.getCinema(id);
		model.addAttribute("cinema", updtCinema);
		List<Address> listAddresses = ibusinessImpl.getAddresses();
		model.addAttribute("listAddresses", listAddresses);
		}catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
		}
	
		return "ccinema";
	}
	@PostMapping("/save")		
	public String save(@Valid Cinema cinema, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs, long addressId) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("addresses",ibusinessImpl.getAddresses());
				return "ccinema";
			}	
			Address address = ibusinessImpl.getAddress(addressId);
			cinema.setAddress(address);
			cinema.setCity(address.getCity());
			ibusinessImpl.saveCinema(cinema);		
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[CINEMA CONTROLLER : SAVE CINEMA] : {} " , e.getMessage());
		}
		return "redirect:/indexVilles";
	}
}
