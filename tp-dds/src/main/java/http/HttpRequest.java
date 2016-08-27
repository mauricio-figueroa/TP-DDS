package http;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpRequest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);
	
	
	public InputStreamReader request(String url) throws ClientProtocolException, IOException {
		//String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins=-34.8116466,-58.4514427&destinations=-34.8148093,-58.4531344&key=AIzaSyADv7wpbNqFOLDQjNXGXEcM1oAhCDEJHzw";
		HttpClient clientHttp = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = clientHttp.execute(request);		
		LOGGER.info(response.getStatusLine().toString());
		return new InputStreamReader(response.getEntity().getContent());
	}
}
