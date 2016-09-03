package tp.dds.services.externalServices.BankService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import tp.dds.api.bankDto.BankDTO;
import tp.dds.domain.poi.Bank;
import tp.dds.utils.json.JsonFactory;
import tp.dds.utils.reader.URLReader;

public class BankService {

	private static BankService instance = null;
	private static BankExternalConvertToJson bankConverter;

	public static BankService getInstance() {
		if (instance == null) {
			instance = new BankService();
			bankConverter = new BankExternalConvertToJson();
			return instance;
		} else {
			return instance;
		}
	}

	public List<Bank> getBanksFromService(String bank, String service) {
		List<BankDTO> banks = null;
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
			banks = jsonFactory.fromJson(urlReader.getStringFromURL(url),
					new TypeReference<ArrayList<BankDTO>>() {
					});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bankConverter.convertToDomain(banks);
	}

}
