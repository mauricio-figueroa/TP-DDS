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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import domain.Address;
import domain.Coordinate;
import domain.EnumActions;
import org.springframework.beans.factory.annotation.Autowired;
import poi.Bank;
import poi.BusStation;
import poi.Poi;
import users.Admin;
import users.Terminal;

public class ProcessServiceTest {

	@Autowired
	private PoiService poiService;
	@Autowired
	private ProcessService processService;
	private List<Poi> pois;
	private Bank santander;
	private Bank icbc;
	private BusStation stop114;
	private Admin admin;
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		santander= new Bank("Santander",new Address("Cordoba 1"), new Coordinate(54,32));
		icbc= new Bank("ICBC",new Address("Corrientes 1"), new Coordinate(52,42));
		stop114= new BusStation("Parada 114", new Address("Mozart 2300"),  new Coordinate(2,4),"114");
		icbc.setId(122);
		santander.setId(123);
		admin= new Admin(null,"Gabo","gabriel.dyck@despegar.com","Email");
		
		
	}
	
	@Test
	public void updateTest(){
		processService.updateComercialShops("/home/gabrieldyck/diseñoDeSistemas/TPIntegrador/TP-DDS/tp-dds/src/main/resources/ComercialsShopsEnviados.txt",admin);
		assertFalse(poiService.getAllPois().isEmpty());
		poiService.removeAllPois();
	}
	
	@Test
	public void turnOffPoisTest() {
		poiService.removeAllPois();
		List<Poi> poisTest = new ArrayList<Poi>();
		poisTest.add(icbc);
		poisTest.add(santander);
		poiService.getAllPois().addAll(poisTest);
		List<Poi> toCheck=null;
		admin.turnOffAPoi();
		toCheck= poiService.getAllPois().stream().filter(poi -> poi.isActived()).collect(Collectors.toList());
		assertTrue(toCheck.isEmpty() || !processService.getProcessStories().stream().filter(story -> story.getResult()=="Error").collect(Collectors.toList()).isEmpty());
		admin= new Admin(null,"Gabo","gabriel.dyck@despegar.com","Repeat");
		admin.turnOffAPoi();
		
	}
	
	
	@Test
	public void addActionToUser(){
	List<String> actionInitialize= new ArrayList<String>();
	actionInitialize.add(EnumActions.ADDPOI.toString());
	List<List<String>> actions= new ArrayList<List<String>>();
	actions.add(actionInitialize);
	poiService.getTerminales().add(new Terminal("Terminal Gabo", new Coordinate(43.23,54.23),actions));
	List<String> actionValidate= new ArrayList<String>();
	actionValidate.add(EnumActions.ADDTERMINAL.toString());
	processService.addActionsToUser("Terminal Gabo", "Terminal",actionValidate,admin);
	assertTrue(poiService.getTerminales().get(0).getActions().size()==2);

	}
	
	@Test
	public void undoAddedAction(){
		poiService.resetAllTerminals();
		addActionToUser();
		processService.undoAddActionToUser("Terminal Gabo", "Terminal",admin);
		assertTrue(poiService.getTerminales().get(0).getActions().size()==1);
	}
	
	@Test
	public void multiplyProcess(){
		List<String> actionInitialize= new ArrayList<String>();
		actionInitialize.add(EnumActions.ADDPOI.toString());
		List<List<String>> actions= new ArrayList<List<String>>();
		actions.add(actionInitialize);
		poiService.getTerminales().add(new Terminal("Terminal Gabo", new Coordinate(43.23,54.23),actions));

		List<String> actionValidate= new ArrayList<String>();
		actionValidate.add(EnumActions.ADDTERMINAL.toString());
		
		Runnable run1= () -> { processService.addActionsToUser("Terminal Gabo", "Terminal", actionValidate,admin);
	};
		Runnable run2= () -> { processService.undoAddActionToUser("Terminal Gabo", "Terminal",admin);
	};
	
	List<Runnable> runnables= new ArrayList<Runnable>();
	runnables.add(run1);
	runnables.add(run2);
	admin.multiplyProcess(runnables);
	processService.getProcessStories().forEach(story -> System.out.println(story.toString()));
	assertTrue(processService.getProcessStories().size()==2);
	
	
	
	}
	
	
}
