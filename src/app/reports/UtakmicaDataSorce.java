package app.reports;

import java.util.List;

import app.repository.IgraJpaRepo;
import model.Igra;
import model.Igrac;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtakmicaDataSorce extends JRAbstractBeanDataSourceProvider {

		private List<Igra> igraci;
		int id;
		IgraJpaRepo iJPAr;
		
		public UtakmicaDataSorce(IgraJpaRepo ilRepo,int id){
			super(Igrac.class);
			iJPAr=ilRepo;
			this.id=id;
		}
		
		@Override
		public JRDataSource create(JasperReport jrReport){
			igraci=iJPAr.vratiZaUtakmicu(id);
			
			return new JRBeanCollectionDataSource(igraci);
		}
		
		@Override
		public void dispose(JRDataSource dataSource){
			igraci=null;
		}
		
}

