package externalServices.CgpService;

import Dto.cgpDto.CgpDto;
import com.fasterxml.jackson.core.type.TypeReference;
import json.JsonFactory;
import org.springframework.stereotype.Service;
import poi.Bank;
import reader.URLReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CGPService {

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
