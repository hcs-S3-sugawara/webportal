package jp.ac.hcs.s3a117.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShopEntity {
	
	private String shopname;
	
	private List<ShopData> shoplist = new ArrayList<ShopData>();

	public void setSearchWord(String shopname2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
