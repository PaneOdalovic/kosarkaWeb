package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.reports.TimDataSorce;
import app.reports.UtakmicaDataSorce;
import app.repository.IgraJpaRepo;
import app.repository.IgracJPARepo;
import app.repository.UtakmicaJPARepo;
import model.Utakmica;
import net.sf.jasperreports.engine.JRDataSource;

@Controller
@RequestMapping(value="/izvestaji")
public class IzvestajController {
	private JRDataSource jrDatasource;
	
	@Autowired
	IgraJpaRepo iJPAr;
	
	@Autowired
	IgracJPARepo igrac;
	
	@Autowired
	UtakmicaJPARepo uJPAr;
	
	@RequestMapping(value="/izvestajSaUtakmice.pdf", method=RequestMethod.GET)
	public String izvestajSaUtakmice(Model m,Integer id){
		Utakmica u=uJPAr.findOne(id);
		UtakmicaDataSorce uds=new UtakmicaDataSorce(iJPAr,id);
		jrDatasource=uds.create(null);
		m.addAttribute("datasource", jrDatasource);
		m.addAttribute("format", "pdf");
		m.addAttribute("PostignutniPoeni", u.getBrojPostignutihPoena());
		m.addAttribute("PrimljeniPoeni", u.getBrojOrimljenihPoena());
		m.addAttribute("datumOdrzavanja", u.getDatumOdrzavanja());
		m.addAttribute("mesto", u.getMestoOdrzavanje());
		m.addAttribute("protivnik", u.getProtivnickKlub());
		return "rpt_utakmica";
	}
	
	@RequestMapping(value="/izvestajTima.pdf", method=RequestMethod.GET)
	public String izvestajTima(Model m){
		TimDataSorce tds=new TimDataSorce(igrac);
		jrDatasource=tds.create(null);
		m.addAttribute("datasource", jrDatasource);
		m.addAttribute("format", "pdf");
		return "rpt_tim";
	}
		
}
