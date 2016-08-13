package testService;

import static org.junit.Assert.*;
import internalService.PoiService;
import internalService.ProcessService;

import org.junit.Before;
import org.junit.Test;

import users.Admin;

public class ProcessServiceTest {
	
	private PoiService poiService;
	private ProcessService processService;

	@Before
	public void setUp() {
	
		this.poiService = PoiService.getInstance();
		processService = new ProcessService();
		
	}
	
	@Test
	public void updateTest(){
		processService.updateComercialShops("/home/gabrieldyck/diseñoDeSistemas/TPIntegrador/TP-DDS/tp-dds/src/main/resources/ComercialsShopsEnviados.txt");
		assertFalse(poiService.getAllPois().isEmpty());
	}

}
