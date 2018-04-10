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
import app.repository.KorisnikJPARepo;
import app.repository.UtakmicaJPARepo;
import app.repository.UtakmicaRepo;
import model.Igra;
import model.Korisnik;
import model.Utakmica;

@Controller
@RequestMapping(value="/igra")
public class IgraController {
	@Autowired
	IgraJpaRepo igra;
	
	@Autowired
	UtakmicaJPARepo ujpaR;
	
	@Autowired
	IgracJPARepo ijpaR;
	
	@Autowired
	UtakmicaJPARepo utak;
	
	@Autowired
	UtakmicaRepo urep;
	
	@Autowired
	KorisnikJPARepo kJAPReop;
	
	@RequestMapping(value="prikazIgraca", method=RequestMethod.GET)
	public String UnosIgraca(Model m,Integer id,HttpServletRequest r){
		Utakmica u=ujpaR.findOne(id);
		Date danasljiDatuma=new Date();
		Date datumUtakmice=u.getDatumOdrzavanja();
		if(datumUtakmice.after(danasljiDatuma)){
			String poruka=new String("utakmica tek treba da se igra"+datumUtakmice);
			m.addAttribute("poruka",poruka);
			return ListaUtakmimcaMenadzer(m,r);
		}
		r.getSession().setAttribute("u", u);
		List<Igra> igraci=igra.vratiZaUtakmicu(id);
		m.addAttribute("igraci", igraci);
		return "UnosRezultataMenadzer";
	}
	
	@RequestMapping(value="ListaUtakmicaMenadzer", method=RequestMethod.GET)
	public String ListaUtakmimcaMenadzer(Model m,HttpServletRequest r){
		List<Utakmica> utakmice=utak.vratiNeodigraneUtakmice();
		m.addAttribute("utakmice", utakmice);
		return "PrelgedUtakmicaMenadzer";
	}
	@RequestMapping(value="provera", method=RequestMethod.POST)
	public String provera(Model m,HttpServletRequest r,String username,String password){
		Korisnik k=kJAPReop.findOne(username);
		if(k!=null){
			if(k.getPassword().equals(password)){
				r.getSession().setAttribute("korisnik", k);
				return "Pocetna";
			}
		}
		m.addAttribute("poruka", "niste uneli dobro ime i sifru!");
		return "index";
	}
	
	
	@RequestMapping(value="odjava", method=RequestMethod.GET)
	public String odjava(Model m,HttpServletRequest r){
		r.getSession().removeAttribute("korisnik");
		return "index";
	}
	
	@RequestMapping(value="sacuvajUtakmicu", method=RequestMethod.POST)
	public String saveUtakmice(Model m, @ModelAttribute("utakmica") Utakmica utakmica,HttpServletRequest r){
		utakmica.setBroj_Igraca(0);
		urep.saveUtakmice(utakmica);
		return ListaUtakmimcaMenadzer(m,r);
	}
	
	@RequestMapping(value="dodajRezultat", method=RequestMethod.POST)
	public String dodajRezultat(Model m,Integer id,String brojPoena,HttpServletRequest r,String prethodni){
		Utakmica utakmica=(Utakmica) r.getSession().getAttribute("u");
		int UkupiniPoeniNaUtakmici=utakmica.getBrojPostignutihPoena();
		int predhodniPoeniIgrraca=Integer.parseInt(prethodni);
		
		if(brojPoena!="" && brojPoena!=null && predhodniPoeniIgrraca==0){
			int pom=0;
			try{
				pom=Integer.parseInt(brojPoena);
			}catch(Exception e){
				m.addAttribute("pogresan", "unesite broj!");
				m.addAttribute("brp", UkupiniPoeniNaUtakmici);
				return UnosIgraca(m,utakmica.getIdutakmica(),r);
			}
			
			Igra igr=igra.pronadjiIgru(id,(Integer)utakmica.getIdutakmica());
			if(utakmica.getBroj_Igraca()<12){
				utakmica.setBrojPostignutihPoena(UkupiniPoeniNaUtakmici);
				utakmica.setBroj_Igraca(utakmica.getBroj_Igraca()+1);
				UkupiniPoeniNaUtakmici+=pom;
				utakmica.setBrojPostignutihPoena(UkupiniPoeniNaUtakmici);
				ujpaR.save(utakmica);
				r.getSession().setAttribute("u", utakmica);
				igr.setBrojPoena(pom);
				igra.save(igr);
				
			}
		}
		m.addAttribute("brp", UkupiniPoeniNaUtakmici);
		return UnosIgraca(m,utakmica.getIdutakmica(),r);
	}
	
	
	@RequestMapping(value="sacuvajUtakmicrez", method=RequestMethod.POST)
	public String sacuvajUtakmicrez(Model m,HttpServletRequest r){
		Utakmica utakmica=(Utakmica) r.getSession().getAttribute("u");
		utakmica.setOdigrana((byte)1);
		ujpaR.save(utakmica);
		m.addAttribute("poruka", "unos rezutata na utakmicu je uspesno sacuvan!");
		return UnosIgraca(m,utakmica.getIdutakmica(),r);
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="dodajPoeneProtivnika", method=RequestMethod.POST)
	public String dodajPoeneProtivnika(Model m,String brojPoena,HttpServletRequest r){
		Utakmica utakmica=(Utakmica) r.getSession().getAttribute("u");
		m.addAttribute("brp", utakmica.getBrojPostignutihPoena());
		int pom=Integer.parseInt(brojPoena);
		utakmica.setBrojOrimljenihPoena(pom);
		m.addAttribute("protivnik", pom);
		utak.save(utakmica);
		return UnosIgraca(m,utakmica.getIdutakmica(),r);
	}
}
