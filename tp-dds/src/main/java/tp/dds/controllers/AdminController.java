package tp.dds.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tp.dds.api.ResponseOK;
import tp.dds.domain.common.Address;
import tp.dds.domain.common.ClosedSchedule;
import tp.dds.domain.common.Coordinate;
import tp.dds.domain.common.EnumActions;
import tp.dds.domain.common.RangeOfAtention;
import tp.dds.domain.common.Schedule;
import tp.dds.domain.poi.Bank;
import tp.dds.domain.poi.BusStation;
import tp.dds.domain.poi.CGP;
import tp.dds.domain.poi.CGPService;
import tp.dds.domain.poi.Poi;
import tp.dds.domain.users.Admin;
import tp.dds.domain.users.Terminal;
import tp.dds.services.internalService.PoiService;

@Controller
public class AdminController {

	private Admin admin;

	public AdminController() {
		admin = new Admin();
	}

	@RequestMapping(value = ("/terminal-add"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity addTerminal(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon,
			@RequestParam(value = "action", required = false) List<String> actions) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		if (actions.stream().allMatch(action -> EnumUtils.isValidEnum(EnumActions.class, action))) {
			List<List<String>> listOfActions = new ArrayList<List<String>>();
			listOfActions.add(actions);
			boolean state = admin.addTerminal(new Terminal(name, new Coordinate(lat, lon), listOfActions));
			return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);
			//return new ResponseEntity(state, HttpStatus.OK);
		} else {
			String error = "ACTIONS MUST BE " + EnumActions.values();
			//return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);

		}
	}

