package tp.dds.domain.observers.busqueda;

import tp.dds.services.internalService.ReportService;

public class AlmacenarResultado implements ObserverBusqueda {
	
	private ReportService reportService=ReportService.getInstance();

	@Override
	public void update(String palabraBuscada, String nombreTerminal, int cantPoisBusqueda,int segundosQueTardo) {
		reportService.addReporte(nombreTerminal, palabraBuscada, cantPoisBusqueda);		
	}
}
