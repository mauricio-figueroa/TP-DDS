import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Dto.cgpLimitPointsDto.CiudadesDto;
import Dto.cgpLimitPointsDto.parseCiudadDtoJson;

public class Prueba2 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// parseCiudadDtoJson parse=new parseCiudadDtoJson();
		// CiudadesDto c=parse.parseJson();

		// System.out.println(c.getCiudades().get(0).getLimitPoints().get(1));

		Date fecha = new Date();
		
		System.out.println(fecha);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(fecha); 
		System.out.println(date); //15/10/2013
		
		
	}

}
