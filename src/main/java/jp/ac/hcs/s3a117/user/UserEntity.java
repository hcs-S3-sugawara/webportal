package jp.ac.hcs.s3a117.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserEntity {
	private List<UserData>userlist = new ArrayList<UserData>();

}
