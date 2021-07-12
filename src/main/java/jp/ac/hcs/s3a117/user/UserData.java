package jp.ac.hcs.s3a117.user;

import lombok.Data;

@Data
public class UserData {
	/**ユーザid*/
	private String user_id;
	
	/**パスワード*/
	private String password;
	
	/**アカウントの有効性*/
	private boolean enabled;
	
	/**ユーザ名*/
	private String user_name;
	
	/**ダークモードの判定*/
	private boolean darkmode;
	
	/**権限*/
	private String role;

}
