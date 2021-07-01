package jp.ac.hcs.s3a117.task;

import java.util.Date;

import lombok.Data;

@Data
public class TaskData {
	private int id;
	
	private String user_id;
	
	private String comment;
	
	private Date limitday;

}
