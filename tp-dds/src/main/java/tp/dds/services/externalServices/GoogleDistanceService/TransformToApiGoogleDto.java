package tp.dds.services.externalServices.GoogleDistanceService;

import java.io.InputStreamReader;

import tp.dds.api.appiGoogleDTO.ApiDirectionsGoogleDTO;
import tp.dds.utils.json.JsonFactory;

import com.fasterxml.jackson.core.type.TypeReference;


public class TransformToApiGoogleDto {
	
	
	public ApiDirectionsGoogleDTO transform(InputStreamReader input){
		JsonFactory jsonFactory=new JsonFactory();
		
		return jsonFactory.fromJson(input, new TypeReference<ApiDirectionsGoogleDTO>() {
		});		
		
	}
}
