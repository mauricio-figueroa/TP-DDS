package testService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;

import domain.Address;
import domain.Coordinate;
import internalService.PoiService;
import internalService.ReportService;
import junit.framework.Assert;
import poi.Bank;
import poi.ComercialShop;
import poi.Newspaper;
import users.Admin;
import users.Terminal;


@SuppressWarnings("deprecation")
public class ReportTest {
	private PoiService poiService;
	private Admin admin;
	private String date;
	private Terminal terminal;
	private Terminal terminal2;
	

	@Before
	public void setup() {
		this.poiService = PoiService.getInstance();
		this.admin = new Admin();
		this.poiService.resetReports();
		this.poiService.resetAllPois();
		this.poiService.removeAllPois();

		this.admin.addPoi(new Bank("BancoNAcion", new Address("Paraguay 2815"), new Coordinate(1.2, 21.3),"pago,retiro"));
		this.admin.addPoi(new ComercialShop("libreria de libros ajajaj", new Address("al lado de la utn"),
				new Coordinate(1.2, 21.3), Newspaper.getInstance(32)));

		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		this.date = sdf.format(fecha);

		terminal  = new Terminal("terminalAbasto","asd", new Coordinate(53.54,12.54),null);
		terminal2 =new Terminal("terminalPalermo","asd", new Coordinate(54.14,45.54),null);

	}

	@Test
	public void testReportesTotales() throws AddressException, MessagingException, InterruptedException {
		this.poiService.searchPois("BancoNAcion", "terminalAbasto");
		this.poiService.searchPois("al lado de la utn", "terminalPalermo");
		terminal.searchPoi("BancoNAcion");
		
		ReportService report= poiService.getReportService();
		
		

		Map<String, Integer> resultadosTotales =report.getReportesTotalesPorFecha();
System.out.println(resultadosTotales.get(date));
System.out.println(resultadosTotales);
		Assert.assertEquals( (Integer)3, resultadosTotales.get(date));
		Assert.assertNotNull(resultadosTotales);
	}

	@Test
	public void testReportesParcialesPorTerminal() throws AddressException, MessagingException, InterruptedException {

		this.admin.addPoi(new Bank("acaNomas", new Address("cngvjhgj"), new Coordinate(1.2, 21.3),"pago,retiro"));
		this.admin.addPoi(new ComercialShop("hgkhjk", new Address("acaNomas"), new Coordinate(1.2, 21.3),
				Newspaper.getInstance(32)));

		this.terminal.searchPoi("acaNomas");
		
				
		Assert.assertEquals( (Integer)2, (this.poiService.getParcialesPorTerminal("terminalAbasto").get(date)));
	}
	
	@Test
	public void testReportesTotalesTodasLasTerminales() throws AddressException, MessagingException, InterruptedException{

			this.terminal.searchPoi("BancoNAcion");
			this.terminal.searchPoi("al lado de la utn");	
			
			this.terminal2.searchPoi("BancoNAcion");
			this.terminal2.searchPoi("al lado de la utn");	
		System.out.println(poiService.getReportesTodasLasTerminales());
		Assert.assertEquals((Integer) 2, this.poiService.getReportesTodasLasTerminales().get("terminalAbasto"));
		Assert.assertEquals((Integer) 2, this.poiService.getReportesTodasLasTerminales().get("terminalPalermo"));
	}	
	
	
	
}
