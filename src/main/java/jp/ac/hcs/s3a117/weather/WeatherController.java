package jp.ac.hcs.s3a117.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	/**
	 * 地域コードからその地域の天気の詳細を検索し、結果画面を表示する
	 * @param citycode　検索する地域コード（ハイフン無し）
	 * @param principal　ログイン情報
	 * @param model
	 * @return　結果画面　―　天気
	 */
	
	@PostMapping("/weather")
	public String getCityCode(@RequestParam("citycode") String citycode,Principal principal, Model model) {
		 
		 WeatherEntity weatherEntity = weatherService.getWeather(citycode);
		 model.addAttribute("weatherEntity",weatherEntity);
		 
		 return"weather/weather";
		 
	 }

}
