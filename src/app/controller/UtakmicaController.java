package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.IgracJPARepo;
import app.repository.UtakmicaJPARepo;
import app.repository.UtakmicaRepo;
import model.Utakmica;

@Controller
@RequestMapping(value="/utakmice")
public class UtakmicaController {
	
	@Autowired
	UtakmicaJPARepo ujpar;
	
	@Autowired
	UtakmicaRepo urep;
	
	@Autowired
	IgracJPARepo iJPAr;
	

	
	@RequestMapping(value="ListaUtakmicaTrener", method=RequestMethod.GET)
	public String ListaUtakmimcaTrener(Model m,HttpServletRequest r){
		List<Utakmica> utakmice=ujpar.vratiNeodigraneUtakmice();
		m.addAttribute("utakmice", utakmice);
		return "PregledUtakmicaTrener";
	}
	
	@RequestMapping(value="ListaOdigranihUtakmicaTrener", method=RequestMethod.GET)
	public String ListaOdigranihUtakmimcaTrener(Model m,HttpServletRequest r){
		List<Utakmica> utakmice=ujpar.vratiOdigraneUtakmice();
		m.addAttribute("utakmice", utakmice);
		return "PregledOdigranihUtakmica";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	
	@RequestMapping(value="utakmica", method=RequestMethod.GET)
	public String utakmica(Model m, @ModelAttribute("utakmica") Utakmica utakmica,HttpServletRequest r){
		m.addAttribute("utakmica", new Utakmica());
		return "UnosUtakmice";
	}
	
	
}
