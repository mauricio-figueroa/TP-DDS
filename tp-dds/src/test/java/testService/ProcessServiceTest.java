package testService;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import internalService.PoiService;
import internalService.ProcessService;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import domain.Address;
import domain.Coordinate;
import poi.Bank;
import poi.BusStation;
import poi.Poi;
import users.Admin;

public class ProcessServiceTest {
	
	private PoiService poiService;
	private ProcessService processService;
	private List<Poi> pois;
	private Bank santander;
	private Bank icbc;
	private BusStation stop114;

	@Before
	public void setUp() {
		this.poiService = PoiService.getInstance();
		processService = new ProcessService();
		santander= new Bank("Santander",new Address("Cordoba 1"), new Coordinate(54,32));
		icbc= new Bank("ICBC",new Address("Corrientes 1"), new Coordinate(52,42));
		stop114= new BusStation("Parada 114", new Address("Mozart 2300"),  new Coordinate(2,4),"114");
		icbc.setId(122);
		santander.setId(123);
		
		
	}
	
	@Test
	public void updateTest(){
		processService.updateComercialShops("/home/gabrieldyck/diseñoDeSistemas/TPIntegrador/TP-DDS/tp-dds/src/main/resources/ComercialsShopsEnviados.txt");
		assertFalse(poiService.getAllPois().isEmpty());
	}
	
	@Test
	public void turnOffPoisTest() throws ClientProtocolException, IOException{
		poiService.removeAllPois();
		List<Poi> poisTest = new ArrayList<Poi>();
		poisTest.add(icbc);
		poisTest.add(santander);
		poisTest.add(stop114);
		poiService.getAllPois().addAll(poisTest);
		processService.turnOffAPoi();
		
		List<Poi> toCheck= poiService.getAllPois().stream().filter(poi -> poi.isActived()).collect(Collectors.toList());
		System.out.println(toCheck.size());
		assertTrue(toCheck.size()==1);
	}

}
