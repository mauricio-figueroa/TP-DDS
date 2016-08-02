package reader;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLReader {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(URLReader.class);

	public String getStringFromURL(String url) throws ClientProtocolException,
			IOException {
		LOGGER.info("Creating HTTPCLient");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);

			LOGGER.debug("Executing request " + httpget.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						LOGGER.info("json created successfully");
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						LOGGER.error("Json wrond");
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			LOGGER.info("----------------------------------------");
			LOGGER.info(responseBody);
			return responseBody;
		} finally {
			httpclient.close();

		}
	}
}
