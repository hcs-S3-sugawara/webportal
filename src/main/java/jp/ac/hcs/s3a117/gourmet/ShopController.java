package jp.ac.hcs.s3a117.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {
	@Autowired
	private GourmetService GourmetService;
	
	/**
	 * その地域の飲食店を料理名で検索する
	 * @param shopname　飲食店の情報
	 * @param large_service_area　地域（札幌）
	 * @param model
	 * @return　結果画面　―　飲食店一覧
	 */
	@PostMapping("/gourmet")
	public String getCityCode(@RequestParam("shopname") String shopname,Principal principal, Model model) {
		
		 String large_service_area = "SS40";
		 
		 ShopEntity shopEntity = GourmetService.getShop(shopname,large_service_area);
		 model.addAttribute("shopEntity",shopEntity);
		 
		 return"gourmet/gourmet";
		 
	 }

}