	@RequestMapping(value = ("/show-terminal"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity showTerminals() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		List<String> terminals = new ArrayList<String>();
		for (Terminal currentTerminal : admin.getPoiService().getTerminales()) {
			terminals.add(currentTerminal.getNombre());
		}
		return new ResponseEntity(terminals,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/terminal-remove"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity removeTerminal(@RequestParam(value = "name", required = true) String name) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		boolean state = admin.removeTerminal(name);
		return new ResponseEntity(state,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/reportByDate"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity reportByDate() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		Map<String, Integer> reportsByDate = PoiService.getReportService().getReportesTotalesPorFecha();
		return new ResponseEntity(reportsByDate,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/reportByTerminal"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity reportByTerminal(@RequestParam(value = "name", required = true) String name) {


		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");


		Map<String, Integer> reportsByDate = PoiService.getReportService().getParcialesPorTerminal(name);
		return new ResponseEntity(reportsByDate,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/all-reports"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity allReports() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		Map<String, Integer> reportsByDate = PoiService.getReportService().getReportesTotalesTodasLasTerminales();
		return new ResponseEntity(reportsByDate,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-remove"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity removePoi(@RequestParam(value = "name", required = true) String name) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		boolean state = admin.removePoi(name);
		return new ResponseEntity(state,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-modify"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity modifyPoi(@RequestParam(value = "name", required = true) String name) {
		Poi poi = null; // TODO Agregar muchos parametros y filtrar tipo por
						// type

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		boolean state = admin.modifyPoi(poi, name);
		return new ResponseEntity(state,responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-addBank"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseOK> addPoiBank(@RequestParam(value = "name", required = true) String name,
												 @RequestParam(value = "type", required = true) String type,
												 @RequestParam(value = "mainStreet", required = true) String mainStreet,
												 @RequestParam(value = "lat", required = true) double lat,
												 @RequestParam(value = "lon", required = true) double lon) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		Poi poi = new Bank(name, new Address(mainStreet), new Coordinate(lat, lon));

		admin.addPoi(poi);
		return new ResponseEntity<ResponseOK>(new ResponseOK("ok"),responseHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-addBusStation"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity addPoiBusStation(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "mainStreet", required = true) String mainStreet,
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon,
			@RequestParam(value = "busNumber", required = true) String busNumber) {
		// TODO Agregar muchos parametros y filtrar tipo por type

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		Poi poi = new BusStation(name, new Address(mainStreet), new Coordinate(lat, lon), busNumber);
		admin.addPoi(poi);
		return new ResponseEntity(responseHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-addCGP"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity addPoiCGP(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "mainStreet", required = true) String mainStreet,
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon,
			@RequestParam(value = "communeRadius", required = true) double communeRadius) {
		// TODO Agregar muchos parametros y filtrar tipo por type
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		Poi poi = new CGP(name, new Address(mainStreet), new Coordinate(lat, lon), communeRadius,
				new ArrayList<CGPService>());
		admin.addPoi(poi);
		return new ResponseEntity(responseHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-addCGPServiceInTo"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity addPoiCGPService(@RequestParam(value = "nameOfCGP", required = true) String nameOfCGP,
			@RequestParam(value = "serviceName", required = true) String serviceName,
			@RequestParam(value = "daysOfAttention", required = true) List<Integer> daysOfAttention) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		for (Integer index : daysOfAttention) {
			System.out.println(index);
		}
		CGPService cgpService = new CGPService(serviceName, new RangeOfAtention(null, daysOfAttention));
		boolean status = admin.addCGPServiceToCGP(nameOfCGP, cgpService);
		return new ResponseEntity(status,responseHeaders, HttpStatus.OK);

	}

	public ResponseEntity addScheduleToCGPService(@RequestParam(value = "nameOfCGP", required = true) String nameOfCGP,
			@RequestParam(value = "serviceName", required = true) String serviceName,
			@RequestParam(value = "hourMax", required = true) String hourMax,
			@RequestParam(value = "hourMin", required = true) String hourMin) {

		boolean status = admin.addScheduleToCGPService(nameOfCGP, serviceName, hourMax, hourMin);
		return new ResponseEntity(status, HttpStatus.OK);

	}

	@RequestMapping(value = ("/poi-addClosedScheduleAllAday"), method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addClosedScheduleAllDay(@RequestParam(value = "name", required = true) String poiName,
			@RequestParam(value = "date", required = true) String date,
			@RequestParam(value = "allDay", required = true) boolean allDay) {
		// TODO Agregar muchos parametros y filtrar tipo por type
		Date dateClosed = new Date(date);

		ClosedSchedule closedSchedule = new ClosedSchedule(dateClosed, allDay);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		admin.addClosedSchedule(poiName, closedSchedule);
		return new ResponseEntity("ok",responseHeaders,HttpStatus.OK);
	}

	@RequestMapping(value = ("/poi-addClosedSchedule"), method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addClosedSchedule(
			@RequestParam(value = "name", required = true) String poiName,
			@RequestParam(value= "date", required=true) String date,
			@RequestParam(value="allDay",required=true) boolean allDay,
			@RequestParam(value="hourMin",required=true) String hourMin,
			@RequestParam(value="hourMax",required=true) String hourMax			
			){	
		// TODO Agregar muchos parametros y filtrar tipo por type
		Date dateClosed=new Date(date);
		Schedule schedule=new Schedule(hourMin, hourMax);
		
		ClosedSchedule closedSchedule=new ClosedSchedule(dateClosed,allDay,schedule);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		admin.addClosedSchedule(poiName,closedSchedule);
		return new ResponseEntity("ok",responseHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value = ("/poi-cgp-addClosedSchedule"), method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addClosedScheduleToCgpService(
			@RequestParam(value = "name", required = true) String poiName,
			@RequestParam(value= "date", required=true) String date,
			@RequestParam(value="allDay",required=true) boolean allDay,
			@RequestParam(value="hourMin",required=true) String hourMin,
			@RequestParam(value="hourMax",required=true) String hourMax,
			@RequestParam(value="serviceName",required=true) String serviceName
			){	
		// TODO Agregar muchos parametros y filtrar tipo por type
		Date dateClosed=new Date(date);
		Schedule schedule=new Schedule(hourMin, hourMax);
		
		ClosedSchedule closedSchedule=new ClosedSchedule(dateClosed,allDay,schedule);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json");

		admin.addClosedScheduletoCgp(poiName,closedSchedule,serviceName);
		return new ResponseEntity("ok",responseHeaders,HttpStatus.OK);
	}
	
	
	
	
	
}

