package jp.ac.hcs.s3a117.task;

import java.util.Date;

import lombok.Data;

@Data
public class TaskData {
	/**id*/
	private int id;
	
	/**ユーザid*/
	private String user_id;
	
	/**タスク内容*/
	private String comment;
	
	/**日付*/
	private Date limitday;

}
