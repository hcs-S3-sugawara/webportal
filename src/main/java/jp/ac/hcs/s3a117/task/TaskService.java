package jp.ac.hcs.s3a117.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	public TaskEntity selectAll(String userId) {
		TaskEntity taskEntity;
		try {
			taskEntity = taskRepository.selectAll(userId);
		}catch (DataAccessException e) {
			e.printStackTrace();
			taskEntity = null;
		}
		return taskEntity;
	}

}
