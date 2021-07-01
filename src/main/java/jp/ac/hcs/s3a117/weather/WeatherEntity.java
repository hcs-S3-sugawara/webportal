package jp.ac.hcs.s3a117.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class WeatherEntity {
	
	private String title;
	
	private String description;
	
	private List<WeatherData> forecasts = new ArrayList<WeatherData>();

}
