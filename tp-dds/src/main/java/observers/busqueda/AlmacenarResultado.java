package observers.busqueda;

import com.sun.deploy.util.StringUtils;
import internalService.ReportService;
import org.eclipse.jetty.util.StringUtil;

import java.util.Arrays;
import java.util.List;

public class AlmacenarResultado implements ObserverBusqueda {
	
	private ReportService reportService=ReportService.getInstance();




	@Override
	public void update(List<String> palabraBuscada, String nombreTerminal, int cantPoisBusqueda, int segundosQueTardo) {
		reportService.addReporte(nombreTerminal, StringUtils.join(palabraBuscada,","), cantPoisBusqueda);
	}

}
