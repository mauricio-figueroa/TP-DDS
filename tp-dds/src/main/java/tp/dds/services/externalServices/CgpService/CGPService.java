package tp.dds.services.externalServices.CgpService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp.dds.api.cgpDto.CgpDto;
import tp.dds.domain.poi.Bank;
import tp.dds.utils.json.JsonFactory;
import tp.dds.utils.reader.URLReader;

import com.fasterxml.jackson.core.type.TypeReference;

public class CGPService {

	private static CGPService instance = null;
	//private static CGPExternalConvertToJson cgpConverter;

	public static CGPService getInstance() {
		if (instance == null) {
			instance = new CGPService();
//			cgpConverter = new CGPExternalConvertToJson();
			return instance;
		} else {
			return instance;
		}
	}

	public List<Bank> getCGPFromService(String bank, String service) {
		List<CgpDto> cgps = null;
		JsonFactory jsonFactory = new JsonFactory();
		URLReader urlReader = new URLReader();
		String url;
		if (!bank.isEmpty() && !service.isEmpty()) {
			url = "http://private-96b476-ddsutn.apiary-mock.com/banks?banco="
					+ bank + "&servicio=" + service;
		} else {
			url = "http://private-96b476-ddsutn.apiary-mock.com/banks?";
		}

		try {
			cgps = jsonFactory.fromJson(urlReader.getStringFromURL(url),
					new TypeReference<ArrayList<CgpDto>>() {
					});

		} catch (IOException e) {
			e.printStackTrace();
		}

	//	return cgpConverter.convertToDomain(cgps);
		return null;
	}
}
