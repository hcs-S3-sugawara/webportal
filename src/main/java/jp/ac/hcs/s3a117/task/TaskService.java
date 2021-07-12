package jp.ac.hcs.s3a117.task;


import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	
	/**
	 * サーバーに保存されているファイルを取得して表示する
	 * @param user_id ユーザid
	 * @return タスクエンティティ
	 * @throws IOException ファイル取得エラー
	 */
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

	/**
	 * タスクを追加する
	 * @param user_id ファイル名
	 * @param comment　入力されたタスク内容
	 * @param limitday 日付
	 * @throws IOException ファイル取得エラー
	 */
	public void InsertTask(String user_id, String comment, String limitday) {
		TaskData taskData = new TaskData();
		
		
		SimpleDateFormat simpleData = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = simpleData.parse(limitday);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		taskData.setUser_id(user_id);
		taskData.setComment(comment);
		taskData.setLimitday(date);
		
		int aiu = taskRepository.insertOne(taskData);
	}

	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		
		return bytes;
	}
	
	/**
	 * 保存されているタスクの削除（１件）する
	 * @param id タスク情報
	 * @return タスクのid
	 * @throws IOException ファイル取得エラー
	 */
	public boolean deleteOne(int id) {
		int count;
		try {
			count = taskRepository.deleteOne(id);
		}catch (DataAccessException e) {
			e.printStackTrace();
			count = 0;
		}
		
		return count > 0;
	}
}
