package testService;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import junit.framework.Assert;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poi.Bank;
import poi.BusStation;
import poi.CGP;
import poi.CGPService;
import poi.ComercialShop;
import poi.Library;
import poi.Newspaper;
import poi.Poi;
import users.Terminal;
import domain.Address;
import domain.Coordinate;
import domain.RangeOfAtention;
import domain.Schedule;
import internalService.AvailabilityService;
import internalService.PoiService;

@SuppressWarnings("deprecation")
public class AvailableServiceTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(AvailableServiceTest.class);
	private PoiService poiService;
	private Bank bank;
	private Coordinate cordinate1;
	private Coordinate cordinate2;
	private CGPService rentas;
	private AvailabilityService availabilityService;
	private ComercialShop newsPapersShop;
	private ComercialShop schoolLibraryShop;
	private Newspaper newspapers;
	private Library schoolLibrary;
	private CGP cgp2;
	private CGPService cgpService;
	private RangeOfAtention range1;
	private ArrayList<CGPService> cgpServices;
	private List<Integer> days1;
	private List<Integer> rentasDaysAttention;
	private List<Schedule> schedules1;
	private List<Schedule> schedulesRentas;
	private Date date;
	private BusStation busStation;
	private CGP cgp;
	private Terminal poiMachine;

	@Before
	public void initialize() {
		date = new Date();
		poiMachine = new Terminal(cordinate1);

		double lat1 = -34.8128118;
		double lon1 = -58.4516456;
		double lat2 = -34.81725;
		double lon2 = -58.4476116;

		cordinate1 = new Coordinate(lat1, lon1);
		cordinate2 = new Coordinate(lat2, lon2);
		poiService = PoiService.getInstance();

		bank = new Bank("Bank", new Address(""), cordinate2);
		busStation = new BusStation("Parada de Bus", new Address(""), cordinate2, "114");
		cgp = new CGP("CGP", new Address(""), cordinate2, 700.0, new ArrayList<CGPService>());
		schedules1 = new ArrayList<Schedule>();
		schedulesRentas = new ArrayList<Schedule>();
		rentasDaysAttention = new ArrayList<Integer>();
		days1 = new ArrayList<Integer>();

		// schedules1.add(new Schedule("05:23", "06:37"));
		schedules1.add(new Schedule("03:00", "19:00"));
		availabilityService = AvailabilityService.getInstance();
		schoolLibrary = Library.getInstance(500);

		newsPapersShop = new ComercialShop("Diarios Sistemas", new Address(""), cordinate1, newspapers);
		schoolLibraryShop = new ComercialShop("Carrousel", new Address(""), cordinate2, schoolLibrary);
		range1 = new RangeOfAtention(schedules1, days1);

		schedulesRentas.add(new Schedule("00:01", "23:59"));

		rentasDaysAttention.add(0);
		rentasDaysAttention.add(1);
		rentasDaysAttention.add(2);
		rentasDaysAttention.add(3);
		rentasDaysAttention.add(4);
		rentasDaysAttention.add(5);
		rentasDaysAttention.add(6);

		rentas = new CGPService("rentas", new RangeOfAtention(schedulesRentas, rentasDaysAttention));

		cgpService = new CGPService("cualquiera", range1);

		availabilityService = AvailabilityService.getInstance();
		cgpServices = new ArrayList<CGPService>();
		cgpServices.add(rentas);
		cgpServices.add(cgpService);

		cgp2 = new CGP("mauri", new Address(""), cordinate1, 300.0, cgpServices);
	}

	@Test
	public void testBankIsAvaiableFalse() throws ClientProtocolException, IOException {
		Date date = new Date();
		LOGGER.info(date.toString());
		Assert.assertTrue(poiService.isAvailable(bank));
	}

	@Test
	public void testRentasIsAvaiableTrue() {
		LOGGER.info(date.toString());
		Assert.assertTrue(cgp2.isAvailable("rentas"));

	}

	@Test
	public void testAnyIsAvaiable() {
		LOGGER.info(date.toString());
		Assert.assertTrue(cgp2.isAvailable());
	}

	@Test
	public void testSchoolLibrary() {
		Assert.assertFalse(schoolLibrary.isAvailable(availabilityService));
	}

	@Test
	public void testSearch() throws AddressException, MessagingException, InterruptedException {
		poiService.getAllPois().add(busStation);
		poiService.getAllPois().add(bank);
		poiService.getAllPois().add(cgp);
		for (Poi poi : poiService.searchPois("", "terminalAbasto")) {
			LOGGER.info("POI---> " + poi.toString());
		}
		Assert.assertFalse(poiService.searchPois("", "terminalAbasto").isEmpty());
	}

	@Test
	public void testSearchString2() throws AddressException, MessagingException, InterruptedException {
		poiService.getAllPois().add(busStation);
		for (Poi poi : poiService.searchPois("", "terminalPalermo")) {
		}
		System.out.println((poiService.searchPois("114", "terminalPalermo").size()));
		Assert.assertTrue(poiService.searchPois("114", "terminalPalermo").size() == 1);
	}

	@Test
	public void rentasHolidaysTest() {
		availabilityService.getHolidays().getHolidays().add("15/5");
		LOGGER.info(date.toString());
		Assert.assertTrue(cgp2.isAvailable("rentas"));
		availabilityService.getHolidays().getHolidays().remove("15/5");
	}

}
