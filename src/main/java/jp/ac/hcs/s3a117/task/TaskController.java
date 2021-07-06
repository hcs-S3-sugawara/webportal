package jp.ac.hcs.s3a117.task;



import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.hcs.s3a117.WebConfig;

@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;
	/**
	 * @return　結果画面　―　タスク一覧
	 */
	
	@PostMapping("/task")
	public String getTask(Principal principal, Model model) {
		 
		 TaskEntity taskEntity = taskService.selectAll(principal.getName());
		 model.addAttribute("taskEntity",taskEntity);
		 
		 return"task/task";
		 
	 }
	
	@PostMapping("/task/insert")
	public String getComment(@RequestParam("comment") String comment,
								@RequestParam("limitday") String limitday,Principal principal, Model model) {

		taskService.InsertTask(principal.getName(),comment,limitday);
		
		
		 return getTask(principal,model);
		
		
	}
	
	/**
	 * 自分の全てのタスク情報をCSVファイルとしてダウンロードさせる.
	 * @param principal ログイン情報
	 * @param model
	 * @return タスク情報のCSVファイル
	 */
	@PostMapping("/task/csv")
	public ResponseEntity<byte[]> getTaskCsv(Principal principal, Model model) {

		final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV;

//		log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

		// CSVファイルをサーバ上に作成
		taskService.taskListCsvOut(principal.getName());

		// CSVファイルをサーバから読み込み
		byte[] bytes = null;
		try {
			bytes = taskService.getFile(OUTPUT_FULLPATH);
//			log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
		} catch (IOException e) {
//			log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
			e.printStackTrace();
		}

		// CSVファイルのダウンロード用ヘッダー情報設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", WebConfig.FILENAME_TASK_CSV);

		// CSVファイルを端末へ送信
		return new ResponseEntity<byte[]>(bytes,header,HttpStatus.OK);
	}
	
	
	@GetMapping("/task/delete/{id}")
	public String deleteTask(@PathVariable("id")  int id,Principal principal, Model model) {
		 System.out.println(id);
		boolean isSuccess = taskService.delete(id);
		if (isSuccess) {
			model.addAttribute("message","正常に削除されました");
		} else {
			model.addAttribute("errorMessage","削除されませんでした。再度操作をやり直してください");
		}
		
		 
		 return getTask(principal,model);
		 
	 }
}


