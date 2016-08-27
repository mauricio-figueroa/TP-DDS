package domain;

import internalService.PoiService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import users.Terminal;
@Component
public class ProcessSearchTerminal implements ProcessSearchInterfaz{

	@Autowired
	private PoiService poiService;


	public List<?> search(String nombre){
		return this.poiService.getTerminales().stream().filter(terminal -> terminal.getNombre()== nombre).map(terminal -> (Terminal) terminal).collect(Collectors.toList());
		
	}

}
