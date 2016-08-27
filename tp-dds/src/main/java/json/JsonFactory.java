package json;

import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JsonFactory {

	private ObjectMapper objectMapper;
	
	private static JsonFactory instance;
	
	public static JsonFactory getInstance(){
		if (instance==null){
			instance=new JsonFactory();
		}
		return instance;
	}

	@SuppressWarnings("deprecation")
	public JsonFactory() {
		this.objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("DefaultModule", new Version(0, 0, 1, null, null, null));
		this.objectMapper.registerModule(module);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public String toJson(Object object) {
		try {
			String jsonString = this.objectMapper.writeValueAsString(object);
			return jsonString;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error creating a json", e);
		}
	}

	public <T> T fromJson(Reader jsonReader, TypeReference<T> typeReference) {
		try {
			return this.objectMapper.readValue(jsonReader, typeReference);
		} catch (IOException e) {
			throw new RuntimeException("Error reading a json", e);
		}
	}
	
	public <T> T fromJson(String string, TypeReference<T> typeReference) {
		try {
			return this.objectMapper.readValue(string, typeReference);
		} catch (IOException e) {
			throw new RuntimeException("Error reading a json", e);
		}
	}

}
