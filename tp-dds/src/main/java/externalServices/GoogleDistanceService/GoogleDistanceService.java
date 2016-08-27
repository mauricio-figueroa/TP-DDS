package externalServices.GoogleDistanceService;

import Dto.appiGoogleDTO.ApiDirectionsGoogleDTO;
import Dto.appiGoogleDTO.DistanceDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import domain.Coordinate;
import http.HttpRequest;
import json.JsonFactory;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GoogleDistanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDistanceService.class);

    @Autowired
    private HttpRequest httpRequest;


    public double getDistance(Coordinate cordinate1, Coordinate cordinate2)
            throws ClientProtocolException, IOException {
        String url = getUrl(cordinate1, cordinate2);
        ApiDirectionsGoogleDTO dto;
        JsonFactory jsonFactory = new JsonFactory();
        InputStreamReader input = this.httpRequest.request(url);
        dto = jsonFactory.fromJson(input, new TypeReference<ApiDirectionsGoogleDTO>() {
        });


        DistanceDTO distance = dto.getRows().get(0).getElements().get(0).getDistance();
        System.out.println(distance.toString());
        return distance.getValue();

    }

    public String getUrl(Coordinate c1, Coordinate c2) {
        // String
        // url2="https://maps.googleapis.com/maps/api/distancematrix/json?origins=-34.8116466,-58.4514427&destinations=-34.8148093,-58.4531344&key=AIzaSyADv7wpbNqFOLDQjNXGXEcM1oAhCDEJHzw";
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + c1.getLatitude() + ","
                + c1.getLongitude() + "&destinations=" + c2.getLatitude() + "," + c2.getLongitude()
                + "&key=AIzaSyADv7wpbNqFOLDQjNXGXEcM1oAhCDEJHzw";
        LOGGER.info(url);
        return url;
    }
}

