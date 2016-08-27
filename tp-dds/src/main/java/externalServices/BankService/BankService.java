package externalServices.BankService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poi.Bank;
import com.fasterxml.jackson.core.type.TypeReference;
import Dto.bankDto.BankDTO;
import reader.URLReader;
import json.JsonFactory;

@Service
public class BankService {

	@Autowired
	private BankExternalConvertToJson bankConverter;

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
