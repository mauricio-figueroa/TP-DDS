package Dto.cgpLimitPointsDto;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class parseCiudadDtoJson {
	
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	
	public CiudadesDto  parseJson() throws JsonParseException, JsonMappingException, IOException{
		
		
		
		CiudadesDto ciudadDto;
		ciudadDto=JSON_MAPPER.readValue(new File("src/main/resources/ciudadesJson.json"), CiudadesDto.class);
		
		return ciudadDto;
		
		
	}
	public limitPoints parseLimit() throws JsonParseException, JsonMappingException, IOException{
		
	
		
		 limitPoints lpoint=JSON_MAPPER.readValue(new File("/home/mauriciofigueroa/Escritorio/tpDds/TP-DDS/tp-dds/src/main/resources/ciudades2.json"), limitPoints.class);
			
		 
		 return lpoint;
	
		
	}
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		CiudadesDto ciudad=new CiudadesDto();
		
		parseCiudadDtoJson parse =new parseCiudadDtoJson();
		
		ciudad=parse.parseJson();
		for (Ciudade ciudade : ciudad.getCiudades()) {
			
			System.out.println(ciudade.getCity());
			
		}
		
		
		
		
	}
	
	
}
