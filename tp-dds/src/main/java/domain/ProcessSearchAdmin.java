package domain;

import internalService.PoiService;

import java.util.List;
import java.util.stream.Collectors;

import users.Terminal;

public class ProcessSearchAdmin implements ProcessSearchInterfaz{
	
	public List<?> search(String nombre){
		return PoiService.getInstance().getTerminales().stream().filter(terminal -> terminal.getNombre()== nombre).map(terminal -> (Terminal) terminal).collect(Collectors.toList());
	}

}
