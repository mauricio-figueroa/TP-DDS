package observers.busqueda;

import internalService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

public class AlmacenarResultado implements ObserverBusqueda {

	@Autowired
	private ReportService reportService;




	@Override
	public void update(String palabraBuscada, String nombreTerminal, int cantPoisBusqueda,int segundosQueTardo) {
		reportService.addReporte(nombreTerminal, palabraBuscada, cantPoisBusqueda);		
	}

}
