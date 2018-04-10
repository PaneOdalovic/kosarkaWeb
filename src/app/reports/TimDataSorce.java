package app.reports;

import java.util.List;

import app.repository.IgracJPARepo;
import model.Igrac;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TimDataSorce extends JRAbstractBeanDataSourceProvider {

	private List<Igrac> igraci;
	IgracJPARepo iJPAr;
	
	public TimDataSorce(IgracJPARepo ilRepo){
		super(Igrac.class);
		this.iJPAr=ilRepo;
	}
	
	@Override
	public JRDataSource create(JasperReport jrReport){
		igraci=iJPAr.vratiAktivneIgrace();
		
		return new JRBeanCollectionDataSource(igraci);
	}
	
	@Override
	public void dispose(JRDataSource dataSource){
		igraci=null;
	}
	
}