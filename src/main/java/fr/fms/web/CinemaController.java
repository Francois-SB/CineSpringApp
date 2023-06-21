package fr.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.fms.business.IBusinessImpl;

@Controller
public class CinemaController {
	@Autowired
	IBusinessImpl businessImpl;
}
