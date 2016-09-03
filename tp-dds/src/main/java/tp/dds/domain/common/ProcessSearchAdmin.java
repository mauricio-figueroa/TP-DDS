package tp.dds.domain.common;

import java.util.List;
import java.util.stream.Collectors;

import tp.dds.domain.users.Terminal;
import tp.dds.services.internalService.PoiService;

public class ProcessSearchAdmin implements ProcessSearchInterfaz{
	
	public List<?> search(String nombre){
		return PoiService.getInstance().getTerminales().stream().filter(terminal -> terminal.getNombre()== nombre).map(terminal -> (Terminal) terminal).collect(Collectors.toList());
	}

}
