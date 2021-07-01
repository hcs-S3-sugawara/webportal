package jp.ac.hcs.s3a117.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Transactional
@Service
public class WeatherService {
	@Autowired
	RestTemplate restTemplate;
	
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	
	public WeatherEntity getWeather(String citycode) {
		String json = restTemplate.getForObject(URL,String.class, citycode);
		
		WeatherEntity weatherEntity = new WeatherEntity();
		
		try { 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			

			
			
			 for(JsonNode forecast : node.get("forecasts")) {
				 
	                WeatherData weatherData = new WeatherData();
	    
	                
	                weatherData.setDateLabel(forecast.get("dateLabel").asText());
	                weatherData.setTelop(forecast.get("telop").asText());
	  
	                
	                weatherEntity.getForecasts().add(weatherData);

	            }
			 
			 
			 JsonNode description = node.get("description");
	         weatherEntity.setDescription(description.get("headlineText").asText() + description.get("bodyText").asText());

	         
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return weatherEntity;
	}
}
