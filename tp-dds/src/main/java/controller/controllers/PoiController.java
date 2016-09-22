package controller.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.response.PoiDTO;
import domain.Address;
import domain.RangeOfAtention;
import internalService.PoiService;
import poi.Bank;
import poi.BusStation;
import poi.Poi;

@Controller
public class PoiController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PoiController.class);

	private PoiService poiService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = ("/poi-show"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PoiDTO>> showPois() {
		poiService = PoiService.getInstance();
		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("REQUEST");
		LOGGER.info("--------------------------------------------------------");
		List<PoiDTO> poisDTO = new ArrayList<PoiDTO>();
		List<Poi> pois = poiService.getAllPois();
		
		
		/*
		 * REFACTOR DE CODIGO SEGUN TIPO, HACER UN POIDTO Y EXTENER LAS DIFERENTES CLASES, LO MISMO CON ADDRESS Y EL RESTO DE MIERDAS
		 */

		//TODO
		for (Poi currentPoi : pois) {

			switch (currentPoi.getType()) {
				case "Bank":
					Bank bank = (Bank) currentPoi;

					poisDTO.add(new PoiDTO ( 0,  bank.getAddress().getMainStreet(),  bank.getAddress().getMainStreet(),  null,  bank.getServices(), null,  null));
					break;

				case "busStation":
					BusStation busStation=(BusStation) currentPoi;
					poisDTO.add(new PoiDTO());
					break;
			}

		}
		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("RESPONSE");
		LOGGER.info("--------------------------------------------------------");
		return new ResponseEntity<List<PoiDTO>>(poisDTO, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = ("/poi-size"), method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> poiSize() {
		poiService = PoiService.getInstance();
		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("REQUEST");
		LOGGER.info("--------------------------------------------------------");

		int size = poiService.getAllPois().size();

		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("RESPONSE");
		LOGGER.info("--------------------------------------------------------");
		return new ResponseEntity<Integer>(size, HttpStatus.OK);

	}

}
