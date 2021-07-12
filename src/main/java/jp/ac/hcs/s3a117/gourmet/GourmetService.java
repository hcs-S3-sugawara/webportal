package jp.ac.hcs.s3a117.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Service
public class GourmetService {
	@Autowired
	
	RestTemplate restTemplate;
	
		private static final String URL = 
				"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
		private static final String API_KEY = "7eb73fc179e5efe1";
	
		
		public ShopEntity getShop(String shopname, String large_service_area) {
			String json = restTemplate.getForObject(URL,String.class, API_KEY, shopname, large_service_area);
			
			ShopEntity shopEntity = new ShopEntity();
			shopEntity.setSearchWord(shopname);
			
			try { 
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(json);
			
			 for(JsonNode shoplist : node.get("results").get("shop")) {
				 
	                ShopData shopData = new ShopData();
	                
	                shopData.setId(shoplist.get("id").asText());
	                shopData.setName(shoplist.get("name").asText());
	                shopData.setLogo_image(shoplist.get("logo_image").asText());
	                shopData.setName_kana(shoplist.get("name_kana").asText());
	                shopData.setAddress(shoplist.get("address").asText());
	                shopData.setAccess(shoplist.get("access").asText());
	                shopData.setUrl(shoplist.get("urls").get("pc").asText());
	                shopData.setImage(shoplist.get("photo").get("mobile").get("l").asText());
	  
	                
	                shopEntity.getShoplist().add(shopData);

	            }
			 
			 
	         
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return shopEntity;
	}

}
