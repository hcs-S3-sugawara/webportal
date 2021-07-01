package jp.ac.hcs.s3a117.task;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TaskEntity {
	
	private List<TaskData> tasklist = new ArrayList<TaskData>();
	

}
