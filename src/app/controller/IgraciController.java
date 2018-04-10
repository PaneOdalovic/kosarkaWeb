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

import app.repository.IgraJpaRepo;
import app.repository.IgracJPARepo;
import app.repository.IgracRepo;
import app.repository.UtakmicaJPARepo;
import model.Igra;
import model.Igrac;
import model.Utakmica;

@Controller
@RequestMapping(value="/igraci")
public class IgraciController {
	
	@Autowired
	IgracRepo ir;
	
	@Autowired
	IgracJPARepo iJPAr;
	
	@Autowired
	UtakmicaJPARepo ujpaR;
	
	@Autowired
	IgraJpaRepo igraRepo;
	
	@RequestMapping(value="saveIgraca", method=RequestMethod.POST)
	public String saveIgraca(Model m, @ModelAttribute("igrac") Igrac igrac,HttpServletRequest r){
		ir.saveClan(igrac);
		return ListaIgraca(m,r);
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="UnosIgracaa", method=RequestMethod.GET)
	public String UnosIgraca(Model m, @ModelAttribute("igrac") Igrac igrac,HttpServletRequest r){
		m.addAttribute("igrac", new Igrac());
		return "UnosIgraca";
	}
	
	@RequestMapping(value="ListaIgraca", method=RequestMethod.GET)
	public String ListaIgraca(Model m,HttpServletRequest r){
		List<Igrac> igraci=iJPAr.vratiAktivneIgrace();
		m.addAttribute("igraci", igraci);
		return "PregledTimaMenadzer";
	}
	
	@RequestMapping(value="odpusti", method=RequestMethod.GET)
	public String odpusti(Model m, Integer id,HttpServletRequest r){
		Igrac i=iJPAr.findOne(id);
		i.setDatumOtpustanja(new Date());
		iJPAr.save(i);
		return ListaIgraca(m,r);
	}
	
	@RequestMapping(value="ListaIgracaZaDodavanje", method=RequestMethod.GET)
	public String ListaIgracaZaDodavanje(Model m, HttpServletRequest r,Integer id){
		Utakmica u=ujpaR.findOne(id);
		r.getSession().setAttribute("u", u);
		List<Igrac> igraci=iJPAr.vratiIgraceKojiNisuNaUtakmicu(id);
		List<Igrac> igraciNaUtakmic=iJPAr.vratiIgraceZaUtakmicu(id);
		m.addAttribute("igraciNaUtakmici", igraciNaUtakmic);
		m.addAttribute("igraci", igraci);
		return "DodavanjeIgracaNaUtakmicuTrener";
	}
	
	@RequestMapping(value="dodajIgracaNaUtakmicu", method=RequestMethod.POST)
	public String dodajIgracaNaUtakmicu(Model m, Integer id,Boolean petorka,HttpServletRequest r){
		Utakmica utakmica=(Utakmica) r.getSession().getAttribute("u");
		int brojIgracaPrvePetorka=utakmica.getBrojIgracaPrvaPetorka();
		int brojIgraca=utakmica.getBrojUnetihIgraca();
		if(brojIgraca<12){
			Igrac igrac=iJPAr.findOne(id);
			Igra i=new Igra();
			i.setIgrac(igrac);
			i.setUtakmica(utakmica);
				if(petorka!=null && brojIgracaPrvePetorka<5){
					i.setPrvaPetorka((byte)1);
					utakmica.setBrojIgracaPrvaPetorka(brojIgracaPrvePetorka+1);
				}else{
					i.setPrvaPetorka((byte)0);
				}
				igraRepo.save(i);
			utakmica.setBrojUnetihIgraca(brojIgraca+1);
			ujpaR.save(utakmica);
		}
		return ListaIgracaZaDodavanje(m,r,utakmica.getIdutakmica());
	}
}
