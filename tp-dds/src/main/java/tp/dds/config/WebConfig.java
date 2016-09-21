package tp.dds.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tp.dds.utils.json.JsonFactory;


@Component
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private JsonFactory jsonFactory;

	public WebConfig(){
		
	}

	public WebConfig(JsonFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(this.jsonFactory.getObjectMapper());
		converters.add(converter);
		converters.add(new HttpMessageConverter() {

			@Override
			public boolean canRead(Class clazz, MediaType mediaType) {
				return false;
			}

			@Override
			public boolean canWrite(Class clazz, MediaType mediaType) {
				return true;
			}

			@Override
			public List<MediaType> getSupportedMediaTypes() {
				List<MediaType> list = new ArrayList<>();
				list.add(MediaType.TEXT_PLAIN);
				return list;
			}

			@Override
			public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException,
					HttpMessageNotReadableException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException,
					HttpMessageNotWritableException {
				outputMessage.getBody().write(t.toString().getBytes());
			}
		});
	}
}
